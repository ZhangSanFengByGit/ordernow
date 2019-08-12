package ordernow.domain;

import ordernow.domain.Pair;

import java.util.ArrayList;

public class Order {
    public long orderId;
    public String phoneNumber;
    public ArrayList<Pair<String,String>> orderList;

    public Order(){}

    public Order(long id, String pn, ArrayList<Pair<String,String>> orderList){
        this.orderId = id;
        this.phoneNumber = pn;
        this.orderList = orderList;
    }
}
