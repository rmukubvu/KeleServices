package za.co.mabatalale.entities;

import javax.persistence.*;

/**
 * Created by robson on 2017/02/27.
 */
@Entity
@Table(name = "look_up_keys", schema = "basil", catalog = "")
public class LookUpKeys {
    private int lookUpKeyId;
    private String lookUpText;

    @Id
    @Column(name = "look_up_key_id")
    public int getLookUpKeyId() {
        return lookUpKeyId;
    }

    public void setLookUpKeyId(int lookUpKeyId) {
        this.lookUpKeyId = lookUpKeyId;
    }

    @Basic
    @Column(name = "look_up_text")
    public String getLookUpText() {
        return lookUpText;
    }

    public void setLookUpText(String lookUpText) {
        this.lookUpText = lookUpText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LookUpKeys that = (LookUpKeys) o;

        if (lookUpKeyId != that.lookUpKeyId) return false;
        if (lookUpText != null ? !lookUpText.equals(that.lookUpText) : that.lookUpText != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = lookUpKeyId;
        result = 31 * result + (lookUpText != null ? lookUpText.hashCode() : 0);
        return result;
    }
}
