package za.co.mabatalale.services;

import za.co.mabatalale.entities.Shifts;
import za.co.mabatalale.repos.ShiftRepository;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by robson on 2017/02/28.
 */
public class ShiftService {

    private final ShiftRepository shiftRepository;

    @Inject
    public ShiftService(ShiftRepository shiftRepository){
        this.shiftRepository = shiftRepository;
    }

    public List<Shifts> getShifts(){
       return shiftRepository.getAll();
    }
}
