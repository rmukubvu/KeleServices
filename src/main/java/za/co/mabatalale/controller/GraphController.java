package za.co.mabatalale.controller;

import za.co.mabatalale.models.BasilResponse;
import za.co.mabatalale.models.ResponseError;
import za.co.mabatalale.services.GraphService;

import javax.inject.Inject;

import static spark.Spark.*;
import static spark.Spark.after;
import static spark.Spark.exception;
import static za.co.mabatalale.common.JsonUtil.json;
import static za.co.mabatalale.common.JsonUtil.toJson;

/**
 * Created by robson on 2017/04/09.
 */
public class GraphController {

    @Inject
    public GraphController(GraphService graphService) {

        get("/dashboard/graph/metersvstarget", (req, res) -> {
            try {
                res.status(200);
                return graphService.getMetersDrilledVsTarget();
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

        get("/dashboard/graph/totalmetersdrilled", (req, res) -> {
            try {
                res.status(200);
                return graphService.getMetersDrilledToDate();
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

        get("/dashboard/graph/actualdepthdrilled", (req, res) -> {
            try {
                res.status(200);
                return graphService.getActualDepthPerHour();
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
