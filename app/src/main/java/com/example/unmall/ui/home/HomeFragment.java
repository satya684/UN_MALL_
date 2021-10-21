package com.example.unmall.ui.home;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.example.unmall.HmePage.HomePageAdapter;
import com.example.unmall.HmePage.HomePageMoel;
import com.example.unmall.R;
import com.example.unmall.Wishlist.WishlistModel;
import com.example.unmall.category.CategoryAdapter;
import com.example.unmall.category.CategoryModel;
//import com.example.unmall.databinding.FragmentHomeBinding;
import com.example.unmall.horizontalproducts.HorizontalProductScrollModel;
import com.example.unmall.horizontalproducts.HorizontalProductsScrollAdapter;
import com.example.unmall.slider.SliderModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static com.example.DBqueries.DBqueries.categoryModelList;
import static com.example.DBqueries.DBqueries.firebaseFirestore;
import static com.example.DBqueries.DBqueries.lists;
import static com.example.DBqueries.DBqueries.loadCategories;
import static com.example.DBqueries.DBqueries.loadFragmentData;
import static com.example.DBqueries.DBqueries.loadedCategoriesNames;

public class HomeFragment extends Fragment {

    private static final String TAG = HomeFragment.class.getSimpleName();

    // fake list to show the user
    private List<CategoryModel> categoryModelFakeList = new ArrayList<>();
    private List<HomePageMoel> homePageMoelFakeList = new ArrayList<>();
    // fake list to show the user


    //todo: category rec
    private ConnectivityManager connectivityManager;
    private NetworkInfo networkInfo;
    private Button retryBtn;


    RecyclerView categoryRecyclerView;
    CategoryAdapter categoryAdapter;
    private RecyclerView homePageRecyclerView;
    private HomePageAdapter adapter;


    public static SwipeRefreshLayout refreshLayout;
    private ImageView noInternetConnection;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        //todo: checking connection first if condition
        //todo: checking connection
        refreshLayout = view.findViewById(R.id.refresh_layout);
        refreshLayout.setColorSchemeColors(getContext().getResources().getColor(R.color.red),
                getContext().getResources().getColor(R.color.red),
                getContext().getResources().getColor(R.color.red));
        noInternetConnection = view.findViewById(R.id.no_internet_connection);
        connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        networkInfo = connectivityManager.getActiveNetworkInfo();
        retryBtn = view.findViewById(R.id.retry_btn);
        categoryRecyclerView = view.findViewById(R.id.category_recyclerView);
        homePageRecyclerView = view.findViewById(R.id.home_page_recyclerview);


        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        categoryRecyclerView.setLayoutManager(layoutManager);

        LinearLayoutManager testingLayoutManager = new LinearLayoutManager(getContext());
        testingLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        homePageRecyclerView.setLayoutManager(testingLayoutManager);


        // category fake list to show the user

        categoryModelFakeList.add(new CategoryModel("null", ""));
        categoryModelFakeList.add(new CategoryModel("", ""));
        categoryModelFakeList.add(new CategoryModel("", ""));
        categoryModelFakeList.add(new CategoryModel("", ""));
        categoryModelFakeList.add(new CategoryModel("", ""));
        categoryModelFakeList.add(new CategoryModel("", ""));
        categoryModelFakeList.add(new CategoryModel("", ""));
        categoryModelFakeList.add(new CategoryModel("", ""));

        //category  fake list to show the user

        // Home page fake list to show the user
        List<SliderModel> sliderModelFakeList = new ArrayList<>();
        Log.d("slider", "onCreateView: " + sliderModelFakeList.size());
        sliderModelFakeList.add(new SliderModel("", "#dfdfdf"));
        sliderModelFakeList.add(new SliderModel("", "#dfdfdf"));
        sliderModelFakeList.add(new SliderModel("", "#dfdfdf"));
        sliderModelFakeList.add(new SliderModel("", "#dfdfdf"));
        sliderModelFakeList.add(new SliderModel("", "#dfdfdf"));
        sliderModelFakeList.add(new SliderModel("", "#dfdfdf"));

        List<HorizontalProductScrollModel> horizontalProductScrollModelFakeList = new ArrayList<>();
        homePageMoelFakeList.add(new HomePageMoel(0, sliderModelFakeList));
        homePageMoelFakeList.add(new HomePageMoel(1, "", "#dfdfdf"));
        homePageMoelFakeList.add(new HomePageMoel(3, "", "#dfdfdf", horizontalProductScrollModelFakeList, new ArrayList<WishlistModel>()));
        homePageMoelFakeList.add(new HomePageMoel(2, "", "#dfdfdf", horizontalProductScrollModelFakeList));


        horizontalProductScrollModelFakeList.add(new HorizontalProductScrollModel("", "", "", "", ""));
        horizontalProductScrollModelFakeList.add(new HorizontalProductScrollModel("", "", "", "", ""));
        horizontalProductScrollModelFakeList.add(new HorizontalProductScrollModel("", "", "", "", ""));
        horizontalProductScrollModelFakeList.add(new HorizontalProductScrollModel("", "", "", "", ""));
        horizontalProductScrollModelFakeList.add(new HorizontalProductScrollModel("", "", "", "", ""));
        horizontalProductScrollModelFakeList.add(new HorizontalProductScrollModel("", "", "", "", ""));
        horizontalProductScrollModelFakeList.add(new HorizontalProductScrollModel("", "", "", "", ""));
        horizontalProductScrollModelFakeList.add(new HorizontalProductScrollModel("", "", "", "", ""));

