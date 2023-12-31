package com.order.OrderNotificationApp.service;

import com.order.OrderNotificationApp.model.Product;
import com.order.OrderNotificationApp.model.ProductCategory;
import com.order.OrderNotificationApp.repository.Inventory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProductService {
    private final Inventory inventory;
    ProductService(Inventory i){
        this.inventory = i;
    }
    public Map<ProductCategory, List<Product>> listAvailableProducts(){
        return (Map<ProductCategory, List<Product>>)inventory.getAll();
    }
    public String listProductDetails(String sn){
        Product product = (Product) inventory.getByID(sn);
        if(product == null){
            return null;
        }
        return "Category: "+product.getCategory().name()+" , SN: "+product.getSN()+" , Name: "+product.getName()+" , Price: "+product.getPrice()+" , Vendor: "+product.getVendor().name()+" , Left in stock: "+product.getCount();
    }
    public List<Product> getAvailableItemsInCategory(String category){
        return inventory.getCategoryProducts(category);
    }
    public boolean addProduct(Product product){
        return  inventory.addProduct(product);
    }
    public boolean removeCategory(String category){
        return  inventory.remove(category);
    }
    public boolean removeProduct(String sn){
        return inventory.removeProduct(sn);
    }

    public Map<ProductCategory, Integer> getCategoryCount() {
        return inventory.getCategoryCount();
    }
}
