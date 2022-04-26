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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.tara.Models.AboutModel;
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

public class AboutCar extends AppCompatActivity implements View.OnClickListener{
    private ImageView ivCarGrant1,ivCarGrant2,ivCarGrant3,ivCarGrant4,
            ivRoadTax1,ivRoadTax2,ivRoadTax3,ivRoadTax4;
    AutoCompleteTextView etYear, etBrand, etTransmission, etDrivetrain,
            etSeats, etType, etFuelType, etMileage;
    private ImageView ivExterior1, ivExterior2,ivExterior3,ivExterior4,
            ivInterior1,ivInterior2,ivInterior3,ivInterior4;
    private EditText etModel, etPlateNumber;
    private CardView cvGrant1,cvGrant2,cvGrant3,cvTax1,cvTax2,cvTax3,cvExterior1,cvExterior2,cvExterior3,
                        cvInterior1,cvInterior2,cvInterior3;
    private String yearValue,brandValue,transmissionValue,drivetrainValue,seatsValue,typeValue,fuelTypeValue,
            mileageValue, plateNoValue;
    Uri imageUri;

    //all values, you can also put this on the string.xml para malinis tingnan
    String[] year =  {"2022","2021","2020","2019","2018","2017","2016","2015","2014","2013","2012","2011"};
    String[] brand = {"Toyota","Mitsubishi","Nisan","Hyundai","Ford","Suzuki","Honda"};
    String[] transmission = {"Manual","Automatic","CVT"};
    String[] drivetrain = {"AWD","4WD","FWD","RWD"};
    String[] seat = {"2 Seater","3 Seater","4 Seater","5 Seater","6 Seater"};
    String[] type = {"Sedan","Coupe","Sport car","Station wagon","Hatchback","Convertible","SUV","Minivan"};
    String[] fuelType = {"Avgas","Avtur","Kerosene","Solar Oil","Diesel Oil","Fuel Oil","Biodiesel","Gasoline"};
    String[] mileage = {"50-100K km","100-150K km","150-200K km","200-250K km","250-300K km"};

