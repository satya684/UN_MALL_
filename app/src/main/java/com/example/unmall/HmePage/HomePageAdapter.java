package com.example.unmall.HmePage;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.gridlayout.widget.GridLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.unmall.Wishlist.WishlistModel;
import com.example.unmall.ProductDetailsActivity;
import com.example.unmall.R;
import com.example.unmall.ViewAllActivity;
import com.example.unmall.horizontalproducts.HorizontalProductScrollModel;
import com.example.unmall.horizontalproducts.HorizontalProductsScrollAdapter;
import com.example.unmall.slider.SliderAdapter;
import com.example.unmall.slider.SliderModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomePageAdapter extends RecyclerView.Adapter {

    private List<HomePageMoel> homePageMoelList;
    private RecyclerView.RecycledViewPool recycledViewPool;
    private int lastPosition = -1;

    public HomePageAdapter(List<HomePageMoel> homePageMoelList) {
        this.homePageMoelList = homePageMoelList;
        Log.d("check", "HomePageAdapter: "+homePageMoelList.size());
        recycledViewPool = new RecyclerView.RecycledViewPool();
    }


    @Override
    public int getItemViewType(int position) {
        switch (homePageMoelList.get(position).getType()) {
            case 0:
                return HomePageMoel.BANNER_SLIDER;
            //////todo:strip code start

            case 1:
                return HomePageMoel.STRIP_AD_BANNER;
            //////todo:strip code end


            /////todo: code horizontal product view

            case 2:
                return HomePageMoel.HORIZONTAL_PRODUCT_VIEW;

            /////todo: code horizontal product view

            ////todo: code grid product view

            case 3:
                return  HomePageMoel.GRID_PRODUCT_VIEW;

            ///todo: code grid product view

            default:
                return -1;
        }

    }


    @NonNull
    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        switch (viewType) {
            case HomePageMoel.BANNER_SLIDER:
                View bannerSliderView = LayoutInflater.from(parent.getContext()).inflate(R.layout.sliding_ad_layout, parent, false);
                return new BannersliderViewHolder(bannerSliderView);
            //////todo:strip code start

            case HomePageMoel.STRIP_AD_BANNER:
                View strupAdView = LayoutInflater.from(parent.getContext()).inflate(R.layout.strip_ad_layout, parent, false);
                return new StripAdBannerViewHolder(strupAdView);
            //////todo:strip code end

            /////todo: code horizontal product view

            case HomePageMoel.HORIZONTAL_PRODUCT_VIEW:
                View horizontalProductView = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_scroll_layout, parent, false);
                return new HorizontalProductViewHolder(horizontalProductView);

            /////todo: code horizontal product view


            ///todo: code grid product view

            case HomePageMoel.GRID_PRODUCT_VIEW:
                View gridProductView = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_product_layout, parent, false);
                return new GridProductViewHolder(gridProductView);


            ///todo: code grid product view
            default:
                return null;
        }

    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder viewHolder, int position) {

        switch (homePageMoelList.get(position).getType()) {
            case HomePageMoel.BANNER_SLIDER:
                List<SliderModel> sliderModelList = homePageMoelList.get(position).getSliderModelList();
                ((BannersliderViewHolder) viewHolder).setBannerSliderViewPager(sliderModelList);
                break;
            //////todo:strip code start

            case HomePageMoel.STRIP_AD_BANNER:
                String resource = homePageMoelList.get(position).getResource();
                String color = homePageMoelList.get(position).getBackgroundColor();
                ((StripAdBannerViewHolder)viewHolder).setStrpAd(resource, color);
                break;
            //////todo:strip code end


            /////todo: code horizontal product view
            case HomePageMoel.HORIZONTAL_PRODUCT_VIEW:
                String layoutColor = homePageMoelList.get(position).getBackgroundColor();
               String horizontalLayoutTitle = homePageMoelList.get(position).getTitle();
               List<WishlistModel> viewAllProductList  = homePageMoelList.get(position).getViewAllProductList();
               List<HorizontalProductScrollModel> horizontalProductScrollModelList = homePageMoelList.get(position).getHorizontalProductScrollModelList();
                ((HorizontalProductViewHolder)viewHolder).setHorizontalProductLayout(horizontalProductScrollModelList, horizontalLayoutTitle,layoutColor,viewAllProductList);
                break;

            /////todo: code horizontal product view


            ///todo: code grid product view

            case HomePageMoel.GRID_PRODUCT_VIEW:
                String gridColor = homePageMoelList.get(position).getBackgroundColor();
                String gridLayoutTitle = homePageMoelList.get(position).getTitle();
                List<HorizontalProductScrollModel> gridHorizontalProductScrollModelList = homePageMoelList.get(position).getHorizontalProductScrollModelList();
                ((GridProductViewHolder)viewHolder).setGridProductLayout(gridHorizontalProductScrollModelList, gridLayoutTitle, gridColor);
                break;
            ///todo: code grid product view
            default:
                return;

        }
        if (lastPosition < position) {
            Animation animation = AnimationUtils.loadAnimation(viewHolder.itemView.getContext(), R.anim.fade_in);
            viewHolder.itemView.setAnimation(animation);
            lastPosition  = position;

        }
    }


    @Override
    public int getItemCount() {
        Log.d("TAG", "getItemCount: ");
        return homePageMoelList.size();
    }

    public class BannersliderViewHolder extends RecyclerView.ViewHolder {

        private ViewPager bannerSliderViewPager;
//                private List<SliderModel> sliderModelList;
        private int currentPage;
        private Timer timer;
        final private long DELAY_TIME = 2000;
        final private long PERIOD_TIME = 2000;
        private List<SliderModel> arrangedList;

        public BannersliderViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            bannerSliderViewPager = itemView.findViewById(R.id.banner_slider_view_pager);


        }


        private void setBannerSliderViewPager(final List<SliderModel> sliderModelList) {
            currentPage = 2;
            if (timer != null){
                timer.cancel();
            }
            //todo slider logic
            arrangedList = new ArrayList<>();
//            arrangedList.addAll(sliderModelList);
               for (int x = 0; x < sliderModelList.size(); x++) {
//                    arrangedList.set(x, sliderModelList.get(x)); //x = 0
                    arrangedList.add(sliderModelList.get(x)); //x = 0
                }
                arrangedList.add(0, sliderModelList.get(sliderModelList.size() - 2));
                arrangedList.add(1, sliderModelList.get(sliderModelList.size() - 1));

                arrangedList.add(sliderModelList.get(0));
                arrangedList.add(sliderModelList.get(1));
                //todo slider logic


                SliderAdapter sliderAdapter = new SliderAdapter(arrangedList);


                bannerSliderViewPager.setAdapter(sliderAdapter);
                bannerSliderViewPager.setClipToPadding(false);
                //todo: modified start
                bannerSliderViewPager.setPadding(10, 10, 10, 10);
                //todo: modified end

                bannerSliderViewPager.setCurrentItem(currentPage);

                ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    }

                    @Override
                    public void onPageSelected(int position) {

                        currentPage = position;

                    }

                    @Override
                    public void onPageScrollStateChanged(int position) {

                        if (position == ViewPager2.SCROLL_STATE_IDLE) {
                            pagerLooper(arrangedList);
                        }
                    }

                };
                bannerSliderViewPager.addOnPageChangeListener(onPageChangeListener);

                startBannerSlideShow(arrangedList);

                bannerSliderViewPager.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        pagerLooper(arrangedList);
                        stopBannerSlideShow();
                        if (event.getAction() == MotionEvent.ACTION_UP) {
                            startBannerSlideShow(arrangedList);

                        }
                        return false;
                    }
                });
        }
        private void pagerLooper(List<SliderModel> sliderModelList) {
            if (currentPage == sliderModelList.size() - 2) {
                currentPage = 2;
                bannerSliderViewPager.setCurrentItem(currentPage, false);

            }
            if (currentPage == 1) {
                currentPage = sliderModelList.size() - 3;
                bannerSliderViewPager.setCurrentItem(currentPage, false);

            }

        }
        private void startBannerSlideShow(List<SliderModel> sliderModelList) {
            Handler handler = new Handler();
            Runnable update = new Runnable() {
                @Override
                public void run() {
                    if (currentPage >= sliderModelList.size()) {
                        currentPage = 1;

                    }
                    bannerSliderViewPager.setCurrentItem(currentPage++, true);
                }
            };
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(update);
                }
            }, DELAY_TIME, PERIOD_TIME);
        }
        private void stopBannerSlideShow() {
            timer.cancel();
        }

    }

    public class StripAdBannerViewHolder extends RecyclerView.ViewHolder{

        private ImageView stripAdImage;
        private ConstraintLayout stripAdCantainer;
        public StripAdBannerViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            stripAdImage =itemView.findViewById(R.id.strip_ad_image);
            stripAdCantainer = itemView.findViewById(R.id.strip_ad_cantainer);

        }
        private void setStrpAd(String resource, String color){
            //stripAdImage.setImageResource(resource);
            Glide.with(itemView.getContext()).load(resource).apply(new RequestOptions().placeholder(R.drawable.place1)).into(stripAdImage);
            stripAdCantainer.setBackgroundColor(Color.parseColor(color));
        }
    }

    public class HorizontalProductViewHolder extends RecyclerView.ViewHolder{

        private ConstraintLayout container;
        private TextView horizontalLayoutTitle;
        private Button horizotalLayoutViewAllBtn;
        private RecyclerView horizontalRecyclerView;


        public HorizontalProductViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            container = itemView.findViewById(R.id.container);
            horizontalLayoutTitle = itemView.findViewById(R.id.horizontal_scroll_layout_title);
            horizotalLayoutViewAllBtn = itemView.findViewById(R.id.horizontal_scroll_viewall_btn);
            horizontalRecyclerView = itemView.findViewById(R.id.horizontal_scroll_layout_recyclerView);
            horizontalRecyclerView.setRecycledViewPool(recycledViewPool);

        }

