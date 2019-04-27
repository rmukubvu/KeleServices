package za.co.mabatalale.services;

import za.co.mabatalale.cache.BasilCoreCache;
import za.co.mabatalale.entities.Countries;
import za.co.mabatalale.entities.SiteConfig;
import za.co.mabatalale.entities.SiteRegions;
import za.co.mabatalale.models.SiteConfigViewModel;
import za.co.mabatalale.models.SiteModel;
import za.co.mabatalale.repos.CountriesRepository;
import za.co.mabatalale.repos.SiteConfigurationRepository;
import za.co.mabatalale.repos.SiteRegionsRepository;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by robson on 2017/03/03.
 */
public class ConfigurationService {

    private final BasilCoreCache instance = BasilCoreCache.getInstance();
    private SiteConfigurationRepository siteConfigurationRepository;
    private CountriesRepository countriesRepository;
    private SiteRegionsRepository siteRegionsRepository;

    @Inject
    public ConfigurationService(SiteConfigurationRepository siteConfigurationRepository){
        this.siteConfigurationRepository = siteConfigurationRepository;
        countriesRepository = new CountriesRepository();
        siteRegionsRepository = new SiteRegionsRepository();
    }

    public List<Countries> getCountries(){
        return countriesRepository.getAll();
    }

    public List<SiteRegions> getRegions(){
       return siteRegionsRepository.getAll();
    }

    public List<SiteConfig> getSites(){
        return instance.getCachedSites();
    }

    public int saveSiteConfig(SiteConfig model){
        if (model.getSiteName().isEmpty())
            return -200;
        if (model.getCountryId() == null ||
                model.getRegionId() == null)
            return -300;
        if(!siteNameExists(model.getSiteName())) {
            int result = siteConfigurationRepository.save(model);
            instance.recacheSites();
            return result;
        }
        return -100;
    }

    private boolean siteNameExists(String site){
        Optional<SiteRegions> region = getRegions()
                .stream()
                .filter(p-> p.getRegionName().equalsIgnoreCase(site))
                .findFirst();
        return region.isPresent();
    }

    public String getSiteNameFromId(int id){
        Optional<SiteConfig> siteConfig = getSites()
                .stream()
                .filter(p-> p.getSiteConfigId() == id)
                .findFirst();

        if ( siteConfig.isPresent() )
            return siteConfig.get().getSiteName();
        return "";
    }

    public List<SiteModel> getSiteDetails(){
        List<SiteConfig> sites = getSites();
        List<SiteModel> models = new ArrayList<>();
        for (SiteConfig site : sites
             ) {
            SiteModel model = new SiteModel();
            Optional<Countries> country = getCountries()
                    .stream()
                    .filter(p-> p.getCountryId() == site.getCountryId())
                    .findFirst();

            Optional<SiteRegions> region = getRegions()
                    .stream()
                    .filter(p-> p.getRegionId() == site.getRegionId())
                    .findFirst();

            model.setCountryName(country.get().getCountryName());
            model.setRegionName(region.get().getRegionName());
            model.setSiteName(site.getSiteName());
            models.add(model);
        }
       return models;
    }
}
