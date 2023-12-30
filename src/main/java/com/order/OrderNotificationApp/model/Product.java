package com.order.OrderNotificationApp.model;

public class Product {
    private String SN;
    private String Name;
    private ProductCategory Category;
    private Double Price;
    private int Count;
    private ProductVendor Vendor;
    public Product(){

    }
    public Product(Product other) {
        this.SN = other.SN;
        this.Name = other.Name;
        this.Category = other.Category;
        this.Price = other.Price;
        this.Count = other.Count;
        this.Vendor = other.Vendor;
    }

    public void setSN(String SN) {
        this.SN = SN;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setCategory(ProductCategory category) {
        Category = category;
    }

    public void setPrice(Double price) {
        Price = price;
    }

    public void setVendor(ProductVendor vendor) {
        Vendor = vendor;
    }

    public String getSN() {
        return SN;
    }

    public String getName() {
        return Name;
    }

    public ProductCategory getCategory() {
        return Category;
    }

    public Double getPrice() {
        return Price;
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
