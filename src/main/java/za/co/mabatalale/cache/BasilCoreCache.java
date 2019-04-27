package za.co.mabatalale.cache;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import redis.clients.jedis.Jedis;
import za.co.mabatalale.entities.*;
import za.co.mabatalale.repos.*;

import java.util.Arrays;
import java.util.List;

/**
 * Created by robson on 2017/03/04.
 */
public class BasilCoreCache {

    private static BasilCoreCache instance;
    private static final String COUNTRIES_KEY = "basil.countries";
    private static final String SITE_CONFIG_KEY = "basil.site.config";
    private static final String PRODUCTION_TYPES_KEY = "basil.production.types";
    private static final String BREAKDOWN_TYPE_KEY = "basil.breakdown.types";
    private static final String STANDING_TIME_TYPE_KEY = "basil.standing.types";
    private static final String SITE_REGION_KEY = "basil.site.region";
    private static final String SHIFTS_KEY = "basil.shifts";


    private SiteConfigurationRepository siteConfigurationRepository;
    private ProductionTypesRepository productionTypesRepository;
    private BreakdownTypesRepository breakdownTypesRepository;
    private StandingTimeTypesRepository standingTimeTypesRepository;
    private SiteRegionsRepository siteRegionsRepository;
    private ShiftRepository shiftRepository;
    private CountriesRepository countriesRepository;
    private RedisCache cache;

    private BasilCoreCache(){
        siteConfigurationRepository = new SiteConfigurationRepository();
        productionTypesRepository = new ProductionTypesRepository();
        breakdownTypesRepository = new BreakdownTypesRepository();
        standingTimeTypesRepository = new StandingTimeTypesRepository();
        siteRegionsRepository = new SiteRegionsRepository();
        countriesRepository = new CountriesRepository();
        shiftRepository = new ShiftRepository();
        cache = new RedisCache();
    }

    public static synchronized BasilCoreCache getInstance(){
        if(instance == null){
            instance = new BasilCoreCache();
        }
        return instance;
    }

    public void cacheAll(){

        List<Countries> countries = countriesRepository.getAll();
        cache.setValue(COUNTRIES_KEY,countries);

        List<SiteConfig> rigs = siteConfigurationRepository.getAll();
        cache.setValue(SITE_CONFIG_KEY,rigs);

        List<ProductionType> productionTypes = productionTypesRepository.getAll();
        cache.setValue(PRODUCTION_TYPES_KEY,productionTypes);

        List<BreakdownTypes> breakdownTypes = breakdownTypesRepository.getAll();
        cache.setValue(BREAKDOWN_TYPE_KEY,breakdownTypes);

        List<StandingTypes> standingTypes = standingTimeTypesRepository.getAll();
        cache.setValue(STANDING_TIME_TYPE_KEY,standingTypes);

        List<SiteRegions> siteRegions = siteRegionsRepository.getAll();
        cache.setValue(SITE_REGION_KEY,siteRegions);

        List<Shifts> shifts = shiftRepository.getAll();
        cache.setValue(SHIFTS_KEY,shifts);
    }

    public void recacheSites(){
        List<SiteConfig> rigs = siteConfigurationRepository.getAll();
        cache.refreshValue(SITE_CONFIG_KEY,rigs);
    }

    public List<Countries> getCountries(){
        return cache.getItemsAsList(COUNTRIES_KEY,Countries[].class);
    }

    public List<SiteRegions> getSiteRegions(){
        return cache.getItemsAsList(SITE_REGION_KEY,SiteRegions[].class);
    }

    public List<SiteConfig> getCachedSites(){
        return cache.getItemsAsList(SITE_CONFIG_KEY,SiteConfig[].class);
    }

    public List<ProductionType> getProductionTypes(){
        return cache.getItemsAsList(PRODUCTION_TYPES_KEY,ProductionType[].class);
    }

    public List<BreakdownTypes> getBreakDownTypes(){
        return cache.getItemsAsList(BREAKDOWN_TYPE_KEY,BreakdownTypes[].class);
    }

    public List<StandingTypes> getStandingTypes(){
        return cache.getItemsAsList(STANDING_TIME_TYPE_KEY,StandingTypes[].class);
    }

    public List<Shifts> getShifts(){
        return cache.getItemsAsList(SHIFTS_KEY,Shifts[].class);
    }

    public void sendInstantMessage(String message){
        cache.sendMessage(message);
    }

    public void cleanUp(){
        cache.dispose();
    }

}
