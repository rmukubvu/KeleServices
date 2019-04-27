package za.co.mabatalale.models;

/**
 * Created by robson on 2017/05/31.
 */

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "current_sessions")
public class CurrentSession {
    private Integer currentSessionId;
    private Integer operatorId;
    private Integer rigId;
    private Integer sessionDate;
    private String sessionKey;
    private Timestamp createdDate;

    @Id
    @Column(name = "current_sessions_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Integer getCurrentSessionId() {
        return currentSessionId;
    }

    public void setCurrentSessionId(Integer currentSessionId) {
        this.currentSessionId = currentSessionId;
    }

    @Basic
    @Column(name = "operator_id")
    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    @Basic
    @Column(name = "rig_id")
    public Integer getRigId() {
        return rigId;
    }

    public void setRigId(Integer rigId) {
        this.rigId = rigId;
    }

    @Basic
    @Column(name = "session_date")
    public Integer getSessionDate() {
        return sessionDate;
    }

    public void setSessionDate(Integer sessionDate) {
        this.sessionDate = sessionDate;
    }

    @Basic
    @Column(name = "session_key")
    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    @Basic
    @Column(name = "created_date")
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }
}
