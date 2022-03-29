package com.example.android.facedetector;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import static android.widget.Toast.LENGTH_SHORT;
public class ResultDialog extends DialogFragment {
Button buttonOk;
TextView textViewNo;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
     View view=inflater.inflate(R.layout.fragment_resultdialog,container);
        Log.e("Result Dialog","Working");
        textViewNo=view.findViewById(R.id.resulttextView);
        buttonOk=view.findViewById(R.id.okbutton);

        int NumberOfFaces=getArguments().getInt("NoOfFaces");
        float smileProbability=getArguments().getFloat("SmileProb");
        float leftEyeProbability=getArguments().getFloat("LeftEyeProb");
        float rightEyeProbility=getArguments().getFloat("RightEyeProb");

        textViewNo.setText("Faces : "+NumberOfFaces+"\n"+"Smile Probability : "+smileProbability+"%\n"+"Left Eye Probability : "+leftEyeProbability+"%\n"+"Right Eye Probability : "+rightEyeProbility+"%");
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.Theme_AppCompat_DayNight_Dialog_Alert);

        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("ResultDialog", "onClick: closing dialog");
                getDialog().dismiss();
            }
        });


 return view;
    }

}
