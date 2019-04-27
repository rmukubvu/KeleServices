package za.co.mabatalale.repos;

import za.co.mabatalale.models.ForemanReportHeader;

import java.util.List;

/**
 * Created by robson on 2017/03/04.
 */
public class ForemanHeaderRepository extends BasilCrudRepository<ForemanReportHeader> {

    public List<ForemanReportHeader> findByDateIntAndShift(int date,String shift) {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        String query = super.createSqlStatement(methodName);
        String shiftQuery = query.replace("?2","'%s'");
        String hql = shiftQuery.replace("?1", String.valueOf(date)).replace("%s", shift);
        return super.where(hql);
    }

    //findByDateInt
    public List<ForemanReportHeader> findByDateInt(int date) {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        String query = super.createSqlStatement(methodName);
        String hql = query.replace("?1", String.valueOf(date));
        return super.where(hql);
    }
}
