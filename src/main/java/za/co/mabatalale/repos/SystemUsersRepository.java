package za.co.mabatalale.repos;

import za.co.mabatalale.entities.BasilSysUser;

/**
 * Created by robson on 2017/03/01.
 */
public class SystemUsersRepository extends BasilCrudRepository<BasilSysUser> {

    public BasilSysUser findByUserName(String userName){
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        String query = super.createSqlStatement(methodName);
        String hql = query.replace("?1","'%s'");
        String finalHql = hql.replace("%s",userName);
        return super.findOne(finalHql);
    }
}
