package za.co.mabatalale.reports;

import za.co.mabatalale.cache.BasilCoreCache;
import za.co.mabatalale.entities.*;
import za.co.mabatalale.models.DelayAnalysisReportModel;
import za.co.mabatalale.repos.BreakdownEntryRepository;
import za.co.mabatalale.repos.DelayAnalysisRepository;
import za.co.mabatalale.repos.SiteConfigurationRepository;
import za.co.mabatalale.repos.StandingTimeEntryRepository;
import za.co.mabatalale.utils.DateUtil;

import java.util.*;

/**
 * Created by robson on 2017/03/27.
 */
public class DelayAnalysisReportBuilder {
    private static final String SOURCE_COLOR_RED = "#ff0000";
    private static final String SOURCE_COLOR_BLUE = "#0000ff";
    private final BasilCoreCache instance = BasilCoreCache.getInstance();

    //do report for previous day
    public void runReportBuilder(){
        DelayAnalysisRepository report = new DelayAnalysisRepository();
        SiteConfigurationRepository site = new SiteConfigurationRepository();
        List<SiteConfig> rigs = site.getAll();
        for(SiteConfig rig : rigs){
            List<DelayAnalysisReportModel> reportModels = executeReport(rig.getSiteConfigId());
            for(DelayAnalysisReportModel reportModel : reportModels ){
                report.save(reportModel);
            }
        }
    }

    //get summary of delays frm breakdown and standing time
    private List<DelayAnalysisReportModel> executeReport(int rig) {
        Integer reportDate = DateUtil.getPreviousDay();
        List<DelayAnalysisReportModel> finalReportModels = new ArrayList<>();

        Map<Integer, Integer> map = new LinkedHashMap<>();
        List<StandingLogs> logs = getStandingTimeLogsForDay();
        for (StandingLogs log: logs
             ) {
            Integer key = log.getStandingTypeId();
            if (map.get(key) != null) {
                map.put(key, map.get(key) + getStandingDelayTime(log));
            } else {
                map.put(key, getStandingDelayTime(log));
            }
        }

        map.forEach((k, v) -> {
            DelayAnalysisReportModel model = new DelayAnalysisReportModel();
            model.setDateInt(reportDate);
            model.setDelayDescription(getStandingTextFromId(k));
            model.setTotalDelay(v);
            model.setRigId(rig);
            model.setDisplayColor(SOURCE_COLOR_BLUE);
            model.setSourceDelay("StandingTime");
            finalReportModels.add(model);
        });

        //do for breakdowns
        map.clear();
        //continue
        List<BreakdownLogs> logs1 = getBreakdownLogsForDay();
        for (BreakdownLogs log : logs1) {
            Integer key = log.getBreakdownTypeId();
            if (map.get(key) != null) {
                map.put(key, map.get(key) + getBreakdownDelayTime(log));
            } else {
                map.put(key, getBreakdownDelayTime(log));
            }
        }

        map.forEach((k, v) -> {
            DelayAnalysisReportModel model = new DelayAnalysisReportModel();
            model.setDateInt(reportDate);
            model.setDelayDescription(getBreakdownTextFromId(k));
            model.setTotalDelay(v);
            model.setRigId(rig);
            model.setDisplayColor(SOURCE_COLOR_RED);
            model.setSourceDelay("Breakdown");
            finalReportModels.add(model);
        });

        //clear map
        map.clear();

        return finalReportModels;
    }

    private List<StandingLogs> getStandingTimeLogsForDay() {
        StandingTimeEntryRepository controller = new StandingTimeEntryRepository();
        List<StandingLogs> logs = controller.findByDateInt(DateUtil.getPreviousDay());
        return logs;
    }

    private int getStandingDelayTime(StandingLogs logs){
        long timeStamp1 = logs.getStartTime().getTime();
        long timeStamp2 = logs.getEndTime().getTime();
        return (int) (timeStamp2 - timeStamp1);
    }

    private int getBreakdownDelayTime(BreakdownLogs logs){
        long timeStamp1 = logs.getStartTime().getTime();
        long timeStamp2 = logs.getEndTime().getTime();
        return (int) (timeStamp2 - timeStamp1);
    }

    private List<BreakdownLogs> getBreakdownLogsForDay() {
        BreakdownEntryRepository controller = new BreakdownEntryRepository();
        List<BreakdownLogs> logs = controller.findByDateInt(DateUtil.getPreviousDay());
        return logs;
    }

    private String getStandingTextFromId(int id){
       List<StandingTypes> standingTypesList = instance.getStandingTypes();
        Optional<StandingTypes> singleRecord = standingTypesList
                .stream()
                .filter(p-> p.getStandingTypesId() == id)
                .findFirst();
        return singleRecord.get().getTypeName();
    }

    private String getBreakdownTextFromId(int id){
        List<BreakdownTypes> standingTypesList = instance.getBreakDownTypes();
        Optional<BreakdownTypes> singleRecord = standingTypesList
                .stream()
                .filter(p-> p.getBreakdownTypesId() == id)
                .findFirst();
        return singleRecord.get().getTypeText();
    }
}
