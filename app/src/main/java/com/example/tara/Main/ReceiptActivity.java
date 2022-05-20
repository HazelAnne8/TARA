package com.example.tara.Main;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.tara.R;

public class ReceiptActivity extends AppCompatActivity {
    Button doneBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt);
        getSupportActionBar().hide();

        doneBtn = findViewById(R.id.doneBtn);

        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ReceiptActivity.this,"Sige lods ayos na lahat bayad ka na, salamat UWU",Toast.LENGTH_LONG).show();
            }
        });
    }
}