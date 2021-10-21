package com.example.unmall;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.unmall.Wishlist.WishlistAdapter;
import com.example.unmall.Wishlist.WishlistModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Wishlist_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Wishlist_Fragment extends Fragment {


    //todo my code
    private RecyclerView myWishlistRecyclerView;
    //todo my code


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Wishlist_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Wishlist_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Wishlist_Fragment newInstance(String param1, String param2) {
        Wishlist_Fragment fragment = new Wishlist_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_wishlist_, container, false);
        //todo my code
        myWishlistRecyclerView = view.findViewById(R.id.my_wishlist_recyclerview);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        myWishlistRecyclerView.setLayoutManager(linearLayoutManager);

        List<WishlistModel> wishlistModelList = new ArrayList<>();
//        wishlistModelList.add(new WishlistModel(R.drawable.phone, "Pixel 2", 1,"3", 154,"Rs. 45559/-","Rs. 656666/-","Cash on delivery"));
//        wishlistModelList.add(new WishlistModel(R.drawable.phone, "Pixel 1", 10,"1", 154,"Rs. 45559/-","Rs. 656666/-","Cash on delivery"));
//        wishlistModelList.add(new WishlistModel(R.drawable.phone, "Pixel 2", 2,"3", 154,"Rs. 45559/-","Rs. 656666/-","Cash on delivery"));
//        wishlistModelList.add(new WishlistModel(R.drawable.phone, "Pixel 8", 1,"4", 154,"Rs. 45559/-","Rs. 656666/-","Cash on delivery"));
//        wishlistModelList.add(new WishlistModel(R.drawable.phone, "Pixel 5", 15,"3", 154,"Rs. 45559/-","Rs. 656666/-","Cash on delivery"));
//        wishlistModelList.add(new WishlistModel(R.drawable.phone, "Pixel 2", 0,"0", 154,"Rs. 45559/-","Rs. 656666/-","Cash on delivery"));

        WishlistAdapter wishlistAdapter = new WishlistAdapter(wishlistModelList,true);
        myWishlistRecyclerView.setAdapter(wishlistAdapter);
        wishlistAdapter.notifyDataSetChanged();
        //todo my code change this ok sir thank you sir :)


        return view;

    }
}