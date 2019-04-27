package za.co.mabatalale.controller;



import za.co.mabatalale.models.BasilResponse;
import za.co.mabatalale.models.ResponseError;
import za.co.mabatalale.services.ShiftService;


import static spark.Spark.after;
import static spark.Spark.exception;
import static spark.Spark.get;
import static za.co.mabatalale.common.JsonUtil.json;
import static za.co.mabatalale.common.JsonUtil.toJson;
/**
 * Created by robson on 2017/02/25.
 */
public class ShiftController {
     public ShiftController(final ShiftService service) {
        get("/shifts", (req, res) -> {
            try {
                res.status(200);
                return service.getShifts();
            } catch (Exception ex) {
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

