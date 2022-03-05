package com.example.android.facedetector;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.SparseIntArray;
import android.view.Surface;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.face.Face;
import com.google.mlkit.vision.face.FaceDetection;
import com.google.mlkit.vision.face.FaceDetector;
import com.google.mlkit.vision.face.FaceDetectorOptions;

import java.util.List;

public class MainActivity extends AppCompatActivity {
Button openCameraButton;
ImageView imageView;
static Bitmap imageBitmap;
Button processButton;
    String CAMERA_ID;
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
        faceDetector(imageBitmap);

    }
    }
});
    InputImage inputImage;
    private static final SparseIntArray ORIENTATIONS = new SparseIntArray();
    static {
        ORIENTATIONS.append(Surface.ROTATION_0, 0);
        ORIENTATIONS.append(Surface.ROTATION_90, 90);
        ORIENTATIONS.append(Surface.ROTATION_180, 180);
        ORIENTATIONS.append(Surface.ROTATION_270, 270);
    }

    /**
     * Get the angle by which an image must be rotated given the device's current
     * orientation.
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private int getRotationCompensation(String cameraId, Activity activity, boolean isFrontFacing)
            throws CameraAccessException {
        // Get the device's current rotation relative to its "native" orientation.
        // Then, from the ORIENTATIONS table, look up the angle the image must be
        // rotated to compensate for the device's rotation.
        int deviceRotation = activity.getWindowManager().getDefaultDisplay().getRotation();
        int rotationCompensation = ORIENTATIONS.get(deviceRotation);

        // Get the device's sensor orientation.
        CameraManager cameraManager = (CameraManager) activity.getSystemService(CAMERA_SERVICE);
        int sensorOrientation = cameraManager
                .getCameraCharacteristics(cameraId)
                .get(CameraCharacteristics.SENSOR_ORIENTATION);

        if (isFrontFacing) {
            rotationCompensation = (sensorOrientation + rotationCompensation) % 360;
        } else { // back-facing
            rotationCompensation = (sensorOrientation - rotationCompensation + 360) % 360;
        }
        return rotationCompensation;
    }
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

           if (takePictureIntent.resolveActivity(getPackageManager())!=null)
           {
               startForResult.launch(takePictureIntent);

           }
           else {
               Toast.makeText(
                               MainActivity.this,
                               "Something went wrong",
                               Toast.LENGTH_SHORT)
                       .show();
           }
       }

   });

    }
    private void faceDetector(Bitmap bitmap)
    {
        FaceDetectorOptions realTimeOpts =
                new FaceDetectorOptions.Builder().setContourMode(FaceDetectorOptions.CONTOUR_MODE_ALL)
                        .setPerformanceMode(FaceDetectorOptions.PERFORMANCE_MODE_ACCURATE).
                        setLandmarkMode(FaceDetectorOptions.LANDMARK_MODE_ALL).
                        setClassificationMode(FaceDetectorOptions.CLASSIFICATION_MODE_ALL).
                        setMinFaceSize(0.5f).
                        build();
        inputImage=InputImage.fromBitmap(bitmap,0);

//        try {
//            inputImage=InputImage.fromBitmap(bitmap,getRotationCompensation(CAMERA_ID,this,true));
//        } catch (CameraAccessException e) {
//            e.printStackTrace();
//        }
        FaceDetector detector = FaceDetection.getClient(realTimeOpts);
        //Process Image
        Task<List<Face>> result =
                detector.process(inputImage)
                        .addOnSuccessListener(
                                new OnSuccessListener<List<Face>>() {
                                    @Override
                                    public void onSuccess(List<Face> faces) {
                                        // Task completed successfully
                                        // ...
                                        processButton.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                Toast.makeText(MainActivity.this,"FACES ARE"+runFaceFeatures(faces),Toast.LENGTH_SHORT).show();
                                                Bundle b=new Bundle();
                                                b.putInt("No",runFaceFeatures(faces));
//                                                DialogFragment dialogFragment=new DialogFragment();
//                                                dialogFragment.setArguments(b);
//                                                dialogFragment.setCancelable(true);
//                                                dialogFragment.show(getSupportFragmentManager(),"Dialog Fragment");
                                                DialogFragment dialogFragment=new DialogFragment();
                                                dialogFragment.setArguments(b);
                                                dialogFragment.setCancelable(true);
                                                dialogFragment.show(getSupportFragmentManager(),"FragmentManager");
                                            }
                                        });

                                    }
                                })
                        .addOnFailureListener(
                                new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        // Task failed with an exception
                                        // ...
                                        Toast.makeText(getApplicationContext(),
                                                "Exception", Toast.LENGTH_LONG).show();                                    }
                                });
    }
    private int runFaceFeatures(List<Face> faces)
    {
        float smileProb=0;
        float rightEyeProb=0;
        float leftEyeProb=0;
        for(Face face:faces)
        {
            if(face.getSmilingProbability()!=null)
            {
                smileProb=face.getSmilingProbability();
            }
            if(face.getLeftEyeOpenProbability()!=null)
            {
                leftEyeProb=face.getLeftEyeOpenProbability();
            }
            if(face.getRightEyeOpenProbability()!=null)
            {
                leftEyeProb=face.getRightEyeOpenProbability();
            }
//        if(face.getTrackingId()!=null)
//        {
//
//            int id=face.getTrackingId();
//        }
        }
        return faces.size();
    }
}