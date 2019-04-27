package za.co.mabatalale.business;

import za.co.mabatalale.entities.BasilSysUser;
import za.co.mabatalale.entities.BasilUsers;
import za.co.mabatalale.enums.RoleTypes;
import za.co.mabatalale.repos.SystemUsersRepository;
import za.co.mabatalale.repos.UserRepository;

import javax.management.relation.Role;

/**
 * Created by robson on 2017/03/01.
 */
public class RoleManager {

    private String userName;
    private SystemUsersRepository systemUsersRepository;
    private UserRepository userRepository;

    public RoleManager(){
        systemUsersRepository = new SystemUsersRepository();
        userRepository = new UserRepository();
    }

    public RoleManager(String userName){
        super();
        this.userName = userName;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public boolean isWebUser(){
        BasilSysUser user = getUserDetails();
        if (user != null){
            BasilUsers basilUsers = userRepository.findByUserIdIs(user.getBasilUserId());
            if (basilUsers != null) {
                RoleTypes roleTypes = RoleTypes.fromId(basilUsers.getRoleTypeId());
                return roleTypes == RoleTypes.WEB || roleTypes == RoleTypes.MOBILEANDWEB;
            }
        }
        return false;
    }

    public boolean isMobileUser(){
        BasilSysUser user = getUserDetails();
        if (user != null) {
            BasilUsers basilUsers = userRepository.findByUserIdIs(user.getBasilUserId());
            if (basilUsers != null) {
                RoleTypes roleTypes = RoleTypes.fromId(basilUsers.getRoleTypeId());
                return roleTypes == RoleTypes.MOBILE || roleTypes == RoleTypes.MOBILEANDWEB;
            }
        }
        return false;
    }

    private BasilSysUser getUserDetails(){
        return systemUsersRepository.findByUserName(this.userName);
    }

    public BasilSysUser getBasilSysUser(){
        return getUserDetails();
    }

}