//        private void setHorizontalProductLayout(List<HorizontalProductScrollModel> horizontalProductScrollModelList, String title){
//
//            horizontalLayoutTitle.setText(title);
//            horizotalLayoutViewAllBtn.setVisibility(View.VISIBLE);
//            if (horizontalProductScrollModelList.size() > 8){
//                horizotalLayoutViewAllBtn.setVisibility(View.VISIBLE);
//
//            }else {
//                //Log.v("checking","Passing");
//                horizotalLayoutViewAllBtn.setVisibility(View.INVISIBLE);
//            }

//            HorizontalProductsScrollAdapter horizontalProductsScrollAdapter = new HorizontalProductsScrollAdapter(horizontalProductScrollModelList);
//            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(itemView.getContext());
//            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//            horizontalRecyclerView.setLayoutManager(linearLayoutManager);
//            //Log.v("checking","Passing");
//            horizontalRecyclerView.setAdapter(horizontalProductsScrollAdapter);
//            horizontalProductsScrollAdapter.notifyDataSetChanged();


//
//        }
//    }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        private void setHorizontalProductLayout(List<HorizontalProductScrollModel> horizontalProductScrollModelList, String title, String color, List<WishlistModel> viewAllProductList){

            container.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(color)));
            horizontalLayoutTitle.setText(title);
            horizotalLayoutViewAllBtn.setVisibility(View.VISIBLE);
            horizotalLayoutViewAllBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ViewAllActivity.wishlistModelList = viewAllProductList;
                    Intent viewAllIntent = new Intent(itemView.getContext(), ViewAllActivity.class);
                    viewAllIntent.putExtra("layout_code",0);
                    viewAllIntent.putExtra("title", title);
                    itemView.getContext().startActivity(viewAllIntent);
                }
            });
