package za.co.mabatalale.entities;

import javax.persistence.*;

/**
 * Created by robson on 2017/02/27.
 */
@Entity
@Table(name = "production_type", schema = "basil", catalog = "")
public class ProductionType {
    private int productionTypeId;
    private String typeText;

    @Id
    @Column(name = "production_type_id")
    public int getProductionTypeId() {
        return productionTypeId;
    }

    public void setProductionTypeId(int productionTypeId) {
        this.productionTypeId = productionTypeId;
    }

    @Basic
    @Column(name = "type_text")
    public String getTypeText() {
        return typeText;
    }

    public void setTypeText(String typeText) {
        this.typeText = typeText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductionType that = (ProductionType) o;

        if (productionTypeId != that.productionTypeId) return false;
        if (typeText != null ? !typeText.equals(that.typeText) : that.typeText != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = productionTypeId;
        result = 31 * result + (typeText != null ? typeText.hashCode() : 0);
        return result;
    }
}
