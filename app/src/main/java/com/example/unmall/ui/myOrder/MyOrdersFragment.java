package com.example.unmall.ui.myOrder;

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
import com.example.unmall.databinding.FragmentMyordersBinding;
import com.example.unmall.myOrdersitem.MyOrdersItemAdapter;
import com.example.unmall.myOrdersitem.MyOrdersItemModel;

import java.util.ArrayList;
import java.util.List;

public class MyOrdersFragment extends Fragment {

    private MyOrdersViewModel myOrdersViewModel;
    private FragmentMyordersBinding binding;

    //todo:
    private RecyclerView myOrdersRecyclerView;
    //todo:


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        myOrdersViewModel =
                new ViewModelProvider(this).get(MyOrdersViewModel.class);

        binding = FragmentMyordersBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        //todo:
        myOrdersRecyclerView = view.findViewById(R.id.myorders_recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        myOrdersRecyclerView.setLayoutManager(layoutManager);

        List<MyOrdersItemModel> myOrdersItemModelList = new ArrayList<>();
        myOrdersItemModelList.add(new MyOrdersItemModel(R.drawable.phone, 1,"Pixel 2XL (RED)", "Delivered on Mon, 15 Nov 2021"));
        myOrdersItemModelList.add(new MyOrdersItemModel(R.drawable.phomes, 2,"Pixel 2XL (BLACK)", "Delivered on Mon, 1 Nov 2021"));
        myOrdersItemModelList.add(new MyOrdersItemModel(R.drawable.phone, 4,"Pixel 2XL (WHITE)", "Cancelled"));
        myOrdersItemModelList.add(new MyOrdersItemModel(R.drawable.phomes, 2,"Pixel 2XL (BLACK)", "Delivered on Mon, 6 Nov 2021"));


        MyOrdersItemAdapter myOrdersItemAdapter = new MyOrdersItemAdapter(myOrdersItemModelList);
        myOrdersRecyclerView.setAdapter(myOrdersItemAdapter);
        myOrdersItemAdapter.notifyDataSetChanged();


        //todo:

//        final TextView textView = binding.textGallery;
//        myOrdersViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}