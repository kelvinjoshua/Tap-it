package com.example.imagecap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ResultReceiver;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddReadings extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.units) EditText volume;
    @BindView(R.id.submit) Button Submit;
    @BindView(R.id.dateView) TextView today;
    @BindView(R.id.gps) ImageView gps;
    @BindView(R.id.location) TextView display;
    @BindView(R.id.gpslocate) TextView gpsLocate;
    @BindView(R.id.progress) ProgressBar progressBar;
    public double getLat,getLong;
    public int record,num;
    public String getText,name,cust;
    public static final int REQUEST_CODE = 1; //binary for access permission set to true
    private ResultReceiver resultReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_readings);
        ButterKnife.bind(this);

        Submit.setOnClickListener(this);
        gps.setOnClickListener(this);
        gpsLocate.setOnClickListener(this);
        /*Calendar implementation*/
        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        today.setText(currentDate);
        getText = today.getText().toString();
        //intents
        Intent intent= getIntent();
        cust = intent.getStringExtra("Cus");
        num  = Integer.parseInt(intent.getStringExtra("num"));
    }

    @Override
    public void onClick(View v) {
        record = Integer.parseInt(volume.getText().toString());
        if (v == Submit) {
            SaveData data = new SaveData(cust, getText, num, record, getLat, getLong);
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference ref = database.getReference(Constants.Location);
            ref.child("").push().setValue(data);
            Toast.makeText(this, "Readings Saved", Toast.LENGTH_LONG).show();
        }
        if (v == gps || v == gpsLocate) {
            if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(AddReadings.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
            } else {
                getLocation();
            }
        }
    }

    @SuppressLint("MissingPermission")
    private void getLocation(){ progressBar.setVisibility(View.VISIBLE);

        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(3000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        LocationServices.getFusedLocationProviderClient(AddReadings.this).requestLocationUpdates(locationRequest, new LocationCallback() {
            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {
                super.onLocationResult(locationResult);
                LocationServices.getFusedLocationProviderClient(AddReadings.this).removeLocationUpdates(this);
                if (locationResult.getLocations().size() > 0){

                    int latestLocationIndex = locationResult.getLocations().size() - 1;
                    double latitude = locationResult.getLocations().get(latestLocationIndex).getLatitude();
                    double longitude = locationResult.getLocations().get(latestLocationIndex).getLongitude();
                    //store co-ordinates
                    getLat = latitude;
                    getLong = longitude;
                    //pass co-ordinates
                    display.setText(String.format("Longitude: %f  \n" + "\n Latitude: %f",longitude,latitude));//float format specifier for double type
                    Location location = new Location("providerNA");
                    location.setLatitude(latitude);
                    location.setLongitude(longitude );
                    //fetchAddressFromLatLong(location);
                }

              progressBar.setVisibility(View.GONE);
            }
        }, Looper.getMainLooper());

    }

}