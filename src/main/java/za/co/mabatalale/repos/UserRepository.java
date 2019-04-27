package za.co.mabatalale.repos;

import za.co.mabatalale.entities.BasilUsers;

/**
 * Created by robson on 2017/03/01.
 */
public class UserRepository extends BasilCrudRepository<BasilUsers> {

    public BasilUsers findByUserIdIs(int id){
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        String query = super.createSqlStatement(methodName);
        String hql = query.replace("?1",String.valueOf(id));
        return super.findOne(hql);
    }
}
