package za.co.mabatalale.repos;

import za.co.mabatalale.entities.BasilUsersLeave;

import java.util.List;

/**
 * Created by robson on 2017/03/01.
 */
public class UserLeaveRepository extends BasilCrudRepository<BasilUsersLeave> {

    public List<BasilUsersLeave> findByDateInt(int date){
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        String query = super.createSqlStatement(methodName);
        String hql = query.replace("?1",String.valueOf(date));
        return super.where(hql);
    }

    public List<BasilUsersLeave> findByDateIntBetween(int date1,int date2){
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        String query = super.createSqlStatement(methodName);
        String hql = query.replace("?1",String.valueOf(date1)).replace("?2",String.valueOf(date2));
        return super.where(hql);
    }

    public BasilUsersLeave findByBasilUsersLeaveId(int id){
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        String query = super.createSqlStatement(methodName);
        String hql = query.replace("?1",String.valueOf(id));
        return super.findOne(hql);
    }

    public void deleteRecord(BasilUsersLeave record){
        super.delete(record);
    }

}
