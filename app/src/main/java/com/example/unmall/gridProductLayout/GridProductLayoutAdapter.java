package com.example.unmall.gridProductLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.unmall.ProductDetailsActivity;
import com.example.unmall.R;
import com.example.unmall.horizontalproducts.HorizontalProductScrollModel;

import java.util.List;

public class GridProductLayoutAdapter extends BaseAdapter {


  List<HorizontalProductScrollModel> horizontalProductScrollModelList;

    public GridProductLayoutAdapter(List<HorizontalProductScrollModel> horizontalProductScrollModelList) {
        this.horizontalProductScrollModelList = horizontalProductScrollModelList;
    }

    @Override
    public int getCount() {
        return horizontalProductScrollModelList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {


            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_product_item_layout,null) ;

            view.setElevation(0);
            view.setBackgroundColor(Color.parseColor("#ffffff"));

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent productDetailsIntent = new Intent(parent.getContext(), ProductDetailsActivity.class);
                   // productDetailsIntent.putExtra("PRODUCT_ID", horizontalProductScrollModelList.get(position).getProductID());
                    parent.getContext().startActivity(productDetailsIntent);
                }
            });

            ImageView productImage = view.findViewById(R.id.grid_product_image);
            TextView productTitle = view.findViewById(R.id.grid_product_title);
            TextView productDescription = view.findViewById(R.id.grid_product_description);
            TextView prductPrice = view.findViewById(R.id.grid_product_price);

//            productImage.setImageResource(horizontalProductScrollModelList.get(position).getProductImage());
            Glide.with(parent.getContext()).load(horizontalProductScrollModelList.get(position).getProductImage()).apply(new RequestOptions().placeholder(R.drawable.placeholder)).into(productImage);
            productTitle.setText(horizontalProductScrollModelList.get(position).getProductTitle());
            productDescription.setText(horizontalProductScrollModelList.get(position).getProductDescription());
            prductPrice.setText("Rs."+horizontalProductScrollModelList.get(position).getProductPrice()+"/-");
        } else {
            view = convertView;
        }
        return view;

    }
}
