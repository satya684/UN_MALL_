package com.example.unmall.Rewards;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unmall.ProductDetailsActivity;
import com.example.unmall.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MyRewardsAdapter  extends RecyclerView.Adapter<MyRewardsAdapter.ViewHolder> {

    private List<RewardModel> rewardModelList;
    private Boolean useMiniLayout = false;

    public MyRewardsAdapter(List<RewardModel> rewardModelList, Boolean useMiniLayout) {
        this.rewardModelList = rewardModelList;
        this.useMiniLayout = useMiniLayout;
    }

    @NonNull
    @NotNull
    @Override
    public MyRewardsAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View  view;
        if (useMiniLayout){
             view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mini_rewards_item_layout,parent, false);
        }else {
             view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rewards_item_layout, parent, false);
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MyRewardsAdapter.ViewHolder holder, int position) {

        String title = rewardModelList.get(position).getRewardTitle();
        String date = rewardModelList.get(position).getRewardExpiryDate();
        String body = rewardModelList.get(position).getRewardCoupenBody();

        holder.setData(title, date, body);
    }


    @Override
    public int getItemCount() {
        return rewardModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView rewardCoupenTitle;
        private TextView rewardCoupenVailidity;
        private TextView rewardCoupenBody;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            rewardCoupenTitle = itemView.findViewById(R.id.reward_coupen_title);
            rewardCoupenVailidity = itemView.findViewById(R.id.reward_coupen_validity);
            rewardCoupenBody = itemView.findViewById(R.id.reward_coupen_body);
        }

        private void setData(String title, String date, String body){
            rewardCoupenTitle.setText(title);
            rewardCoupenVailidity.setText(date);
            rewardCoupenBody.setText(body);



            if (useMiniLayout){
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ProductDetailsActivity.coupenTitle.setText(title);
                        ProductDetailsActivity.coupenExpirtDate.setText(date);
                        ProductDetailsActivity.coupenBody.setText(body);
                        ProductDetailsActivity.showDialogRecyclerView();
                    }
                });
            }
        }
    }
}
