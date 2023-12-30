package com.order.OrderNotificationApp.repository;

import com.order.OrderNotificationApp.model.Order;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Repository
public class OrderRepository implements BaseRepository{
    private final Map<String, List<Order>> orders;

    public OrderRepository() {
        this.orders = new HashMap<>();
    }

    @Override
    public boolean add(Object o) {
        Order order = (Order)o;
        if(orders.containsKey(order.getAccount().getAccountNumber())){
            List<Order> myOrders = orders.get(order.getAccount().getAccountNumber());
            myOrders.add(order);
            return true;
        }
        List<Order> orderList = new ArrayList<>();
        orderList.add(order);
        orders.put(order.getAccount().getAccountNumber(),orderList );
        return true;
    }

    @Override
    public boolean remove(Object o) {
        String orderID = (String)o;
        for(Map.Entry<String,List<Order>> entry: orders.entrySet()){
            for(Order order :entry.getValue()){
                if(order.getOrderID().equals(orderID)){
                    entry.getValue().remove(order);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Object getByID(Object o) {
        String orderID = (String)o;
        for(Map.Entry<String,List<Order>> entry: orders.entrySet()){
            for(Order order :entry.getValue()){
                if(order.getOrderID().equals(orderID)){
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
}
