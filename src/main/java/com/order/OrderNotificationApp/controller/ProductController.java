package com.order.OrderNotificationApp.controller;

import com.order.OrderNotificationApp.model.Product;
import com.order.OrderNotificationApp.model.ProductCategory;
import com.order.OrderNotificationApp.service.ProductService;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @GetMapping("/categories/{category}")
    public ResponseEntity<Object> getAvailableItemsInCategory(@PathVariable String category){
        List<Product> products = productService.getAvailableItemsInCategory(category);
        if(products==null){
            return new ResponseEntity<>("no products in category!", HttpStatus.OK);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    @GetMapping("/serial/{sn}")
    public ResponseEntity<Object> listProductDetails(@PathVariable@Pattern(regexp = "\\d+", message = "Serial Number must be numeric") String sn){
        String details = productService.listProductDetails(sn);
        if(details == null){
            return new ResponseEntity<>("Product not found!", HttpStatus.OK);
        }
        return new ResponseEntity<>(details, HttpStatus.OK);
    }
    @GetMapping("all")
    public ResponseEntity<Object> listAvailableProducts(){
        Map<ProductCategory,List<Product>> available = productService.listAvailableProducts();
        if(available.isEmpty()){
            return new ResponseEntity<>("no products available!", HttpStatus.OK);
        }
        return new ResponseEntity<>(available, HttpStatus.OK);
    }
    @DeleteMapping ("/categories/remove/{category}")
    public ResponseEntity<Object> removeCategory(@PathVariable String category){
        boolean removed = productService.removeCategory(category);
        if(!removed){
            return new ResponseEntity<>("category not found!", HttpStatus.OK);
        }
        return new ResponseEntity<>("category removed!",HttpStatus.OK);
    }
    @DeleteMapping ("/remove/{sn}")
    public ResponseEntity<Object> removeProduct(@PathVariable @Pattern(regexp = "\\d+", message = "Serial Number must be numeric")String sn){
        boolean removed = productService.removeProduct(sn);
        if(!removed){
            return new ResponseEntity<>("product not found!", HttpStatus.OK);
        }
        return new ResponseEntity<>("product removed!",HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<Object> addProduct(@RequestBody Product product){
        boolean added = productService.addProduct(product);
        if(!added){
            return new ResponseEntity<>("product with this SN already in inventory!", HttpStatus.OK);
        }
        return new ResponseEntity<>("products added successfully!",HttpStatus.OK);
    }
}
