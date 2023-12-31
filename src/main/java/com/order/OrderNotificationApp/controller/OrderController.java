package com.order.OrderNotificationApp.controller;

import com.order.OrderNotificationApp.Requests.CompoundOrderMyRequest;
import com.order.OrderNotificationApp.Requests.SimpleOrderMyRequest;
import com.order.OrderNotificationApp.model.Order;
import com.order.OrderNotificationApp.service.OrderService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Object> placeSimpleOrder(@RequestBody SimpleOrderMyRequest orderMyRequest){
        Map.Entry<List<String>, Order> order = orderService.placeSimpleOrder(orderMyRequest);
        if(order != null){
            String redirect="/notifications/"+orderMyRequest.getUsername()+"/"+order.getValue().getOrderID();
            return ResponseEntity.status(302).header("Location", redirect).body("redirecting");
        }
        return new ResponseEntity<>(order.getKey(), HttpStatusCode.valueOf(200));
    }
    @PostMapping("/place/compound")
    public ResponseEntity<Object> placeCompoundOrder(@RequestBody CompoundOrderMyRequest orderMyRequest){
        Map.Entry<List<List<String>>, Order> order = orderService.placeCompoundOrder(orderMyRequest);
        if(order != null){
            String redirect="/notifications/compound/"+orderMyRequest.getUsername()+"/"+order.getValue().getOrderID();
            return ResponseEntity.status(302).header("Location", redirect).body("redirecting");
        }
        return new ResponseEntity<>(order.getKey(), HttpStatusCode.valueOf(200));
    }

    @PostMapping("/ship/simple")
    public List<String> shipSimpleOrder(@RequestBody Map.Entry<String ,Integer> shipRequest){
        List<String>order = orderService.shipSimpleOrder(shipRequest);

    }
    @PostMapping("/ship/compound")
    public List<String> shipCompoundOrder(@RequestBody Map.Entry<String ,Integer> shipRequest){
        return orderService.shipCompoundOrder(shipRequest);
    }
    @GetMapping("/cancel-ship/simple/{orderId}")
    public Map.Entry<List<String>, Boolean> cancelOrderShippingSimple(@PathVariable int orderId){
        return orderService.cancelOrderShippingSimple(orderId);
    }
    @GetMapping("/cancel-ship/compound/{orderId}")
    public Map.Entry<List<String>, Boolean> cancelOrderShippingCompound(@PathVariable int orderId){
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
