package com.fyt.domain;

public class saleGoods {   //商品销售信息
    private String saleTime;
    private int saleNumber;
    private int goodsId;
    private int price;
    private int total;
    private int userId;
    private String suppliers;//供应商

    public String getSaleTime() {
        return saleTime;
    }

    public void setSaleTime(String saleTime) {
        this.saleTime = saleTime;
    }

    public int getSaleNumber() {
        return saleNumber;
    }

    public void setSaleNumber(int saleNumber) {
        this.saleNumber = saleNumber;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(String suppliers) {
        this.suppliers = suppliers;
    }

    @Override
    public String toString() {
        return "saleGoods{" +
                "saleTime='" + saleTime + '\'' +
                ", saleNumber=" + saleNumber +
                ", goodsId=" + goodsId +
                ", price=" + price +
                ", total=" + total +
                ", userId=" + userId +
                ", suppliers='" + suppliers + '\'' +
                '}';
    }
}
