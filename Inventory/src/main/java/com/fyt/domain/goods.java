package com.fyt.domain;

public class goods {   //商品库存信息
    private int id;
    private String name;
    private String updateTime;
    private int purchasePrice;//进价
    private int sellPrice; //售价
    private String unit;
    private int inventories;
    private String buyUserName; //添加商品的人的Id
    private int goodsIndex;
    private int addGoodsNumber;
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

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public int getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(int purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public int getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(int sellPrice) {
        this.sellPrice = sellPrice;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getInventories() {
        return inventories;
    }

    public void setInventories(int inventories) {
        this.inventories = inventories;
    }

    public String getBuyUserName() {
        return buyUserName;
    }

    public void setBuyUserName(String buyUserName) {
        this.buyUserName = buyUserName;
    }

    public int getGoodsIndex() {
        return goodsIndex;
    }

    public void setGoodsIndex(int goodsIndex) {
        this.goodsIndex = goodsIndex;
    }

    @Override
    public String toString() {
        return "goods{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", purchasePrice=" + purchasePrice +
                ", sellPrice=" + sellPrice +
                ", unit='" + unit + '\'' +
                ", inventories=" + inventories +
                ", buyUserName='" + buyUserName + '\'' +
                ", goodsIndex=" + goodsIndex +
                '}';
    }

    public int getAddGoodsNumber() {
        return addGoodsNumber;
    }

    public void setAddGoodsNumber(int addGoodsNumber) {
        this.addGoodsNumber = addGoodsNumber;
    }
}
