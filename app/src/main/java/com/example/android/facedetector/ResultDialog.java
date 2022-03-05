package com.example.android.facedetector;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class ResultDialog extends DialogFragment {
Button buttonOk;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
     //   return super.onCreateView(inflater, container, savedInstanceState);
    View view=inflater.inflate(R.layout.fragment_resultdialog,container);
buttonOk=view.findViewById(R.id.okbutton);
buttonOk.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        dismiss();
    }
});
return view;
    }

}
