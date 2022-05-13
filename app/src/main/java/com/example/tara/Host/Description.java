package com.example.tara.Host;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tara.Main.Main;
import com.example.tara.Models.DescriptionModel;
import com.example.tara.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent;
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener;

public class Description extends AppCompatActivity {
    EditText etPriceRate, etDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        getSupportActionBar().hide();

        Button nextBtn = findViewById(R.id.descriptionNextBtn);
        Toolbar toolbar = findViewById(R.id.appBar);
        etPriceRate=  findViewById(R.id.etPricing);
        etDescription = findViewById(R.id.etDescription);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etPriceRate.getText().toString().equals("")){
                    Toast.makeText(Description.this,"Please enter price rate!",Toast.LENGTH_LONG).show();
                }
                else{
                    uploadData();
                }
            }
        });

        KeyboardVisibilityEvent.setEventListener(
                this,
                new KeyboardVisibilityEventListener() {
                    @Override
                    public void onVisibilityChanged(boolean isOpen) {
                        if(isOpen){
                            nextBtn.setVisibility(View.INVISIBLE);
                        }else{
                            nextBtn.setVisibility(View.VISIBLE);
                        }
                    }
                });
    }

    //upload data to database, from the name itself
    private void uploadData(){
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        String databaseLocation = getString(R.string.databasePath);
        int priceRate = Integer.parseInt(etPriceRate.getText().toString());
        String description = etDescription.getText().toString();
        DescriptionModel model = new DescriptionModel(priceRate,description);

        FirebaseDatabase.getInstance(databaseLocation).getReference().child("users").child(userId)
                .child("isHost").setValue(true).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Description.this,"Something went wrong, try again",Toast.LENGTH_LONG).show();
            }
        });
//        FirebaseDatabase.getInstance(databaseLocation).getReference().child("car").child(userId)
//                .push().setValue(model).addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//                startActivity(new Intent(Description.this, Main.class));
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(Description.this,"Something went wrong, try again",Toast.LENGTH_LONG).show();
//            }
//        });

        Intent intent = new Intent(Description.this,TEstData.class);
        intent.putExtra("dataPriceRate",priceRate);
        intent.putExtra("dataDescription",description);
        startActivity(intent);
    }
}