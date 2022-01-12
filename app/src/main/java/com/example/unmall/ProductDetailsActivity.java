package com.example.unmall;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.unmall.ProductImages.ProductImagesAdapter;
import com.example.unmall.ProductSpecification.ProductSpecificationModel;
import com.example.unmall.ProductsDetails.ProductDetailsAdapter;
import com.example.unmall.Rewards.MyRewardsAdapter;
import com.example.unmall.Rewards.RewardModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import static com.example.unmall.MainActivity.showCart;
import static com.example.unmall.RegisterActivity.setSignUpFragment;

public class ProductDetailsActivity extends AppCompatActivity {

    //todo: product Image Layout// reward layout
    private TextView productTitle;
    private TextView averageRating;
    private TextView totalRatings;
    private TextView productPrice;
    private TextView cuttedPrice;
    TabLayoutMediator tabLayoutMediator;
    private TextView tvcodIndicator;
    private ImageView codImage;
    private FirebaseUser currentUser;

    private TextView rewardTitle;
    private TextView rewardBody;
    //todo: product Image Layout// reward layout

    private Dialog signInDialog;

    // todo: product description
    private List<ProductSpecificationModel> productSpecificationModelList = new ArrayList<>();
    private  String productDescription;
    private String productOtherDetails;
//    public static  int tabPosition = -1;

    private ConstraintLayout productDetailsTabContainer;
    private ConstraintLayout productDetailContainer;

    private TextView productOnlyDescriptionBody;
    // todo: product description

    private ViewPager productImagesViewPager ;
    private TabLayout viewPagerIndicator;
    private LinearLayout coupenRedemptionLayout;
    private Button coupenRedeemBtn;

    //todo coupenDialog
    public static TextView coupenTitle;
    public static TextView coupenExpirtDate;
    public static  TextView coupenBody;
    private static RecyclerView coupensRecyclerview;
    private static LinearLayout selectedCoupen;


    //todo coupenDialog

    private ViewPager2 productDetailsViewPager;
    private TabLayout productDetalsTabLayout;


    private Button buyNowBtn;
    private LinearLayout addToCartButton;

    //todo: rating layoout
    private TextView  raAverageRating;
    private LinearLayout ratingsProgressBarContainer;
    private LinearLayout ratingsNoContainer;
    private TextView pDTotalRating;
    private  TextView totalRatingFigure;
    private LinearLayout rateNowContainer;
    //todo: rating layoout

    ProductDetailsAdapter productDetailsAdapter;

    private static boolean ALREADY_ADDED_TO_WISHLIST = false;
    private FloatingActionButton addToWishlistBtn;

    private FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //todo cotinue btn code
        buyNowBtn =  findViewById(R.id.buy_now_btn);
        addToCartButton = findViewById(R.id.add_to_cart_btn);
        //todo cotinue btn code


        coupenRedeemBtn = findViewById(R.id.cupon_redemption_btn);
        coupenRedemptionLayout = findViewById(R.id.cupon_redeemption_layout_cart);

        productImagesViewPager  = findViewById(R.id.product_images_viewPager);
        viewPagerIndicator = findViewById(R.id.viewPager_Indicator);
        addToWishlistBtn = findViewById(R.id.add_To_Wishlist_Btn);

        productDetailsViewPager = findViewById(R.id.product_details_viewPager);
        productDetalsTabLayout = findViewById(R.id.products_details_tabLayout);

        //todo: product Image Layout // reward layout
        firebaseFirestore = FirebaseFirestore.getInstance();
        productTitle = findViewById(R.id.product_title);
        averageRating = findViewById(R.id.tv_product_rating_miniview);
        totalRatings = findViewById(R.id.total_ratings_miniview);
        productPrice = findViewById(R.id.product_price);
        cuttedPrice = findViewById(R.id.cutted_price);
        tvcodIndicator = findViewById(R.id.tv_cod_indicator_);
        codImage = findViewById(R.id.cod_indicator_imageview);

        rewardTitle = findViewById(R.id.reward_title);
        rewardBody = findViewById(R.id.reward_body);
        //todo: product Image Layout // rewaed layout


