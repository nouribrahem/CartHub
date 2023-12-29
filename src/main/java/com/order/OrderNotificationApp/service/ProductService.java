package com.order.OrderNotificationApp.service;

import com.order.OrderNotificationApp.model.Product;
import com.order.OrderNotificationApp.model.ProductCategory;
import com.order.OrderNotificationApp.repository.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProductService {
    @Autowired
    private Inventory inventory;
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
    public boolean addCategory(ProductCategory category){
        return  inventory.add(category);
    }
    public boolean addProduct(Product product){
        return  inventory.addProduct(product);
    }
    public boolean removeCategory(ProductCategory category){
        return  inventory.remove(category);
    }
    public boolean removeProduct(String sn){
        return inventory.removeProduct(sn);
    }

}
