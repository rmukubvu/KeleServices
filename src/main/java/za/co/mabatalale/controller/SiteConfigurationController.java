package za.co.mabatalale.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import za.co.mabatalale.entities.SiteConfig;
import za.co.mabatalale.models.BasilResponse;
import za.co.mabatalale.models.ResponseError;
import za.co.mabatalale.services.ConfigurationService;

import javax.inject.Inject;

import static spark.Spark.*;
import static spark.Spark.after;
import static spark.Spark.exception;
import static za.co.mabatalale.common.JsonUtil.json;
import static za.co.mabatalale.common.JsonUtil.toJson;

/**
 * Created by robson on 2017/03/03.
 */
public class SiteConfigurationController {

    @Inject
    public SiteConfigurationController(ConfigurationService configurationService){

        post("/config/create", (req, res) -> {
            ObjectMapper mapper = new ObjectMapper();
            BasilResponse response = new BasilResponse();
            SiteConfig model = mapper.readValue(req.body(), SiteConfig.class);
            if (model != null) {
                int reply = configurationService.saveSiteConfig(model);
                if (reply == -300) {
                    response.setResponseMessage("Country or Site cannot be null");
                    res.status(400);
                }else if (reply == -200) {
                    response.setResponseMessage("Sitename cannot be null");
                    res.status(400);
                }else if (reply == -100){
                    response.setResponseMessage("Sitename already exists");
                    res.status(400);
                }else{
                    response.setResponseMessage("Site has been added");
                    res.status(200);
                }
                return response;
            }
            res.status(400);
            return new ResponseError("Failed to map site json to model");
        }, json());

        after((req, res) -> res.type("application/json"));

        exception(IllegalArgumentException.class, (e, req, res) -> {
            res.status(400);
            res.body(toJson(new ResponseError(e)));
        });

        get("/config/countries", (req, res) -> {
            try {
                res.status(200);
                return configurationService.getCountries();
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

        get("/config/regions", (req, res) -> {
            try {
                res.status(200);
                return configurationService.getRegions();
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

        get("/config/rigs", (req, res) -> {
            try {
                res.status(200);
                return configurationService.getSites();
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

        get("/config/view", (req, res) -> {
            try {
                res.status(200);
                return configurationService.getSiteDetails();
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
