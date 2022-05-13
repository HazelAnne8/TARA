package com.example.tara.Host;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.tara.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Insurance extends AppCompatActivity implements View.OnClickListener {
    EditText etAmount;
    CardView cvInsurance1, cvInsurance2;
    ImageView ivInsurance1,ivInsurance2,ivInsurance3;
    Uri imageUri;
    private RadioGroup radioGroup;
    String insuranceType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insurance);
        getSupportActionBar().hide();

        Toolbar toolbar = findViewById(R.id.appBar);
//        Button nextBtn = findViewById(R.id.insuranceNextBtn);

        etAmount= findViewById(R.id.etAmount);
        cvInsurance1 = findViewById(R.id.cvInsurance1);
        cvInsurance2 = findViewById(R.id.cvInsurance2);
        ivInsurance1=findViewById(R.id.ivInsurance1);
        ivInsurance2=findViewById(R.id.ivInsurance2);
        ivInsurance3=findViewById(R.id.ivInsurance3);
        radioGroup = findViewById(R.id.insuranceRadioGroup);

        ivInsurance1.setOnClickListener(this);
        ivInsurance2.setOnClickListener(this);
        ivInsurance3.setOnClickListener(this);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.insuranceOption1:
                        insuranceType = "Comprehensive coverage";
                        break;
                    case R.id.insuranceOption2:
                        insuranceType = "3rd party, fire and damage coverage";
                        break;
                    case R.id.insuranceOption3:
                        insuranceType = "3rd party coverage";
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

//        nextBtn.setOnClickListener(new View.OnClickListener() {
//            @SuppressLint("UseCompatLoadingForDrawables")
//            @Override
//            public void onClick(View view) {
//                if(radioGroup.getCheckedRadioButtonId()==-1||etAmount.getText().toString().equals("")){
//                    Toast.makeText(Insurance.this,"Some of the fields are empty! ",Toast.LENGTH_LONG).show();
//                }
//                else{
//                    startActivity(new Intent(Insurance.this,Protection.class));
//                    //uploadImage();
//                }
//            }
//        });
    }
    private void selectImage(int requestCode){ //opens image selector
        Intent galleryIntent = new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, requestCode);
    }

    //this method is executed when an image is selected
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //if image is selected, display the image
        if (requestCode == 17 && resultCode == -1 && data != null) {
            imageUri = data.getData();
            ivInsurance1.setImageURI(imageUri);
            cvInsurance1.setVisibility(View.VISIBLE);
            Toast.makeText(this, "insurance 1", Toast.LENGTH_LONG).show();
        }
        if (requestCode == 18 && resultCode == -1 && data != null) {
            imageUri = data.getData();
            ivInsurance2.setImageURI(imageUri);
            cvInsurance2.setVisibility(View.VISIBLE);
            Toast.makeText(this, "insurance 2", Toast.LENGTH_LONG).show();
        }
        if (requestCode == 19 && resultCode == -1 && data != null) {
            imageUri = data.getData();
            ivInsurance3.setImageURI(imageUri);
            Toast.makeText(this, "insurance 3", Toast.LENGTH_LONG).show();
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ivInsurance1:
                selectImage(17);
                break;
            case R.id.ivInsurance2:
                selectImage(18);
                break;
            case R.id.ivInsurance3:
                selectImage(19);
                break;
        }
    }

//    private void uploadImage(){
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.CANADA);
//        Date now = new Date();
//        String fileName = formatter.format(now);
//
//        StorageReference storageReference = FirebaseStorage.getInstance("gs://tara-f89da.appspot.com").getReference("insuranceImages/"+fileName);
//        storageReference.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//
//                taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
//                    // retrieve image url and store in the  database
//                    @Override
//                    public void onComplete(@NonNull Task<Uri> task) {
//                        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
//                        String imageUrl = task.getResult().toString();
////                        String databaseLocation = getString(R.string.databasePath);
////                        FirebaseDatabase.getInstance(databaseLocation).getReference().child("car").child(userId)
////                                .push().setValue(imageUrl);
//                        Intent intent = new Intent();
//                        intent.putExtra("dataInsuranceUrl",imageUrl);
//                        //startActivity(intent);
//                    }
//                });
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(Insurance.this, "Something occurred, please try again later",Toast.LENGTH_LONG).show();
//            }
//        });
//    }
}