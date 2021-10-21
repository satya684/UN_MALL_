package com.example.unmall.MyAddresses;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unmall.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import static android.content.Context.APP_OPS_SERVICE;
import static com.example.unmall.AccountFragment.MANAGE_ADDRESS;
import static com.example.unmall.DeliveryActivity.SELECT_ADDRESS;
import static com.example.unmall.MyAddressActivity.refreshItem;

public class AddressesAdapter extends RecyclerView.Adapter<AddressesAdapter.ViewHolder> {

    List<AddressesModel> addressesModelList;
    private int MODE;
    private int preSelectedPosition = -1;


    public AddressesAdapter(List<AddressesModel> addressesModelList,int MODE) {
        this.addressesModelList = addressesModelList;
        this.MODE = MODE;
    }

    @NonNull
    @NotNull
    @Override
    public AddressesAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.address_item_layout, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AddressesAdapter.ViewHolder holder, int position) {

        String userName =addressesModelList.get(position).getAddressesName();
        String userAddress = addressesModelList.get(position).getAddresses();
        String  userPincode = addressesModelList.get(position).getAddressesPincode();
        Boolean selected = addressesModelList.get(position).getSelected();

        holder.setData(userName, userAddress, userPincode, selected, position);
    }

    @Override
    public int getItemCount() {
        return addressesModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView addressesName;
        private TextView addresses;
        private TextView addressesPincode;
        private ImageView icon;
        private LinearLayout optionContainer;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            addressesName = itemView.findViewById(R.id.addresses_name);
            addresses = itemView.findViewById(R.id.addresses);
            addressesPincode = itemView.findViewById(R.id.addresses_pincode);
            icon = itemView.findViewById(R.id.icon_view);
            optionContainer = itemView.findViewById(R.id.option_container);

        }

        private void setData(String userName, String userAddress, String  userPincode, Boolean selected, int position){
            addressesName.setText(userName);
            addresses.setText(userAddress);
            addressesPincode.setText(userPincode);

            //TODO condition on address edit and delete

            if (MODE == SELECT_ADDRESS){
                icon.setImageResource(R.drawable.check);
                if (selected){
                    icon.setVisibility(View.VISIBLE);
                    preSelectedPosition = position;
                }else {
                    icon.setVisibility(View.GONE);
                }
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (preSelectedPosition != position) {
                            addressesModelList.get(position).setSelected(true);
                            addressesModelList.get(preSelectedPosition).setSelected(false);
                            refreshItem(preSelectedPosition, position);
                            preSelectedPosition = position;
                        }

                    }
                });
            }else if (MODE == MANAGE_ADDRESS){
                optionContainer.setVisibility(View.GONE);
                icon.setImageResource(R.drawable.vertical_dots);
                icon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        optionContainer.setVisibility(View.VISIBLE);
                        refreshItem(preSelectedPosition,preSelectedPosition);
                        preSelectedPosition = position;

                    }
                });

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        refreshItem(preSelectedPosition,preSelectedPosition);
                        preSelectedPosition = -1;
                    }
                });


            }
            //TODO condition on address edit and delete
        }
    }
}
