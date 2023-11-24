package com.fyt.domain;

public class buyGoods {   //商品进货信息
    private String buyTime;
    private int buyNumber;
    private int goodsId;
    private int price;
    private int total;
    private int userId;

    public String getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(String buyTime) {
        this.buyTime = buyTime;
    }

    public int getBuyNumber() {
        return buyNumber;
    }

    public void setBuyNumber(int buyNumber) {
        this.buyNumber = buyNumber;
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

    @Override
    public String toString() {
        return "buyGoods{" +
                "buyTime='" + buyTime + '\'' +
                ", buyNumber=" + buyNumber +
                ", goodsId=" + goodsId +
                ", price=" + price +
                ", total=" + total +
                ", userId=" + userId +
                '}';
    }
}
