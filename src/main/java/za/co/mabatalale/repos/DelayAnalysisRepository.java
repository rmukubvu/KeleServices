package za.co.mabatalale.repos;

import za.co.mabatalale.models.DelayAnalysisReportModel;

import java.util.List;

/**
 * Created by robson on 2017/03/03.
 */
public class DelayAnalysisRepository extends BasilCrudRepository<DelayAnalysisReportModel> {

    public List<DelayAnalysisReportModel> findByDateInt(int date){
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        String query = super.createSqlStatement(methodName);
        String hql = query.replace("?1",String.valueOf(date));
        return super.where(hql);
    }

    public List<DelayAnalysisReportModel> findByDateIntBetween(int date1,int date2){
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        String query = super.createSqlStatement(methodName);
        String hql = query.replace("?1",String.valueOf(date1)).replace("?2",String.valueOf(date2));
        return super.where(hql);
    }
}
