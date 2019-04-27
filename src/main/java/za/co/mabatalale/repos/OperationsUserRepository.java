package za.co.mabatalale.repos;

import za.co.mabatalale.entities.OperationsUser;

import java.util.List;

/**
 * Created by robson on 2017/03/02.
 */
public class OperationsUserRepository extends BasilCrudRepository<OperationsUser> {

    public List<OperationsUser> findByRoleId(int id){
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        String query = super.createSqlStatement(methodName);
        String hql = query.replace("?1",String.valueOf(id));
        return super.where(hql);
    }

    public OperationsUser findByOperationsId(int id){
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        String query = super.createSqlStatement(methodName);
        String hql = query.replace("?1",String.valueOf(id));
        return super.findOne(hql);
    }
}
