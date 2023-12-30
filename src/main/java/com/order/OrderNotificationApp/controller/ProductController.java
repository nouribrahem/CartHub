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
        if(products.isEmpty()){
            return new ResponseEntity<>("no products in category!", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    @GetMapping("/serial/{sn}")
    public ResponseEntity<Object> listProductDetails(@PathVariable@Pattern(regexp = "\\d+", message = "Serial Number must be numeric") String sn){
        String details = productService.listProductDetails(sn);
        if(details == null){
            return new ResponseEntity<>("Product not found!", HttpStatus.NO_CONTENT);
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
        return new ResponseEntity<>("category removed!",HttpStatus.ACCEPTED);
    }
    @DeleteMapping ("/remove/{sn}")
    public ResponseEntity<Object> removeProduct(@PathVariable @Pattern(regexp = "\\d+", message = "Serial Number must be numeric")String sn){
        boolean removed = productService.removeProduct(sn);
        if(!removed){
            return new ResponseEntity<>("product not found!", HttpStatus.OK);
        }
        return new ResponseEntity<>("product removed!",HttpStatus.ACCEPTED);
    }
    @PostMapping("/add")
    public ResponseEntity<Object> addProduct(@RequestBody Product product){
//        boolean added = productService.addProduct(product);
//        if(!added){
//            return new ResponseEntity<>("product already in inventory!", HttpStatus.OK);
//        }
        return new ResponseEntity<>(product,HttpStatus.OK);
    }
    @PostMapping("/categories/add")
    public ResponseEntity<Object> addCategory(@RequestBody ProductCategory category){
        boolean added = productService.addCategory(category);
        if(!added){
            return new ResponseEntity<>("category already in inventory!", HttpStatus.OK);
        }
        return new ResponseEntity<>("category added successfully!",HttpStatus.OK);
    }
}
