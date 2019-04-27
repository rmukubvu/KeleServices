package za.co.mabatalale.entities;

import javax.persistence.*;

/**
 * Created by robson on 2017/02/27.
 */
@Entity
@Table(name = "site_config", schema = "basil", catalog = "")
public class SiteConfig {
    private int siteConfigId;
    private Integer countryId;
    private Integer regionId;
    private String siteName;

    @Id
    @Column(name = "site_config_id")
    public int getSiteConfigId() {
        return siteConfigId;
    }

    public void setSiteConfigId(int siteConfigId) {
        this.siteConfigId = siteConfigId;
    }

    @Basic
    @Column(name = "country_id")
    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    @Basic
    @Column(name = "region_id")
    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    @Basic
    @Column(name = "site_name")
    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SiteConfig that = (SiteConfig) o;

        if (siteConfigId != that.siteConfigId) return false;
        if (countryId != null ? !countryId.equals(that.countryId) : that.countryId != null) return false;
        if (regionId != null ? !regionId.equals(that.regionId) : that.regionId != null) return false;
        if (siteName != null ? !siteName.equals(that.siteName) : that.siteName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = siteConfigId;
        result = 31 * result + (countryId != null ? countryId.hashCode() : 0);
        result = 31 * result + (regionId != null ? regionId.hashCode() : 0);
        result = 31 * result + (siteName != null ? siteName.hashCode() : 0);
        return result;
    }
}
