package com.example.white_city;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.white_city.databinding.ActivityDashboardBinding;

import java.util.ArrayList;

public class DashboardActivity extends DrawerBaseActivity {
    ImageSlider imageSlider;
    ActivityDashboardBinding activityDashboardBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityDashboardBinding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(activityDashboardBinding.getRoot());
        allocateActivityTitle("Dashboard");

        imageSlider = findViewById(R.id.image_slider);

        ArrayList<SlideModel> imageList = new ArrayList<>();
        imageList.add(new SlideModel(R.drawable.caldas1, null));
        imageList.add(new SlideModel(R.drawable.humilladero1, null));
        imageList.add(new SlideModel(R.drawable.humilladero1, null));
        imageList.add(new SlideModel(R.drawable.caldas2, null));
        imageList.add(new SlideModel(R.drawable.teatro1, null));
        imageSlider.setImageList(imageList, ScaleTypes.CENTER_CROP);


    }

    public void OnButtonLogout(View view) {
        Intent intent = new Intent(DashboardActivity.this, LoginActivity.class);
        startActivity(intent);
    }

}
