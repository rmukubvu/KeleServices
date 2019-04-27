package za.co.mabatalale.services;

import za.co.mabatalale.cache.BasilCoreCache;
import za.co.mabatalale.entities.ProductionRecord;
import za.co.mabatalale.entities.ProductionType;
import za.co.mabatalale.entities.SiteConfig;
import za.co.mabatalale.models.DisplayTimes;
import za.co.mabatalale.models.TopFiveDrillers;
import za.co.mabatalale.repos.*;
import za.co.mabatalale.utils.DateUtil;
import za.co.mabatalale.utils.GraphsUtil;
import za.co.mabatalale.utils.Sorter;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by robson on 2017/03/02.
 */
public class ProductionCaptureService {

    private final ProductionEntryRepository productionEntryRepository;
    private OperationsUserRepository operationsUserRepository;
    private SiteConfigurationRepository siteConfigurationRepository;
    private ProductionTypesRepository productionTypesRepository;
    private OperatorStatusService operatorStatusService;


    @Inject
    public ProductionCaptureService(ProductionEntryRepository productionEntryRepository){
        this.productionEntryRepository = productionEntryRepository;
        operationsUserRepository = new OperationsUserRepository();
        siteConfigurationRepository = new SiteConfigurationRepository();
        productionTypesRepository = new ProductionTypesRepository();
        operatorStatusService = new OperatorStatusService(new OperatorStatusRepository());
    }

    public void save(ProductionRecord model){
        productionEntryRepository.save(model);
        //String operator = operationsUserRepository.findByOperationsId(model.getOperatorId()).toString();
        List<ProductionType> productionTypeList = productionTypesRepository.getAll();
        Optional<ProductionType> singleRecord = productionTypeList
                .stream()
                .filter(p-> p.getProductionTypeId() == model.getStatusId())
                .findFirst();
        operatorStatusService.saveStatus(model.getOperatorId(), GraphsUtil.PRODUCTION_COLOR_GREEN,singleRecord.get().getTypeText());
        //BasilCoreCache.getInstance().sendInstantMessage(String.format("%s|%s",operator,singleRecord.get().getTypeText()));
    }

    public List<ProductionRecord> findByDateInt(int date){
        return productionEntryRepository.findByDateInt(date);
    }

    public List<ProductionRecord> findByOperatorIdAndDateInt(int operatorId, int date){
        return productionEntryRepository.findByOperatorIdAndDateInt(operatorId, date);
    }

    public DisplayTimes getTotalProductionTime(){
        int today = DateUtil.getCurrentDay();
        List<ProductionRecord> logs = productionEntryRepository.findByDateInt(today);
        long difference = 0;
        for (ProductionRecord log: logs
                ) {
            if (log.getEndTime() == null){
                difference += DateUtil.getCurrentTimeStamp().getTime() - log.getStartTime().getTime();
            }else{
                difference += log.getEndTime().getTime() - log.getStartTime().getTime();
            }
        }
        return new DisplayTimes(difference);
    }

    //top 5 drilled
    public List<TopFiveDrillers> getTopFiveDrillers(){
        int today = DateUtil.getCurrentDay();
        Map<Integer, Driller> map = new LinkedHashMap<>();
        Driller driller = new Driller();
        List<ProductionRecord> logs = productionEntryRepository.findByDateInt(today);
        for (ProductionRecord log : logs
             ) {
            int key = log.getOperatorId();
            BigDecimal meters = log.getMeters();
            if (map.get(key) != null) {
                driller.setOperatorId(key);
                driller.setMeters(map.get(key).getMeters().add(meters));
                driller.setRigId(log.getRigId());
                map.put(key, driller);
            } else {
                driller.setMeters(meters);
                driller.setOperatorId(key);
                driller.setRigId(log.getRigId());
                map.put(key, driller);
            }
        }
        //sort here ...
        Sorter<BigDecimal,Driller> sorter = new Sorter<>();
        Set set2 = map.entrySet();
        Iterator iterator2 = set2.iterator();
        while(iterator2.hasNext()) {
            Map.Entry me2 = (Map.Entry) iterator2.next();
            Driller driller1 = (Driller) me2.getValue();
            sorter.add(driller1.getMeters() , driller1);
        }
        return getItemsFromMap(sorter.sort());
    }

    private List<TopFiveDrillers> getItemsFromMap(Map<BigDecimal,Driller> map){
        List<TopFiveDrillers> items = new ArrayList<>();
        List<SiteConfig> siteConfigs = siteConfigurationRepository.getAll();//BasilCoreCache.getInstance().getCachedSites();
        final int max = 5;
        int count = 0;
        Set set2 = map.entrySet();
        Iterator iterator2 = set2.iterator();
        while(iterator2.hasNext()) {
            if (count <= max) {
                TopFiveDrillers topFiveDrillers = new TopFiveDrillers();
                Map.Entry me2 = (Map.Entry) iterator2.next();
                Driller driller = (Driller)me2.getValue();
                BigDecimal meters = (BigDecimal)me2.getKey();
                topFiveDrillers.setMeters(String.valueOf(meters));
                topFiveDrillers.setOperator(operationsUserRepository.findByOperationsId(driller.getOperatorId()).toString());
                Optional<SiteConfig> singleRecord1 = siteConfigs.stream().filter(p-> p.getSiteConfigId()== driller.getRigId()).findFirst();
                topFiveDrillers.setRig(singleRecord1.get().getSiteName());
                items.add(topFiveDrillers);
                count++;
            }else
                break;
        }
        return items;
    }


   class Driller {
       private int rigId;
       private BigDecimal meters;
       private int operatorId;

       public int getRigId() {
           return rigId;
       }

       public void setRigId(int rigId) {
           this.rigId = rigId;
       }

       public BigDecimal getMeters() {
           return meters;
       }

       public void setMeters(BigDecimal meters) {
           this.meters = meters;
       }

       public int getOperatorId() {
           return operatorId;
       }

       public void setOperatorId(int operatorId) {
           this.operatorId = operatorId;
       }
   }
}
