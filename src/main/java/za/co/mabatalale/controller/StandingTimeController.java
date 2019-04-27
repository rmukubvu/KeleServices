package za.co.mabatalale.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import za.co.mabatalale.entities.BreakdownLogs;
import za.co.mabatalale.entities.StandingLogs;
import za.co.mabatalale.models.BasilResponse;
import za.co.mabatalale.models.ResponseError;
import za.co.mabatalale.services.BreakdownCaptureService;
import za.co.mabatalale.services.StandingTimeCaptureService;

import javax.inject.Inject;

import static spark.Spark.*;
import static za.co.mabatalale.common.JsonUtil.json;
import static za.co.mabatalale.common.JsonUtil.toJson;

/**
 * Created by robson on 2017/03/02.
 */
public class StandingTimeController {

    @Inject
    public StandingTimeController(StandingTimeCaptureService standingTimeCaptureService){

        post("/standingtime", (req, res) -> {
            ObjectMapper mapper = new ObjectMapper();
            BasilResponse response = new BasilResponse();
            StandingLogs model = mapper.readValue(req.body(), StandingLogs.class);
            if (model != null) {
                standingTimeCaptureService.save(model);
                response.setResponseMessage("Standing time added");
                response.setError(false);
                res.status(200);
                return response;
            }
            res.status(400);
            return new ResponseError("Failed to map the standing time json to model");
        }, json());

        after((req, res) -> res.type("application/json"));

        exception(IllegalArgumentException.class, (e, req, res) -> {
            res.status(400);
            res.body(toJson(new ResponseError(e)));
        });

        get("/standingtime/topfive", (req, res) -> {
            try {
                res.status(200);
                return standingTimeCaptureService.getTopFiveStandingIssues();
            } catch (Exception ex) {
                res.status(200);
                BasilResponse response = new BasilResponse();
                response.setResponseMessage(ex.getMessage());
                return response;
            }
        }, json());

        after((req, res) -> res.type("application/json"));
        exception(IllegalArgumentException.class, (e, req, res) -> {
            res.status(400);
            res.body(toJson(new ResponseError(e)));
        });

        get("/standingtime/total", (req, res) -> {
            try {
                res.status(200);
                return standingTimeCaptureService.getTotalStandingTime();
            } catch (Exception ex) {
                res.status(200);
                BasilResponse response = new BasilResponse();
                response.setResponseMessage(ex.getMessage());
                return response;
            }
        }, json());

        after((req, res) -> res.type("application/json"));
        exception(IllegalArgumentException.class, (e, req, res) -> {
            res.status(400);
            res.body(toJson(new ResponseError(e)));
        });

        get("/standingtime/:id/:date", (req, res) -> {
            String n_id = req.params(":id");
            String n_date = req.params(":date");
            try {
                res.status(200);
                return standingTimeCaptureService.findByOperatorIdAndDateInt(Integer.parseInt(n_id), Integer.parseInt(n_date));
            } catch (Exception ex) {
                res.status(200);
                BasilResponse response = new BasilResponse();
                response.setResponseMessage(ex.getMessage());
                return response;
            }
        }, json());

        after((req, res) -> res.type("application/json"));
        exception(IllegalArgumentException.class, (e, req, res) -> {
            res.status(400);
            res.body(toJson(new ResponseError(e)));
        });
    }
}
