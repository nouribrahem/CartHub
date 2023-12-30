package com.order.OrderNotificationApp.service;

import com.order.OrderNotificationApp.model.Order;
import com.order.OrderNotificationApp.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    public OrderService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    public boolean addOrder(Order order){
        return orderRepository.add(order);
    }
    public String getOrderDetails(String orderId){
        Order order = (Order) orderRepository.getByID(orderId);
        if(order == null){
            return null;
        }
        return order.listOrderDetails();
    }
    public Boolean cancelOrder(String orderId){
        return orderRepository.remove(orderId);
    }

}
