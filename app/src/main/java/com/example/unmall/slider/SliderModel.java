package com.example.unmall.slider;

public class SliderModel {

    private String banner;
    private String backgroudColor;

    public SliderModel(String banner, String backgroudColor) {
        this.banner = banner;
        this.backgroudColor = backgroudColor;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getBackgroudColor() {
        return backgroudColor;
    }

    public void setBackgroudColor(String backgroudColor) {
        this.backgroudColor = backgroudColor;
    }
}
