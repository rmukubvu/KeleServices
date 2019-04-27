package za.co.mabatalale.entities;

import javax.persistence.*;

/**
 * Created by robson on 2017/02/27.
 */
@Entity
@Table(name = "work_items_desc", schema = "basil", catalog = "")
public class WorkItemsDesc {
    private int workItemsDescId;
    private String workItem;

    @Id
    @Column(name = "work_items_desc_id")
    public int getWorkItemsDescId() {
        return workItemsDescId;
    }

    public void setWorkItemsDescId(int workItemsDescId) {
        this.workItemsDescId = workItemsDescId;
    }

    @Basic
    @Column(name = "work_item")
    public String getWorkItem() {
        return workItem;
    }

    public void setWorkItem(String workItem) {
        this.workItem = workItem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WorkItemsDesc that = (WorkItemsDesc) o;

        if (workItemsDescId != that.workItemsDescId) return false;
        if (workItem != null ? !workItem.equals(that.workItem) : that.workItem != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = workItemsDescId;
        result = 31 * result + (workItem != null ? workItem.hashCode() : 0);
        return result;
    }
}
