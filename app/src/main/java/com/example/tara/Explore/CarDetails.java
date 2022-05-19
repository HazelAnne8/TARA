package com.example.tara.Explore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.tara.Profile.EditProfile;
import com.example.tara.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CarDetails extends AppCompatActivity {
    String carId,uId, carUrl;
    DatabaseReference vehicleRef,userRef;
    ImageSlider imageSlider;
    TextView tvBmy, tvLocation, tvPriceRate, tvTransmission, tvDrivetrain, tvSeats,
                tvType, tvFuelType, tvMileage, tvDescription, hostName,tvPriceRate2;
    ImageView hostPic;
    Button bookBtn;
    DataSnapshot dataSnapshot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_details);
        getSupportActionBar().hide();

        String databaseLocation = getString(R.string.databasePath);
        Toolbar toolbar = findViewById(R.id.appBar);
        ArrayList<SlideModel> slideModels = new ArrayList<>();

        imageSlider = findViewById(R.id.slider);
        carId = getIntent().getStringExtra("carId");
        uId = getIntent().getStringExtra("userId");
        tvBmy = findViewById(R.id.tvcdBMY);
        tvLocation = findViewById(R.id.tvcdLocation);
        tvPriceRate = findViewById(R.id.tvPrice);
        tvTransmission = findViewById(R.id.tvTransmission);
        tvDrivetrain = findViewById(R.id.tvDrivetrain);
        tvSeats = findViewById(R.id.tvSeats);
        tvType = findViewById(R.id.tvType);
        tvFuelType = findViewById(R.id.tvFuelType);
        tvMileage = findViewById(R.id.tvcdMileage);
        tvDescription = findViewById(R.id.tvDescription);
        hostName = findViewById(R.id.tvHost);
        hostPic = findViewById(R.id.ivHost);
        bookBtn = findViewById(R.id.bookBtn);
        tvPriceRate2 = findViewById(R.id.tvcdPricing);
        vehicleRef = FirebaseDatabase.getInstance(databaseLocation).getReference("vehicle").child(carId).child(uId);
        userRef = FirebaseDatabase.getInstance(databaseLocation).getReference("users").child(uId);

        bookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Car has been booked successfully\n"+"Car Id booked: "+ carId+"\nUser id booked: "+uId,Toast.LENGTH_LONG).show();
            }
        });

        vehicleRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                tvBmy.setText(snapshot.child("bmy").getValue().toString());
                tvLocation.setText(snapshot.child("location").getValue().toString());
                tvPriceRate.setText(snapshot.child("priceRate").getValue().toString());
                tvTransmission.setText(snapshot.child("transmission").getValue().toString());
                tvDrivetrain.setText(snapshot.child("drivetrain").getValue().toString());
                tvSeats.setText(snapshot.child("seats").getValue().toString());
                tvType.setText(snapshot.child("type").getValue().toString());
                tvFuelType.setText(snapshot.child("fuelType").getValue().toString());
                tvMileage.setText(snapshot.child("mileage").getValue().toString());
                tvDescription.setText(snapshot.child("description").getValue().toString());
                tvPriceRate2.setText(snapshot.child("priceRate").getValue().toString());

                carUrl = snapshot.child("carUrl").getValue().toString();

                slideModels.add(new SlideModel(carUrl,null));
                slideModels.add(new SlideModel("https://thecinemaholic.com/wp-content/uploads/2021/01/nezuu-e1638963260523.jpg",null));
                slideModels.add(new SlideModel("https://cdn.animenewsnetwork.com/thumbnails/crop900x350gIL/cms/preview-guide/183646/spy-3.jpg",null));

                imageSlider.setImageList(slideModels, ScaleTypes.FIT);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(!(snapshot.child("imageUrl").getValue().toString().isEmpty())){
                    String imageUrl = snapshot.child("imageUrl").getValue().toString();
                    Glide.with(CarDetails.this).load(imageUrl).into(hostPic);
                }
                hostName.setText(capitalizeWord(snapshot.child("name").getValue().toString()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //
//        vehicleRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                String exterior1 = snapshot.child("exterior1").getValue().toString();
//                String exterior2 = snapshot.child("exterior2").getValue().toString();
//                String exterior3 = snapshot.child("exterior3").getValue().toString();
//                String exterior4 = snapshot.child("exterior4").getValue().toString();
//                String interior1 = snapshot.child("interior1").getValue().toString();
//                String interior2 = snapshot.child("interior2").getValue().toString();
//                String interior3 = snapshot.child("interior3").getValue().toString();
//                String interior4 = snapshot.child("interior4").getValue().toString();
//
//                slideModels.add(new SlideModel(exterior1,null));
//                slideModels.add(new SlideModel(exterior2,null));
//                slideModels.add(new SlideModel(exterior3,null));
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    public String capitalizeWord(String str){
        String[] words =str.split("\\s");
        String capitalizeWord="";
        for(String w:words){
            String first=w.substring(0,1);
            String afterfirst=w.substring(1);
            capitalizeWord+=first.toUpperCase()+afterfirst+" ";
        }
        return capitalizeWord.trim();
    }
}