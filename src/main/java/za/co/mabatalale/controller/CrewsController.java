package za.co.mabatalale.controller;

import spark.Spark;
import spark.utils.IOUtils;
import za.co.mabatalale.models.BasilResponse;
import za.co.mabatalale.models.ResponseError;
import za.co.mabatalale.services.CrewService;

import javax.inject.Inject;

import static spark.Spark.after;
import static spark.Spark.exception;
import static spark.Spark.get;
import static za.co.mabatalale.common.JsonUtil.json;
import static za.co.mabatalale.common.JsonUtil.toJson;

/**
 * Created by robson on 2017/03/01.
 */
public class CrewsController {

    @Inject
    public CrewsController(CrewService crewService){

        get("/" , (req, res) -> IOUtils.toString(Spark.class.getResourceAsStream("/resources/public/index.html")));

        get("/crews/all", (req, res) -> {
            try {
                res.status(200);
                return crewService.getCrews();
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
