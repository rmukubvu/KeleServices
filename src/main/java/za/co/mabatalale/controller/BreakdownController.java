package za.co.mabatalale.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import za.co.mabatalale.entities.BreakdownLogs;
import za.co.mabatalale.models.BasilResponse;
import za.co.mabatalale.models.ResponseError;
import za.co.mabatalale.services.BreakdownCaptureService;

import javax.inject.Inject;

import static spark.Spark.*;
import static za.co.mabatalale.common.JsonUtil.json;
import static za.co.mabatalale.common.JsonUtil.toJson;

/**
 * Created by robson on 2017/03/02.
 */
public class BreakdownController {

    @Inject
    public BreakdownController(BreakdownCaptureService breakdownCaptureService){

        post("/breakdown", (req, res) -> {
            ObjectMapper mapper = new ObjectMapper();
            BasilResponse response = new BasilResponse();
            BreakdownLogs model = mapper.readValue(req.body(), BreakdownLogs.class);
            if (model != null) {
                breakdownCaptureService.save(model);
                response.setResponseMessage("Breakdown added");
                response.setError(false);
                res.status(200);
                return response;
            }
            res.status(400);
            return new ResponseError("Failed to map the breakdown json to model");
        }, json());

        after((req, res) -> res.type("application/json"));

        exception(IllegalArgumentException.class, (e, req, res) -> {
            res.status(400);
            res.body(toJson(new ResponseError(e)));
        });

        get("/breakdown/total", (req, res) -> {
            try {
                res.status(200);
                return breakdownCaptureService.getTotalBreakdownTime();
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

        get("/breakdown/topfive", (req, res) -> {
            try {
                res.status(200);
                return breakdownCaptureService.getTopFiveStandingIssues();
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

        get("/breakdown/:id/:date", (req, res) -> {
            String n_id = req.params(":id");
            String n_date = req.params(":date");
            try {
                res.status(200);
                return breakdownCaptureService.findByOperatorIdAndDateInt(Integer.parseInt(n_id), Integer.parseInt(n_date));
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
