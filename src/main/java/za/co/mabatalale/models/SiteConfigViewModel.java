package za.co.mabatalale.models;

/**
 * Created by robson on 2017/03/26.
 */
public class SiteConfigViewModel {
    private int siteConfigId;
    private String country;
    private String region;
    private String siteName;

    public int getSiteConfigId() {
        return siteConfigId;
    }

    public void setSiteConfigId(int siteConfigId) {
        this.siteConfigId = siteConfigId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

}
