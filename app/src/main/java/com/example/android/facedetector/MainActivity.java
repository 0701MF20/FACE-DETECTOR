package com.example.android.facedetector;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
Button openCameraButton;
ImageView imageView;
static Bitmap imageBitmap;
Button processButton;
Context context=getApplicationContext();
ActivityResultLauncher<Intent> startForResult=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
    @Override
    public void onActivityResult(ActivityResult result) {
    if(result.getResultCode()==RESULT_OK&&result.getData()!=null)
    {
        Bundle bundle=result.getData().getExtras();
     imageBitmap=(Bitmap)bundle.get("data");
     //Bitmap photo=(Bitmap)getIntent().getExtras().get(getIntent().toString());
     openCameraButton.setVisibility(View.INVISIBLE);
     imageView.setVisibility(View.VISIBLE);
     processButton.setVisibility(View.VISIBLE);
     imageView.setImageBitmap(imageBitmap);
     processButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             DialogFragment dialogFragment=new DialogFragment();
             dialogFragment.setCancelable(true);
             dialogFragment.show(getSupportFragmentManager(),"Dialog Fragment");
         }
     });
    }
    }
});
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openCameraButton=findViewById(R.id.OpenCameraButton);
        imageView=findViewById(R.id.imageView);
        processButton=findViewById(R.id.processImage);
        processButton.setVisibility(View.INVISIBLE);
        openCameraButton.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View view) {
           Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
           startForResult.launch(takePictureIntent);
       }
   });
    }
}