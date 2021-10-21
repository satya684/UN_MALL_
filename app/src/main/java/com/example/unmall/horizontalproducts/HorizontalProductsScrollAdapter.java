package com.example.unmall.horizontalproducts;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.unmall.ProductDetailsActivity;
import com.example.unmall.R;
import com.example.unmall.category.CategoryAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class HorizontalProductsScrollAdapter extends RecyclerView.Adapter<HorizontalProductsScrollAdapter.ViewHolder> {


    private Context context;
   private List<HorizontalProductScrollModel> horizontalProductScrollModelList;

    public HorizontalProductsScrollAdapter(Context context, List<HorizontalProductScrollModel> horizontalProductScrollModelList) {
        this.context = context;
        this.horizontalProductScrollModelList = horizontalProductScrollModelList;
    }

    //        public HorizontalProductsScrollAdapter(List<HorizontalProductScrollModel> horizontalProductScrollModelList) {
//        this.horizontalProductScrollModelList = horizontalProductScrollModelList;
//    }

    @NonNull
    @NotNull
    @Override
    public HorizontalProductsScrollAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int position) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_scroll_item_layout, parent,false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull HorizontalProductsScrollAdapter.ViewHolder viewHolder, int position){
        String resource = horizontalProductScrollModelList.get(position).getProductImage();
        String title = horizontalProductScrollModelList.get(position).getProductTitle();
        String description = horizontalProductScrollModelList.get(position).getProductDescription();
        String price = horizontalProductScrollModelList.get(position).getProductPrice();

        viewHolder.setData(resource,title, description, price);

    }

    @Override
    public int getItemCount() {
        if (horizontalProductScrollModelList.size() > 8){
          return 8;

        }else {
           return horizontalProductScrollModelList.size();

        }
       // Log.v("checking","Passing");
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView productImage;
        private TextView productTitle;
        private  TextView productDescription;
        private  TextView productPrice;


        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            productImage = itemView.findViewById(R.id.h_s_product_image);
            productTitle = itemView.findViewById(R.id.h_s_product_title);
            productDescription = itemView.findViewById(R.id.h_s_product_description);
            productPrice = itemView.findViewById(R.id.h_s_product_price);

        }
        private  void  setData(String resource, String title, String price, String description) {
            Glide.with(itemView.getContext()).load(resource).apply(new RequestOptions().placeholder(R.drawable.placeholder)).into(productImage);
            productPrice.setText("Rs." + price + "/-");
            productDescription.setText(description);
            productTitle.setText(title);


            if (!title.equals(" ")) {
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
}
