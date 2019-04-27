package za.co.mabatalale.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import za.co.mabatalale.entities.ProductionRecord;
import za.co.mabatalale.models.BasilResponse;
import za.co.mabatalale.models.ResponseError;
import za.co.mabatalale.services.ProductionCaptureService;

import javax.inject.Inject;

import static spark.Spark.*;
import static spark.Spark.after;
import static spark.Spark.exception;
import static za.co.mabatalale.common.JsonUtil.json;
import static za.co.mabatalale.common.JsonUtil.toJson;

/**
 * Created by robson on 2017/03/02.
 */
public class ProductionController {

    @Inject
    public ProductionController(ProductionCaptureService productionCaptureService){

        post("/production", (req, res) -> {
            ObjectMapper mapper = new ObjectMapper();
            BasilResponse response = new BasilResponse();
            ProductionRecord model = mapper.readValue(req.body(), ProductionRecord.class);
            if (model != null) {
                productionCaptureService.save(model);
                response.setResponseMessage("Production added");
                response.setError(false);
                res.status(200);
                return response;
            }
            res.status(400);
            return new ResponseError("Failed to map the production json to model");
        }, json());

        after((req, res) -> res.type("application/json"));

        exception(IllegalArgumentException.class, (e, req, res) -> {
            res.status(400);
            res.body(toJson(new ResponseError(e)));
        });

        get("/production/total", (req, res) -> {
            try {
                res.status(200);
                return productionCaptureService.getTotalProductionTime();
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

        get("/production/topfivedrillers", (req, res) -> {
            try {
                res.status(200);
                return productionCaptureService.getTopFiveDrillers();
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

        get("/production/:id/:date", (req, res) -> {
            String n_id = req.params(":id");
            String n_date = req.params(":date");
            try {
                res.status(200);
                return productionCaptureService.findByOperatorIdAndDateInt(Integer.parseInt(n_id), Integer.parseInt(n_date));
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
