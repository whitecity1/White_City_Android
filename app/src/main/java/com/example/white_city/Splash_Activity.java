package com.example.white_city;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Splash_Activity extends AppCompatActivity {

        private ImageView imgv;
        private TextView textView;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_splash);

            imgv=findViewById(R.id.imageView);
            Animation animation= AnimationUtils.loadAnimation(this, R.anim.animacion);
            imgv.setAnimation(animation);
            ImageView imgv =findViewById(R.id.imageView);
            imgv.animate().translationX(1000).setDuration(1000).setStartDelay(2500);

            textView=findViewById(R.id.textView);
            Animation animation2= AnimationUtils.loadAnimation(this, R.anim.animacion);
            textView.setAnimation(animation2);
            TextView textView =findViewById(R.id.textView);
            textView.animate().translationY(1000).setDuration(1000).setStartDelay(2500);

            Thread splashScreenStarter = new Thread() {
                public void run() {
                    try {
                        int delay = 0;
                        while (delay < 2500) {
                            sleep(150);
                            delay = delay + 100;
                        }

                        startActivity(new

                                Intent(Splash_Activity.this, HomeActivity.class));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        finish();
                    }
                }
            };
            splashScreenStarter.start();
        }

    }