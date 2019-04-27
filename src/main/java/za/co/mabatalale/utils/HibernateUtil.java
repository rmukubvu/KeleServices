package za.co.mabatalale.utils;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
/**
 * Created by robson on 2017/02/28.
 */
public class HibernateUtil {
    private static final EntityManagerFactory emFactory;
    static {
        emFactory = Persistence.createEntityManagerFactory("za.co.mabatalale");
    }
    public static CriteriaBuilder getCriteriaBuilder(){
        CriteriaBuilder builder = emFactory.getCriteriaBuilder();
        return  builder;
    }
    public static EntityManager getEntityManager(){
        return emFactory.createEntityManager();
    }

    public static void close(){
        emFactory.close();
    }
}
