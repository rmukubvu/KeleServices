package za.co.mabatalale.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import za.co.mabatalale.entities.BasilUsersLeave;
import za.co.mabatalale.models.BasilResponse;
import za.co.mabatalale.models.ResponseError;
import za.co.mabatalale.services.LeaveService;

import javax.inject.Inject;

import static spark.Spark.*;
import static za.co.mabatalale.common.JsonUtil.json;
import static za.co.mabatalale.common.JsonUtil.toJson;

/**
 * Created by robson on 2017/03/02.
 */
public class LeaveController {

    @Inject
    public LeaveController(LeaveService leaveService){

        post("/leave", (req, res) -> {
            ObjectMapper mapper = new ObjectMapper();
            BasilResponse response = new BasilResponse();
            BasilUsersLeave model = mapper.readValue(req.body(), BasilUsersLeave.class);
            if (model != null) {
                if (model.getLeaveTypeId() == null ||
                        model.getUserId() == null){
                    response.setResponseMessage("Reason or User cannot be null");
                    res.status(400);
                    return response;
                }
                leaveService.saveLeave(model);
                response.setResponseMessage("Leave has been captured");
                res.status(200);
                return response;
            }
            res.status(400);
            return new ResponseError("Failed to map the leave json to model");
        }, json());

        after((req, res) -> res.type("application/json"));

        exception(IllegalArgumentException.class, (e, req, res) -> {
            res.status(400);
            res.body(toJson(new ResponseError(e)));
        });

        post("/delete/leave", (req, res) -> {
            ObjectMapper mapper = new ObjectMapper();
            BasilResponse response = new BasilResponse();
            BasilUsersLeave model = mapper.readValue(req.body(), BasilUsersLeave.class);
            if (model != null) {
                leaveService.deleteLeave(model);
                response.setResponseMessage("Leave has been deleted.");
                res.status(200);
                return response;
            }
            res.status(400);
            return new ResponseError("Failed to map the leave json to model");
        }, json());

        after((req, res) -> res.type("application/json"));

        exception(IllegalArgumentException.class, (e, req, res) -> {
            res.status(400);
            res.body(toJson(new ResponseError(e)));
        });

        get("/leave/:date", (req, res) -> {
            String n_date = req.params(":date");
            try {
                res.status(200);
                return leaveService.getUserLeaveByDay(Integer.parseInt(n_date));
            } catch (Exception ex) {
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

        get("/leave/:date1/:date2", (req, res) -> {
            String n_date1 = req.params(":date1");
            String n_date2 = req.params(":date2");
            try {
                res.status(200);
                return leaveService.getUserLeaveByBetweenDays(Integer.parseInt(n_date1),Integer.parseInt(n_date2));
            } catch (Exception ex) {
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

        get("/lookup/leave/reasons", (req, res) -> {
            try {
                res.status(200);
                return leaveService.getLeaveReason();
            } catch (Exception ex) {
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
