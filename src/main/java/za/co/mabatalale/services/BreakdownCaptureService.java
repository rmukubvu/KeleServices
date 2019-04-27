package za.co.mabatalale.services;

import za.co.mabatalale.cache.BasilCoreCache;
import za.co.mabatalale.entities.BreakdownLogs;
import za.co.mabatalale.entities.BreakdownTypes;
import za.co.mabatalale.entities.ProductionRecord;
import za.co.mabatalale.entities.SiteConfig;
import za.co.mabatalale.models.DisplayTimes;
import za.co.mabatalale.models.TopFiveDailyProblems;
import za.co.mabatalale.repos.*;
import za.co.mabatalale.utils.DateUtil;
import za.co.mabatalale.utils.GraphsUtil;
import za.co.mabatalale.utils.Sorter;


import javax.inject.Inject;
import java.util.*;

/**
 * Created by robson on 2017/03/02.
 */
public class BreakdownCaptureService {

    private final BreakdownEntryRepository breakdownEntryRepository;
    private OperationsUserRepository operationsUserRepository;
    private BreakdownTypesRepository breakdownTypesRepository;
    private SiteConfigurationRepository siteConfigurationRepository;
    private OperatorStatusService operatorStatusService;

    @Inject
    public BreakdownCaptureService(BreakdownEntryRepository breakdownEntryRepository){
        this.breakdownEntryRepository = breakdownEntryRepository;
        operationsUserRepository = new OperationsUserRepository();
        breakdownTypesRepository = new BreakdownTypesRepository();
        siteConfigurationRepository = new SiteConfigurationRepository();
        operatorStatusService = new OperatorStatusService(new OperatorStatusRepository());
    }

    public void save(BreakdownLogs model){
        breakdownEntryRepository.save(model);
        //String operator = operationsUserRepository.findByOperationsId(model.getOperatorSheetId()).toString();
        List<BreakdownTypes> breakdownTypesList = breakdownTypesRepository.getAll();
        Optional<BreakdownTypes> singleRecord = breakdownTypesList
                .stream()
                .filter(p-> p.getBreakdownTypesId() == model.getBreakdownTypeId())
                .findFirst();
        operatorStatusService.saveStatus(model.getOperatorSheetId(), GraphsUtil.BREAKDOWN_COLOR_RED,singleRecord.get().getTypeText());
        //BasilCoreCache.getInstance().sendInstantMessage(String.format("%s|%s",operator,singleRecord.get().getTypeText()));
    }

    public List<BreakdownLogs> findByOperatorIdAndDateInt(int operatorId, int date){
        return breakdownEntryRepository.findByOperatorSheetIdAndDateInt(operatorId, date);
    }

    public DisplayTimes getTotalBreakdownTime(){
        int today = DateUtil.getCurrentDay();
        List<BreakdownLogs> logs = breakdownEntryRepository.findByDateInt(today);
        long difference = 0;
        for (BreakdownLogs log: logs
                ) {
            if (log.getEndTime() == null){
                difference += DateUtil.getCurrentTimeStamp().getTime() - log.getStartTime().getTime();
            }else{
                difference += log.getEndTime().getTime() - log.getStartTime().getTime();
            }
        }
        return new DisplayTimes(difference);
    }

    public List<TopFiveDailyProblems> getTopFiveStandingIssues(){
        int today = DateUtil.getCurrentDay();
        Sorter<Long,BreakdownLogs> sorter = new Sorter<>();
        List<BreakdownLogs> logs = breakdownEntryRepository.findByDateInt(today);
        for (BreakdownLogs log: logs
                ) {
            long difference;
            if (log.getEndTime() == null){
                difference = DateUtil.getCurrentTimeStamp().getTime() - log.getStartTime().getTime();
            }else{
                difference = log.getEndTime().getTime() - log.getStartTime().getTime();
            }
            sorter.add(difference,log);
        }
        return getItemsFromMap(sorter.sort());
    }

    private List<TopFiveDailyProblems> getItemsFromMap(Map<Long,BreakdownLogs> map){
        List<TopFiveDailyProblems> items = new ArrayList<>();
        List<SiteConfig> siteConfigs = siteConfigurationRepository.getAll();//BasilCoreCache.getInstance().getCachedSites();
        List<BreakdownTypes> breakdownTypes = breakdownTypesRepository.getAll();
        final int max = 5;
        int count = 0;
        Set set2 = map.entrySet();
        Iterator iterator2 = set2.iterator();
        while(iterator2.hasNext()) {
            if (count <= max ) {
                Map.Entry me2 = (Map.Entry) iterator2.next();
                TopFiveDailyProblems fiveDailyProblems = new TopFiveDailyProblems();
                BreakdownLogs breakdownLogs = (BreakdownLogs) me2.getValue();
                fiveDailyProblems.setOperator(operationsUserRepository.findByOperationsId(breakdownLogs.getOperatorSheetId()).toString());
                Optional<BreakdownTypes> singleRecord = breakdownTypes.stream().filter(p-> p.getBreakdownTypesId()== breakdownLogs.getBreakdownTypeId()).findFirst();
                fiveDailyProblems.setProblem(singleRecord.get().getTypeText());
                Optional<SiteConfig> singleRecord1 = siteConfigs.stream().filter(p-> p.getSiteConfigId()== breakdownLogs.getRigId()).findFirst();
                fiveDailyProblems.setRig(singleRecord1.get().getSiteName());
                count++;
                items.add(fiveDailyProblems);
            }else
                break;
        }
        return items;
    }
}
