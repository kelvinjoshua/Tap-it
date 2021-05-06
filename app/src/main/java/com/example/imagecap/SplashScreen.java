package com.example.imagecap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashScreen extends AppCompatActivity {
    @BindView(R.id.imageView) ImageView logo;
    @BindView(R.id.splash_screen_message) TextView slogan;
    @BindView(R.id.tap_it_logo) TextView Company;
    Animation Top,Bottom;
    private static int Load_delay = 3500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        ButterKnife.bind(this);
        Top = AnimationUtils.loadAnimation(SplashScreen.this,R.anim.top);
        Bottom = AnimationUtils.loadAnimation(SplashScreen.this,R.anim.bottom);
        logo.setAnimation(Top);
        slogan.setAnimation(Bottom);
        Company.setAnimation(Top);

        //Automatically start next activity after 5000 milli seconds
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this,login_form.class);
                startActivity(intent);
                finish();
            }
        },Load_delay);
    }
}