package com.example.imagecap;

public  class SaveData {

    String customerName;
    String Date;
    int VolumeConsumed;
    int phoneNumber;
    double Longitude;
    double Latitude;
    String MeterNumber;
    
    public SaveData(String customerName, String Date, int phoneNumber, int Volume, double Longitude, double Latitude,String MeterNumber) {
        this.customerName = customerName;
        this.Date = Date;
        this.phoneNumber = phoneNumber;
        this.VolumeConsumed = Volume;
        this.Latitude = Latitude;
        this.Longitude = Longitude;
        this.MeterNumber = MeterNumber;
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

    public String getMeterNumber() {
        return MeterNumber;
    }
}