//            if (horizontalProductScrollModelList.size() > 8){
//                horizotalLayoutViewAllBtn.setVisibility(View.VISIBLE);
//
//            }else {
//                horizotalLayoutViewAllBtn.setVisibility(View.INVISIBLE);
//            }


//            HorizontalProductViewHolder horizontalProductsScrollAdapter;
//            horizontalRecyclerView.setLayoutManager(new LinearLayoutManager(itemView.getContext(), RecyclerView.HORIZONTAL, false));
          //  horizontalProductScrollModelList = new ArrayList<>();
//           // horizontalProductsScrollAdapter = new HorizontalProductsScrollAdapter(itemView.getContext(), horizontalProductScrollModelList);
//            horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.phone, "Reealme 5A", "gfgfhgfbcgfdfgcgn", "Rs. 49999/-"));
//            horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.phone, "Reealme 5A", "gfgfhgfbcgfdfgcgn", "Rs. 49999/-"));
//            horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.phone, "Reealme 5A", "gfgfhgfbcgfdfgcgn", "Rs. 49999/-"));
//            horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.phone, "Reealme 5A", "gfgfhgfbcgfdfgcgn", "Rs. 49999/-"));
//            horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.phone, "Reealme 5A", "gfgfhgfbcgfdfgcgn", "Rs. 49999/-"));
//            horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.phone, "Reealme 5A", "gfgfhgfbcgfdfgcgn", "Rs. 49999/-"));
//            horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.phone, "Reealme 5A", "gfgfhgfbcgfdfgcgn", "Rs. 49999/-"));
//            horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.phone, "Reealme 5A", "gfgfhgfbcgfdfgcgn", "Rs. 49999/-"));
//            horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.phone, "Reealme 5A", "gfgfhgfbcgfdfgcgn", "Rs. 49999/-"));
//            horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.phone, "Reealme 5A", "gfgfhgfbcgfdfgcgn", "Rs. 49999/-"));
//            horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.phone, "Reealme 5A", "gfgfhgfbcgfdfgcgn", "Rs. 49999/-"));
//            horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.phone, "Reealme 5A", "gfgfhgfbcgfdfgcgn", "Rs. 49999/-"));
//            horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.phone, "Reealme 5A", "gfgfhgfbcgfdfgcgn", "Rs. 49999/-"));
//            horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.phone, "Reealme 5A", "gfgfhgfbcgfdfgcgn", "Rs. 49999/-"));

