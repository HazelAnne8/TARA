package com.example.tara.Host;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.tara.Models.LocationModel;
import com.example.tara.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Location extends AppCompatActivity {
    private EditText etAL1, etAL2, etCity, etPostcode, etProvince;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        getSupportActionBar().hide();


        Toolbar toolbar = findViewById(R.id.appBar);
        Button nextBtn = findViewById(R.id.locationNextBtn);
        etAL1 = findViewById(R.id.etAddressLine1);
        etAL2 = findViewById(R.id.etAddressLine2);
        etCity = findViewById(R.id.etCity);
        etPostcode = findViewById(R.id.etPostcode);
        etProvince =findViewById(R.id.etProvince);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                if(etAL1.getText().toString().isEmpty()){
                    etAL1.setError("Address line 1 is required");
                    etAL1.requestFocus();
                }else if(etAL1.getText().toString().isEmpty()){
                    etCity.setError("City is required");
                    etCity.requestFocus();
                }else{
                    uploadData();
                }
            }
        });
    }

    private void uploadData(){
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        String address1 = etAL1.getText().toString();
        String address2 = etAL2.getText().toString();
        String city = etCity.getText().toString();
        String postcode = etPostcode.getText().toString();
        String province = etProvince.getText().toString();

        Intent intent = new Intent(Location.this,AboutCar.class);
        intent.putExtra("dataAL1",address1);
        intent.putExtra("dataAL2",address2);
        intent.putExtra("dataCity",city);
        intent.putExtra("dataPostCode",postcode);
        intent.putExtra("dataProvince",province); //boss renz pogi
        startActivity(intent);


//        LocationModel model = new LocationModel(address1,address2,city,postcode,province);
//
//        String databaseLocation = getString(R.string.databasePath);
//        FirebaseDatabase.getInstance(databaseLocation).getReference().child("car").child(userId)
//                    .push().setValue(model).addOnCompleteListener(new OnCompleteListener<Void>() {
//                @Override
//                public void onComplete(@NonNull Task<Void> task) {
//                    startActivity(new Intent(Location.this,AboutCar.class));
//                }
//            });

    }
}