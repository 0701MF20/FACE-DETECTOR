package com.example.android.facedetector;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class ResultDialog extends DialogFragment {
Button buttonOk;
TextView textViewNo;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
     //   return super.onCreateView(inflater, container, savedInstanceState);
 View view=inflater.inflate(R.layout.fragment_resultdialog,container);

        textViewNo=view.findViewById(R.id.resulttextView);
        buttonOk=view.findViewById(R.id.okbutton);
        Bundle bundle=getArguments();
        int no=bundle.getInt("No");
        textViewNo.setText("Faces:"+no);
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               getDialog().dismiss();
            }
        });

 return view;
    }

//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//
//
//
//    }
}
