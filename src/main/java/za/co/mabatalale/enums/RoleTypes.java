package za.co.mabatalale.enums;

/**
 * Created by robson on 2016/12/26.
 */
public enum RoleTypes {
    WEB(1),
    MOBILE(2),
    MOBILEANDWEB(3);

    private int id; // Could be other data type besides int
    RoleTypes(int id) {
        this.id = id;
    }

    public static RoleTypes fromId(int id) {
        for (RoleTypes type : RoleTypes.values()) {
            if (type.id == id) {
                return type;
            }
        }
        return null;
    }
}
