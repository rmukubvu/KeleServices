package za.co.mabatalale.controller;

import za.co.mabatalale.models.BasilResponse;
import za.co.mabatalale.models.ResponseError;
import za.co.mabatalale.services.ForemanReportService;

import javax.inject.Inject;

import static spark.Spark.after;
import static spark.Spark.exception;
import static spark.Spark.get;
import static za.co.mabatalale.common.JsonUtil.json;
import static za.co.mabatalale.common.JsonUtil.toJson;

/**
 * Created by robson on 2017/03/04.
 */
public class ForemanReportController {

    @Inject
    public ForemanReportController(ForemanReportService foremanReportService){

         get("/reports/foreman/header/:date/:shift/:crew", (req, res) -> {
            String n_date = req.params(":date");
            String n_shift = req.params(":shift");
            String n_crew = req.params(":crew");

            try {
                res.status(200);
                return foremanReportService.getReportHeader(Integer.parseInt(n_date),n_shift,n_crew);
            }catch (Exception ex) {
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

        //here get by date by shift and by crew....
        //later do for all shifts
        get("/reports/foreman/details/:refId", (req, res) -> {
            String n_id = req.params(":refId");
            try {
                res.status(200);
                return foremanReportService.getReportDetails(Integer.parseInt(n_id));
            }catch (Exception ex) {
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

        get("/reports/foreman/complete/details", (req, res) -> {
            try {
                res.status(200);
                return foremanReportService.getReportDetailsForPreviousDay();
            }catch (Exception ex) {
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

        //here get by date by shift and by crew....
        get("/reports/foreman/footer/:refId", (req, res) -> {
            String n_id = req.params(":refId");

            try {
                res.status(200);
                return foremanReportService.getReportFooter(Integer.parseInt(n_id));
            }catch (Exception ex) {
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

        get("/reports/foreman/complete/footer", (req, res) -> {
            try {
                res.status(200);
                return foremanReportService.getReportFooterForPreviousDay();
            }catch (Exception ex) {
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

        //here get by date by shift and by crew....
        get("/reports/foreman/summary/:refId", (req, res) -> {
            String n_id = req.params(":refId");

            try {
                res.status(200);
                return foremanReportService.getReportSummary(Integer.parseInt(n_id));
            }catch (Exception ex) {
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

        get("/reports/foreman/complete/summary", (req, res) -> {

            try {
                res.status(200);
                return foremanReportService.getReportSummaryForPreviousDay();
            }catch (Exception ex) {
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
