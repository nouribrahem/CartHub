package com.order.OrderNotificationApp.repository;

import com.order.OrderNotificationApp.model.Product;
import com.order.OrderNotificationApp.model.ProductCategory;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class Inventory implements BaseRepository {
    private static Map<ProductCategory, List<Product>> categories = new HashMap<>();

    public Inventory() {
        categories = new HashMap<>();
    }
    public boolean addProduct(Product p){
        for(Map.Entry<ProductCategory,List<Product>> set : categories.entrySet()){
            for(Product product : set.getValue()){
                if(product.getSN().equals(p.getSN())){
                    return false;
                }
            }
        }
        if(categories.containsKey(p.getCategory())){
            List<Product> found = categories.get(p.getCategory());
            if(!found.isEmpty()){
                for(Product product : found){
                    if(product.getSN().equals(p.getSN())){
                        return false;
                    }
                }
                categories.get(p.getCategory()).add(p);
                return true;
            }
        }
        add(p.getCategory());
        categories.get(p.getCategory()).add(p);
        return true;
    }
    public void setCategories(Map<ProductCategory, List<Product>> categories) {
        this.categories = categories;
    }
    public List<Product> getCategoryProducts(String category) {
        Set<ProductCategory> c = categories.keySet();
        for(ProductCategory cat: c){
            if(cat.name().equals(category)){
                return categories.get(cat);
            }
        }
        return null;
    }

    @Override
    public boolean add(Object o) {
        if(!categories.containsKey((ProductCategory) o)){
            categories.put((ProductCategory) o, new ArrayList<>());
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Object o) {
        for(ProductCategory cat : categories.keySet()){
            if(cat.name().equals(o)){
                categories.remove(cat);
                return true;
            }
        }
        return false;
    }

    @Override
    public Object getByID(Object o) {
        for(Map.Entry<ProductCategory,List<Product>> set : categories.entrySet()){
            for(Product p : set.getValue()){
                if(p.getSN().equals(o)){
                    return p;
                }
            }
        }
        return null;
    }

    @Override
    public Object getAll() {
        return categories;
    }

    public boolean removeProduct(String sn) {
        Product toBeRemoved = (Product) getByID(sn);
        if(toBeRemoved != null){
            return categories.get(toBeRemoved.getCategory()).remove(toBeRemoved);
        }
        return false;
    }

    public Product getProductByName(String productName){
        for(Map.Entry<ProductCategory,List<Product>> set : categories.entrySet()){
            for(Product p : set.getValue()){
                if(p.getName().equals(productName)){
                    return p;
                }
            }
        }
        return null;
    }
    public Boolean updateProductCount(String productName, int count){
        for(Map.Entry<ProductCategory,List<Product>> set : categories.entrySet()){
            for(Product p : set.getValue()){
                if(p.getName().equals(productName)){
                    p.setCount(p.getCount()+count);
                    return true;
                }
            }
        }
        return false;
    }
}
