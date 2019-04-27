package za.co.mabatalale.repos;

import za.co.mabatalale.models.ForemanReportDetails;
import za.co.mabatalale.models.ForemanReportFooter;

import java.util.List;

/**
 * Created by robson on 2017/03/04.
 */
public class ForemanDetailsRepository extends BasilCrudRepository<ForemanReportDetails> {

    public List<ForemanReportDetails> findByHeaderIdRef(int id) {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        String query = super.createSqlStatement(methodName);
        String hql = query.replace("?1", String.valueOf(id));
        return super.where(hql);
    }

    public List<ForemanReportDetails> findByDateInt(int date) {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        String query = super.createSqlStatement(methodName);
        String hql = query.replace("?1", String.valueOf(date));
        return super.where(hql);
    }
}