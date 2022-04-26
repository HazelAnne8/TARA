package com.example.tara.ProfileActivities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.tara.Models.User;
import com.example.tara.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class EditProfile extends AppCompatActivity {
    private TextView tvEditName;
    private ImageView ivEditPhoto;
    private Button saveChangesBtn;
    GoogleSignInAccount signInAccount;
    StorageReference storageReference;
    DatabaseReference databaseReference;
    String databaseLocation;
    Toolbar toolbar;
    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        getSupportActionBar().hide();

        databaseLocation = getString(R.string.databasePath);
        toolbar = findViewById(R.id.appBar);
        ivEditPhoto = findViewById(R.id.editPhoto);
        tvEditName = findViewById(R.id.editName);
        saveChangesBtn = findViewById(R.id.saveChangesBtn);
        signInAccount = GoogleSignIn.getLastSignedInAccount(this);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        ivEditPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();
            }
        });

        //connect to the database
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        FirebaseDatabase database = FirebaseDatabase.getInstance(databaseLocation);
        databaseReference = database.getReference("users").child(currentUser.getUid());

        // if an account is signed in using gmail
        if(signInAccount != null){
            Uri photoUrl = signInAccount.getPhotoUrl();
            tvEditName.setText(signInAccount.getDisplayName());
            Glide.with(this).load(photoUrl).into(ivEditPhoto);
        }
        else
            ivEditPhoto.setImageResource(R.drawable.ic_profile_image);

        //fetch data in database then displays the image and name
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists() && snapshot.getChildrenCount() > 0){
                    User user =  snapshot.getValue(User.class);
                    assert user != null;
                    if(!user.imageUrl.isEmpty()){
                        String imageUrl = snapshot.child("imageUrl").getValue().toString();
                        Glide.with(EditProfile.this).load(imageUrl).into(ivEditPhoto);
                    }
                    else
                        ivEditPhoto.setImageResource(R.drawable.ic_profile_image);
                    tvEditName.setText(snapshot.child("name").getValue().toString());
                }
                else {
                    Toast.makeText(EditProfile.this,"Error retrieving info",Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
    //opens image selector
    private void selectImage(){
        Intent galleryIntent = new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, 2);
    }

    // calls this method after image is selected
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //if image is selected, display the image
        if(requestCode==2 && resultCode== -1 && data != null){
            imageUri = data.getData();
            ivEditPhoto.setImageURI(imageUri);
            saveChangesBtn.setVisibility(View.VISIBLE);
            saveChangesBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    uploadImage();
                }
            });
        }
    }

    // upload the image to the cloud storage
    private void uploadImage(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.CANADA);
        Date now = new Date();
        String fileName = formatter.format(now);

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Updating profile picture");
        progressDialog.setMessage("Please wait, we are setting your picture");
        progressDialog.show();

        storageReference = FirebaseStorage.getInstance("gs://tara-f89da.appspot.com").getReference("images/"+fileName);
        storageReference.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                progressDialog.dismiss();
                taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                    // gets the image url and store it in the database
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        String imageUrl = task.getResult().toString();
                        databaseReference.child("imageUrl").setValue(imageUrl);
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(EditProfile.this, "Upload failed",Toast.LENGTH_LONG).show();
            }
        });
    }
}