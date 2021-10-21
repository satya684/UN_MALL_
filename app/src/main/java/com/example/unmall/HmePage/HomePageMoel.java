package com.example.unmall.HmePage;

import com.example.unmall.Wishlist.WishlistModel;
import com.example.unmall.horizontalproducts.HorizontalProductScrollModel;
import com.example.unmall.slider.SliderModel;

import java.util.List;

public class HomePageMoel {


    public  static final  int BANNER_SLIDER = 0;
    public static final int STRIP_AD_BANNER = 1;
    public static final  int HORIZONTAL_PRODUCT_VIEW = 2;
    public  static final int GRID_PRODUCT_VIEW = 3;


    private int type;
    private  String backgroundColor;


    /////todo: code start banner slider
    private List<SliderModel> sliderModelList;
    public HomePageMoel(int type, List<SliderModel> sliderModelList) {
        this.type = type;
        this.sliderModelList = sliderModelList;
    }
    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public List<SliderModel> getSliderModelList() {
        return sliderModelList;
    }
    public void setSliderModelList(List<SliderModel> sliderModelList) {
        this.sliderModelList = sliderModelList;
    }
    /////todo: code end banner slider


    //////todo:strip code start

    private String resource;


    public HomePageMoel(int type, String resource, String backgroundColor) {
        this.type = type;
        this.resource = resource;
        this.backgroundColor = backgroundColor;
    }
    public String getResource() {
        return resource;
    }
    public void setResource(String resource) {
        this.resource = resource;
    }
    public String getBackgroundColor() {
        return backgroundColor;
    }
    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
    //////todo:strip code end

    private String title;
    private List<HorizontalProductScrollModel> horizontalProductScrollModelList;


    /////todo: code horizontal product view
    private List<WishlistModel> viewAllProductList;


    public HomePageMoel(int type, String title, String backgroundColor , List<HorizontalProductScrollModel> horizontalProductScrollModelList, List<WishlistModel> viewAllProducList) {
        this.type = type;
        this.title = title;
        this.backgroundColor = backgroundColor;
        this.horizontalProductScrollModelList = horizontalProductScrollModelList;
        this.viewAllProductList = viewAllProducList;
    }

    public List<WishlistModel> getViewAllProductList() {
        return viewAllProductList;
    }

    public void setViewAllProductList(List<WishlistModel> viewAllProductList) {
        this.viewAllProductList = viewAllProductList;
    }

    /////todo: code horizontal product view


    /////todo: code grid product view
    public HomePageMoel(int type, String title, String backgroundColor , List<HorizontalProductScrollModel> horizontalProductScrollModelList) {
        this.type = type;
        this.title = title;
        this.backgroundColor = backgroundColor;
        this.horizontalProductScrollModelList = horizontalProductScrollModelList;
    }
    /////todo: code horizontal product view
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public List<HorizontalProductScrollModel> getHorizontalProductScrollModelList() {
        return horizontalProductScrollModelList;
    }
    public void setHorizontalProductScrollModelList(List<HorizontalProductScrollModel> horizontalProductScrollModelList) {
        this.horizontalProductScrollModelList = horizontalProductScrollModelList;
    }



}
