package za.co.mabatalale.reports;

import za.co.mabatalale.entities.*;
import za.co.mabatalale.models.ForemanReportDetails;
import za.co.mabatalale.models.ForemanReportFooter;
import za.co.mabatalale.models.ForemanReportHeader;
import za.co.mabatalale.models.ForemanReportSummary;
import za.co.mabatalale.repos.*;
import za.co.mabatalale.services.*;
import za.co.mabatalale.utils.DateUtil;
import za.co.mabatalale.utils.GraphsUtil;
import za.co.mabatalale.utils.MathRounder;


import java.math.BigDecimal;
import java.util.*;

/**
 * Created by robson on 2017/03/27.
 */
public class ForemanReportBuilder {

    private ConfigurationService configurationService;
    private OperationsUsersServices operationsUsersServices;
    private ForemanReportService foremanReportService;
    private StartEndShiftService startEndShiftService;
    private ShiftRepository shiftRepository;
    private LookupService lookupService;
    private ProductionCaptureService productionCaptureService;
    private static final BigDecimal DEFAULT_VALUE = new BigDecimal(0);

    public ForemanReportBuilder(){
        this.configurationService = new ConfigurationService(new SiteConfigurationRepository());
        this.operationsUsersServices = new OperationsUsersServices(new OperationsUserRepository());
        this.foremanReportService = new ForemanReportService(
                                        new ForemanHeaderRepository(),
                                        new ForemanDetailsRepository(),
                                        new ForemanSummaryRepository(),
                                        new ForemanFooterRepository()
                                        );
        this.startEndShiftService = new StartEndShiftService(new EndOfShiftRepository(),new ShiftStartRepository(),new CurrentSessionsRepository());
        this.shiftRepository = new ShiftRepository();
        this.lookupService = new LookupService(new DeviceStatusRepository());
        this.productionCaptureService = new ProductionCaptureService(new ProductionEntryRepository());
    }

    public void runReportBuilder(){
        try {
            int date = DateUtil.getPreviousDay();
            List<ForemanReportHeader> foremanReportHeaderList = getForemanReportHeader(date);
            foremanReportService.saveForemanReportHeader(foremanReportHeaderList);
            System.out.println("Header information saved");
            List<ForemanReportDetails> foremanReportDetailsList = getForemanReportDetails(date);
            foremanReportService.saveForemanReportDetails(foremanReportDetailsList);
            System.out.println("Details information saved");
            List<ForemanReportSummary> foremanReportSummaryList = getForemanReportSummary(date);
            foremanReportService.saveForemanReportSummary(foremanReportSummaryList);
            System.out.println("Summary information saved");
            List<ForemanReportFooter> foremanReportFooterList = getForemanFooter(date);
            foremanReportService.saveForemanReportFooter(foremanReportFooterList);
            System.out.println("Footer information saved");
        }catch (Exception ex){
            System.err.println(ex.getMessage());
        }
    }

    private List<ForemanReportFooter> getForemanFooter(int date) {
        List<ForemanReportFooter> foremanReportFooterList = new ArrayList<>();

        StandingTimeEntryRepository standingTimeEntryRepository = new StandingTimeEntryRepository();
        List<StandingLogs> standingLogs = standingTimeEntryRepository.findByDateInt(date);

        BreakdownEntryRepository breakdownEntryRepository = new BreakdownEntryRepository();
        List<BreakdownLogs> breakdownLogss = breakdownEntryRepository.findByDateInt(date);

        for(BreakdownLogs logs : breakdownLogss){
            ForemanReportFooter foremanReportFooter = new ForemanReportFooter();
            foremanReportFooter.setDateInt(date);
            foremanReportFooter.setPlantNumber(configurationService.getSiteNameFromId(logs.getRigId()));
            foremanReportFooter.setRigId(logs.getRigId());
            int headerId = getHeaderReferenceIdFromOperator(logs.getOperatorSheetId(),date);
            foremanReportFooter.setHeaderIdRef(headerId);
            foremanReportFooter.setDelayType("Breakdown");
            foremanReportFooter.setDelayDescription(getBreakDownDescription(logs.getBreakdownTypeId()));
            long duration = logs.getEndTime().getTime() - logs.getStartTime().getTime();
            foremanReportFooter.setFrom(DateUtil.getTimeFromTimeStamp(logs.getStartTime().getTime()));
            foremanReportFooter.setTo(DateUtil.getTimeFromTimeStamp(logs.getEndTime().getTime()));
            foremanReportFooter.setDurationHours(DateUtil.getFormattedTime(duration));

            foremanReportFooterList.add(foremanReportFooter);
        }

        for(StandingLogs st : standingLogs){
            ForemanReportFooter foremanReportFooter = new ForemanReportFooter();
            foremanReportFooter.setDateInt(date);
            foremanReportFooter.setRigId(st.getRigId());
            foremanReportFooter.setPlantNumber(configurationService.getSiteNameFromId(st.getRigId()));
            int headerId = getHeaderReferenceIdFromOperator(st.getOperatorSheetId(),date);
            foremanReportFooter.setHeaderIdRef(headerId);
            foremanReportFooter.setDelayType("Standing Time");
            foremanReportFooter.setDelayDescription(getStandingTimeDescription(st.getStandingTypeId()));
            long duration = st.getEndTime().getTime() - st.getStartTime().getTime();
            foremanReportFooter.setFrom(DateUtil.getTimeFromTimeStamp(st.getStartTime().getTime()));
            foremanReportFooter.setTo(DateUtil.getTimeFromTimeStamp(st.getEndTime().getTime()));
            foremanReportFooter.setDurationHours(DateUtil.getFormattedTime(duration));

            foremanReportFooterList.add(foremanReportFooter);
        }

        return foremanReportFooterList;
    }


