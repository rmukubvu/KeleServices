package za.co.mabatalale.repos;

import za.co.mabatalale.entities.OperatorStatus;
import za.co.mabatalale.utils.DateUtil;
import za.co.mabatalale.utils.HibernateUtil;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by robson on 2017/03/03.
 */
public class OperatorStatusRepository extends BasilCrudRepository<OperatorStatus> {

    public OperatorStatus findByOperatorId(int id){
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        String query = super.createSqlStatement(methodName);
        String hql = query.replace("?1",String.valueOf(id));
        return super.findOne(hql);
    }

    public List<OperatorStatus> getAllOrderByDate(){
        return super.whereOrderedByColumn("OperatorStatus",DateUtil.getCurrentDay(),"statusDate");
    }

    public void updateStatus(int id,String color,String status){
        EntityManager entityManager = HibernateUtil.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            OperatorStatus model = findByOperatorId(id);
            model.setStatusDate(DateUtil.getCurrentTimeStamp());
            model.setActualStatus(status);
            model.setPlantNumber(color);
            model.setDateInt(DateUtil.getCurrentDay());
            entityManager.merge(model);
            entityManager.getTransaction().commit();
        }catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            entityManager.close();
        }
    }


}
