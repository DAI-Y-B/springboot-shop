package com.turing.entity;

public class Product {
    private int id;
    private String name;
    private String description;
    private double price;
    private int stock;
    private String brand;
    private String barcode;
    private int cId;
    private int cChildId;
    private String fileName;
    private int goodnum;

    public int getGoodnum() {
        return goodnum;
    }

    public void setGoodnum(int goodnum) {
        this.goodnum = goodnum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    public int getcChildId() {
        return cChildId;
    }

    public void setcChildId(int cChildId) {
        this.cChildId = cChildId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price + ", stock="
                + stock + ", brand=" + brand + ", barcode=" + barcode + ", cId=" + cId + ", cChildId=" + cChildId
                + ", fileName=" + fileName + ", goodnum=" + goodnum + "]";
    }

}
// id int not null primary key auto_increment,/* 商品编号 */
// name varchar(100) not null,/* 商品名称 */
// description varchar(100),/* 商品描述 */
// price decimal(12,2) not null,/* 商品价格 */
// stock int not null,/* 库存 */
// brand varchar(20) not null,/* 品牌 */
// barcode varchar(30)not null,/* 商品编码 */
// c_id int,/* 一级分类 */
// c_child_id int,/* 二级分类 */
// file_name varchar(200)/* 文件名称（上传图片） */
//
// id int not null primary key autoIncrement,/* 商品编号 */
// name varchar(100) not null,/* 商品名称 */
// description varchar(100),/* 商品描述 */
// price decimal(12,2) not null,/* 商品价格 */
// stock int not null,/* 库存 */
// brand varchar(20) not null,/* 品牌 */
// barcode varchar(30)not null,/* 商品编码 */
// cId int,/* 一级分类 */
// cChildId int,/* 二级分类 */
// fileName varchar(200)/* 文件名称（上传图片） */