        // todo: product description
        productOnlyDescriptionBody = findViewById(R.id.product_details_body);
        productDetailsTabContainer = findViewById(R.id.product_details_tabs_container);
        productDetailContainer = findViewById(R.id.product_details_container);

        // todo: product description

        //todo: rating layoout
        ratingsProgressBarContainer = findViewById(R.id.ratings_progresBar_container);
        ratingsNoContainer = findViewById(R.id.ratings_numbers_container);
        pDTotalRating = findViewById(R.id.total_ratings);
        totalRatingFigure = findViewById(R.id.total_ratings_figure);
        raAverageRating = findViewById(R.id.avarage_rating);
        rateNowContainer = findViewById(R.id.rate_now_container);


        for (int x = 0; x< rateNowContainer.getChildCount(); x++){
            final  int starPosition = x;
            rateNowContainer.getChildAt(x).setOnClickListener(new View.OnClickListener() {
               //automatic
                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                //automatic
                @Override
                public void onClick(View v) {
                    if (currentUser == null){
                        signInDialog.show();
                    }else {
                        setRating(starPosition);
                    }
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
            });

        }
        //todo: rating layoout


        //todo cotinue btn code
        buyNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentUser == null){
                    signInDialog.show();
                }else {
                    Intent deliveryIntent = new Intent(ProductDetailsActivity.this, DeliveryActivity.class);
                    startActivity(deliveryIntent);
                }
            }
        });

        addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentUser == null){
                    signInDialog.show();
                }else {
                    //todo raadd to cart
                }
            }
        });
        //todo cotinue btn code

        ///todo : coupen dialog btn end

         Dialog checkCoupenPriceDialog = new Dialog(ProductDetailsActivity.this);
                checkCoupenPriceDialog.setContentView(R.layout.coupen_redeem_dialog);
                checkCoupenPriceDialog.setCancelable(true);
                checkCoupenPriceDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                ImageView toggleRecyclerview = checkCoupenPriceDialog.findViewById(R.id.toggle_recyclerview);
                coupensRecyclerview = checkCoupenPriceDialog.findViewById(R.id.coupens_recyclerview);
                selectedCoupen = checkCoupenPriceDialog.findViewById(R.id.selected_coupens);

                //todo reward Dialog
                coupenTitle = checkCoupenPriceDialog.findViewById(R.id.reward_coupen_title);
                coupenExpirtDate = checkCoupenPriceDialog.findViewById(R.id.reward_coupen_validity);
                coupenBody = checkCoupenPriceDialog.findViewById(R.id.reward_coupen_body);
                //todo reward Dialog

                TextView  orignalPrice = checkCoupenPriceDialog.findViewById(R.id.orignal_price);
                TextView discountPrice = checkCoupenPriceDialog.findViewById(R.id.discount_price);


                LinearLayoutManager layoutManager = new LinearLayoutManager(ProductDetailsActivity.this);
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                coupensRecyclerview.setLayoutManager(layoutManager);

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

                MyRewardsAdapter myRewardsAdapter = new MyRewardsAdapter(rewardModelList, true);
                coupensRecyclerview.setAdapter(myRewardsAdapter);
                myRewardsAdapter.notifyDataSetChanged();

                toggleRecyclerview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDialogRecyclerView();
                    }
                });

        coupenRedeemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkCoupenPriceDialog.show();
            }
        });

        ///todo : coupen dialog btn end