    private List<ForemanReportHeader> getForemanReportHeader(int date){
        List<ForemanReportHeader> foremanReportHeaderList = new ArrayList<>();
        List<OperatorSignIn> startShift = startEndShiftService.getStartOfShiftPerRigPerDate(date);
        for (OperatorSignIn model : startShift
             ) {
            ForemanReportHeader foremanReportHeader = new ForemanReportHeader();
            foremanReportHeader.setDateInt(date);
            foremanReportHeader.setRigId(model.getRigId());
            foremanReportHeader.setCrew(model.getShiftCrew());
            foremanReportHeader.setForeMan(getOperatorNameById(model.getForemanId()));
            foremanReportHeader.setShift(getShiftNameById(model.getShiftId()));
            foremanReportHeaderList.add(foremanReportHeader);
        }
        return foremanReportHeaderList;
    }

    private List<ForemanReportDetails> getForemanReportDetails(int date){

        List<ForemanReportDetails> detailsList = new ArrayList<>();
        List<OperatorSignIn> startShift = startEndShiftService.getStartOfShiftPerRigPerDate(date);
        //List<EndOfShift> endOfShifts = startEndShiftService.getEndOfShiftPerRigPerDate(date);
        List<ProductionRecord> productionRecords = productionCaptureService.findByDateInt(date);

        double machineOpenHours;
        double trammingOpenHours;
        double machineClosing = 0.0;
        double trammingClosing = 0.0;
        String operatorName;
        String shiftName;

        for (OperatorSignIn operator : startShift
                ) {
            EndOfShift endOfShift = getEndOfShiftForOperator(operator.getOperatorId(),operator.getRigId(),date);
            shiftName = getShiftNameById(operator.getShiftId());
            int headerId = getHeaderReferenceId(shiftName,operator.getShiftCrew(),date);
            operatorName = getOperatorNameById(operator.getOperatorId());
            /*Optional<EndOfShift> endOfShiftOptional = endOfShifts.stream()
                    .filter(p-> p.getOperatorId() == operator.getOperatorId())
                    .findFirst();

            if (endOfShiftOptional.isPresent()){
                endOfShift = endOfShiftOptional.get();
            }*/
            ForemanReportDetails foremanReportDetails = new ForemanReportDetails();
            foremanReportDetails.setDateInt(date);
            foremanReportDetails.setOperator(operatorName);
            foremanReportDetails.setRigId(operator.getRigId());
            foremanReportDetails.setAssistant(getOperatorNameById(operator.getAssistantId()));
            foremanReportDetails.setPlantNumber(configurationService.getSiteNameFromId(operator.getRigId()));

            trammingOpenHours = operator.getTrammingOpenHours();
            foremanReportDetails.setTradingOpenHours(MathRounder.roundTo2Decimals(trammingOpenHours));
            if (endOfShift != null) {
                trammingClosing = endOfShift.getTrammingClosing();
                foremanReportDetails.setTradingcClosingHours(MathRounder.roundTo2Decimals(trammingClosing));
            }
            double totalTrammingHours = trammingClosing - trammingOpenHours;
            foremanReportDetails.setTradingTotalHours(MathRounder.roundTo2Decimals(totalTrammingHours));

            machineOpenHours = operator.getMachineOpenHours();
            foremanReportDetails.setMachineOpenHours(MathRounder.roundTo2Decimals(machineOpenHours));
            if (endOfShift != null) {
                machineClosing = endOfShift.getMachineClosing();
                foremanReportDetails.setMachineClosingHours(MathRounder.roundTo2Decimals(machineClosing));
            }
            double totalMachineHours = machineClosing - machineOpenHours;
            foremanReportDetails.setMachineTotalHours(MathRounder.roundTo2Decimals(totalMachineHours));

            String benchNumber = "";
            String benchEndNumber = "";
            int newHoles = 0;
            BigDecimal newMetersDrilled = DEFAULT_VALUE;
            int redrillHoles = 0;
            BigDecimal redrillMeters = DEFAULT_VALUE;

            for (ProductionRecord record : productionRecords
                 ) {
                if (record.getOperatorId() == operator.getOperatorId()){
                    benchNumber = record.getBenchNumber();
                    benchEndNumber = record.getBenchEndNumber();
                    if (record.getDrillType().equalsIgnoreCase("new")){
                        newHoles += record.getHoles();
                        newMetersDrilled = newMetersDrilled.add(record.getMeters());
                    }
                    else {
                        redrillMeters = redrillMeters.add(record.getMeters());
                        redrillHoles += record.getHoles();
                    }
                }
            }
            foremanReportDetails.setNewHoles(String.valueOf(newHoles));
            foremanReportDetails.setNewMetersDrilled(MathRounder.roundTo2Decimals(newMetersDrilled.doubleValue()));
            foremanReportDetails.setRedrillHoles(String.valueOf(redrillHoles));
            foremanReportDetails.setRedrillMetersDrilled(MathRounder.roundTo2Decimals(redrillMeters.doubleValue()));
            foremanReportDetails.setMhr(GraphsUtil.getMetersDrilledPerHour(productionRecords,operator.getOperatorId(),totalMachineHours));
            foremanReportDetails.setBenchStartNumber(benchNumber);
            foremanReportDetails.setBenchEndNumber(benchEndNumber);
            foremanReportDetails.setHeaderIdRef(headerId);

            detailsList.add(foremanReportDetails);
        }
        return detailsList;

    }

