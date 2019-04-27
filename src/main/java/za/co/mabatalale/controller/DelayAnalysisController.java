package za.co.mabatalale.controller;

import za.co.mabatalale.models.BasilResponse;
import za.co.mabatalale.models.ResponseError;
import za.co.mabatalale.services.DelayAnalysisService;

import javax.inject.Inject;

import static spark.Spark.after;
import static spark.Spark.exception;
import static spark.Spark.get;
import static za.co.mabatalale.common.JsonUtil.json;
import static za.co.mabatalale.common.JsonUtil.toJson;

/**
 * Created by robson on 2017/03/04.
 */
public class DelayAnalysisController {

    @Inject
    public DelayAnalysisController(DelayAnalysisService delayAnalysisService){

        get("/reports/delayanalysis/:date", (req, res) -> {
            String n_date = req.params(":date");
            try {
                res.status(200);
                return delayAnalysisService.getDelayReportForDate(Integer.parseInt(n_date));
            }catch (Exception ex) {
                res.status(400);
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

        get("/reports/delayanalysis/:date1/:date2", (req, res) -> {
            String n_date1 = req.params(":date1");
            String n_date2 = req.params(":date2");
            try {
                res.status(200);
                return delayAnalysisService.getDelayReportBetweenRange(Integer.parseInt(n_date1),Integer.parseInt(n_date2));
            }catch (Exception ex) {
                res.status(400);
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

