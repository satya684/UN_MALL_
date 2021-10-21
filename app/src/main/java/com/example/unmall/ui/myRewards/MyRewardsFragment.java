package com.example.unmall.ui.myRewards;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unmall.R;
import com.example.unmall.Rewards.MyRewardsAdapter;
import com.example.unmall.Rewards.RewardModel;
import com.example.unmall.databinding.FragmentMyrewardsBinding;

import java.util.ArrayList;
import java.util.List;

public class MyRewardsFragment extends Fragment {


    //todo my code
    private RecyclerView rewarsRecyclerView;
    //todo my code

    private MyRewardsViewModel myRewardsViewModel;
    private FragmentMyrewardsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        myRewardsViewModel =
                new ViewModelProvider(this).get(MyRewardsViewModel.class);

        binding = FragmentMyrewardsBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

       // final TextView textView = binding.hellotv;
//        myRewardsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        //todo my code
        rewarsRecyclerView =  view.findViewById(R.id.myRewards_recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rewarsRecyclerView.setLayoutManager(linearLayoutManager);

        List<RewardModel> rewardModelList = new ArrayList<>();
        rewardModelList.add(new RewardModel("Cashback", "till 25th Nov 2021","Get 25% off on any product upto Rs. 2500/- /n  and below Rs. 200/-"));
        rewardModelList.add(new RewardModel("NoCashback", "till 21th Nov 2022","Get 25% off on any product upto Rs. 25000/- and below Rs. 2000/-"));
        rewardModelList.add(new RewardModel("Cashback", "till 25th Nov 2021","Get 25% off on any product upto Rs. 2500/- and below Rs. 200/-"));
        rewardModelList.add(new RewardModel("Cashback", "till 25th Nov 2021","Get 25% off on any product upto Rs. 2500/- and below Rs. 200/-"));
        rewardModelList.add(new RewardModel("NoCashback", "till 21th Nov 2022","Get 25% off on any product upto Rs. 25000/- and below Rs. 2000/-"));
        rewardModelList.add(new RewardModel("NoCashback", "till 21th Nov 2022","Get 25% off on any product upto Rs. 25000/- and below Rs. 2000/-"));
        rewardModelList.add(new RewardModel("Cashback", "till 25th Nov 2021","Get 25% off on any product upto Rs. 2500/- and below Rs. 200/-"));
        rewardModelList.add(new RewardModel("Cashback", "till 25th Nov 2021","Get 25% off on any product upto Rs. 2500/- and below Rs. 200/-"));
        rewardModelList.add(new RewardModel("Cashback", "till 25th Nov 2021","Get 25% off on any product upto Rs. 2500/- and below Rs. 200/-"));
        rewardModelList.add(new RewardModel("Cashback", "till 25th Nov 2021","Get 25% off on any product upto Rs. 2500/- and below Rs. 200/-"));
        rewardModelList.add(new RewardModel("Cashback", "till 25th Nov 2021","Get 25% off on any product upto Rs. 2500/- and below Rs. 200/-"));
        rewardModelList.add(new RewardModel("NoCashback", "till 21th Nov 2022","Get 25% off on any product upto Rs. 25000/- and below Rs. 2000/-"));
        rewardModelList.add(new RewardModel("NoCashback", "till 21th Nov 2022","Get 25% off on any product upto Rs. 25000/- and below Rs. 2000/-"));
        rewardModelList.add(new RewardModel("Cashback", "till 25th Nov 2021","Get 25% off on any product upto Rs. 2500/- and below Rs. 200/-"));
        rewardModelList.add(new RewardModel("NoCashback", "till 21th Nov 2022","Get 25% off on any product upto Rs. 25000/- and below Rs. 2000/-"));

        MyRewardsAdapter myRewardsAdapter = new MyRewardsAdapter(rewardModelList, false);
        rewarsRecyclerView.setAdapter(myRewardsAdapter);
        myRewardsAdapter.notifyDataSetChanged();

        //todo my code
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}