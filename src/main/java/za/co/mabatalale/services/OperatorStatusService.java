package za.co.mabatalale.services;

import za.co.mabatalale.entities.OperationsUser;
import za.co.mabatalale.entities.OperatorStatus;
import za.co.mabatalale.models.UserStatus;
import za.co.mabatalale.repos.OperationsUserRepository;
import za.co.mabatalale.repos.OperatorStatusRepository;
import za.co.mabatalale.utils.DateUtil;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created by robson on 2017/05/06.
 */
public class OperatorStatusService {

    private final OperatorStatusRepository operatorStatusRepository;
    private OperationsUsersServices operationsUsersServices;

    @Inject
    public OperatorStatusService(OperatorStatusRepository operatorStatusRepository){
        this.operatorStatusRepository = operatorStatusRepository;
        this.operationsUsersServices = new OperationsUsersServices(new OperationsUserRepository());
    }

    public List<UserStatus> getUserStatus(){
        List<UserStatus> formattedUserStatus = new ArrayList<>();
        List<OperatorStatus> allStatus = operatorStatusRepository.getAllOrderByDate();
        for (OperatorStatus status :
             allStatus ) {
            UserStatus userStatus = new UserStatus();
            long diff = DateUtil.getCurrentTimeStamp().getTime() - status.getStatusDate().getTime();
            String difference = DateUtil.getFormattedTime(diff);
            userStatus.setCurrentStatus(String.format("%s - %s",status.getActualStatus(),difference));
            userStatus.setStatusDate(DateUtil.getDateTimeFromTimeStamp(status.getStatusDate().getTime()));
            userStatus.setUser(getOperatorNameById(status.getOperatorId()));
            userStatus.setStatusColor(status.getPlantNumber());
            formattedUserStatus.add(userStatus);
        }
        return formattedUserStatus;
    }

    public void saveStatus(int userId,String color,String status){
        OperatorStatus model = getOperatorStatus(userId);
        if ( model != null ){
            operatorStatusRepository.updateStatus(userId,color,status);
        }else {
            OperatorStatus operatorStatus = new OperatorStatus();
            operatorStatus.setActualStatus(status);
            operatorStatus.setLookUpId(1);
            operatorStatus.setOperatorId(userId);
            operatorStatus.setPlantNumber(color);
            operatorStatus.setDateInt(DateUtil.getCurrentDay());
            operatorStatus.setStatusDate(DateUtil.getCurrentTimeStamp());
            operatorStatusRepository.save(operatorStatus);
        }
    }

    private OperatorStatus getOperatorStatus(int userId){
       return operatorStatusRepository.findByOperatorId(userId);
    }

    private String getOperatorNameById(int id){
        List<OperationsUser> operationsUserList = operationsUsersServices.getAllUsers();
        Optional<OperationsUser> record = operationsUserList. stream()
                .filter(p-> p.getOperationsId() == id)
                .findFirst();
        if (record.isPresent()){
            OperationsUser operationsUser = record.get();
            return String.format("%s %s",operationsUser.getFirstName(),operationsUser.getLastName());
        }
        return "";
    }
}
