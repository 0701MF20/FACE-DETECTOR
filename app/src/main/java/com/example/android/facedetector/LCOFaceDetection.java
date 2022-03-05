package com.example.android.facedetector;
import android.app.Application;
import com.google.firebase.FirebaseApp;
//This file does not need but i will used because it is written in instruction to use
public class LCOFaceDetection extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(this);
    }
}



    /*String CAMERA_ID="CAMERALD";
    InputImage inputImage;
    private static final SparseIntArray ORIENTATIONS = new SparseIntArray();
    static {
        ORIENTATIONS.append(Surface.ROTATION_0, 0);
        ORIENTATIONS.append(Surface.ROTATION_90, 90);
        ORIENTATIONS.append(Surface.ROTATION_180, 180);
        ORIENTATIONS.append(Surface.ROTATION_270, 270);
    }


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
    }*/

  /*  private void faceDetector(Bitmap bitmap)
    {
        FaceDetectorOptions realTimeOpts =
                new FaceDetectorOptions.Builder().setContourMode(FaceDetectorOptions.CONTOUR_MODE_ALL)
                .setPerformanceMode(FaceDetectorOptions.PERFORMANCE_MODE_ACCURATE).
                       setLandmarkMode(FaceDetectorOptions.LANDMARK_MODE_ALL).
                        setClassificationMode(FaceDetectorOptions.CLASSIFICATION_MODE_ALL).
                        setMinFaceSize(0.5f).
                        build();
        try {
          inputImage=InputImage.fromBitmap(bitmap,getRotationCompensation(CAMERA_ID,this,true));
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
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
                                                Bundle b=new Bundle();
                                                b.putInt("No",runFaceFeatures(faces));
                                                DialogFragment dialogFragment=new DialogFragment();
                                                dialogFragment.setArguments(b);
                                                dialogFragment.setCancelable(true);
                                                dialogFragment.show(getSupportFragmentManager(),"Dialog Fragment");
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
        int count=0;
        Bundle b=new Bundle();
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
            count++;
        }
        return count;
    }*/



