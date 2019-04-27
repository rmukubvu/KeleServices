package za.co.mabatalale.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import za.co.mabatalale.entities.EndOfShift;
import za.co.mabatalale.entities.OperatorSignIn;
import za.co.mabatalale.models.BasilResponse;
import za.co.mabatalale.models.ResponseError;
import za.co.mabatalale.services.StartEndShiftService;

import javax.inject.Inject;

import static spark.Spark.*;
import static za.co.mabatalale.common.JsonUtil.json;
import static za.co.mabatalale.common.JsonUtil.toJson;

/**
 * Created by robson on 2017/03/02.
 */
public class StartAndEndShiftController {

    @Inject
    public StartAndEndShiftController(StartEndShiftService operatorService) {

        post("/startofshift", (req, res) -> {
            ObjectMapper mapper = new ObjectMapper();
            BasilResponse response = new BasilResponse();
            OperatorSignIn model = mapper.readValue(req.body(), OperatorSignIn.class);
            if (model != null) {
                operatorService.saveStartOfShift(model);
                response.setResponseMessage("Start of shift added");
                response.setError(false);
                res.status(200);
                return response;
            }
            res.status(400);
            return new ResponseError("Failed to map the Start of shift json to model");
        }, json());

        after((req, res) -> res.type("application/json"));

        exception(IllegalArgumentException.class, (e, req, res) -> {
            res.status(400);
            res.body(toJson(new ResponseError(e)));
        });

        get("/startofshift/all/:date", (req, res) -> {
            //String n_rig = req.params(":rig");
            String n_date = req.params(":date");
            try {
                res.status(200);
                return operatorService.getStartOfShiftPerRigPerDate(Integer.parseInt(n_date));
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



        post("/endofshift", (req, res) -> {
            ObjectMapper mapper = new ObjectMapper();
            BasilResponse response = new BasilResponse();
            EndOfShift model = mapper.readValue(req.body(), EndOfShift.class);
            if (model != null) {
                operatorService.saveEndOfShift(model);
                response.setResponseMessage("End of shift added");
                response.setError(false);
                res.status(200);
                return response;
            }
            res.status(400);
            return new ResponseError("Failed to map the end of shift json to model");
        }, json());

        after((req, res) -> res.type("application/json"));

        exception(IllegalArgumentException.class, (e, req, res) -> {
            res.status(400);
            res.body(toJson(new ResponseError(e)));
        });

       /* get("/endofshift/single/:id/:date", (req, res) -> {
            String n_id = req.params(":id");
            String n_date = req.params(":date");
            try {
                res.status(200);
                return operatorService.getEndOfShiftByOperatorAndDate(Integer.parseInt(n_id), Integer.parseInt(n_date));
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
        });*/

        get("/endofshift/all/:date", (req, res) -> {
            //String n_rig = req.params(":rig");
            String n_date = req.params(":date");
            try {
                res.status(200);
                return operatorService.getEndOfShiftPerRigPerDate(Integer.parseInt(n_date));
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
