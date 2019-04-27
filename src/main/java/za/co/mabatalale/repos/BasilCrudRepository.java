package za.co.mabatalale.repos;

import za.co.mabatalale.utils.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by robson on 2017/02/27.
 * https://www.jetbrains.com/help/idea/2017.1/generate-persistence-mapping-import-dialogs.html
 */
public class BasilCrudRepository<T> {

    private EntityManager entityManager;
    private Class<T> clazz;
    private CriteriaBuilder builder;
    private static Map<String,String> literalsMap;
    private String[] keywords = new String[]{"And","Or","Equals","Is","Between","LessThan","GreaterThan","GreaterOrEqualTo","LessOrEqualTo","IsNotNull","IsNull"};

    static {
        literalsMap = new HashMap<>();
        literalsMap.put("And","where x.%s = ?1 and x.%s = ?2");
        literalsMap.put("Or","where x.%s = ?1 or x.%s = ?2");
        literalsMap.put("Equals","where x.%s = ?1");
        literalsMap.put("Is","where x.%s = ?1");
        literalsMap.put("Between","where x.%s between ?1 and ?2");
        literalsMap.put("LessThan","where x.%s < ?1");
        literalsMap.put("GreaterThan","where x.%s > ?1");
        literalsMap.put("GreaterOrEqualTo","where x.%s >= ?1");
        literalsMap.put("LessOrEqualTo","where x.%s <= ?1");
        literalsMap.put("IsNotNull","where x.%s is not null");
        literalsMap.put("IsNull","where x.%s is null");
    }


    public BasilCrudRepository() {
        builder = HibernateUtil.getCriteriaBuilder();
        Type genericSuperclass = this.getClass().getGenericSuperclass();
        if (genericSuperclass instanceof ParameterizedType) {
            ParameterizedType pt = (ParameterizedType) genericSuperclass;
            Type type = pt.getActualTypeArguments()[0];
            clazz = (Class<T>) type;
        }
    }

    public List<T> getAll(){
        try {
            entityManager = HibernateUtil.getEntityManager();
            CriteriaQuery<T> criteriaQuery = builder.createQuery(clazz);
            Root<T> root = criteriaQuery.from(clazz);
            criteriaQuery.select(root);
            return entityManager.createQuery(criteriaQuery).getResultList();
        }finally {
            entityManager.close();
        }
    }

    public int save(T model) {
        try {
            entityManager = HibernateUtil.getEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(model);
            entityManager.getTransaction().commit();
            return 1;
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            entityManager.close();
        }
        return -1;
    }

    public T findOne(String queryString){
        try {
            entityManager = HibernateUtil.getEntityManager();
            TypedQuery<T> query = entityManager.createQuery(queryString, clazz);
            return query.getSingleResult();

        }catch (Exception ex){
            return null;
        }finally {
            entityManager.close();
        }
    }

    public List<T> where(String queryString) {
        try {
            entityManager = HibernateUtil.getEntityManager();
            TypedQuery<T> query = entityManager.createQuery(queryString, clazz);
            return query.getResultList();
        } catch (Exception ex) {
            return null;
        } finally {
            entityManager.close();
        }
    }

    public List<T> whereOrderedByColumn(String table,int date,String columnName) {
        try {
            entityManager = HibernateUtil.getEntityManager();
            String orderedQuery = String.format("SELECT x FROM %s x WHERE x.dateInt = %d ORDER BY x.%s DESC",table,date,columnName);
            TypedQuery<T> query = entityManager.createQuery(orderedQuery, clazz);
            return query.getResultList();
        } catch (Exception ex) {
            return null;
        } finally {
            entityManager.close();
        }
    }


    public void delete(T model){
        try {
            entityManager = HibernateUtil.getEntityManager();
            entityManager.getTransaction().begin();
            entityManager.remove(model);
            entityManager.getTransaction().commit();
        }catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            entityManager.close();
        }
    }


    public String createSqlStatement(String methodName) {
        String query = "";
        String className;
        String topFormat;

        String fullClassName = clazz.getSimpleName();
        if (fullClassName.contains("."))
            className = fullClassName.substring(fullClassName.lastIndexOf('.') + 1);
        else
            className = fullClassName;

        String stripName = methodName.replace("findBy", "");
        for (String keyword : keywords) {
            if (stripName.contains(keyword)) {
                String[] split = stripName.split(keyword);
                topFormat = String.format("SELECT x FROM %s x %s", className, literalsMap.get(keyword));
                if (split.length > 1) {
                    query = String.format(topFormat, getQualifiedPropertyName(split[0]), getQualifiedPropertyName(split[1]));
                } else {
                    query = String.format(topFormat, getQualifiedPropertyName(split[0]));
                }
                break;
            }
        }

        if (query.isEmpty()) {
            query = String.format("SELECT x FROM %s x WHERE x.%s = ?1 ", className, getQualifiedPropertyName(stripName));
        }

        return query;
    }

    private String getQualifiedPropertyName(String name){
        return name.substring(0, 1).toLowerCase() + name.substring(1);
    }

}
///usr/bin/java /usr/share/java /usr/lib/jvm/java-8-oracle/bin/java /usr/lib/jvm/java-8-oracle/jre/bin/java /usr/share/man/man1/java.1.gz
