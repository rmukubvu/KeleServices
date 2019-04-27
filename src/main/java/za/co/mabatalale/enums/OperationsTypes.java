package za.co.mabatalale.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by robson on 2017/03/02.
 */
public enum OperationsTypes {
    OPERATOR(1),
    MECHANIC(2),
    SUPERVISOR(3),
    FOREMAN(4),
    MANAGER(5),
    ASSISTANT(6);

    private static final Map<Integer,OperationsTypes> lookup
            = new HashMap<>();
    private int id; // Could be other data type besides int

    static {
        for(OperationsTypes w : EnumSet.allOf(OperationsTypes.class))
            lookup.put(w.getCode(), w);
    }

    OperationsTypes(int id) {
        this.id = id;
    }

    public static OperationsTypes fromId(int id) {
        for (OperationsTypes type : OperationsTypes.values()) {
            if (type.id == id) {
                return type;
            }
        }
        return null;
    }

    public int getCode() { return id; }
}

