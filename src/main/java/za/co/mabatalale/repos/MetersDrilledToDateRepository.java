package za.co.mabatalale.repos;

import za.co.mabatalale.models.MetersDrilledToDate;

import java.util.List;

/**
 * Created by robson on 2017/04/09.
 */
public class MetersDrilledToDateRepository extends BasilCrudRepository<MetersDrilledToDate> {

    public List<MetersDrilledToDate> findByPeriod(int period){
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        String query = super.createSqlStatement(methodName);
        String hql = query.replace("?1",String.valueOf(period));
        return super.where(hql);
    }

    public MetersDrilledToDate findByDateInt(int date){
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        String query = super.createSqlStatement(methodName);
        String hql = query.replace("?1",String.valueOf(date));
        return super.findOne(hql);
    }

}
