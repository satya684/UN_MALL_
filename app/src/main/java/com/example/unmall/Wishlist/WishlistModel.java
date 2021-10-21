package com.example.unmall.Wishlist;

public class WishlistModel {

    private String wishlistProductImage;
    private String wishlistProductTitle;
    private long wishlistFreeCoupens;
    private String wishlistRating;
    private long wishlistTotalRatings;
    private String wishlistProductPrice;
    private String wishlistCuttedPrice;
    private boolean COD;


    public WishlistModel(String wishlistProductImage, String wishlistProductTitle, long wishlistFreeCoupens, String wishlistRating, long wishlistTotalRatings, String wishlistProductPrice, String wishlistCuttedPrice, boolean COD) {
        this.wishlistProductImage = wishlistProductImage;
        this.wishlistProductTitle = wishlistProductTitle;
        this.wishlistFreeCoupens = wishlistFreeCoupens;
        this.wishlistRating = wishlistRating;
        this.wishlistTotalRatings = wishlistTotalRatings;
        this.wishlistProductPrice = wishlistProductPrice;
        this.wishlistCuttedPrice = wishlistCuttedPrice;
        this.COD = COD;
    }

    public String getWishlistProductImage() {
        return wishlistProductImage;
    }

    public void setWishlistProductImage(String wishlistProductImage) {
        this.wishlistProductImage = wishlistProductImage;
    }

    public String getWishlistProductTitle() {
        return wishlistProductTitle;
    }

    public void setWishlistProductTitle(String wishlistProductTitle) {
        this.wishlistProductTitle = wishlistProductTitle;
    }

    public long getWishlistFreeCoupens() {
        return wishlistFreeCoupens;
    }

    public void setWishlistFreeCoupens(long wishlistFreeCoupens) {
        this.wishlistFreeCoupens = wishlistFreeCoupens;
    }

    public String getWishlistRating() {
        return wishlistRating;
    }

    public void setWishlistRating(String wishlistRating) {
        this.wishlistRating = wishlistRating;
    }

    public long getWishlistTotalRatings() {
        return wishlistTotalRatings;
    }

    public void setWishlistTotalRatings(long wishlistTotalRatings) {
        this.wishlistTotalRatings = wishlistTotalRatings;
    }

    public String getWishlistProductPrice() {
        return wishlistProductPrice;
    }

    public void setWishlistProductPrice(String wishlistProductPrice) {
        this.wishlistProductPrice = wishlistProductPrice;
    }

    public String getWishlistCuttedPrice() {
        return wishlistCuttedPrice;
    }

    public void setWishlistCuttedPrice(String wishlistCuttedPrice) {
        this.wishlistCuttedPrice = wishlistCuttedPrice;
    }

    public boolean isCOD() {
        return COD;
    }

    public void setCOD(boolean COD) {
        this.COD = COD;
    }
}
