package za.co.mabatalale.controller;

import za.co.mabatalale.models.BasilResponse;
import za.co.mabatalale.models.ResponseError;
import za.co.mabatalale.services.LookupService;

import javax.inject.Inject;

import static spark.Spark.after;
import static spark.Spark.exception;
import static spark.Spark.get;
import static za.co.mabatalale.common.JsonUtil.json;
import static za.co.mabatalale.common.JsonUtil.toJson;

/**
 * Created by robson on 2017/02/28.
 */
public class LookupController {

    @Inject
    public LookupController(LookupService lookupService){

        //getDeviceStatusById
        get("/lookup/productiontypes", (req, res) -> {
            try {
                res.status(200);
                return lookupService.getProductionTypes();
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

        get("/lookup/standingtypes", (req, res) -> {
            try {
                res.status(200);
                return lookupService.getStandingTypes();
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

        get("/lookup/devicestatus", (req, res) -> {
            try {
                res.status(200);
                return lookupService.getDeviceStatus();
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

        get("/lookup/breakdowntypes", (req, res) -> {
            try {
                res.status(200);
                return lookupService.getBreakdownTypes();
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