//        List<String> productImages = new ArrayList<>();
//        productImages.add(R.drawable.home);
//        productImages.add(R.drawable.cart_black);
//        productImages.add(R.drawable.greenicon);
//        productImages.add(R.drawable.redicon);
        List<String> productImages = new ArrayList<>();
        //YWM9Zz4vvlPYLK316w6U
        try {
            firebaseFirestore.collection("PRODUCTS").document("YWM9Zz4vvlPYLK316w6U"/*getIntent().getStringExtra("PRODUCT_ID")*/)
                    .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull @NotNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot documentSnapshot = task.getResult();
                            for (long x = 1; x < (long) documentSnapshot.get("no_of_product_images") + 1; x++) {
                                productImages.add(documentSnapshot.get("product_image_" + x).toString());

                            }

                        ProductImagesAdapter productImagesAdapter = new ProductImagesAdapter(productImages);
                        productImagesViewPager.setAdapter(productImagesAdapter);

                        productTitle.setText(documentSnapshot.get("product_title").toString());
                        averageRating.setText(documentSnapshot.get("average_rating").toString());
                        totalRatings.setText("(" + (long) documentSnapshot.get("total_ratings") + ")raings");
                        productPrice.setText("Rs." + documentSnapshot.get("product_price").toString() + "/-");
                        cuttedPrice.setText("Rs." + documentSnapshot.get("cutted_price").toString() + "/-");
                        if ((boolean) documentSnapshot.get("COD")) {
                            codImage.setVisibility(View.VISIBLE);
                            tvcodIndicator.setVisibility(View.VISIBLE);
                        } else {
                            codImage.setVisibility(View.INVISIBLE);
                            tvcodIndicator.setVisibility(View.INVISIBLE);
                        }

                        rewardTitle.setText((long) documentSnapshot.get("free_coupens") + documentSnapshot.get("free_coupen_title").toString());
                        rewardBody.setText(documentSnapshot.get("free_coupen_body").toString());

                        if ((boolean) documentSnapshot.get("use_tab_layout")) {
                            productDetailsTabContainer.setVisibility(View.VISIBLE);
                            productDetailContainer.setVisibility(View.GONE);
                            productDescription = documentSnapshot.get("product_description").toString();
//                        ProductSpecificationFragment.productSpecificationModelList = new ArrayList<>();
                            productOtherDetails = documentSnapshot.get("product_other_details").toString();
                            for (long x = 1; x < (long) documentSnapshot.get("total_spec_title") + 1; x++) {
                                productSpecificationModelList.add(new ProductSpecificationModel(0, documentSnapshot.get("spec_title_" + x).toString()));
                                for (long y = 1; y < (long) documentSnapshot.get("spec_title_" + x + "_total_fields") + 1; y++) {
                                    productSpecificationModelList.add(new ProductSpecificationModel(1,
                                            documentSnapshot.get("spec_title_" + x + "_field_" + y + "_name").toString(),
                                            documentSnapshot.get("spec_title_" + x + "_field_" + y + "_value").toString()));

                                }

                            }
                        } else {
                            productDetailsTabContainer.setVisibility(View.GONE);
                            productDetailContainer.setVisibility(View.VISIBLE);
                            productOnlyDescriptionBody.setText(documentSnapshot.get("product_description").toString());
                        }


                        pDTotalRating.setText((long) documentSnapshot.get("total_ratings") + " ratings");
                        for (int z = 0; z < 5; z++) {
                            TextView rating = (TextView) ratingsNoContainer.getChildAt(z);
                            rating.setText(String.valueOf((long) documentSnapshot.get((5 - z) + "_star")));

                            ProgressBar progressBar = (ProgressBar) ratingsProgressBarContainer.getChildAt(z);
                            int maxProgress = Integer.parseInt(String.valueOf((long) documentSnapshot.get("total_ratings")));
                            progressBar.setMax(maxProgress);
                            progressBar.setProgress(Integer.parseInt(String.valueOf((long) documentSnapshot.get((5 - z) + "_star"))));
                        }

                        totalRatingFigure.setText(String.valueOf((long) documentSnapshot.get("total_ratings")));
                        raAverageRating.setText(documentSnapshot.get("average_rating").toString());
                        //FragmentManager fragmentManager = getSupportFragmentManager();
                        productDetailsViewPager.setAdapter(new ProductDetailsAdapter(getSupportFragmentManager(), getLifecycle(), productDescription, productOtherDetails, productSpecificationModelList, productDetalsTabLayout.getTabCount()));
                        tabLayoutMediator.attach();// here is occuring problem my usb shwing problem right now

                        // productDetailsViewPager.setAdapter(new ProductDetailsAdapter(getSupportFragmentManager(), productDescription, productOtherDetails, productSpecificationModelList, productDetalsTabLayout.getTabCount()));

                    } else {
                        String error = task.getException().getMessage();
                        Toast.makeText(ProductDetailsActivity.this, error, Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }catch (NullPointerException e){
            Log.e("productDetails","ProductDetails");
        }

        viewPagerIndicator.setupWithViewPager(productImagesViewPager, true);


        addToWishlistBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (currentUser == null){
                    signInDialog.show();
                }else {
                    if (ALREADY_ADDED_TO_WISHLIST) {
                        ALREADY_ADDED_TO_WISHLIST = false;
                        addToWishlistBtn.setSupportImageTintList(ColorStateList.valueOf(Color.parseColor("#9e9e9e")));
                    } else {
                        ALREADY_ADDED_TO_WISHLIST = true;
                        addToWishlistBtn.setSupportImageTintList(getResources().getColorStateList(R.color.red));
                    }
                }
            }
        });

        //todo: sliding description
