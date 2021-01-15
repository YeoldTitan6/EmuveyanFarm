package com.example.emuveyanfarmsapp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Sales extends Fragment {
    private SalesListener listener;
    private EditText editText1;
    private EditText editText2;
    private Button buttonSave;

    public interface SalesListener{
        void onInputSalesSent(CharSequence input);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_sales, container, false);

        editText1 = v.findViewById(R.id.salesitem);
        editText2 = v.findViewById(R.id.prizeofsales);
        buttonSave = v.findViewById(R.id.salesSave);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence input = editText1.getText();
                CharSequence input1 = editText2.getText();
                listener.onInputSalesSent(input);
                listener.onInputSalesSent(input1);
            }
        });
        return v;
    }
    public void updateEditText1(CharSequence newText){
        editText1.setText(newText);
    }
    public void updateEditText2(CharSequence newText){
        editText2.setText(newText);
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof SalesListener){
            listener = (SalesListener) context;
        }else {
            throw new RuntimeException(context.toString()
            + "You must implement SalesListener");
        }
    }
    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}
