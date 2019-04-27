package za.co.mabatalale.repos;

import za.co.mabatalale.entities.StandingLogs;

import java.util.List;

/**
 * Created by robson on 2017/03/01.
 */
public class StandingTimeEntryRepository extends BasilCrudRepository<StandingLogs> {

    public List<StandingLogs> findByOperatorSheetIdAndDateInt(int id, int date){
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        String query = super.createSqlStatement(methodName);
        String hql = query.replace("?1",String.valueOf(id)).replace("?2",String.valueOf(date));
        return super.where(hql);
    }

    public List<StandingLogs> findByDateInt(int date){
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        String query = super.createSqlStatement(methodName);
        String hql = query.replace("?1",String.valueOf(date));
        return super.where(hql);
    }
}