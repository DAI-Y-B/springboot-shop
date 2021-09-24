package com.turing.entity;

public class OrderDetail {
    private int eodId;
    private int eoId;
    private int epId;
    private int eodQuantity;
    private double eodCost;
    private Product products;

    public Product getProducts() {
        return products;
    }

    public void setProducts(Product products) {
        this.products = products;
    }

    public int getEodId() {
        return eodId;
    }

    public void setEodId(int eodId) {
        this.eodId = eodId;
    }

    public int getEoId() {
        return eoId;
    }

    public void setEoId(int eoId) {
        this.eoId = eoId;
    }

    public int getEpId() {
        return epId;
    }

    public void setEpId(int epId) {
        this.epId = epId;
    }

    public int getEodQuantity() {
        return eodQuantity;
    }

    public void setEodQuantity(int eodQuantity) {
        this.eodQuantity = eodQuantity;
    }

    public double getEodCost() {
        return eodCost;
    }

    public void setEodCost(double eodCost) {
        this.eodCost = eodCost;
    }

    @Override
    public String toString() {
        return "OrderDetail [eodId=" + eodId + ", eoId=" + eoId + ", epId=" + epId + ", eodQuantity=" + eodQuantity
                + ", eodCost=" + eodCost + ", products=" + products + "]";
    }

}
// easybuy_order_detail
// eod_id int not null primary key auto_increment,/* */
// eo_id int not null,/* 订单ID */
// ep_id int not null,/* 商品ID */
// eod_quantity int(6) not null,/* 订单数量 */
// eod_cost decimal(12,2) not null/* 金额 */
//
// eodId int not null primary key autoIncrement,/* */
// eoId int not null,/* 订单id */
// epId int not null,/* 商品id */
// eodQuantity int(6) not null,/* 订单数量 */
// eodCost decimal(12,2) not null/* 金额 */