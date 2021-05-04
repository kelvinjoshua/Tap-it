package com.example.imagecap;

public  class SaveData {

    String customerName;
    String Date;
    int VolumeConsumed;
    int phoneNumber;
    double Longitude;
    double Latitude;
    
    public SaveData(String customerName, String Date, int phoneNumber, int Volume, double Longitude, double Latitude) {
        this.customerName = customerName;
        this.Date = Date;
        this.phoneNumber = phoneNumber;
        this.VolumeConsumed = Volume;
        this.Latitude = Latitude;
        this.Longitude = Longitude;
    }

    public String getCustomerName() {
        return customerName;
    }

    public int getVolumeConsumed() {
        return VolumeConsumed;
    }

    public String getDate() {
        return Date;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public double getLongitude() {
        return Longitude;
    }

    public double getLatitude() {
        return Latitude;
    }
}
