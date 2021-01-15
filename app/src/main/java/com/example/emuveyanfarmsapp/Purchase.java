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

public class Purchase extends Fragment {

    private Purchase.PurchaseListener listener;
    private EditText editText3;
    private EditText editText4;
    private Button buttonSave;

    public interface PurchaseListener{
        void onInputPurchaseSent(CharSequence input);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_purchase, container, false);

        editText3 = v.findViewById(R.id.purchaseitem);
        editText4 = v.findViewById(R.id.prizeofpurchase);
        buttonSave = v.findViewById(R.id.purchasesave);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence input = editText3.getText();
                CharSequence input1 = editText4.getText();
                listener.onInputPurchaseSent(input);
                listener.onInputPurchaseSent(input1);
            }
        });
        return v;
    }
    public void updateEditText3(CharSequence newText){
        editText3.setText(newText);
    }
    public void updateEditText4(CharSequence newText){
        editText4.setText(newText);
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof Purchase.PurchaseListener){
            listener = (Purchase.PurchaseListener) context;
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

