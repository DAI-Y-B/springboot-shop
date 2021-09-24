package com.turing.entity;

public class Car {
    private int carId;
    private int userId;
    private int productId;
    private int productCount;
    private Product products;

    public Product getProducts() {
        return products;
    }

    public void setProducts(Product products) {
        this.products = products;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getProductCount() {
        return productCount;
    }

    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }

    @Override
    public String toString() {
        return "Car [carId=" + carId + ", userId=" + userId + ", productId=" + productId + ", productCount="
                + productCount + ", products=" + products + "]";
    }

}
// car_id int not null primary key auto_increment,/* 购物车编号*/
// user_id int(100) ,/* 购物车对应用户*/
// product_id int(100) , /* 产品Id*/
// product_count int(100)/* 购买数量*/
//
// carId int not null primary key autoIncrement,/* 购物车编号*/
// userId int(100) ,/* 购物车对应用户*/
// productId int(100) , /* 产品id*/
// productCount int(100)/* 购买数量*/