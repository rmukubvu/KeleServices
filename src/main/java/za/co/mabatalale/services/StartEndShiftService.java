package za.co.mabatalale.services;

import za.co.mabatalale.entities.EndOfShift;
import za.co.mabatalale.entities.OperatorSignIn;
import za.co.mabatalale.models.CurrentSession;
import za.co.mabatalale.repos.*;
import za.co.mabatalale.utils.DateUtil;
import za.co.mabatalale.utils.GraphsUtil;

import javax.inject.Inject;
import java.util.List;
import java.util.UUID;

/**
 * Created by robson on 2017/03/02.
 */
public class StartEndShiftService {

    private final EndOfShiftRepository endOfShiftRepository;
    private final ShiftStartRepository shiftStartRepository;
    private final CurrentSessionsRepository currentSessionsRepository;
    private OperatorStatusService operatorStatusService;

    @Inject
    public StartEndShiftService(EndOfShiftRepository endOfShiftRepository,
                                ShiftStartRepository shiftStartRepository,
                                CurrentSessionsRepository currentSessionsRepository){

        this.endOfShiftRepository = endOfShiftRepository;
        this.shiftStartRepository = shiftStartRepository;
        this.currentSessionsRepository= currentSessionsRepository;
        operatorStatusService = new OperatorStatusService(new OperatorStatusRepository());
    }

    public int saveEndOfShift(EndOfShift model){
        operatorStatusService.saveStatus(model.getOperatorId(), GraphsUtil.SHIFTS_COLOR_GREEN,"Shift Ended");
        String sessionKey = getSessionKey(model.getOperatorId(),model.getRigId());
        model.setSessionKey(sessionKey);
        return endOfShiftRepository.save(model);
    }


    public List<EndOfShift> getEndOfShiftPerRigPerDate(int date){
        return endOfShiftRepository.findByDateInt(date);
    }

    public int saveStartOfShift(OperatorSignIn model){
        operatorStatusService.saveStatus(model.getOperatorId(),GraphsUtil.SHIFTS_COLOR_GREEN,"Shift Started");
        createSessionStart(model.getOperatorId(),model.getRigId());
        return shiftStartRepository.save(model);
    }

    public List<OperatorSignIn> getStartOfShiftPerRigPerDate(int date){
        return shiftStartRepository.findByDateInt(date);
    }

    public EndOfShift getEndOfShiftForOperator(int operator,int rigId,int currentDate){
        String sessionKey = null;
        List<CurrentSession> openSessions = currentSessionsRepository.findBySessionDate(currentDate);
        for (CurrentSession session : openSessions
                ) {
            if (session.getRigId() == rigId && session.getOperatorId() == operator) {
                sessionKey = session.getSessionKey();
                break;
            }
        }

        if (sessionKey != null){
            return endOfShiftRepository.findBySessionKey(sessionKey);
        }
        return null;
    }

    private void createSessionStart(int operatorId,int rig){
        CurrentSession currentSession = new CurrentSession();
        String sessionKey = UUID.randomUUID().toString();
        currentSession.setCreatedDate(DateUtil.getCurrentTimeStamp());
        currentSession.setSessionDate(DateUtil.getCurrentDay());
        currentSession.setOperatorId(operatorId);
        currentSession.setRigId(rig);
        currentSession.setSessionKey(sessionKey);
        currentSessionsRepository.save(currentSession);
    }

    private String getSessionKey(int operator,int rigId){
        int currentDate = DateUtil.getCurrentDay();
        String sessionKey = null;
        int i = 1;
        while (sessionKey == null && i < 3) {
            List<CurrentSession> openSessions = currentSessionsRepository.findBySessionDate(currentDate);
            for (CurrentSession session : openSessions
                    ) {
                if (session.getRigId() == rigId && session.getOperatorId() == operator) {
                    sessionKey = session.getSessionKey();
                    break;
                }
            }
            currentDate = DateUtil.getPreviousDays(i);
            i++;
        }
        return sessionKey;
    }


}
