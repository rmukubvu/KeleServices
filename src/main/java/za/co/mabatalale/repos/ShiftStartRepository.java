package za.co.mabatalale.repos;

import za.co.mabatalale.entities.OperatorSignIn;

import java.util.List;

/**
 * Created by robson on 2017/03/03.
 */
public class ShiftStartRepository extends BasilCrudRepository<OperatorSignIn> {

    public List<OperatorSignIn> findByDateInt(int date){
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        String query = super.createSqlStatement(methodName);
        String hql = query.replace("?1",String.valueOf(date));
        return super.where(hql);
    }
}
