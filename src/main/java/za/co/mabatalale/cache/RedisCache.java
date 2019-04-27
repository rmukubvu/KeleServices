package za.co.mabatalale.cache;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by robson on 2017/03/04.
 */
public class RedisCache {

    private Jedis _jedis;
    private static final String CHANNEL_REDIS = "basilsub";
    private ObjectMapper mapper = new ObjectMapper();

    public RedisCache(){
        _jedis = new Jedis("localhost");
    }

    public <T> void setValue(String key,T value){
        try {
            _jedis.set(key,mapper.writeValueAsString(value));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public <T> void refreshValue(String key,T value){
        if ( _jedis.exists(key) )
            deleteRecord(key);
        setValue(key,value);
    }

    private String get(String key) {
       if ( !_jedis.exists(key) ) return null;
        return _jedis.get(key);
    }

    public <T> T getItem(String key,Class<T> responseClass){
        String jsonArray = get(key);
        try {
            return mapper.readValue(jsonArray, responseClass);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public <T> T[] getItems(String key,Class<T[]> responseClass){
        String jsonArray = get(key);
        try {
            return mapper.readValue(jsonArray, responseClass);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public <T> List<T> getItemsAsList(String key, Class<T[]> responseClass){
        String jsonArray = get(key);
        try {
            T[] array = mapper.readValue(jsonArray, responseClass);
            return Arrays.asList(array);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void deleteRecord(String key) {
        _jedis.del(key);
    }

    public void sendMessage(String message){
        _jedis.publish(CHANNEL_REDIS,message);
    }

    public void dispose() {
       if (_jedis != null)
           _jedis.close();
    }
}
