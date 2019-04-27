package za.co.mabatalale.repos;

import za.co.mabatalale.entities.LeaveReason;

/**
 * Created by robson on 2017/03/02.
 */
public class LeaveReasonRepository extends BasilCrudRepository<LeaveReason> {
    public LeaveReason findByLeaveReasonId(int id){
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        String query = super.createSqlStatement(methodName);
        String hql = query.replace("?1",String.valueOf(id));
        return super.findOne(hql);
    }
}
