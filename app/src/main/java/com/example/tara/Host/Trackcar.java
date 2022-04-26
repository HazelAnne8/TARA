package com.example.tara.Host;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.tara.R;

import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent;
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener;

public class Trackcar extends AppCompatActivity {
    private RadioGroup radioGroup;
    private String trackType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trackcar);
        getSupportActionBar().hide();

        Button nextBtn = findViewById(R.id.trackerNextBtn);
        Toolbar toolbar = findViewById(R.id.appBar);
        radioGroup = findViewById(R.id.trackRadioGroup);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.trackerRadioBtn1:
                        trackType = "Tracker on";
                        break;
                    case R.id.trackerRadioBtn2:
                        trackType = "Tracker off";
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
                if(radioGroup.getCheckedRadioButtonId()==-1){
                    Toast.makeText(Trackcar.this,"Please select from above",Toast.LENGTH_LONG).show();
                }
                startActivity(new Intent(Trackcar.this,Description.class));
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
}