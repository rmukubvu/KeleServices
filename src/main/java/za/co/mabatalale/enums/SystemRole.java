package za.co.mabatalale.enums;

/**
 * Created by robson on 2016/12/26.
 */
public enum SystemRole {
    SUPERVISIOR(1),
    ASSISTANT(2),
    OPERATOR(3),
    ADMINISTRATOR(4),
    MANAGER(5);

    private int id; // Could be other data type besides int
    SystemRole(int id) {
        this.id = id;
    }

    public static SystemRole fromId(int id) {
        for (SystemRole type : SystemRole.values()) {
            if (type.id == id) {
                return type;
            }
        }
        return null;
    }
}
