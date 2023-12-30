package com.order.OrderNotificationApp.controller;

import com.order.OrderNotificationApp.model.Product;
import com.order.OrderNotificationApp.model.ProductCategory;
import com.order.OrderNotificationApp.service.ProductService;
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
    public List<Product> getAvailableItemsInCategory(@PathVariable String category){
        return productService.getAvailableItemsInCategory(category);
    }
    @GetMapping("/serial/{sn}")
    public ResponseEntity<Object> listProductDetails(@PathVariable String sn){
        String details = productService.listProductDetails(sn);
        if(details == null){
            return new ResponseEntity<>("Product not found!", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(details, HttpStatus.OK);
    }
    @GetMapping("all")
    public Map<ProductCategory,List<Product>> listAvailableProducts(){
        return productService.listAvailableProducts();
    }
    @DeleteMapping ("/categories/remove/{category}")
    public boolean removeCategory(@PathVariable String category){
        return productService.removeCategory(category);
    }
    @DeleteMapping ("/remove/{sn}")
    public boolean removeProduct(@PathVariable String sn){
        return productService.removeProduct(sn);
    }
    @PostMapping("/add")
    public Boolean addProduct(@RequestBody Product product){

        return productService.addProduct(product);
    }
    @PostMapping("/categories/add")
    public boolean addCategory(@RequestBody ProductCategory category){
        return productService.addCategory(category);
    }
}
