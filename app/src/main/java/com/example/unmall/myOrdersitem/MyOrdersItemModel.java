package com.example.unmall.myOrdersitem;

public class MyOrdersItemModel {

    private int orderProductImage;
    private int rating;
    private String orderProductTitle;
    private String orderDeliveryStatus;


    public MyOrdersItemModel(int orderProductImage, int rating, String orderProductTitle, String orderDeliveryStatus) {
        this.orderProductImage = orderProductImage;
        this.rating = rating;
        this.orderProductTitle = orderProductTitle;
        this.orderDeliveryStatus = orderDeliveryStatus;
    }

    public int getOrderProductImage() {
        return orderProductImage;
    }

    public void setOrderProductImage(int orderProductImage) {
        this.orderProductImage = orderProductImage;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getOrderProductTitle() {
        return orderProductTitle;
    }

    public void setOrderProductTitle(String orderProductTitle) {
        this.orderProductTitle = orderProductTitle;
    }

    public String getOrderDeliveryStatus() {
        return orderDeliveryStatus;
    }

    public void setOrderDeliveryStatus(String orderDeliveryStatus) {
        this.orderDeliveryStatus = orderDeliveryStatus;
    }
}
