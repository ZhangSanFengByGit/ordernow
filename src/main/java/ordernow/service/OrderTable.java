package ordernow.service;

import ordernow.domain.Pair;
import ordernow.domain.Order;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class OrderTable {
    private HashMap<Long, Order> orderMap;
    private HashMap<Long, Order> finishMap;
    private long idForUse;

    @PostConstruct
    public void init(){
        orderMap = new HashMap<>();
        finishMap = new HashMap<>();
        idForUse = 1L;
    }

    public long offer(String phoneNumber, ArrayList<Pair<String,String>> orderList){
        Order cur = new Order(idForUse++, phoneNumber, orderList);
        orderMap.put(cur.orderId, cur);
        return idForUse-1;
    }

    public boolean finish(long id){
        Order finished = orderMap.getOrDefault(id, null);
        if (finished==null) return false;
        finishMap.put(finished.orderId, finished);
        orderMap.remove(id);
        return true;
    }

    public boolean pickUp(Long id){
        Order finished = finishMap.getOrDefault(id, null);
        if (finished==null) return false;
        finishMap.remove(id);
        return true;
    }

    public Collection<Order> getFinishedKeys(){
        return finishMap.values();
    }

    public Collection<Order> getUnfinishedOrders(){
        return orderMap.values();
    }
}
