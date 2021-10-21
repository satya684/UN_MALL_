package com.example.unmall;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;

import com.example.unmall.Wishlist.WishlistAdapter;
import com.example.unmall.Wishlist.WishlistModel;
import com.example.unmall.gridProductLayout.GridProductLayoutAdapter;
import com.example.unmall.horizontalproducts.HorizontalProductScrollModel;

import java.util.List;

public class ViewAllActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private GridView gridView;
    public static List<HorizontalProductScrollModel> horizontalProductScrollModelList;
    public   static List<WishlistModel> wishlistModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(getIntent().getStringExtra("title"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.recyclerview);
        gridView = findViewById(R.id.grid_view);
        int layout_code = getIntent().getIntExtra("layout_code",-1);

        if (layout_code == 0) {
            recyclerView.setVisibility(View.VISIBLE);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);

//            List<WishlistModel> wishlistModelList = new ArrayList<>();
//            wishlistModelList.add(new WishlistModel(R.drawable.phone, "Pixel 2", 1, "3", 154, "Rs. 45559/-", "Rs. 656666/-", "Cash on delivery"));
//            wishlistModelList.add(new WishlistModel(R.drawable.phone, "Pixel 1", 10, "1", 154, "Rs. 45559/-", "Rs. 656666/-", "Cash on delivery"));
//            wishlistModelList.add(new WishlistModel(R.drawable.phone, "Pixel 2", 2, "3", 154, "Rs. 45559/-", "Rs. 656666/-", "Cash on delivery"));
//            wishlistModelList.add(new WishlistModel(R.drawable.phone, "Pixel 8", 1, "4", 154, "Rs. 45559/-", "Rs. 656666/-", "Cash on delivery"));
//            wishlistModelList.add(new WishlistModel(R.drawable.phone, "Pixel 2", 1, "3", 154, "Rs. 45559/-", "Rs. 656666/-", "Cash on delivery"));
//            wishlistModelList.add(new WishlistModel(R.drawable.phone, "Pixel 1", 10, "1", 154, "Rs. 45559/-", "Rs. 656666/-", "Cash on delivery"));
//            wishlistModelList.add(new WishlistModel(R.drawable.phone, "Pixel 2", 2, "3", 154, "Rs. 45559/-", "Rs. 656666/-", "Cash on delivery"));
//            wishlistModelList.add(new WishlistModel(R.drawable.phone, "Pixel 8", 1, "4", 154, "Rs. 45559/-", "Rs. 656666/-", "Cash on delivery"));
//            wishlistModelList.add(new WishlistModel(R.drawable.phone, "Pixel 5", 15, "3", 154, "Rs. 45559/-", "Rs. 656666/-", "Cash on delivery"));
//            wishlistModelList.add(new WishlistModel(R.drawable.phone, "Pixel 2", 0, "0", 154, "Rs. 45559/-", "Rs. 656666/-", "Cash on delivery"));
//            wishlistModelList.add(new WishlistModel(R.drawable.phone, "Pixel 5", 15, "3", 154, "Rs. 45559/-", "Rs. 656666/-", "Cash on delivery"));
//            wishlistModelList.add(new WishlistModel(R.drawable.phone, "Pixel 2", 0, "0", 154, "Rs. 45559/-", "Rs. 656666/-", "Cash on delivery"));


            WishlistAdapter adapter = new WishlistAdapter(wishlistModelList, false);
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }else if (layout_code == 1) {
            gridView.setVisibility(View.VISIBLE);


            GridProductLayoutAdapter gridProductLayoutAdapter = new GridProductLayoutAdapter(horizontalProductScrollModelList);
            gridView.setAdapter(gridProductLayoutAdapter);
        }


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
      int id = item.getItemId();
      if (id == android.R.id.home ){
          finish();
          return true;
      }
        return super.onOptionsItemSelected(item);
    }
}