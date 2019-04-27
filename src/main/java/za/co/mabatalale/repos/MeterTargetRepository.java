package za.co.mabatalale.repos;

import za.co.mabatalale.models.MeterDrilledVsTarget;

import java.util.List;

/**
 * Created by robson on 2017/04/09.
 */
public class MeterTargetRepository extends BasilCrudRepository<MeterDrilledVsTarget> {

    public MeterDrilledVsTarget findByDateInt(int date){
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        String query = super.createSqlStatement(methodName);
        String hql = query.replace("?1",String.valueOf(date));
        return super.findOne(hql);
    }

    public List<MeterDrilledVsTarget> findByPeriod(int date){
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        String query = super.createSqlStatement(methodName);
        String hql = query.replace("?1",String.valueOf(date));
        return super.where(hql);
    }
}