   private List<ForemanReportSummary> getForemanReportSummary(int date){
       List<ForemanReportSummary> foremanReportSummaryList = new ArrayList<>();
       List<OperatorSignIn> startShift = startEndShiftService.getStartOfShiftPerRigPerDate(date);

       double totalTrammingHours = 0.0;
       double totalMachineHours = 0.0;
       double totalNewMeters = 0.0;
       double totalRedrillMeters = 0.0;
       double totalProductionMeters = 0.0;
       double totalMetersPerHour = 0.0;
       double revolvingTotal = 0.0;

       String shiftName;
       Map<Integer,ForemanReportSummary> map = new HashMap<>();
       List<ForemanReportDetails> reportDetailsList = foremanReportService.getReportDetailsByDate(date);

       for (OperatorSignIn operator : startShift
               ) {
           ForemanReportSummary foremanReportSummary = new ForemanReportSummary();
           shiftName = getShiftNameById(operator.getShiftId());
           int headerId = getHeaderReferenceId(shiftName,operator.getShiftCrew(),date);

           if (map.get(operator.getRigId()) != null){
               foremanReportSummary = map.get(operator.getRigId());
               for (ForemanReportDetails foreman: reportDetailsList
                       ) {
                   if(foreman.getRigId() == operator.getRigId()) {
                       totalTrammingHours = Double.sum(Double.parseDouble(foremanReportSummary.getTotalTrammingHours()), Double.parseDouble(foreman.getTradingTotalHours()));
                       totalMachineHours = Double.sum(Double.parseDouble(foremanReportSummary.getTotalMachineHours()), Double.parseDouble(foreman.getMachineTotalHours()));
                       totalNewMeters = Double.sum(Double.parseDouble(foremanReportSummary.getTotalNewMeters()),Double.parseDouble(foreman.getNewMetersDrilled()));
                       totalRedrillMeters = Double.sum(Double.parseDouble(foremanReportSummary.getTotatRedrillMeters()), Double.parseDouble(foreman.getRedrillMetersDrilled()));
                       revolvingTotal = Double.sum(totalNewMeters,totalRedrillMeters);
                       totalProductionMeters = Double.sum(Double.parseDouble(foremanReportSummary.getTotalProductionMeters()), revolvingTotal);
                       totalMetersPerHour = Double.sum(Double.parseDouble(foremanReportSummary.getTotalMhr()), Double.parseDouble(foreman.getMhr()));
                   }
               }
               foremanReportSummary.setTotalMachineHours(MathRounder.roundTo2Decimals(totalMachineHours));
               foremanReportSummary.setTotalNewMeters(MathRounder.roundTo2Decimals(totalNewMeters));
               foremanReportSummary.setTotalProductionMeters(MathRounder.roundTo2Decimals(totalProductionMeters));
               foremanReportSummary.setTotalTrammingHours(MathRounder.roundTo2Decimals(totalTrammingHours));
               foremanReportSummary.setTotatRedrillMeters(MathRounder.roundTo2Decimals(totalRedrillMeters));
               foremanReportSummary.setTotalMhr(MathRounder.roundTo2Decimals(totalMetersPerHour));
           }
           else {

               for (ForemanReportDetails foreman: reportDetailsList
                    ) {

                   if(foreman.getRigId() == operator.getRigId()){
                       totalTrammingHours = Double.parseDouble(foreman.getTradingTotalHours());
                       totalMachineHours =  Double.parseDouble(foreman.getMachineTotalHours());
                       totalNewMeters = Double.parseDouble(foreman.getNewMetersDrilled());
                       totalRedrillMeters = Double.parseDouble(foreman.getRedrillMetersDrilled());
                       revolvingTotal = Double.sum(totalNewMeters,totalRedrillMeters);
                       totalProductionMeters = revolvingTotal;
                       totalMetersPerHour = Double.parseDouble(foreman.getMhr());
                   }
               }
               foremanReportSummary.setTotalMachineHours(MathRounder.roundTo2Decimals(totalMachineHours));
               foremanReportSummary.setTotalNewMeters(MathRounder.roundTo2Decimals(totalNewMeters));
               foremanReportSummary.setTotalProductionMeters(MathRounder.roundTo2Decimals(totalProductionMeters));
               foremanReportSummary.setTotalTrammingHours(MathRounder.roundTo2Decimals(totalTrammingHours));
               foremanReportSummary.setTotatRedrillMeters(MathRounder.roundTo2Decimals(totalRedrillMeters));
               foremanReportSummary.setTotalMhr(MathRounder.roundTo2Decimals(totalMetersPerHour));
           }
           foremanReportSummary.setDateInt(date);
           foremanReportSummary.setHeaderIdRef(headerId);
           foremanReportSummary.setRigId(operator.getRigId());
           map.put(operator.getRigId(),foremanReportSummary);
       }

       for (Map.Entry<Integer,ForemanReportSummary> keys : map.entrySet()
            ) {
           foremanReportSummaryList.add(keys.getValue());
       }
       return foremanReportSummaryList;
   }

