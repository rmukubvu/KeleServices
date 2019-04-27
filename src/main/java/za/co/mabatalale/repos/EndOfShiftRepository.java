package za.co.mabatalale.repos;

import za.co.mabatalale.entities.EndOfShift;

import java.util.List;

/**
 * Created by robson on 2017/03/02.
 */
public class EndOfShiftRepository extends BasilCrudRepository<EndOfShift> {

    public EndOfShift findByOperatorIdAndDateInt(int id,int date){
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        String query = super.createSqlStatement(methodName);
        String hql = query.replace("?1",String.valueOf(id)).replace("?2",String.valueOf(date));
        return super.findOne(hql);
    }

    public EndOfShift findBySessionKey(String sessionKey){
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        String query = super.createSqlStatement(methodName);
        String shiftQuery = query.replace("?1","'%s'");
        String hql = shiftQuery.replace("%s", sessionKey);
        return super.findOne(hql);
    }

    public List<EndOfShift> findByDateInt(int date){
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        String query = super.createSqlStatement(methodName);
        String hql = query.replace("?1",String.valueOf(date));
        return super.where(hql);
    }

}