    ArrayAdapter<String> yearItems, brandItems, transmissionItems, driveItems, seatItems, typeItems,
                        fuelItems,mileageItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_car);
        getSupportActionBar().hide();

        Toolbar toolbar = findViewById(R.id.appBar);
        Button nextBtn = findViewById(R.id.aboutNextBtn);
        ivCarGrant1 = findViewById(R.id.carGrant1);
        ivCarGrant2 = findViewById(R.id.carGrant2);
        ivCarGrant3 = findViewById(R.id.carGrant3);
        ivCarGrant4 = findViewById(R.id.carGrant4);
        ivRoadTax1 = findViewById(R.id.roadTax1);
        ivRoadTax2 = findViewById(R.id.roadTax2);
        ivRoadTax3 = findViewById(R.id.roadTax3);
        ivRoadTax4 = findViewById(R.id.roadTax4);
        etYear = findViewById(R.id.etYear);
        etBrand = findViewById(R.id.etBrand);
        etModel = findViewById(R.id.etModel);
        etTransmission = findViewById(R.id.etTransmission);
        etDrivetrain = findViewById(R.id.etDrivetrain);
        etSeats = findViewById(R.id.etSeats);
        etType = findViewById(R.id.etType);
        etFuelType = findViewById(R.id.etFuelType);
        etMileage = findViewById(R.id.etMileage);
        etPlateNumber = findViewById(R.id.etPlateNumber);
        ivInterior1 = findViewById(R.id.carInterior1);
        ivInterior2 = findViewById(R.id.carInterior2);
        ivInterior3 = findViewById(R.id.carInterior3);
        ivInterior4 = findViewById(R.id.carInterior4);
        ivExterior1 = findViewById(R.id.carExterior1);
        ivExterior2 = findViewById(R.id.carExterior2);
        ivExterior3 = findViewById(R.id.carExterior3);
        ivExterior4 = findViewById(R.id.carExterior4);
        cvGrant1 = findViewById(R.id.cv1);
        cvGrant2 = findViewById(R.id.cv2);
        cvGrant3 = findViewById(R.id.cv3);
        cvTax1 = findViewById(R.id.cv4);
        cvTax2 = findViewById(R.id.cv5);
        cvTax3 = findViewById(R.id.cv6);
        cvExterior1 = findViewById(R.id.cvExterior1);
        cvExterior2 = findViewById(R.id.cvExterior2);
        cvExterior3 = findViewById(R.id.cvExterior3);
        cvInterior1 = findViewById(R.id.cvInterior1);
        cvInterior2 = findViewById(R.id.cvInterior2);
        cvInterior3 = findViewById(R.id.cvInterior3);
        etModel = findViewById(R.id.etModel);
        etPlateNumber = findViewById(R.id.etPlateNumber);

        yearItems = new ArrayAdapter<String>(this,R.layout.list_item,year);
        brandItems = new ArrayAdapter<String>(this,R.layout.list_item, brand);
        transmissionItems = new ArrayAdapter<String>(this,R.layout.list_item,transmission);
        driveItems = new ArrayAdapter<String>(this,R.layout.list_item,drivetrain);
        seatItems = new ArrayAdapter<String>(this,R.layout.list_item,seat);
        typeItems = new ArrayAdapter<String>(this,R.layout.list_item,type);
        fuelItems = new ArrayAdapter<String>(this,R.layout.list_item,fuelType);
        mileageItems = new ArrayAdapter<String>(this,R.layout.list_item,mileage);

        ivCarGrant1.setOnClickListener(this);
        ivCarGrant2.setOnClickListener(this);
        ivCarGrant3.setOnClickListener(this);
        ivCarGrant4.setOnClickListener(this);
        ivRoadTax1.setOnClickListener(this);
        ivRoadTax2.setOnClickListener(this);
        ivRoadTax3.setOnClickListener(this);
        ivRoadTax4.setOnClickListener(this);
        ivExterior1.setOnClickListener(this);
        ivExterior2.setOnClickListener(this);
        ivExterior3.setOnClickListener(this);
        ivExterior4.setOnClickListener(this);
        ivInterior1.setOnClickListener(this);
        ivInterior2.setOnClickListener(this);
        ivInterior3.setOnClickListener(this);
        ivInterior4.setOnClickListener(this);

        etYear.setAdapter(yearItems);
        etBrand.setAdapter(brandItems);
        etTransmission.setAdapter(transmissionItems);
        etDrivetrain.setAdapter(driveItems);
        etSeats.setAdapter(seatItems);
        etType.setAdapter(typeItems);
        etFuelType.setAdapter(fuelItems);
        etMileage.setAdapter(mileageItems);

        etYear.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                yearValue = parent.getItemAtPosition(position).toString();
            }
        });

        etBrand.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                brandValue = parent.getItemAtPosition(position).toString();
            }
        });

        etTransmission.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                transmissionValue = parent.getItemAtPosition(position).toString();
            }
        });

        etDrivetrain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                drivetrainValue = parent.getItemAtPosition(position).toString();
            }
        });

        etSeats.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                seatsValue = parent.getItemAtPosition(position).toString();
            }
        });

        etType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                typeValue = parent.getItemAtPosition(position).toString();
            }
        });

        etFuelType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                fuelTypeValue = parent.getItemAtPosition(position).toString();
            }
        });

        etMileage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mileageValue = parent.getItemAtPosition(position).toString();
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
                if(yearValue==null||brandValue==null||transmissionValue==null||drivetrainValue==null||seatsValue==null
                    ||typeValue==null||fuelTypeValue==null||mileageValue==null||etModel.getText().toString().equals("")||
                etPlateNumber.getText().toString().equals("")){
                    Toast.makeText(AboutCar.this,"Some of the fields are empty!",Toast.LENGTH_LONG).show();
                }else {
                    uploadImage("carImages/");
                    uploadData(yearValue, brandValue, transmissionValue, drivetrainValue, seatsValue, typeValue, fuelTypeValue, mileageValue);
                }
            }
        });
    }

    //upload data to database, from the name itself
    private void uploadData(String year, String brand, String transmission, String drivetrain,String seats, String type, String fuelType, String mileage){
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        String model = etModel.getText().toString();
        String plateNumber = etPlateNumber.getText().toString();
        AboutModel aboutModel = new AboutModel(year,brand,transmission,drivetrain,seats,type,fuelType,mileage,model,plateNumber);
        String databaseLocation = getString(R.string.databasePath);

        //understand how this works para alam kung pano naiistore sa database
        FirebaseDatabase.getInstance(databaseLocation).getReference().child("users").child(userId)
                .child("car").child("car_specs").setValue(aboutModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                public void onComplete(@NonNull Task<Void> task) {
                    startActivity(new Intent(AboutCar.this,Insurance.class));
                }
            });
    }

    //opens image selector
    private void selectImage(int requestCode){
        Intent galleryIntent = new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, requestCode);
    }

    //upload image to storage
    private void uploadImage(String fileLocation){
        //filename format
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.CANADA);
        Date now = new Date();
        String fileName = formatter.format(now);

        //gets the location of the storage
        StorageReference storageReference = FirebaseStorage.getInstance("gs://tara-f89da.appspot.com").getReference(fileLocation+fileName);

        //stores the image
        storageReference.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                    // gets the image url and store in the realtime database
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
                        String imageUrl = task.getResult().toString();
                        String databaseLocation = getString(R.string.databasePath);
                        FirebaseDatabase.getInstance(databaseLocation).getReference().child("users").child(userId)
                                .child("car").child("carImage").setValue(imageUrl);
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AboutCar.this, "Something occurred, please try again later",Toast.LENGTH_LONG).show();
            }
        });
    }

    //this method is executed when an image is selected
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //if image is selected based on the code, display the image
        if(requestCode==1 && resultCode== -1 && data != null){
            imageUri = data.getData();
            ivCarGrant1.setImageURI(imageUri);
            cvGrant1.setVisibility(View.VISIBLE);
            Toast.makeText(this, "grant 1",Toast.LENGTH_LONG).show();
        }
        if(requestCode==2 && resultCode== -1 && data != null){
            imageUri = data.getData();
            ivCarGrant2.setImageURI(imageUri);
            cvGrant2.setVisibility(View.VISIBLE);
            Toast.makeText(this, "grant 2",Toast.LENGTH_LONG).show();
        }
        if(requestCode==3 && resultCode== -1 && data != null){
            imageUri = data.getData();
            ivCarGrant3.setImageURI(imageUri);
            cvGrant3.setVisibility(View.VISIBLE);
            Toast.makeText(this, "grant 3",Toast.LENGTH_LONG).show();
        }
        if(requestCode==4 && resultCode== -1 && data != null){
            imageUri = data.getData();
            ivCarGrant4.setImageURI(imageUri);
            Toast.makeText(this, "grant 4",Toast.LENGTH_LONG).show();
        }

        if(requestCode==5 && resultCode== -1 && data != null){
            imageUri = data.getData();
            ivRoadTax1.setImageURI(imageUri);
            cvTax1.setVisibility(View.VISIBLE);
            Toast.makeText(this, "road tax 1",Toast.LENGTH_LONG).show();
        }
        if(requestCode==6 && resultCode== -1 && data != null){
            imageUri = data.getData();
            ivRoadTax2.setImageURI(imageUri);
            cvTax2.setVisibility(View.VISIBLE);
            Toast.makeText(this, "road tax 2",Toast.LENGTH_LONG).show();
        }
        if(requestCode==7 && resultCode== -1 && data != null){
            imageUri = data.getData();
            ivRoadTax3.setImageURI(imageUri);
            cvTax3.setVisibility(View.VISIBLE);
            Toast.makeText(this, "road tax 3",Toast.LENGTH_LONG).show();
        }
        if(requestCode==8 && resultCode== -1 && data != null){
            imageUri = data.getData();
            ivRoadTax4.setImageURI(imageUri);
            Toast.makeText(this, "road tax 4",Toast.LENGTH_LONG).show();
        }
        if(requestCode==9 && resultCode== -1 && data != null){
            imageUri = data.getData();
            ivExterior1.setImageURI(imageUri);
            cvExterior1.setVisibility(View.VISIBLE);
            Toast.makeText(this, "exterior 1",Toast.LENGTH_LONG).show();
        }
        if(requestCode==10 && resultCode== -1 && data != null){
            imageUri = data.getData();
            ivExterior2.setImageURI(imageUri);
            cvExterior2.setVisibility(View.VISIBLE);
            Toast.makeText(this, "exterior 2",Toast.LENGTH_LONG).show();
        }
        if(requestCode==11 && resultCode== -1 && data != null){
            imageUri = data.getData();
            ivExterior3.setImageURI(imageUri);
            cvExterior3.setVisibility(View.VISIBLE);
            Toast.makeText(this, "exterior 3",Toast.LENGTH_LONG).show();
        }
        if(requestCode==12 && resultCode== -1 && data != null){
            imageUri = data.getData();
            ivExterior4.setImageURI(imageUri);
            Toast.makeText(this, "exterior 4",Toast.LENGTH_LONG).show();
        }

        if(requestCode==13 && resultCode== -1 && data != null){
            imageUri = data.getData();
            ivInterior1.setImageURI(imageUri);
            cvInterior1.setVisibility(View.VISIBLE);
            Toast.makeText(this, "interior 1",Toast.LENGTH_LONG).show();
        }
        if(requestCode==14 && resultCode== -1 && data != null){
            imageUri = data.getData();
            ivInterior2.setImageURI(imageUri);
            cvInterior2.setVisibility(View.VISIBLE);
            Toast.makeText(this, "interior 2",Toast.LENGTH_LONG).show();
        }
        if(requestCode==15 && resultCode== -1 && data != null){
            imageUri = data.getData();
            ivInterior3.setImageURI(imageUri);
            cvInterior3.setVisibility(View.VISIBLE);
            Toast.makeText(this, "interior 3",Toast.LENGTH_LONG).show();
        }
        if(requestCode==16 && resultCode== -1 && data != null){
            imageUri = data.getData();
            ivInterior4.setImageURI(imageUri);
            Toast.makeText(this, "interior 4",Toast.LENGTH_LONG).show();
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.carGrant1:
                selectImage(1);
                break;
            case R.id.carGrant2:
                selectImage(2);
                break;
            case R.id.carGrant3:
                selectImage(3);
                break;
            case R.id.carGrant4:
                selectImage(4);
                break;
            case R.id.roadTax1:
                selectImage(5);
                break;
            case R.id.roadTax2:
                selectImage(6);
                break;
            case R.id.roadTax3:
                selectImage(7);
                break;
            case R.id.roadTax4:
                selectImage(8);
                break;
            case R.id.carExterior1:
                selectImage(9);
                break;
            case R.id.carExterior2:
                selectImage(10);
                break;
            case R.id.carExterior3:
                selectImage(11);
                break;
            case R.id.carExterior4:
                selectImage(12);
                break;
            case R.id.carInterior1:
                selectImage(13);
                break;
            case R.id.carInterior2:
                selectImage(14);
                break;
            case R.id.carInterior3:
                selectImage(15);
                break;
            case R.id.carInterior4:
                selectImage(16);
                break;
        }
    }
}