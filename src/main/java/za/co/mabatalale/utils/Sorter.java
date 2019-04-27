package za.co.mabatalale.utils;

import java.util.*;

/**
 * Created by robson on 2017/03/04.
 */
public class Sorter<T,K> {

    private HashMap<T, K> hmap = new HashMap<>();

    public void add(T key,K value){
        hmap.put(key, value);
    }

    public Map<T, K> sort(){
        return new TreeMap<>(hmap);
        /*Set set2 = map.entrySet();
        Iterator iterator2 = set2.iterator();
        while(iterator2.hasNext()) {
            Map.Entry me2 = (Map.Entry)iterator2.next();
            System.out.print(me2.getKey() + ": ");
            System.out.println(me2.getValue());
        }*/
    }
}
