package za.co.mabatalale.services;

import za.co.mabatalale.entities.Crews;
import za.co.mabatalale.repos.CrewsRepository;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by robson on 2017/03/01.
 */
public class CrewService {

    private final CrewsRepository crewsRepository;

    @Inject
    public CrewService(CrewsRepository crewsRepository){
        this.crewsRepository = crewsRepository;
    }

    public List<Crews> getCrews(){
        return crewsRepository.getAll();
    }

}
