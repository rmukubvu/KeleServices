package za.co.mabatalale.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import spark.ResponseTransformer;

/**
 * Created by robson on 2016/12/16.
 */
public class JsonUtil {
    private static ObjectMapper mapper = new ObjectMapper();

    public static String toJson(Object object){
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ResponseTransformer json() {
        return JsonUtil::toJson;
    }
}
