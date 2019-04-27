package za.co.mabatalale.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import za.co.mabatalale.entities.OperatorStatus;
import za.co.mabatalale.models.BasilResponse;
import za.co.mabatalale.models.ResponseError;
import za.co.mabatalale.services.OperatorStatusService;

import javax.inject.Inject;

import static spark.Spark.*;
import static spark.Spark.after;
import static spark.Spark.exception;
import static za.co.mabatalale.common.JsonUtil.json;
import static za.co.mabatalale.common.JsonUtil.toJson;

/**
 * Created by robson on 2017/05/06.
 */
public class StatusController {

    @Inject
    public StatusController(OperatorStatusService operatorStatusService) {

        post("/realtime/operator/status", (req, res) -> {
            ObjectMapper mapper = new ObjectMapper();
            BasilResponse response = new BasilResponse();
            OperatorStatus model = mapper.readValue(req.body(), OperatorStatus.class);
            if (model != null) {
                operatorStatusService.saveStatus(model.getOperatorId(),model.getPlantNumber(),model.getActualStatus());
                response.setResponseMessage("Status updated");
                response.setError(false);
                res.status(200);
                return response;
            }
            res.status(400);
            return new ResponseError("Failed to map the status json to model");
        }, json());

        after((req, res) -> res.type("application/json"));

        exception(IllegalArgumentException.class, (e, req, res) -> {
            res.status(400);
            res.body(toJson(new ResponseError(e)));
        });

        get("/dashboard/status", (req, res) -> {
            try {
                res.status(200);
                return operatorStatusService.getUserStatus();
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
