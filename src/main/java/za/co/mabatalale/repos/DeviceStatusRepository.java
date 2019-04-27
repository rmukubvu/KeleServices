package za.co.mabatalale.repos;

import za.co.mabatalale.entities.DeviceStatus;

import java.util.List;

/**
 * Created by robson on 2017/02/28.
 */
public class DeviceStatusRepository  extends BasilCrudRepository<DeviceStatus> {

    public DeviceStatus findByDeviceStatusId(int id){
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        String query = super.createSqlStatement(methodName);
        String hql = query.replace("?1",String.valueOf(id));
        return super.findOne(hql);
    }

    public List<DeviceStatus> findByDeviceStatusIdGreaterThan(int id){
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        String query = super.createSqlStatement(methodName);
        String hql = query.replace("?1",String.valueOf(id));
        return super.where(hql);
    }

}
