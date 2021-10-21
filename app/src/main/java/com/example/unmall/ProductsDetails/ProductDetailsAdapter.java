package com.example.unmall.ProductsDetails;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.unmall.ProductDescriptionFragment;
import com.example.unmall.ProductDetailsActivity;
import com.example.unmall.ProductSpecification.ProductSpecificationModel;
import com.example.unmall.ProductSpecificationFragment;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ProductDetailsAdapter extends FragmentStateAdapter {

    private int totalTabs;

    private String productDescription;
    private String productOtherDetails;
    private List<ProductSpecificationModel> productSpecificationModelList;

//    public ProductDetailsAdapter(@NonNull FragmentManager fragmentActivity, String productDescription, String productOtherDetails , List<ProductSpecificationModel> productSpecificationModelList, int totalTabs) {
//        super(fragmentActivity);
//        this.productDescription = productDescription;
//        this.productOtherDetails = productOtherDetails;
//        this.productSpecificationModelList = productSpecificationModelList;
//        this.totalTabs = totalTabs;
//    }



    public ProductDetailsAdapter(@NonNull @NotNull FragmentManager fragmentManager, @NonNull @NotNull Lifecycle lifecycle,String productDescription, String productOtherDetails , List<ProductSpecificationModel> productSpecificationModelList, int totalTabs) {
        super(fragmentManager, lifecycle);
        this.totalTabs = totalTabs;
        this.productDescription = productDescription;
        this.productOtherDetails = productOtherDetails;
        this.productSpecificationModelList = productSpecificationModelList;
    }
    @NonNull
    @NotNull
    @Override
    public Fragment createFragment(int position) {
       switch (position){
               case 0:
               ProductDescriptionFragment productDescriptionFragment1 = new ProductDescriptionFragment();
               productDescriptionFragment1.body = productDescription;
               return  productDescriptionFragment1;
               case 1:
                   ProductSpecificationFragment productSpecificationFragment = new ProductSpecificationFragment();
                   productSpecificationFragment.productSpecificationModelList = productSpecificationModelList;
                   return productSpecificationFragment;


               case 2:
               ProductDescriptionFragment productDescriptionFragment2 = new ProductDescriptionFragment();
               productDescriptionFragment2.body = productOtherDetails;
               return  productDescriptionFragment2;
               default:
                 return null;
       }
    }

    @Override
    public int getItemCount() {
        return totalTabs;
    }
}
