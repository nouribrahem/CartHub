package com.order.OrderNotificationApp.model;

public class Product {
    private String SN;
    private String Name;
    private ProductCategory Category;
    private Double Price;
    private int Count;
    private ProductVendor Vendor;

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
}
