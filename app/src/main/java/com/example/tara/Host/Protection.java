package com.example.tara.Host;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.tara.R;

public class Protection extends AppCompatActivity {
    private RadioGroup protectionRadioGroup;
    String protectionType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_protection);
        getSupportActionBar().hide();

        Toolbar toolbar = findViewById(R.id.appBar);
        Button nextBtn = findViewById(R.id.protectionNextBtn);
        protectionRadioGroup=findViewById(R.id.protectionRadioGroup);

        protectionRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.protectionOption1:
                        protectionType = "Basic protection";
                        break;
                    case R.id.protectionOption2:
                        protectionType = "TARA Shield";
                        break;
                }
            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(protectionRadioGroup.getCheckedRadioButtonId()==-1){
                    Toast.makeText(Protection.this,"Please select from above",Toast.LENGTH_LONG).show();
                }
                else{
                    startActivity(new Intent(Protection.this,Trackcar.class));
                }
            }
        });
    }
}