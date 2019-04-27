package za.co.mabatalale.services;

import za.co.mabatalale.entities.OperationsUser;
import za.co.mabatalale.enums.OperationsTypes;
import za.co.mabatalale.repos.OperationsUserRepository;

import javax.inject.Inject;
import java.util.List;

import static za.co.mabatalale.enums.OperationsTypes.*;

/**
 * Created by robson on 2017/03/02.
 */
public class OperationsUsersServices {

    private final OperationsUserRepository operationsUserRepository;

    @Inject
    public OperationsUsersServices(OperationsUserRepository operationsUserRepository){
        this.operationsUserRepository = operationsUserRepository;
    }

    public List<OperationsUser> getAllUsers(){
        return operationsUserRepository.getAll();
    }

    public List<OperationsUser> getOperators(){
        return operationsUserRepository.findByRoleId(OPERATOR.getCode());
    }

    public List<OperationsUser> getAssistant(){
        return operationsUserRepository.findByRoleId(ASSISTANT.getCode());
    }

    public List<OperationsUser> getForeman(){
        return operationsUserRepository.findByRoleId(FOREMAN.getCode());
    }

    public List<OperationsUser> getMechanics(){
        return operationsUserRepository.findByRoleId(MECHANIC.getCode());
    }

    public List<OperationsUser> getSupervisor(){
        return operationsUserRepository.findByRoleId(SUPERVISOR.getCode());
    }

    public OperationsUser getUserById(int id){
        return operationsUserRepository.findByOperationsId(id);
    }

}