        // Home page fake list to show the user

        categoryAdapter = new CategoryAdapter(categoryModelFakeList);
//        categoryRecyclerView.setAdapter(categoryAdapter);
        adapter = new HomePageAdapter(homePageMoelFakeList);
//        homePageRecyclerView.setAdapter(adapter);

        //todo: checking connection
        if (networkInfo != null && networkInfo.isConnected() == true) {
            noInternetConnection.setVisibility(View.GONE);
            retryBtn.setVisibility(View.GONE);
            // todo here
            categoryRecyclerView.setVisibility(View.VISIBLE);
            homePageRecyclerView.setVisibility(View.VISIBLE);
            // todo here
            if (categoryModelList.size() == 0) {
                loadCategories(categoryRecyclerView, getContext());
            } else {
                categoryAdapter = new CategoryAdapter(categoryModelList); // ye first home pe jo hai vo haina .. home icon ka han
                Log.d("createview", "onCreateView: " + categoryModelList.size());
                categoryAdapter.notifyDataSetChanged();
            }
            categoryRecyclerView.setAdapter(categoryAdapter);
            if (lists.size() == 0) {
                loadedCategoriesNames.add("HOME");
                lists.add(new ArrayList<HomePageMoel>());
                loadFragmentData(homePageRecyclerView, getContext(), 0, "HOME");
            } else {
                adapter = new HomePageAdapter(lists.get(0));
                adapter.notifyDataSetChanged();
            }
            homePageRecyclerView.setAdapter(adapter);

        } else {
            // todo here
            categoryRecyclerView.setVisibility(View.GONE);
            homePageRecyclerView.setVisibility(View.GONE);
            // todo here
            Glide.with(this).load(R.drawable.internet).into(noInternetConnection);
            noInternetConnection.setVisibility(View.VISIBLE);
            retryBtn.setVisibility(View.VISIBLE);

        }
        ///todo refresh code

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshLayout.setRefreshing(true);
                reloadPage();
            }
        });
        ///todo refresh code
        retryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reloadPage();
            }
        });


        return view;
    }

    private void reloadPage() {
        // todo here
        networkInfo = connectivityManager.getActiveNetworkInfo();

        // todo here
        categoryModelList.clear();
        lists.clear();
        loadedCategoriesNames.clear();

        if (networkInfo != null && networkInfo.isConnected() == true) {
            noInternetConnection.setVisibility(View.GONE);
            retryBtn.setVisibility(View.GONE);
            // todo here
            categoryRecyclerView.setVisibility(View.VISIBLE);
            homePageRecyclerView.setVisibility(View.VISIBLE);
            // todo here

            categoryAdapter = new CategoryAdapter(categoryModelFakeList);
            adapter = new HomePageAdapter(homePageMoelFakeList);

            categoryRecyclerView.setAdapter(categoryAdapter);
            homePageRecyclerView.setAdapter(adapter);


            loadCategories(categoryRecyclerView, getContext());

            loadedCategoriesNames.add("HOME");
            lists.add(new ArrayList<HomePageMoel>());
            loadFragmentData(homePageRecyclerView, getContext(), 0, "HOME");
        } else {
            Toast.makeText(getContext(), "No internet connection found !", Toast.LENGTH_SHORT).show();
            // todo here
            categoryRecyclerView.setVisibility(View.GONE);
            homePageRecyclerView.setVisibility(View.GONE);
            // todo here
            Glide.with(getContext()).load(R.drawable.internet).into(noInternetConnection);
            noInternetConnection.setVisibility(View.VISIBLE);
            retryBtn.setVisibility(View.VISIBLE);
            refreshLayout.setRefreshing(false);
        }
        adapter = new HomePageAdapter(homePageMoelFakeList);
        adapter.notifyDataSetChanged();
    }



    @Override
    public void onStart() {
        super.onStart();

        categoryModelList.clear();
        lists.clear();
        loadedCategoriesNames.clear();

        if(networkInfo != null && networkInfo.isConnected() == true) {
            noInternetConnection.setVisibility(View.GONE);
            retryBtn.setVisibility(View.GONE);

            categoryAdapter = new CategoryAdapter(categoryModelFakeList);
            adapter = new HomePageAdapter(homePageMoelFakeList);

            categoryRecyclerView.setAdapter(categoryAdapter);
            homePageRecyclerView.setAdapter(adapter);


            loadCategories(categoryRecyclerView, getContext());

            loadedCategoriesNames.add("HOME");
            lists.add(new ArrayList<HomePageMoel>());
            loadFragmentData(homePageRecyclerView,getContext(),0, "HOME");
        }else {
            Glide.with(getContext()).load(R.drawable.internet).into(noInternetConnection);
            noInternetConnection.setVisibility(View.VISIBLE);
            retryBtn.setVisibility(View.VISIBLE);
        }
        adapter = new HomePageAdapter(homePageMoelFakeList);
        adapter.notifyDataSetChanged();
    }

}