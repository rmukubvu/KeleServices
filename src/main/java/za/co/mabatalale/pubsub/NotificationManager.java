package za.co.mabatalale.pubsub;

import redis.clients.jedis.Jedis;

/**
 * Created by robson on 2016/12/17.
 */
public class NotificationManager {

    private Jedis jedis;
    private static final String CHANNEL_REDIS = "basilsub";

    public NotificationManager(){
        jedis = new Jedis("localhost");
    }

    public void sendMessage(String message){
        jedis.publish(CHANNEL_REDIS,message);
    }
}
