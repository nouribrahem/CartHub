package com.order.OrderNotificationApp.model;

import com.order.OrderNotificationApp.model.Enums.ProductCategory;
import com.order.OrderNotificationApp.model.Enums.ProductVendor;

public class Product {
    private String SN;
    private String Name;
    private ProductCategory Category;
    private Double Price;
    private int Count;
    private ProductVendor Vendor;
    public Product(){

    }
    public Product(String sn,String name,ProductCategory cat, Double p, int c, ProductVendor pv ){
        this.SN = sn;
        this.Name = name;
        this.Category = cat;
        this.Price = p;
        this.Count = c;
        this.Vendor = pv;
    }
    public Product(Product other) {
        this.SN = other.SN;
        this.Name = other.Name;
        this.Category = other.Category;
        this.Price = other.Price;
        this.Count = other.Count;
        this.Vendor = other.Vendor;
    }
    public String getSN() {
        return SN;
    }

    public void setSN(String SN) {
        this.SN = SN;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public ProductCategory getCategory() {
        return Category;
    }

    public void setCategory(ProductCategory category) {
        Category = category;
    }

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double price) {
        Price = price;
    }

    public int getCount() {
        return Count;
    }

    public void setCount(int count) {
        Count = count;
    }

    public ProductVendor getVendor() {
        return Vendor;
    }

    public void setVendor(ProductVendor vendor) {
        Vendor = vendor;
    }

    @Override
    public String toString() {
        return "Product{" +
                "SN='" + SN + '\'' +
                ", Name='" + Name + '\'' +
                ", Count= " + Count +
                ", Category=" + Category +
                ", Price=" + Price +
                ", Vendor=" + Vendor +
                '}';
    }
}
