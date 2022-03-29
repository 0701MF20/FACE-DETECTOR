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