    private int getHeaderReferenceId(String shift,String crew,int date){
       ForemanReportHeader foremanReportHeader = foremanReportService.getReportHeader(date,shift,crew);
       if (foremanReportHeader != null )
           return foremanReportHeader.getHeaderId();
       return -1;
    }

    private int getHeaderReferenceIdFromOperator(int id,int date){
       List<OperatorSignIn> operatorSignInList = startEndShiftService.getStartOfShiftPerRigPerDate(date);
        Optional<OperatorSignIn> record = operatorSignInList. stream()
                .filter(p-> p.getOperatorId() == id)
                .findFirst();
        if (record.isPresent()){
            OperatorSignIn startShift = record.get();
            return getHeaderReferenceId(getShiftNameById(startShift.getShiftId()),startShift.getShiftCrew(),date);
        }
        return -1;
    }

    private String getShiftNameById(int id){
        List<Shifts> shiftsList = shiftRepository.getAll();
        Optional<Shifts> record = shiftsList. stream()
                .filter(p-> p.getShiftId() == id)
                .findFirst();
        if (record.isPresent()){
            Shifts shift = record.get();
            return shift.getShiftName();
        }
        return "";
    }

    private String getBreakDownDescription(int breakdownId){
        List<BreakdownTypes> breakdownTypes = lookupService.getBreakdownTypes();
        Optional<BreakdownTypes> record = breakdownTypes. stream()
                .filter(p-> p.getBreakdownTypesId() == breakdownId)
                .findFirst();
        if (record.isPresent()){
            BreakdownTypes type = record.get();
            return type.getTypeText();
        }
        return "";
    }

    private String getStandingTimeDescription(int standingId){
        List<StandingTypes> standingTypes = lookupService.getStandingTypes();
        Optional<StandingTypes> record = standingTypes. stream()
                .filter(p-> p.getStandingTypesId() == standingId)
                .findFirst();
        if (record.isPresent()){
            StandingTypes type = record.get();
            return type.getTypeName();
        }
        return "";
    }

    private String getOperatorNameById(int id){
        List<OperationsUser> operationsUserList = operationsUsersServices.getAllUsers();
        Optional<OperationsUser> record = operationsUserList. stream()
                .filter(p-> p.getOperationsId() == id)
                .findFirst();
        if (record.isPresent()){
            OperationsUser operationsUser = record.get();
            return String.format("%s %s",operationsUser.getFirstName(),operationsUser.getLastName());
        }
        return "";
    }

    private EndOfShift getEndOfShiftForOperator(int operatorId,int rigId,int date){
        return startEndShiftService.getEndOfShiftForOperator(operatorId,rigId,date);
    }

}
