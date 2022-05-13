package com.example.tara.Host;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.tara.R;

public class TEstData extends AppCompatActivity {
    TextView address1,address2,city,postcode,province,year,brand,transmission,drivetrain,seats,type,fuelType,mileage,model,plateNumber
            ,insuranceUrl,priceRate,description,carUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_data);

        address1 = findViewById(R.id.dataAL1);
        address2 = findViewById(R.id.dataAL2);
        city = findViewById(R.id.dataCity);
        postcode = findViewById(R.id.dataPostcode);
        province = findViewById(R.id.dataProvince);
        year = findViewById(R.id.dataYear);
        brand = findViewById(R.id.dataBrand);
        transmission = findViewById(R.id.dataTransmission);
        drivetrain = findViewById(R.id.dataDriveTrain);
        seats = findViewById(R.id.dataSeats);
        type = findViewById(R.id.dataType);
        fuelType = findViewById(R.id.dataFuelType);
        mileage = findViewById(R.id.dataMileage);
        model = findViewById(R.id.dataModel);
        plateNumber = findViewById(R.id.dataPlateNumber);
        insuranceUrl = findViewById(R.id.dataInsuranceURl);
        priceRate = findViewById(R.id.dataPriceRate);
        description = findViewById(R.id.dataDescription);
        carUrl = findViewById(R.id.dataCarUrl);

        String dataAddress1 = getIntent().getStringExtra("dataAL1");
        String dataaddress2 = getIntent().getStringExtra("dataAL2");
        String datacity = getIntent().getStringExtra("dataCity");
        String datapostcode = getIntent().getStringExtra("dataPostcode");
        String dataprovince = getIntent().getStringExtra("dataProvince");
        String datayear = getIntent().getStringExtra("dataYear");
        String databrand = getIntent().getStringExtra("dataBrand");
        String datatransmission = getIntent().getStringExtra("dataTransmission");
        String datadrivetrain = getIntent().getStringExtra("dataDrivetrain");
        String dataseats = getIntent().getStringExtra("dataSeats");
        String datatype = getIntent().getStringExtra("dataType");
        String datafuelType = getIntent().getStringExtra("dataFuelType");
        String datamileage = getIntent().getStringExtra("dataMileage");
        String datamodel = getIntent().getStringExtra("dataModel");
        String dataplateNumber = getIntent().getStringExtra("dataPlateNumber");
        String datainsuranceUrl = getIntent().getStringExtra("dataInsuranceUrl");
        String datapriceRate = getIntent().getStringExtra("dataPriceRate");
        String datadescription = getIntent().getStringExtra("dataDescription");
        String datacarUrl = getIntent().getStringExtra("dataCarUrl");

        address1.setText(dataAddress1);
        address2.setText(dataaddress2);
        city.setText(datacity);
        postcode.setText(datapostcode);
        province.setText(dataprovince);
        year.setText(datayear);
        brand.setText(databrand);
        transmission.setText(datatransmission);
        drivetrain.setText(datadrivetrain);
        seats.setText(dataseats);
        type.setText(datatype);
        fuelType.setText(datafuelType);
        mileage.setText(datamileage);
        model.setText(datamodel);
        plateNumber.setText(dataplateNumber);
        insuranceUrl.setText(datainsuranceUrl);
        priceRate.setText(datapriceRate);
        description.setText(datadescription);
        carUrl.setText(datacarUrl);
    }
}