package za.co.mabatalale.services;

import za.co.mabatalale.business.RoleManager;
import za.co.mabatalale.entities.BasilSysUser;
import za.co.mabatalale.entities.LoginResponse;

/**
 * Created by robson on 2017/03/01.
 */
public class LoginService {

    private RoleManager roleManager;

    public LoginService(){
        roleManager = new RoleManager();
    }

    private LoginResponse login(String userName, String password, boolean isMobile){
        roleManager.setUserName(userName);
        LoginResponse loginResponse = new LoginResponse();
        BasilSysUser basilSysUser = roleManager.getBasilSysUser();

        if (basilSysUser == null) {
            loginResponse.setAllowed(false);
            loginResponse.setErrorMessage("Invalid user entered");
            return loginResponse;
        }

        if (isMobile) {
            if (roleManager.isMobileUser()) {
                if (password.compareTo(basilSysUser.getPassword()) == 0) {
                    loginResponse.setAllowed(true);
                    loginResponse.setErrorMessage("");
                    return loginResponse;
                }
                loginResponse.setAllowed(false);
                loginResponse.setErrorMessage("Invalid username/password combination supplied");
                return loginResponse;
            }
            loginResponse.setAllowed(false);
            loginResponse.setErrorMessage("You are not authorised to use this system");
            return loginResponse;
        }else{
            if (roleManager.isWebUser()) {
                if (password.compareTo(basilSysUser.getPassword()) == 0) {
                    loginResponse.setAllowed(true);
                    loginResponse.setErrorMessage("");
                    return loginResponse;
                }
                loginResponse.setAllowed(false);
                loginResponse.setErrorMessage("Invalid username/password combination supplied");
                return loginResponse;
            }
            loginResponse.setAllowed(false);
            loginResponse.setErrorMessage("You are not authorised to use this system");
            return loginResponse;
        }
    }

    public LoginResponse webLogin(String userName,String password) {
        return login(userName,password,false);
    }

    public LoginResponse mobileLogin(String userName,String password){
        return login(userName,password,true);
    }

}
