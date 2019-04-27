package za.co.mabatalale.jobs;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import za.co.mabatalale.Application;
import za.co.mabatalale.reports.ForemanReportBuilder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * Created by robson on 2017/03/27.
 */
public class JobManager implements Runnable {

    private void startJob(){
        try {
            System.out.println("Waiting for job...");

            // define the job and tie it to our MyJob class
            JobDetail job = newJob(NightlyJobs.class)
                    .withIdentity("basilReports", "reportGroup")
                    .build();

            String startDateStr = "2017-03-04 02:00:00.0";
            String endDateStr = "2099-12-31 00:00:00.0";

            Date startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(startDateStr);
            Date endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(endDateStr);

            CronTrigger cronTrigger = newTrigger()
                    .withIdentity("basil", "reportJob")
                    .startAt(startDate)
                    .withSchedule(CronScheduleBuilder.cronSchedule("0 00 1 * * ?").withMisfireHandlingInstructionDoNothing())
                    .endAt(endDate)
                    .build();
            // Tell quartz to schedule the job using our trigger
            //schedule it
            Scheduler scheduler = new StdSchedulerFactory().getScheduler();
            scheduler.scheduleJob(job, cronTrigger);
            scheduler.start();
        } catch (SchedulerException | ParseException ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        startJob();
    }
}
