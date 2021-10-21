package com.example.unmall.myOrdersitem;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unmall.OredeDetailsActivity;
import com.example.unmall.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MyOrdersItemAdapter extends RecyclerView.Adapter<MyOrdersItemAdapter.ViewHolder> {

   private List<MyOrdersItemModel> myOrdersItemModelList;

    public MyOrdersItemAdapter(List<MyOrdersItemModel> myOrdersItemModelList) {
        this.myOrdersItemModelList = myOrdersItemModelList;
    }

    @NonNull
    @NotNull
    @Override
    public MyOrdersItemAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_orders_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull @NotNull MyOrdersItemAdapter.ViewHolder holder, int position) {

        int resource  = myOrdersItemModelList.get(position).getOrderProductImage();
        int rating = myOrdersItemModelList.get(position).getRating();
        String title = myOrdersItemModelList.get(position).getOrderProductTitle();
        String deliveryDate = myOrdersItemModelList.get(position).getOrderDeliveryStatus();


        holder.setData(resource, title, deliveryDate, rating);
    }

    @Override
    public int getItemCount() {
        return myOrdersItemModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView orderProductImage;
        private ImageView orderIndicator;
        private TextView orderProductTitle;
        private TextView orderDeliveryStatus;
        private LinearLayout rateNowContainer;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            orderProductImage = itemView.findViewById(R.id.order_product_image);
            orderIndicator = itemView.findViewById(R.id. order_indicator);
            orderProductTitle = itemView.findViewById(R.id.order_product_title);
            orderDeliveryStatus = itemView.findViewById(R.id.order__delivered_date);
            rateNowContainer = itemView.findViewById(R.id.rate_now_container);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent orderDetailsIntent = new Intent(itemView.getContext(), OredeDetailsActivity.class);
                    itemView.getContext().startActivity(orderDetailsIntent);
                }
            });

        }
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        private void setData(int resource, String title, String deliverdDate, int rating){
            orderProductImage.setImageResource(resource);
            orderProductTitle.setText(title);
            if (deliverdDate.equals("Cancelled")) {
                orderIndicator.setImageTintList(ColorStateList.valueOf(itemView.getContext().getResources().getColor(R.color.red)));
            }else {
                orderIndicator.setImageTintList(ColorStateList.valueOf(itemView.getContext().getResources().getColor(R.color.successGreen)));
            }
                orderDeliveryStatus.setText(deliverdDate);

            //todo: rating layoout
            setRating(rating);
            for (int x = 0; x< rateNowContainer.getChildCount(); x++){
                final  int starPosition = x;
                rateNowContainer.getChildAt(x).setOnClickListener(new View.OnClickListener() {
                    //automatic
                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                    //automatic
                    @Override
                    public void onClick(View v) {
                        setRating(starPosition);
                    }

                   // @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//                    private void setRating(int starPosition) {
//                        for (int x = 0; x < rateNowContainer.getChildCount(); x++){
//                            ImageView starBtn = (ImageView)rateNowContainer.getChildAt(x);
//                            starBtn.setImageTintList(ColorStateList.valueOf(Color.parseColor("#bebebe")));
//                            if (x <= starPosition){
//                                starBtn.setImageTintList(ColorStateList.valueOf(Color.parseColor("#ffbb00")));
//                            }
//                        }
//                    }
                });


            }

            //todo: rating layoout

        }
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        private void setRating(int starPosition) {
            for (int x = 0; x < rateNowContainer.getChildCount(); x++){
                ImageView starBtn = (ImageView)rateNowContainer.getChildAt(x);
                starBtn.setImageTintList(ColorStateList.valueOf(Color.parseColor("#bebebe")));
                if (x <= starPosition){
                    starBtn.setImageTintList(ColorStateList.valueOf(Color.parseColor("#ffbb00")));
                }
            }
        }
    }

}
