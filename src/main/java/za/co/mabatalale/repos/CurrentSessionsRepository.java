package za.co.mabatalale.repos;

import za.co.mabatalale.models.CurrentSession;

import java.util.List;

/**
 * Created by robson on 2017/05/31.
 */
public class CurrentSessionsRepository extends BasilCrudRepository<CurrentSession> {

    public List<CurrentSession> findBySessionDate(int date){
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        String query = super.createSqlStatement(methodName);
        String hql = query.replace("?1",String.valueOf(date));
        return super.where(hql);
    }
}
