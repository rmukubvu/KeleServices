package za.co.mabatalale.repos;

import za.co.mabatalale.models.ForemanReportHeader;
import za.co.mabatalale.models.ForemanReportSummary;

import java.util.List;

/**
 * Created by robson on 2017/03/04.
 */
public class ForemanSummaryRepository extends BasilCrudRepository<ForemanReportSummary> {

    public List<ForemanReportSummary> findByHeaderIdRef(int id) {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        String query = super.createSqlStatement(methodName);
        String hql = query.replace("?1", String.valueOf(id));
        return super.where(hql);
    }

    //findByDateInt
    public List<ForemanReportSummary> findByDateInt(int date) {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        String query = super.createSqlStatement(methodName);
        String hql = query.replace("?1", String.valueOf(date));
        return super.where(hql);
    }
}
