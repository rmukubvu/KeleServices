package za.co.mabatalale.enums;

/**
 * Created by robson on 2016/12/23.
 */
public enum MysqlTypes {
    INTEGER("integer"),
    DATETTIME("datetime"),
    STRING("string");

    private String value;

    private MysqlTypes(String value) {
        this.value = value;
    }

    public MysqlTypes getValue(){
        return MysqlTypes.valueOf(this.value);
    }
}
