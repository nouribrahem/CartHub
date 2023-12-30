package com.order.OrderNotificationApp.repository;

import com.order.OrderNotificationApp.model.Order;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Math.max;

@Repository
public class OrderRepository implements BaseRepository{
    private final Map<String, List<Order>> orders;

    public OrderRepository() {
        this.orders = new HashMap<>();
    }

    @Override
    public boolean add(Object o) {
        Map.Entry<String,Order> mp = (Map.Entry<String,Order>)o;
        if(orders.containsKey(mp.getKey())){
            List<Order> myOrders = orders.get(mp.getKey());
            myOrders.add(mp.getValue());
            return true;
        }
        List<Order> orderList = new ArrayList<>();
        orderList.add(mp.getValue());
        orders.put(mp.getKey(),orderList);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        for(Map.Entry<String,List<Order>> entry: orders.entrySet()){
            for(Order order :entry.getValue()){
                if(order.getOrderID() == ((int)o)){
                    entry.getValue().remove(order);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Object getByID(Object o) {
        for(Map.Entry<String,List<Order>> entry: orders.entrySet()){
            for(Order order :entry.getValue()){
                if(order.getOrderID() == (int) o){
                    return order;
                }
            }
        }
        return null;
    }

    @Override
    public Object getAll() {
        return orders;
    }
    public int getLastID(){
        int lastID = -1;
        for(Map.Entry<String,List<Order>> entry: orders.entrySet()){
            for(Order o :entry.getValue()){
                lastID = max(o.getOrderID(),lastID);
            }
        }
        return lastID;
    }
}
