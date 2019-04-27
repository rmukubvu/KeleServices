package za.co.mabatalale.services;

import za.co.mabatalale.cache.BasilCoreCache;
import za.co.mabatalale.entities.SiteConfig;
import za.co.mabatalale.entities.StandingLogs;
import za.co.mabatalale.entities.StandingTypes;
import za.co.mabatalale.models.DisplayTimes;
import za.co.mabatalale.models.TopFiveDailyProblems;
import za.co.mabatalale.repos.*;
import za.co.mabatalale.utils.DateUtil;
import za.co.mabatalale.utils.GraphsUtil;
import za.co.mabatalale.utils.Sorter;

import javax.inject.Inject;
import java.util.*;
import java.util.stream.Stream;

/**
 * Created by robson on 2017/03/02.
 */
public class StandingTimeCaptureService {

    private final StandingTimeEntryRepository standingTimeEntryRepository;
    private OperationsUserRepository operationsUserRepository;
    private SiteConfigurationRepository siteConfigurationRepository;
    private StandingTimeTypesRepository standingTimeTypesRepository;
    private OperatorStatusService operatorStatusService;

    @Inject
    public StandingTimeCaptureService(StandingTimeEntryRepository standingTimeEntryRepository){
        this.standingTimeEntryRepository = standingTimeEntryRepository;
        operationsUserRepository = new OperationsUserRepository();
        siteConfigurationRepository = new SiteConfigurationRepository();
        standingTimeTypesRepository = new StandingTimeTypesRepository();
        operatorStatusService = new OperatorStatusService(new OperatorStatusRepository());
    }

    public void save(StandingLogs model){
        standingTimeEntryRepository.save(model);
        //fire notification
        //String operator = operationsUserRepository.findByOperationsId(model.getOperatorSheetId()).toString();
        List<StandingTypes> standingTypesList = standingTimeTypesRepository.getAll();
        Optional<StandingTypes> singleRecord = standingTypesList
                .stream()
                .filter(p-> p.getStandingTypesId() == model.getStandingTypeId())
                .findFirst();
        operatorStatusService.saveStatus(model.getOperatorSheetId(), GraphsUtil.STANDING_COLOR_BLUE,singleRecord.get().getTypeName());
        //BasilCoreCache.getInstance().sendInstantMessage(String.format("%s|%s",operator,singleRecord.get().getTypeName()));
    }

    public List<StandingLogs> findByOperatorIdAndDateInt(int operatorId, int date){
        return standingTimeEntryRepository.findByOperatorSheetIdAndDateInt(operatorId, date);
    }

    public DisplayTimes getTotalStandingTime(){
        int today = DateUtil.getCurrentDay();
        List<StandingLogs> logs = standingTimeEntryRepository.findByDateInt(today);
        long difference = 0;
        for (StandingLogs log: logs
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
        Sorter<Long,StandingLogs> sorter = new Sorter<>();
        List<StandingLogs> logs = standingTimeEntryRepository.findByDateInt(today);
        for (StandingLogs log: logs
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

    private List<TopFiveDailyProblems> getItemsFromMap(Map<Long,StandingLogs> map){
        List<TopFiveDailyProblems> items = new ArrayList<>();
        List<SiteConfig> siteConfigs = siteConfigurationRepository.getAll();//BasilCoreCache.getInstance().getCachedSites();
        List<StandingTypes> standingTypes = standingTimeTypesRepository.getAll();//BasilCoreCache.getInstance().getStandingTypes();
        final int max = 5;
        int count = 0;
        Set set2 = map.entrySet();
        Iterator iterator2 = set2.iterator();
        while(iterator2.hasNext()) {
            if (count <= max ) {
                Map.Entry me2 = (Map.Entry) iterator2.next();
                TopFiveDailyProblems fiveDailyProblems = new TopFiveDailyProblems();
                StandingLogs standingLogs = (StandingLogs) me2.getValue();
                fiveDailyProblems.setOperator(operationsUserRepository.findByOperationsId(standingLogs.getOperatorSheetId()).toString());
                Optional<StandingTypes> singleRecord = standingTypes.stream().filter(p-> p.getStandingTypesId()== standingLogs.getStandingTypeId()).findFirst();
                fiveDailyProblems.setProblem(singleRecord.get().getTypeName());
                Optional<SiteConfig> singleRecord1 = siteConfigs.stream().filter(p-> p.getSiteConfigId()== standingLogs.getRigId()).findFirst();
                fiveDailyProblems.setRig(singleRecord1.get().getSiteName());
                count++;
                items.add(fiveDailyProblems);
            }else
                break;
        }
        return items;
    }
}
