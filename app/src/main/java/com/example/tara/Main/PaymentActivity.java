package com.example.tara.Main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.content.Intent;
import android.os.Handler;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tara.Adapter.LoadingDialog;
import com.example.tara.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.regex.Pattern;

public class PaymentActivity extends AppCompatActivity {
    Button payBtn;
    TextView tvPrice;
    EditText cardNumberEditText, etExpiration, etCardName;
    DatabaseReference userReference;
    FirebaseAuth mAuth;
    String userId,cardId;
    DataSnapshot dataSnapshot;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        getSupportActionBar().hide();

        String price = getIntent().getStringExtra("price");
        cardId = getIntent().getStringExtra("carId");
        String databaseLocation = getString(R.string.databasePath);

        mAuth = FirebaseAuth.getInstance();
        payBtn = findViewById(R.id.payBtn);
        tvPrice = findViewById(R.id.tvPaymentPrice);
        cardNumberEditText = findViewById(R.id.etCardNumber);
        etExpiration = findViewById(R.id.etExpiration);
        etCardName = findViewById(R.id.etNameOnCard);
        etCardName.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_WORDS);
        tvPrice.setText("₱"+price);
        userId = mAuth.getCurrentUser().getUid();
        userReference = FirebaseDatabase.getInstance(databaseLocation).getReference("users").child(userId);

        LoadingDialog loadingDialog = new LoadingDialog(PaymentActivity.this);

        payBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadingDialog.startLoadingDialog();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadingDialog.dismissDialog();
                        userReference.child("bookCarIds").push().setValue(cardId);
                        startActivity(new Intent(PaymentActivity.this,ReceiptActivity.class));
                    }
                },3000);

            }
        });


        cardNumberEditText.addTextChangedListener(new TextWatcher() {
            private static final char space = ' ';
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void afterTextChanged(Editable s) {
                int pos = 0;
                while (true) {
                    if (pos >= s.length()) break;
                    if (space == s.charAt(pos) && (((pos + 1) % 5) != 0 || pos + 1 == s.length())) {
                        s.delete(pos, pos + 1);
                    } else { pos++; }
                }
                pos = 4;
                while (true) {
                    if (pos >= s.length()) break;
                    final char c = s.charAt(pos);
                    if ("0123456789".indexOf(c) >= 0) {
                        s.insert(pos, "" + space);
                    }
                    pos += 5;
                }
            }
        });
        etExpiration.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() > 0 && (editable.length() % 3) == 0) {
                    final char c = editable.charAt(editable.length() - 1);
                    if ('/' == c) {
                        editable.delete(editable.length() - 1, editable.length());
                    }
                }
                if (editable.length() > 0 && (editable.length() % 3) == 0) {
                    char c = editable.charAt(editable.length() - 1);
                    if (Character.isDigit(c) && TextUtils.split(editable.toString(), String.valueOf("/")).length <= 2) {
                        editable.insert(editable.length() - 1, String.valueOf("/"));
                    }
                }
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
        });


    }


}