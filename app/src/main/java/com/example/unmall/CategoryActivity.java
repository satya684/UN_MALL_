package com.example.unmall;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.unmall.HmePage.HomePageAdapter;
import com.example.unmall.HmePage.HomePageMoel;
import com.example.unmall.Wishlist.WishlistModel;
import com.example.unmall.horizontalproducts.HorizontalProductScrollModel;
import com.example.unmall.slider.SliderModel;

import java.util.ArrayList;
import java.util.List;

import static com.example.DBqueries.DBqueries.lists;
import static com.example.DBqueries.DBqueries.loadFragmentData;
import static com.example.DBqueries.DBqueries.loadedCategoriesNames;

public class CategoryActivity extends AppCompatActivity {

    private RecyclerView categoryRecyclerview;

    private List<HomePageMoel> homePageMoelFakeList = new ArrayList<>();
    private HomePageAdapter adapter;


    @SuppressLint("RestrictedApi")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String title = getIntent().getStringExtra("CategoryName");
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Home page fake list to show the user
        List<SliderModel> sliderModelFakeList = new ArrayList<>();
        sliderModelFakeList.add(new SliderModel("","#FFFFFF"));
        sliderModelFakeList.add(new SliderModel("","#FFFFFF"));
        sliderModelFakeList.add(new SliderModel("","#FFFFFF"));
        sliderModelFakeList.add(new SliderModel("","#FFFFFF"));
        sliderModelFakeList.add(new SliderModel("","#FFFFFF"));
        sliderModelFakeList.add(new SliderModel("","#FFFFFF"));

        List<HorizontalProductScrollModel> horizontalProductScrollModelFakeList = new ArrayList<>();
        homePageMoelFakeList.add(new HomePageMoel(0,sliderModelFakeList));
        homePageMoelFakeList.add(new HomePageMoel(1,"","#ffffff"));
        homePageMoelFakeList.add(new HomePageMoel(3,"","#ffffff",horizontalProductScrollModelFakeList,new ArrayList<WishlistModel>()));
        homePageMoelFakeList.add(new HomePageMoel(2,"","#ffffff", horizontalProductScrollModelFakeList));


        horizontalProductScrollModelFakeList.add(new HorizontalProductScrollModel("","","","",""));
        horizontalProductScrollModelFakeList.add(new HorizontalProductScrollModel("","","","",""));
        horizontalProductScrollModelFakeList.add(new HorizontalProductScrollModel("","","","",""));
        horizontalProductScrollModelFakeList.add(new HorizontalProductScrollModel("","","","",""));
        horizontalProductScrollModelFakeList.add(new HorizontalProductScrollModel("","","","",""));
        horizontalProductScrollModelFakeList.add(new HorizontalProductScrollModel("","","","",""));
        horizontalProductScrollModelFakeList.add(new HorizontalProductScrollModel("","","","",""));
        horizontalProductScrollModelFakeList.add(new HorizontalProductScrollModel("","","","",""));

        // Home page fake list to show the user

        categoryRecyclerview = findViewById(R.id.category_recyclerview_afteropen);
        List<SliderModel> sliderModelList = new ArrayList<SliderModel>();
        List<HorizontalProductScrollModel> horizontalProductScrollModelList = new ArrayList<>();

        ///todo:  home page testing recycler view  code start
        LinearLayoutManager testingLayoutManager = new LinearLayoutManager(this);
        testingLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        categoryRecyclerview.setLayoutManager(testingLayoutManager);

        adapter = new HomePageAdapter(homePageMoelFakeList);

        HomePageAdapter adapter;
        int listPosition = 0;
        for (int x = 0; x < loadedCategoriesNames.size(); x++){
            if (loadedCategoriesNames.get(x).equals(title.toUpperCase())){
                listPosition = x;
            }
        }if (listPosition == 0){
            loadedCategoriesNames.add(title.toUpperCase());
            lists.add(new ArrayList<HomePageMoel>());
//            adapter = new HomePageAdapter(lists.get(loadedCategoriesNames.size() - 1));
            loadFragmentData(categoryRecyclerview,this,loadedCategoriesNames.size() - 1, title );
        }else {
            adapter = new HomePageAdapter(lists.get(listPosition));
        }
        adapter = new HomePageAdapter(homePageMoelFakeList);
        categoryRecyclerview.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        ///todo: testing recycler view  code end

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_icon, menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.main_search_icon){
            return true;
        }else  if (id == android.R.id.home){

            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}