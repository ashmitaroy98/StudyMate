package com.example.ashmita.studymate;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
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

import java.net.URI;

public class PostActivity extends AppCompatActivity {

    private ImageButton mSelectImage;
    private EditText mPostName;
    public EditText mPostDesc;
    private EditText mPostTime;
    private Button mSubmitBtn;
    private ProgressDialog mProgress;

    private Uri mImageUri = null;

    private static final int GallERY_REQUEST = 1;

    private StorageReference mStorage;
    private DatabaseReference mDatabase;
    private FirebaseAuth firebaseAuth;

    private FirebaseAuth mAuth;
    private FirebaseUser mCurrent_user;

    private DatabaseReference mDatabaseUsers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        mStorage = FirebaseStorage.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();

    //    mDatabaseUsers = FirebaseDatabase.getInstance().getReference().child("Users").child(mCurrent_user.getUid());

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Posts");

        mCurrent_user = firebaseAuth.getCurrentUser();

        mSelectImage = (ImageButton) findViewById(R.id.profilephoto);

        mPostName = (EditText) findViewById(R.id.name);
        mPostDesc = (EditText) findViewById(R.id.description);
        mPostTime = (EditText) findViewById(R.id.Posttime);
        mSubmitBtn = (Button) findViewById(R.id.btn_submit);

        mProgress = new ProgressDialog(this);

        mSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, GallERY_REQUEST);
            }
        });

        mSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startPosting();
            }
        });
    }

    private void startPosting() {

        mProgress.setMessage("Posting");
            mProgress.show();

        final String name_val = mPostName.getText().toString().trim();
        final String desc_val = mPostDesc.getText().toString().trim();
        final String time_val = mPostTime.getText().toString().trim();

        if (!TextUtils.isEmpty(name_val) && !TextUtils.isEmpty(desc_val) && !TextUtils.isEmpty(time_val) && mImageUri != null){

            StorageReference filepath = mStorage.child("Profile_Images").child(mImageUri.getLastPathSegment());

            filepath.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                final Task<Uri> downloadUrl = taskSnapshot.getMetadata().getReference().getDownloadUrl();

                    final DatabaseReference newPost = mDatabase.push();
                    mDatabase.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            newPost.child("name").setValue(name_val);
                            newPost.child("desc").setValue(desc_val);
                            newPost.child("time").setValue(time_val);
                            newPost.child("image").setValue(downloadUrl.toString());
                            newPost.child("uid").setValue(mCurrent_user.getUid()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if(task.isSuccessful()){
                                        startActivity(new Intent(PostActivity.this, HomeActivity.class));
                                    }
                                }
                            });

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });


                    mProgress.dismiss();

                }
            });

        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == GallERY_REQUEST && resultCode == RESULT_OK){

            mImageUri = data.getData();

            mSelectImage.setImageURI(mImageUri);
        }
    }
}
