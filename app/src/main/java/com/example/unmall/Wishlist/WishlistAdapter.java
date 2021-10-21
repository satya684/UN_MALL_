package com.example.unmall.Wishlist;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.unmall.ProductDetailsActivity;
import com.example.unmall.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class WishlistAdapter extends RecyclerView.Adapter<WishlistAdapter.ViewHolder> {

    List<WishlistModel> wishlistModelList;
    private Boolean wishlist;

    public WishlistAdapter(List<WishlistModel> wishlistModelList, Boolean wishlist) {
        this.wishlistModelList = wishlistModelList;
        this.wishlist = wishlist;
    }

    @NonNull
    @NotNull
    @Override
    public WishlistAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wishlist_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull WishlistAdapter.ViewHolder holder, int position) {

        String resource = wishlistModelList.get(position).getWishlistProductImage();
        String title = wishlistModelList.get(position).getWishlistProductTitle();
        long wishlistFreeCoupensNo = wishlistModelList.get(position).getWishlistFreeCoupens();
        String averageRate = wishlistModelList.get(position).getWishlistRating();
        long totalRatingsNo = wishlistModelList.get(position).getWishlistTotalRatings();
        String price = wishlistModelList.get(position).getWishlistProductPrice();
        String cuttedPriceValue = wishlistModelList.get(position).getWishlistCuttedPrice();
        boolean payMethod = wishlistModelList.get(position).isCOD();

        holder.setData(resource,title, wishlistFreeCoupensNo, averageRate, totalRatingsNo, price,  cuttedPriceValue, payMethod);
    }

    @Override
    public int getItemCount() {
        return wishlistModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView wishlistProductImage;
        private TextView wishlistProductTitle;
        private TextView wishlistFreeCoupens;
        private ImageView wishlistCoupenIcon;
        private TextView wishlistRatings;
        private TextView wishlistTotalRatings;
        private View wishlistPriceCut;
        private  TextView wishlistProductPrice;
        private TextView wishlistCuttedPrice;
        private TextView wishlistPaymentMethod;
        private ImageButton wishlistDeleteBtn;


        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            wishlistProductImage = itemView.findViewById(R.id.wishlist_product_image);
            wishlistProductTitle = itemView.findViewById(R.id.wishlist_product_title);
            wishlistFreeCoupens = itemView.findViewById(R.id.wishlist_free_coupen);
            wishlistCoupenIcon = itemView.findViewById(R.id.wishlist_coupn_icon);
            wishlistRatings = itemView.findViewById(R.id.tv_product_rating_miniview);
            wishlistTotalRatings = itemView.findViewById(R.id.wishlist_total_rtings);
            wishlistProductPrice = itemView.findViewById(R.id.wishlist_product_price);
            wishlistCuttedPrice = itemView.findViewById(R.id.wishlist_cutted_price);
            wishlistPaymentMethod = itemView.findViewById(R.id.wishlist_payment_method);
            wishlistPriceCut = itemView.findViewById(R.id.wishlist_price_cut);
            wishlistDeleteBtn = itemView.findViewById(R.id.wishlist_delete_btn);

        }

        private void setData(String resource, String title, long wishlistFreeCoupensNo, String averageRate, long totalRatingsNo, String price, String cuttedPriceValue, boolean cod){
//            wishlistProductImage.setImageResource(resource);
            Glide.with(itemView.getContext()).load(resource).apply(new RequestOptions().placeholder(R.drawable.home)).into(wishlistProductImage);
            wishlistProductTitle.setText(title);

            if (wishlistFreeCoupensNo != 0){
                wishlistCoupenIcon.setVisibility(View.VISIBLE);
                if (wishlistFreeCoupensNo == 1){
                    wishlistFreeCoupens.setText("free " +wishlistFreeCoupensNo+ " coupen");
                }else {
                    wishlistFreeCoupens.setText("free " + wishlistFreeCoupensNo + " coupens");
                }
            }else {
                wishlistCoupenIcon.setVisibility(View.INVISIBLE);
                wishlistFreeCoupens.setVisibility(View.INVISIBLE);
            }
            wishlistRatings.setText(averageRate);
            wishlistTotalRatings.setText("("+totalRatingsNo+")"+"ratings");
            wishlistProductPrice.setText("Rs."+price+"/-");
            wishlistCuttedPrice.setText("Rs."+cuttedPriceValue+"/-");
            if (cod == true){
                wishlistPaymentMethod.setVisibility(View.VISIBLE);
            }else {
                wishlistPaymentMethod.setVisibility(View.INVISIBLE);
            }

            if (wishlist) {
                wishlistDeleteBtn.setVisibility(View.VISIBLE);
            }else {
                wishlistDeleteBtn.setVisibility(View.GONE);
            }
            wishlistDeleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(itemView.getContext(), "delete", Toast.LENGTH_SHORT).show();
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent productDetailsIntent = new Intent(itemView.getContext(), ProductDetailsActivity.class);
                    itemView.getContext().startActivity(productDetailsIntent);
                }
            });
        }
    }
}