//            Log.e("Horizontal","Layout-->"+new Gson().toJson(horizontalProductScrollModelList));
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(itemView.getContext(), RecyclerView.HORIZONTAL,false);
            horizontalRecyclerView.setLayoutManager(linearLayoutManager);
            HorizontalProductsScrollAdapter horizontalProductsScrollAdapter = new HorizontalProductsScrollAdapter(itemView.getContext(),horizontalProductScrollModelList);
            horizontalRecyclerView.setAdapter(horizontalProductsScrollAdapter);
            horizontalProductsScrollAdapter.notifyDataSetChanged();

        }
    }

    public class GridProductViewHolder extends RecyclerView.ViewHolder{

        private ConstraintLayout gridContainer;
        private TextView gridLayoutTitle;
        private Button gridLayoutViewAllBtn;
        private GridLayout gridProductLayout;


//        private GridLayout gridProductLayout;

        public GridProductViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            gridContainer = itemView.findViewById(R.id.grid_container);
            gridLayoutTitle = itemView.findViewById(R.id.grid_product_layout_title);
            gridLayoutViewAllBtn = itemView.findViewById(R.id.grid_product_layout_viewall_btn);
            gridProductLayout = itemView.findViewById(R.id.grid_layout);
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        private  void setGridProductLayout(List<HorizontalProductScrollModel> horizontalProductScrollModelList, String title, String color){
            gridContainer.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(color)));
            gridLayoutTitle.setText(title);

            for (int x = 0; x < 4; x++){
                ImageView productImage = gridProductLayout.getChildAt(x).findViewById(R.id.h_s_product_image);
                TextView productTitle = gridProductLayout.getChildAt(x).findViewById(R.id.h_s_product_title);
                TextView productDiscription = gridProductLayout.getChildAt(x).findViewById(R.id.h_s_product_description);
                TextView productPrice = gridProductLayout.getChildAt(x).findViewById(R.id.h_s_product_price);

//                productImage.setImageResource(horizontalProductScrollModelList.get(x).getProductImage());
                Glide.with(itemView.getContext()).load(horizontalProductScrollModelList.get(x).getProductImage()).apply(new RequestOptions().placeholder(R.drawable.placeholder)).into(productImage);
                productTitle.setText(horizontalProductScrollModelList.get(x).getProductTitle());
                productDiscription.setText(horizontalProductScrollModelList.get(x).getProductDescription()+"/-");
                productPrice.setText("Rs."+horizontalProductScrollModelList.get(x).getProductPrice());
                gridProductLayout.getChildAt(x).setBackgroundColor(Color.parseColor("#ffffff"));

                if (!title.equals("")) {
                    gridProductLayout.getChildAt(x).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent productDetaitsIntent = new Intent(itemView.getContext(), ProductDetailsActivity.class);
                            itemView.getContext().startActivity(productDetaitsIntent);
                        }
                    });

                }

            }

            if (!title.equals("")) {
                gridLayoutViewAllBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ViewAllActivity.horizontalProductScrollModelList = horizontalProductScrollModelList;
                        Intent viewAllIntent = new Intent(itemView.getContext(), ViewAllActivity.class);
                        viewAllIntent.putExtra("layout_code", 1);
                        viewAllIntent.putExtra("title", title);
                        itemView.getContext().startActivity(viewAllIntent);
                    }
                });
            }
        }
    }
}
