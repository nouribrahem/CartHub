package com.order.OrderNotificationApp.controller;

import com.order.OrderNotificationApp.model.Order;
import com.order.OrderNotificationApp.model.SimpleOrder;
import com.order.OrderNotificationApp.service.OrderService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @GetMapping("{orderId}")
    public String listOrderDetails(@PathVariable String orderId){
        return orderService.getOrderDetails(orderId);
    }

    @PostMapping("/add")
    public String placeOrder(@RequestBody SimpleOrder order){
        if(orderService.addOrder(order)){
            return "Order is placed";
        }
        return "Could not create order!";
    }

}
