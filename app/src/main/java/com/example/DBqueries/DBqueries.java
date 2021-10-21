package com.example.DBqueries;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.unmall.Wishlist.WishlistModel;
import com.example.unmall.HmePage.HomePageAdapter;
import com.example.unmall.HmePage.HomePageMoel;
import com.example.unmall.category.CategoryAdapter;
import com.example.unmall.category.CategoryModel;
import com.example.unmall.horizontalproducts.HorizontalProductScrollModel;
import com.example.unmall.slider.SliderModel;
import com.example.unmall.ui.home.HomeFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class DBqueries {

    public static FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    public static FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
   public static FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    public static List<CategoryModel> categoryModelList = new ArrayList<>();



    //public static List<HomePageMoel> homePageMoelList = new ArrayList<>();

    public static List<List<HomePageMoel>> lists = new ArrayList<>();
    public static List<String> loadedCategoriesNames = new ArrayList<>();



    public static void  loadCategories(RecyclerView categoryRecyclerView, Context context){


        //todo: firebase
        firebaseFirestore.collection("CATEGORIES").orderBy("index").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            if (categoryModelList.size()>0){
                                categoryModelList.clear();
                            }
                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                                categoryModelList.add(new CategoryModel(documentSnapshot.get("icon").toString(), documentSnapshot.get("categoryName").toString()));
                            }
                            Log.d("oncomplete", "onComplete: "+categoryModelList.size());
                            CategoryAdapter categoryAdapter = new CategoryAdapter(categoryModelList);
                            categoryRecyclerView.setAdapter(categoryAdapter);
                            categoryAdapter.notifyDataSetChanged();
                        } else {
                            String error = task.getException().getMessage();
                            Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        //todo: firebase


    }
    public static void  loadFragmentData( RecyclerView homePageRecyclerView, Context context , final  int index, String categoryName){
        //todo : firevase
        firebaseFirestore.collection("CATEGORIES")
                .document(categoryName.toUpperCase())
                .collection("TOP_DEALS")
                .orderBy("index").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
//                            if (lists.size() > 0){
//                                lists.clear();
//                            }
                            if (lists.get(0).size()>0){
                                lists.get(0).clear();
                            }
                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
//                                Log.d(TAG, "onComplete main: "+documentSnapshot.size());
                                Log.d("mainList", "onComplete: "+lists.size());

                                if ((long) documentSnapshot.get("view_type") == 0){
                                    List<SliderModel> sliderModelList = new ArrayList<>();
                                    long no_of_banners = (long) documentSnapshot.get("no_of_banners");

                                    Log.d("passSlider", "onComplete: "+sliderModelList.size());
                                    for (long x = 1; x < no_of_banners + 1 ; x++){
                                        sliderModelList.add(new SliderModel(documentSnapshot.get("banner_" + x).toString(),
                                                documentSnapshot.get("banner_" + x + "_background").toString()));

                                    }
//                                    if (lists.size()>0){
                                    lists.get(index).add(new HomePageMoel(0,sliderModelList));

                                }else if ((long) documentSnapshot.get("view_type") == 1){
                                    lists.get(index).add(new HomePageMoel(1,documentSnapshot.get("strip_ad_banner").toString(),
                                            documentSnapshot.get("strip_ad_background").toString()));

                                }else if ((long) documentSnapshot.get("view_type") == 2){

                                    List<WishlistModel> viewAllProductList = new ArrayList<>();
                                    List<HorizontalProductScrollModel> horizontalProductScrollModelList = new ArrayList<>();
                                    long no_of_products = (long) documentSnapshot.get("no_of_products");
                                    for (long x = 1; x < no_of_products + 1 ; x++){
                                        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(documentSnapshot.get("product_ID_"+x).toString(),
                                                documentSnapshot.get("product_image_"+x).toString(),
                                                documentSnapshot.get("product_title_"+x).toString(),
                                                documentSnapshot.get("product_subtitle_"+x).toString(),
                                                documentSnapshot.get("product_price_"+x).toString()));

                                        viewAllProductList.add(new WishlistModel(documentSnapshot.get("product_image_"+x).toString(),
                                                documentSnapshot.get("product_full_title_"+x).toString(),
                                                (long)documentSnapshot.get("free_coupens_"+x),
                                                documentSnapshot.get("average_rating_"+x).toString(),
                                                (long)documentSnapshot.get("total_ratings_"+x),
                                                documentSnapshot.get("product_price_"+x).toString(),
                                                documentSnapshot.get("cutted_price_"+x).toString(),
                                                (boolean)documentSnapshot.get("COD_"+x)));
                                    }
                                    lists.get(index).add(new HomePageMoel(2, documentSnapshot.get("layout_title").toString(),
                                            documentSnapshot.get("layout_background").toString(),horizontalProductScrollModelList, viewAllProductList));

                                }else if ((long) documentSnapshot.get("view_type") == 3){
                                    List<HorizontalProductScrollModel> gridLayoutList = new ArrayList<>();
                                    long no_of_products = (long) documentSnapshot.get("no_of_products");
                                    for (long x = 1; x < no_of_products + 1 ; x++){
                                        gridLayoutList.add(new HorizontalProductScrollModel(documentSnapshot.get("product_ID_"+x).toString(),
                                                documentSnapshot.get("product_image_"+x).toString(),
                                                documentSnapshot.get("product_title_"+x).toString(),
                                                documentSnapshot.get("product_subtitle_"+x).toString(),
                                                documentSnapshot.get("product_price_"+x).toString()));
                                    }
                                    lists.get(index).add(new HomePageMoel(3, documentSnapshot.get("layout_title").toString(),
                                            documentSnapshot.get("layout_background").toString(),gridLayoutList));
                                }
                            }
                            HomePageAdapter homePageAdapter = new HomePageAdapter(lists.get(index));
                            homePageRecyclerView.setAdapter(homePageAdapter);
//                            adapter.notifyDataSetChanged();
                            homePageAdapter.notifyDataSetChanged();
                            HomeFragment.refreshLayout.setRefreshing(false);
                        } else {
                            String error = task.getException().getMessage();
                            Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }
}
