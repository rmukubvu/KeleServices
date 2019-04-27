package za.co.mabatalale.repos;

import za.co.mabatalale.entities.ProductionRecord;

import java.util.List;

/**
 * Created by robson on 2017/03/01.
 */
public class ProductionEntryRepository extends BasilCrudRepository<ProductionRecord> {

    public List<ProductionRecord> findByOperatorIdAndDateInt(int operatorId,int date){
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        String query = super.createSqlStatement(methodName);
        String hql = query.replace("?1",String.valueOf(operatorId)).replace("?2",String.valueOf(date));
        return super.where(hql);
    }

    public List<ProductionRecord> findByDateInt(int date) {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        String query = super.createSqlStatement(methodName);
        String hql = query.replace("?1",String.valueOf(date));
        return super.where(hql);
    }
}
