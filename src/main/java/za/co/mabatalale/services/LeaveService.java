package za.co.mabatalale.services;

import za.co.mabatalale.entities.BasilUsersLeave;
import za.co.mabatalale.entities.LeaveReason;
import za.co.mabatalale.models.LeaveViewModel;
import za.co.mabatalale.repos.LeaveReasonRepository;
import za.co.mabatalale.repos.OperationsUserRepository;
import za.co.mabatalale.repos.UserLeaveRepository;
import za.co.mabatalale.utils.DateUtil;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by robson on 2017/03/02.
 */
public class LeaveService {

    private final LeaveReasonRepository leaveReasonRepository;
    private UserLeaveRepository userLeaveRepository;
    private OperationsUserRepository operationsUserRepository;

    @Inject
    public LeaveService(LeaveReasonRepository leaveReasonRepository, UserLeaveRepository userLeaveRepository){
        this.leaveReasonRepository = leaveReasonRepository;
        this.userLeaveRepository = userLeaveRepository;
        this.operationsUserRepository = new OperationsUserRepository();
    }

    public List<LeaveReason> getLeaveReason(){
        return leaveReasonRepository.getAll();
    }

    public int saveLeave(BasilUsersLeave model){
        return userLeaveRepository.save(model);
    }

    public List<LeaveViewModel> getUserLeaveByDay(int day){
        return transposeData(userLeaveRepository.findByDateInt(day));
    }

    public List<LeaveViewModel> getUserLeaveByBetweenDays(int day1,int day2) throws Exception {
        if (!DateUtil.checkDatesBetween(day1,day2))
            throw new Exception("End date cannot be less than start date");
        return transposeData(userLeaveRepository.findByDateIntBetween(day1,day2));
    }

    public void deleteLeave(BasilUsersLeave model){
        userLeaveRepository.deleteRecord(model);
    }

    private List<LeaveViewModel> transposeData(List<BasilUsersLeave> list){
        List<LeaveViewModel> viewModels = new ArrayList<>();
        for (BasilUsersLeave model: list
             ) {
            LeaveViewModel viewModel = new LeaveViewModel();
            viewModel.setBasilUsersLeaveId(model.getBasilUsersLeaveId());
            viewModel.setDateInt(model.getDateInt());
            LeaveReason leaveReason = leaveReasonRepository.findByLeaveReasonId(model.getLeaveTypeId());
            if (leaveReason != null)
                viewModel.setReason(leaveReason.getReason());
            viewModel.setUser(operationsUserRepository.findByOperationsId(model.getUserId()).toString());
            viewModel.setStartDate(DateUtil.getDateFromTimeStamp(model.getStartDate().getTime()));
            viewModel.setEndDate(DateUtil.getDateFromTimeStamp(model.getEndDate().getTime()));
            viewModel.setCreatedDate(DateUtil.getDateFromTimeStamp(model.getCreatedDate().getTime()));

            viewModels.add(viewModel);
        }
        return viewModels;
    }

}
