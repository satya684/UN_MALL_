package com.example.unmall.MyAddresses;

public class AddressesModel {

    private  String addressesName;
    private String addresses;
    private String addressesPincode;
    private Boolean selected;

    public AddressesModel(String addressesName, String addresses, String addressesPincode, Boolean selected) {
        this.addressesName = addressesName;
        this.addresses = addresses;
        this.addressesPincode = addressesPincode;
        this.selected = selected;
    }

    public String getAddressesName() {
        return addressesName;
    }

    public void setAddressesName(String addressesName) {
        this.addressesName = addressesName;
    }

    public String getAddresses() {
        return addresses;
    }

    public void setAddresses(String addresses) {
        this.addresses = addresses;
    }

    public String getAddressesPincode() {
        return addressesPincode;
    }

    public void setAddressesPincode(String addressesPincode) {
        this.addressesPincode = addressesPincode;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }
}

