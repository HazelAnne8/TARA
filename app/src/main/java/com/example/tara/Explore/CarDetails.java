package com.example.tara.Explore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.tara.R;

import java.util.ArrayList;
import java.util.List;

public class CarDetails extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_details);
        getSupportActionBar().hide();

        Toolbar toolbar = findViewById(R.id.appBar);
        ArrayList<SlideModel> slideModels = new ArrayList<>();
        ImageSlider imageSlider;
        imageSlider = findViewById(R.id.slider);

        slideModels.add(new SlideModel("https://ichef.bbci.co.uk/news/976/cpsprodpb/F382/production/_123883326_852a3a31-69d7-4849-81c7-8087bf630251.jpg",null));
        slideModels.add(new SlideModel("https://thecinemaholic.com/wp-content/uploads/2021/01/nezuu-e1638963260523.jpg",null));
        slideModels.add(new SlideModel("https://cdn.animenewsnetwork.com/thumbnails/crop900x350gIL/cms/preview-guide/183646/spy-3.jpg",null));

        imageSlider.setImageList(slideModels, ScaleTypes.FIT);



        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}