package com.order.OrderNotificationApp.service;

import com.order.OrderNotificationApp.Requests.CompoundOrderRequest;
import com.order.OrderNotificationApp.Requests.SimpleOrderRequest;
import com.order.OrderNotificationApp.model.*;
import com.order.OrderNotificationApp.repository.Inventory;
import com.order.OrderNotificationApp.repository.OrderRepository;
import com.order.OrderNotificationApp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final Inventory inventory;
    private final UserRepository userRepository;
    public List<String> outputMessages;
    public OrderService(OrderRepository orderRepository,Inventory inventory,UserRepository userRepository){
        this.orderRepository = orderRepository;
        this.inventory = inventory;
        this.userRepository = userRepository;
        outputMessages = new ArrayList<>();
    }

    public Order createSimpleOrder(SimpleOrderRequest orderRequest){
        List<Product> orderProducts = new ArrayList<>();

        Order simpleOrder = new SimpleOrder();
        ((SimpleOrder) simpleOrder).setLocation(orderRequest.getLocation());

        double totalPrice = 0.0;
        for(Map.Entry<String,Integer> entry:orderRequest.getProductDetails()){
            Product tmp = (inventory.getProductByName(entry.getKey()));
            if(tmp != null && entry.getValue() <= tmp.getCount()){
                Product p = new Product(tmp);
                p.setCount(entry.getValue());
                orderProducts.add(p);
                totalPrice += (p.getPrice() * entry.getValue());
                outputMessages.add(entry.getValue() + " of " + p.getName() + " is(are) added successfully!");
            }else{
                outputMessages.add(entry.getKey() + " is not available!");
            }
        }
        if(orderProducts.isEmpty()){
            return null;
        }
        ((SimpleOrder) simpleOrder).setPrice(totalPrice);
        ((SimpleOrder) simpleOrder).setProductList(orderProducts);
        return simpleOrder;
    }
    public Map.Entry<List<String>,Order> placeSimpleOrder(SimpleOrderRequest orderRequest){
        outputMessages = new ArrayList<>();
        User user = (User) userRepository.getByID(orderRequest.getUsername());
        if(user == null){
            outputMessages.add("Invalid user please sign up!");
            return new AbstractMap.SimpleEntry<>(outputMessages, null);
        }
        Order myorder = (SimpleOrder)createSimpleOrder(orderRequest);
        if(myorder == null){
            outputMessages.add("All selected items are out of Stock!");
            return new AbstractMap.SimpleEntry<>(outputMessages, null);
        }
        if( ((SimpleOrder) myorder).getPrice() + (myorder).getShippingFee() > user.getAccount().getBalance()){
            outputMessages.add("No sufficient balance!");
            return new AbstractMap.SimpleEntry<>(outputMessages, myorder);
        }
        myorder.setAccount(user.getAccount());
        updateProductCount(orderRequest.getProductDetails());
        user.getAccount().setBalance((user.getAccount().getBalance())-((SimpleOrder) myorder).getPrice());
        ((SimpleOrder) myorder).setCreatedAt(LocalDateTime.now());
        ((SimpleOrder) myorder).setOrderID(orderRepository.getLastID());

        Map.Entry<String, Order> entry = new AbstractMap.SimpleEntry<>(orderRequest.getUsername(), myorder);

        orderRepository.add(entry);
        return new AbstractMap.SimpleEntry<>(outputMessages, myorder);
    }
    public Boolean updateProductCount(List<Map.Entry<String, Integer>> products ){
        for(Map.Entry<String, Integer> product:products){
           if(!inventory.updateProductCount(product.getKey(),-product.getValue())){
               return false;
           }
        }
        return true;
    }
    public String getOrderDetails(int orderId){
        Order order = (Order) orderRepository.getByID(orderId);
        if(order == null){
            return null;
        }
        return order.listOrderDetails();
    }

    public List<List<String>> placeCompoundOrder(CompoundOrderRequest orderRequest) {
        List<List<String>> compoundMessages = new ArrayList<>();
        User user = (User) userRepository.getByID(orderRequest.getUsername());
        Order myorder = new CompoundOrder();
        for(SimpleOrderRequest simpleOrderRequest :orderRequest.getSimpleOrderRequest()){
            Map.Entry<List<String>, Order> entry2 = placeSimpleOrder(simpleOrderRequest);
            compoundMessages.add(entry2.getKey());
            ((CompoundOrder) myorder).addOrder(entry2.getValue());
        }
        myorder.setAccount(user.getAccount());
        myorder.setCreatedAt(LocalDateTime.now());
        ((CompoundOrder) myorder).setOrderID(orderRepository.getLastID());
        Map.Entry<String, Order> entry = new AbstractMap.SimpleEntry<>(orderRequest.getUsername(), myorder);
        orderRepository.add(entry);
        return compoundMessages;
    }

    public List<String> shipSimpleOrder(Map.Entry<String, Integer> shipRequest){
        outputMessages = new ArrayList<>();
        User user = (User) userRepository.getByID(shipRequest.getKey());
        Order order = (Order) orderRepository.getByID(shipRequest.getValue());
        if(order == null){
            outputMessages.add("Order not found!");
            return outputMessages;
        }
        user.getAccount().setBalance((user.getAccount().getBalance())-((SimpleOrder) order).getShippingFee());
        order.setShippedAt(LocalDateTime.now());
        order.setShipped(true);
        outputMessages.add("Your order was shipped successfully at " + LocalDateTime.now());
        return outputMessages;
    }

    public List<String> shipCompoundOrder(Map.Entry<String, Integer> shipRequest) {
        outputMessages = new ArrayList<>();
        User user = (User) userRepository.getByID(shipRequest.getKey());
        Order order = (Order) orderRepository.getByID(shipRequest.getValue());
        if(order == null){
            outputMessages.add("Order not found!");
            return outputMessages;
        }
        double shippingPerUser = order.getShippingFee() / ((CompoundOrder) order).getOrders().size();
        for(Order o:((CompoundOrder) order).getOrders()){
            o.getAccount().setBalance(o.getAccount().getBalance()-shippingPerUser);
            o.setShippingFee(shippingPerUser);
        }
        order.setShippedAt(LocalDateTime.now());
        order.setShipped(true);
        outputMessages.add("Your order was shipped successfully at " + LocalDateTime.now());
        return outputMessages;
    }
    public Map.Entry<List<String>, Boolean>  cancelOrderShippingSimple(int orderId){
        outputMessages = new ArrayList<>();
        Order order = (Order) orderRepository.getByID(orderId);
        if(order == null){
            outputMessages.add("Order not found!");
            return new AbstractMap.SimpleEntry<>(outputMessages, false);
        }
        Duration duration = Duration.between(order.getShippedAt(), LocalDateTime.now());
        if(duration.toSeconds() >= 60){
            outputMessages.add("Could not cancel order duration exceeded (Max 60 Seconds!)");
            return new AbstractMap.SimpleEntry<>(outputMessages, false);
        }
        order.getAccount().setBalance(order.getAccount().getBalance()+order.getShippingFee());
        outputMessages.add("Shipping is canceled successfully");
        return new AbstractMap.SimpleEntry<>(outputMessages, true);

    }
    public Map.Entry<List<String>, Boolean>cancelOrderShippingCompound(int orderId){
        outputMessages = new ArrayList<>();
        Order order = (Order) orderRepository.getByID(orderId);
        if(order == null){
            outputMessages.add("Order not found!");
            return new AbstractMap.SimpleEntry<>(outputMessages, false);
        }
        Duration duration = Duration.between(order.shippedAt, LocalDateTime.now());
        if(duration.toSeconds() >= 60){
            outputMessages.add("Could not cancel order duration exceeded (Max 60 Seconds!)");
            return new AbstractMap.SimpleEntry<>(outputMessages, false);
        }
        for(Order o:((CompoundOrder) order).getOrders()){
            o.getAccount().setBalance(o.getAccount().getBalance()+o.getShippingFee());
        }
        outputMessages.add("Shipping is canceled successfully");
        return new AbstractMap.SimpleEntry<>(outputMessages, true);
    }
    public List<String> cancelOrderPlacementSimple(int orderId) {
        outputMessages = new ArrayList<>();
        Order order = (Order) orderRepository.getByID(orderId);
        if(order.isShipped()){
            outputMessages.add("Your order is already shipped! Cancel shipping first");
            return outputMessages;

        }
        Duration duration = Duration.between(order.createdAt, LocalDateTime.now());
        if(duration.toSeconds() >= 120){
            outputMessages.add("Could not cancel order duration exceeded (Max 120 Seconds!)");
            return outputMessages;
        }
        for(Product p:((SimpleOrder) order).getProductList()){
            inventory.updateProductCount(p.getName(),p.getCount());
        }
        order.getAccount().setBalance(order.getAccount().getBalance()+((SimpleOrder) order).getPrice());
        orderRepository.remove(orderId);
        outputMessages.add("Cancellation successful");
        return outputMessages;
    }
    public List<String> cancelOrderPlacementCompound(int orderId) {
        outputMessages = new ArrayList<>();
        Order order = (Order) orderRepository.getByID(orderId);
        if(order.isShipped()){
            outputMessages.add("Could not cancel order placement! Your order is already shipped! Cancel shipping first");
            return outputMessages;
        }
        Duration duration = Duration.between(order.getCreatedAt(), LocalDateTime.now());
        if(duration.toSeconds() >= 120){
            outputMessages.add("Could not cancel order duration exceeded (Max 120 Seconds!)");
            return outputMessages;
        }
        for(Order p:((CompoundOrder) order).getOrders()){
            cancelOrderPlacementSimple(p.getOrderID());
        }
        orderRepository.remove(orderId);
        outputMessages.add("Cancellation successful");
        return outputMessages;
    }
}