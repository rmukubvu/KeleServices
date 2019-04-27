package za.co.mabatalale.models;

import javax.persistence.*;

/**
 * Created by robson on 2017/03/02.
 */
@Entity
@Table(name ="foreman_header_report")
public class ForemanReportHeader {

    private Integer headerId;
    private String shift;
    private String crew;
    private String foreMan;
    private int rigId;
    private int dateInt;

    @Id
    @Column(name = "foreman_header_report_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Integer getHeaderId() {
        return headerId;
    }

    public void setHeaderId(Integer headerId) {
        this.headerId = headerId;
    }

    @Basic
    @Column(name = "shift")
    public String getShift() {
    return shift;
}

    public void setShift(String shift) {
        this.shift = shift;
    }

    @Basic
    @Column(name = "crew")
    public String getCrew() {
        return crew;
    }

    public void setCrew(String crew) {
        this.crew = crew;
    }

    @Basic
    @Column(name = "foreman")
    public String getForeMan() {
        return foreMan;
    }

    public void setForeMan(String foreMan) {
        this.foreMan = foreMan;
    }

    @Basic
    @Column(name = "date_int")
    public int getDateInt() {
        return dateInt;
    }

    public void setDateInt(int dateInt) {
        this.dateInt = dateInt;
    }

    @Basic
    @Column(name = "rig_id")
    public int getRigId() {
        return rigId;
    }

    public void setRigId(int rigId) {
        this.rigId = rigId;
    }
}
