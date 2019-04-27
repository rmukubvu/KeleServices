package za.co.mabatalale.rest;

import spark.Filter;
import spark.Request;
import spark.Response;
import spark.Spark;
import za.co.mabatalale.cache.BasilCoreCache;
import za.co.mabatalale.controller.*;
import za.co.mabatalale.repos.*;
import za.co.mabatalale.services.*;


import java.util.HashMap;

import static spark.Spark.staticFileLocation;

/**
 * Created by robson on 2016/12/16.
 */
public class SparkInit {
    private static final HashMap<String, String> corsHeaders = new HashMap<>();

    static {
        corsHeaders.put("Access-Control-Allow-Methods", "GET,PUT,POST,DELETE,OPTIONS");
        corsHeaders.put("Access-Control-Allow-Origin", "*");
        corsHeaders.put("Access-Control-Allow-Headers", "Content-Type,Authorization,X-Requested-With,Content-Length,Accept,Origin,");
        corsHeaders.put("Access-Control-Allow-Credentials", "true");
        staticFileLocation("/public");
    }

    public final static void apply() {
        Filter filter = (Request request, Response response) -> corsHeaders.forEach((key, value) -> response.header(key, value));
        Spark.after(filter);
    }

    public SparkInit(){
        setup();
    }

    private void setup(){
        apply();
        new BreakdownController(new BreakdownCaptureService(new BreakdownEntryRepository()));
        new CrewsController(new CrewService(new CrewsRepository()));
        new DelayAnalysisController(new DelayAnalysisService(new DelayAnalysisRepository()));
        new ForemanReportController(new ForemanReportService(new ForemanHeaderRepository(),
                new ForemanDetailsRepository(),
                new ForemanSummaryRepository(),
                new ForemanFooterRepository()));
        new LeaveController(new LeaveService(new LeaveReasonRepository(),new UserLeaveRepository()));
        new LoginController(new LoginService());
        new LookupController(new LookupService(new DeviceStatusRepository()));
        new OperationsUserController(new OperationsUsersServices(new OperationsUserRepository()));
        new StartAndEndShiftController(new StartEndShiftService(new EndOfShiftRepository(), new ShiftStartRepository(),new CurrentSessionsRepository()));
        new ProductionController(new ProductionCaptureService(new ProductionEntryRepository()));
        new ShiftController(new ShiftService(new ShiftRepository()));
        new SiteConfigurationController(new ConfigurationService(new SiteConfigurationRepository()));
        new StandingTimeController(new StandingTimeCaptureService(new StandingTimeEntryRepository()));
        new StatusController(new OperatorStatusService(new OperatorStatusRepository()));
        new GraphController(new GraphService(new MeterTargetRepository(),new MetersDrilledToDateRepository()));
        //cache
        //cache
        BasilCoreCache.getInstance().cacheAll();
    }
}