//        ProductDetailsAdapter productDetailsAdapter = new ProductDetailsAdapter(getSupportFragmentManager(),getLifecycle(),)
//        productDetailsViewPager.setAdapter(new ProductDetailsAdapter(this, productDetalsTabLayout.getTabCount()));
        productDetalsTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                productDetailsViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
         tabLayoutMediator = new TabLayoutMediator(productDetalsTabLayout, productDetailsViewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull @NotNull TabLayout.Tab tab, int position) {

                switch (position){
                    case 0:{
                        tab.setText("DESCRIPTION");
                        break;
                    }
                    case 1:{
                        tab.setText("SPECIFICATION");
                        break;
                    }
                    case 2:{
                        tab.setText("OTHER DETAILS");
                        break;
                    }
                }

            }
        });


        productDetalsTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                productDetailsViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        //todo: sliding description


        //todo signIn dialog
        signInDialog = new Dialog(ProductDetailsActivity.this);
        signInDialog.setContentView(R.layout.sign_in_dialog);
        signInDialog.setCancelable(true);
        signInDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        Button dSignInBtn = signInDialog.findViewById(R.id.d_sign_in_button);
        Button dSignUpBtn = signInDialog.findViewById(R.id.d_sign_up_btn);

        Intent registerIntent = new Intent(ProductDetailsActivity.this, RegisterActivity.class);

        dSignInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignInFragment.disableCloseBtn= true;
                SignUpFragment.disableCloseBtn = true;
                signInDialog.dismiss();
                setSignUpFragment = false;
                startActivity(registerIntent);
            }
        });

//        if (currentUser == null){
//            coupenRedemptionLayout.setVisibility(View.GONE);
//        }

        dSignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignInFragment.disableCloseBtn= true;
                SignUpFragment.disableCloseBtn = true;
                signInDialog.dismiss();
                setSignUpFragment = true;
                startActivity(registerIntent);
            }
        });
        //todo signIn dialog


    }

    @Override
    protected void onStart() {
        super.onStart();
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser == null){
            coupenRedemptionLayout.setVisibility(View.GONE);
        }else {
            coupenRedemptionLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search_and_cart_icon, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

       if (id == android.R.id.home){
           finish();
           return true;
       }else if (id == R.id.main_search_icon){
           return true;
       }else if (id == R.id.main_cart_icon){
           if (currentUser == null){
               signInDialog.show();
           }else {
               Intent cartIntent = new Intent(ProductDetailsActivity.this, MainActivity.class);
               showCart = true;
               startActivity(cartIntent);
               return true;
           }
       }

        return super.onOptionsItemSelected(item);
    }
    public static  void showDialogRecyclerView(){
        if (coupensRecyclerview.getVisibility() == View.GONE){
            coupensRecyclerview.setVisibility(View.VISIBLE);
            selectedCoupen.setVisibility(View.GONE);
        }else {
            coupensRecyclerview.setVisibility(View.GONE);
            selectedCoupen.setVisibility(View.VISIBLE);
        }
    }
}