package za.co.mabatalale.entities;

import javax.persistence.*;

/**
 * Created by robson on 2017/03/01.
 */
@Entity
@Table(name = "crews")
public class Crews {

    private int crewId;
    private String crewName;

    @Id
    @Column(name = "crews_id")
    public int getCrewId() {
        return crewId;
    }

    public void setCrewId(int crewId) {
        this.crewId = crewId;
    }

    @Basic
    @Column(name = "crew_name")
    public String getCrewName() {
        return crewName;
    }

    public void setCrewName(String crewName) {
        this.crewName = crewName;
    }

}
