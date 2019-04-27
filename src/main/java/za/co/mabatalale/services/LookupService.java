package za.co.mabatalale.services;

import javax.inject.Inject;

import za.co.mabatalale.cache.BasilCoreCache;
import za.co.mabatalale.entities.BreakdownTypes;
import za.co.mabatalale.entities.DeviceStatus;
import za.co.mabatalale.entities.ProductionType;
import za.co.mabatalale.entities.StandingTypes;
import za.co.mabatalale.repos.BreakdownTypesRepository;
import za.co.mabatalale.repos.DeviceStatusRepository;
import za.co.mabatalale.repos.ProductionTypesRepository;
import za.co.mabatalale.repos.StandingTimeTypesRepository;

import java.util.List;

/**
 * Created by robson on 2017/02/28.
 */
public class LookupService {

    private final DeviceStatusRepository deviceStatusRepository;
    private final BasilCoreCache instance = BasilCoreCache.getInstance();

    @Inject
    public LookupService(DeviceStatusRepository deviceStatusRepository) {
        this.deviceStatusRepository = deviceStatusRepository;
    }

    public List<DeviceStatus> getDeviceStatus(){
        return deviceStatusRepository.getAll();
    }

   public List<ProductionType> getProductionTypes(){
        return instance.getProductionTypes();
   }

   public List<StandingTypes> getStandingTypes(){
       return instance.getStandingTypes();
   }

   public List<BreakdownTypes> getBreakdownTypes(){
       return instance.getBreakDownTypes();
   }

}
