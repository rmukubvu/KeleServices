package za.co.mabatalale.repos;

import za.co.mabatalale.entities.BreakdownLogs;

import java.util.List;

/**
 * Created by robson on 2017/03/01.
 */
public class BreakdownEntryRepository extends BasilCrudRepository<BreakdownLogs> {

    public List<BreakdownLogs> findByOperatorSheetIdAndDateInt(int id,int date){
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        String query = super.createSqlStatement(methodName);
        String hql = query.replace("?1",String.valueOf(id)).replace("?2",String.valueOf(date));
        return super.where(hql);
    }

    public List<BreakdownLogs> findByDateInt(int date) {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        String query = super.createSqlStatement(methodName);
        String hql = query.replace("?1",String.valueOf(date));
        return super.where(hql);
    }
}
