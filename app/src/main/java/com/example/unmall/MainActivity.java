package com.example.unmall;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.unmall.databinding.ActivityMainBinding;
import com.example.unmall.ui.home.HomeFragment;
import com.example.unmall.ui.myOrder.MyOrdersFragment;
import com.example.unmall.ui.myRewards.MyRewardsFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.NotNull;
import static com.example.unmall.RegisterActivity.setSignUpFragment;

public class MainActivity extends AppCompatActivity implements HomeFragment.DrawerLocker {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    private static final int HOME_FRAGMENT = 0;
    private static final int CART_FRAGMENT = 1;
    private static final int ORDERS_FRAGMENT = 2;
    private static final int WISHLIST_FRAGMENT = 3;
    private static final int REWARDS_FRAGMENT = 4;
    private static final int ACCOUNT_FRAGMENT = 5;
    public static Boolean showCart = false;

    public static DrawerLayout drawerLayout;
    private FirebaseUser currentUser;



    private Window window;
    private  Dialog signInDialog;

    private ConstraintLayout mainContanerHome;
    private ImageView actionBarLogo;
    private int currentFragment = -1;
    private NavigationView navigationView;
    private   DrawerLayout drawer;

    @SuppressLint({"WrongConstant", "RestrictedApi"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        drawerLayout = findViewById(R.id.drawer_layout);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        setSupportActionBar(binding.appBarMain.toolbar);
        /////coding stary here for main Activity
        getSupportActionBar().setDisplayShowTitleEnabled(false);// title disable
        //

         drawer = binding.drawerLayout;
        navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_my_home, R.id.nav_my_orders, R.id.nav_my_rewards, R.id.nav_my_cart, R.id.nav_my_wishlist, R.id.nav_my_account, R.id.nav_my_signout)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                if (currentUser != null) {
                    int id = item.getItemId();
                    if (id == R.id.nav_my_home) {
                        actionBarLogo.setVisibility(View.VISIBLE);
                        invalidateOptionsMenu();
                        setFragment(new HomeFragment(), HOME_FRAGMENT);
                    } else if (id == R.id.nav_my_orders) {
                        gotoFragment("My Orders", new MyOrdersFragment(), ORDERS_FRAGMENT);
                    } else if (id == R.id.nav_my_rewards) {
                        gotoFragment("My Rewards", new MyRewardsFragment(), REWARDS_FRAGMENT);
                    } else if (id == R.id.nav_my_cart) {
                        gotoFragment("My Cart", new CartFragment(), CART_FRAGMENT);
                    } else if (id == R.id.nav_my_wishlist) {
                        gotoFragment("My WishList", new Wishlist_Fragment(), WISHLIST_FRAGMENT);
                    } else if (id == R.id.nav_my_account) {
                        gotoFragment("My Account", new AccountFragment(), ACCOUNT_FRAGMENT);

                    } else if (id == R.id.nav_my_signout) {
                      FirebaseAuth.getInstance().signOut();
                      Intent registerIntent = new Intent(MainActivity.this, RegisterActivity.class);
                      startActivity(registerIntent);
                      finish();
                    }
                    //11/1/22start
                    drawer.closeDrawer(GravityCompat.START);
                    return true;
                    //11/1/22start


                }else {
                    binding.drawerLayout.closeDrawer(GravityCompat.START);
                    signInDialog.show();
                    return false;

                }
                //todo color change krne ke liye tool bar
                //todo color change krne ke liye tool bar
            }
        });


        actionBarLogo = findViewById(R.id.actionbar_logo);

        mainContanerHome = findViewById(R.id.main_container_home_page);

        //todo:remove hamburger icon
        if (showCart) {
            drawer.setDrawerLockMode(1);
            getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
            gotoFragment("My Cart", new CartFragment(), -2);
        } else {
            //todo remove navigation drawer
                ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, binding.appBarMain.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
                drawer.addDrawerListener(toggle);
                toggle.syncState();
            //todo remove navigation drawer
            setFragment(new HomeFragment(), HOME_FRAGMENT);
        }
//        if (currentUser == null){
//            navigationView.getMenu().getItem(navigationView.getMenu().size() - 1).setEnabled(false);
//        }else {
//            navigationView.getMenu().getItem(navigationView.getMenu().size() - 1).setEnabled(true);
//
//        }


        signInDialog = new Dialog(MainActivity.this);
        signInDialog.setContentView(R.layout.sign_in_dialog);
        signInDialog.setCancelable(true);
        signInDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        Button dSignInBtn = signInDialog.findViewById(R.id.d_sign_in_button);
        Button dSignUpBtn = signInDialog.findViewById(R.id.d_sign_up_btn);

        Intent registerIntent = new Intent(MainActivity.this, RegisterActivity.class);

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

    }


    @Override
    protected void onStart() {
        super.onStart();
        currentUser = FirebaseAuth.getInstance().getCurrentUser();

        if (currentUser == null){
            navigationView.getMenu().getItem(navigationView.getMenu().size() - 1).setEnabled(false);
        }else {
            navigationView.getMenu().getItem(navigationView.getMenu().size() - 1).setEnabled(true);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        if (currentFragment == HOME_FRAGMENT) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getMenuInflater().inflate(R.menu.main, menu);
        }
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.main_search_icon) {

            ///todo: search
            return true;
        } else if (id == R.id.main_notification_icon) {
            /////todo: notification

            return true;
        } else if (id == R.id.main_cart_icon) {
           if (currentUser == null) {
               signInDialog.show();
           }else {
               gotoFragment("My Cart", new CartFragment(), CART_FRAGMENT);
           }
            return true;
        }else  if (id == android.R.id.home){
            if (showCart){
                showCart = false;
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void gotoFragment(String title, Fragment fragment, int fragmentNo) {
        actionBarLogo.setVisibility(View.GONE);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(title);
        invalidateOptionsMenu();
        setFragment(fragment, fragmentNo);
        if (fragmentNo == CART_FRAGMENT){
            binding.navView.getMenu().getItem(3).setChecked(true);
        }

    }

    //todo: my code
    private void setFragment(Fragment fragment, int fragmentNo) {
        if (fragmentNo != currentFragment) {
            currentFragment = fragmentNo;
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(mainContanerHome.getId(), fragment);
            fragmentTransaction.commit();

        }
    }

    //todo phones back button code
    public void onBackPressed(){
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);

        }else {
            if (currentFragment == HOME_FRAGMENT ){
                currentFragment = -1;
                super.onBackPressed();
            }else {
                if (showCart){
                    showCart = false;
                    finish();
                }else {
                    actionBarLogo.setVisibility(View.VISIBLE);
                    invalidateOptionsMenu();
                    setFragment(new HomeFragment(), HOME_FRAGMENT);
                    binding.navView.getMenu().getItem(0).setChecked(true);
                }
            }

        }
        //todo phones back button code
    }

    @Override
    public void setDrawerEnabled(boolean enabled) {
        if (enabled && drawer != null) {
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        }
    }
    //todo: my code

}