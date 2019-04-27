package za.co.mabatalale.jobs;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import za.co.mabatalale.graphs.MetersDrilledToDateCummulativeJob;
import za.co.mabatalale.graphs.MetersDrilledVsTargetJob;
import za.co.mabatalale.reports.DelayAnalysisReportBuilder;
import za.co.mabatalale.reports.ForemanReportBuilder;

/**
 * Created by robson on 2017/03/27.
 */
public class NightlyJobs implements org.quartz.Job {

    private final DelayAnalysisReportBuilder delayAnalysis;
    private final ForemanReportBuilder foremanReport;
    private final MetersDrilledVsTargetJob metersDrilledVsTargetJob;
    private final MetersDrilledToDateCummulativeJob metersDrilledToDateCummulativeJob;

    public NightlyJobs() {
        delayAnalysis = new DelayAnalysisReportBuilder();
        foremanReport = new ForemanReportBuilder();
        metersDrilledVsTargetJob = new MetersDrilledVsTargetJob();
        metersDrilledToDateCummulativeJob = new MetersDrilledToDateCummulativeJob();
    }

    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {
        System.out.println("Job started for Delay Analysis Report.");
        delayAnalysis.runReportBuilder();
        System.out.println("Delay Analysis Report completed.");
        //do logs later
        System.out.println("Job started for Foreman Report.");
        foremanReport.runReportBuilder();
        System.out.println("Foreman Report completed.");
        //start graphs job
        System.out.println("Job started for Meters drilled to date");
        metersDrilledToDateCummulativeJob.runReportBuilder();
        System.out.println("Meters drilled to date completed.");
        //target vs meters
        System.out.println("Job started for Meters drilled vs target");
        metersDrilledVsTargetJob.runReportBuilder();
        System.out.println("Meters drilled vs target completed.");
    }
}
