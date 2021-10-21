package com.example.unmall;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.unmall.MyAddresses.AddressesAdapter;
import com.example.unmall.MyAddresses.AddressesModel;

import java.util.ArrayList;
import java.util.List;

import static com.example.unmall.DeliveryActivity.SELECT_ADDRESS;

public class MyAddressActivity extends AppCompatActivity {


    private RecyclerView myAddressesRecyclerView;
    private static AddressesAdapter addressesAdapter;
    private Button deliverHereBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_address);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("My Address");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        myAddressesRecyclerView = findViewById(R.id.addresses_recyclerview);
        deliverHereBtn = findViewById(R.id.delivere_here_btn);

        LinearLayoutManager layoutManager= new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        myAddressesRecyclerView.setLayoutManager(layoutManager);

        List<AddressesModel> addressesModelList = new ArrayList<>();
        addressesModelList.add(new AddressesModel("Satya", "village + post bhilampur", "22201", true));
        addressesModelList.add(new AddressesModel("Satya", "village + post bhilampur", "22201", false));
        addressesModelList.add(new AddressesModel("Satya", "village + post bhilampur", "22201", false));
        addressesModelList.add(new AddressesModel("Satya", "village + post bhilampur", "22201", false));
        addressesModelList.add(new AddressesModel("Satya", "village + post bhilampur", "22201", false));
        addressesModelList.add(new AddressesModel("Satya", "village + post bhilampur", "22201", false));
        addressesModelList.add(new AddressesModel("Satya", "village + post bhilampur", "22201", false));
        addressesModelList.add(new AddressesModel("Satya", "village + post bhilampur", "22201", false));
        addressesModelList.add(new AddressesModel("Satya", "village + post bhilampur", "22201", false));
        addressesModelList.add(new AddressesModel("Satya", "village + post bhilampur", "22201", false));


        int mode = getIntent().getIntExtra("MODE", -1);
        if (mode == SELECT_ADDRESS){
            deliverHereBtn.setVisibility(View.VISIBLE);
        }else {
            deliverHereBtn.setVisibility(View.GONE);
        }
        addressesAdapter = new AddressesAdapter(addressesModelList, mode);
        myAddressesRecyclerView.setAdapter(addressesAdapter);
        //todo disable the animation after refresh
        ((SimpleItemAnimator)myAddressesRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        //todo disable the animation after refresh
        addressesAdapter.notifyDataSetChanged();

    }

    //todo to refresh the edit date layout
    public static void refreshItem(int deselect, int select){
        addressesAdapter.notifyItemChanged(deselect);
        addressesAdapter.notifyItemChanged(select);
    }
    //todo to refresh the edit date layout

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}