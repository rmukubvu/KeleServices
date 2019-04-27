package za.co.mabatalale.controller;

import za.co.mabatalale.entities.LoginResponse;
import za.co.mabatalale.models.ResponseError;
import za.co.mabatalale.services.LoginService;

import javax.inject.Inject;

import static spark.Spark.after;
import static spark.Spark.exception;
import static spark.Spark.get;
import static za.co.mabatalale.common.JsonUtil.json;
import static za.co.mabatalale.common.JsonUtil.toJson;

/**
 * Created by robson on 2017/03/01.
 */
public class LoginController {

    @Inject
    public LoginController(LoginService loginService){

        get("/login/web/:username/:password", (req, res) -> {
            String userName = req.params(":username");
            String password = req.params(":password");
            try {
                res.status(200);
                return loginService.webLogin(userName,password);
            }catch (Exception ex) {
                res.status(200);
                LoginResponse loginResponse = new LoginResponse();
                loginResponse.setAllowed(false);
                loginResponse.setErrorMessage(ex.getMessage());
                return loginResponse;
            }
        }, json());

        after((req, res) -> res.type("application/json"));
        exception(IllegalArgumentException.class, (e, req, res) -> {
            res.status(400);
            res.body(toJson(new ResponseError(e)));
        });

        get("/login/mobile/:username/:password", (req, res) -> {
            String userName = req.params(":username");
            String password = req.params(":password");
            try {
                res.status(200);
                return loginService.mobileLogin(userName,password);
            }catch (Exception ex) {
                res.status(200);
                LoginResponse loginResponse = new LoginResponse();
                loginResponse.setAllowed(false);
                loginResponse.setErrorMessage(ex.getMessage());
                return loginResponse;
            }
        }, json());

        after((req, res) -> res.type("application/json"));
        exception(IllegalArgumentException.class, (e, req, res) -> {
            res.status(400);
            res.body(toJson(new ResponseError(e)));
        });


    }
}
