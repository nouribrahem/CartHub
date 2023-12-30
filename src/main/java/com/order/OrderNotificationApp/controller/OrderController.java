package com.order.OrderNotificationApp.controller;

import com.order.OrderNotificationApp.Requests.CompoundOrderRequest;
import com.order.OrderNotificationApp.Requests.SimpleOrderRequest;
import com.order.OrderNotificationApp.model.Order;
import com.order.OrderNotificationApp.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @GetMapping("{orderId}")
    public String listOrderDetails(@PathVariable int orderId){
        return orderService.getOrderDetails(orderId);
    }

    @PostMapping("/place/simple")
    public Map.Entry<List<String>, Order> placeSimpleOrder(@RequestBody SimpleOrderRequest orderRequest){
       return orderService.placeSimpleOrder(orderRequest);
    }
    @PostMapping("/place/compound")
    public List<List<String>> placeCompoundOrder(@RequestBody CompoundOrderRequest orderRequest){
       return orderService.placeCompoundOrder(orderRequest);
    }

    @PostMapping("/ship/simple")
    public List<String> shipSimpleOrder(@RequestBody Map.Entry<String ,Integer> shipRequest){
        return orderService.shipSimpleOrder(shipRequest);
    }
    @PostMapping("/ship/compound")
    public List<String> shipCompoundOrder(@RequestBody Map.Entry<String ,Integer> shipRequest){
        return orderService.shipCompoundOrder(shipRequest);
    }
    @GetMapping("/cancel/simple/{orderId}")
    public List<String> cancelOrderShippingSimple(@PathVariable int orderId){
        return orderService.cancelOrderShippingSimple(orderId);
    }
    @GetMapping("/cancel/compound/{orderId}")
    public List<String> cancelOrderShippingCompound(@PathVariable int orderId){
        return orderService.cancelOrderShippingCompound(orderId);
    }

    @GetMapping("/cancel/simple/{orderId}")
    public List<String> cancelOrderPlacementSimple(@PathVariable int orderId){
        return orderService.cancelOrderPlacementSimple(orderId);
    }
    @GetMapping("/cancel/compound/{orderId}")
    public List<String> cancelOrderPlacementCompound(@PathVariable int orderId){
        return orderService.cancelOrderPlacementCompound(orderId);
    }

}
