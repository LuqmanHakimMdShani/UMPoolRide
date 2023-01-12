package com.example.umpoolride;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

public class WalletFragment extends Fragment {

    DatabaseHelper myDB;

    public WalletFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_wallet, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.ToolbarMain);
        toolbar.setVisibility(View.GONE);

        //((MainActivity) getActivity()).getSupportActionBar().setTitle("Wallet");

        double sum = 0;

        myDB = new DatabaseHelper(getContext());

        ImageButton TopupBtn = view.findViewById(R.id.TopupBtn);

        TextView TVAmount = view.findViewById(R.id.TVAmountMain);
        TextView TVType = view.findViewById(R.id.TVType);

        TVType.setText(Credentials.TYPEUSER);

        Cursor cursor = myDB.SumAmount();
        if (cursor.moveToFirst()) {
            sum = cursor.getDouble(0);
        }
        TVAmount.setText(String.format("RM %.2f", sum));
        cursor.close();

        View.OnClickListener OCLTopup = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.WalletToTopup);
            }
        };
        TopupBtn.setOnClickListener(OCLTopup);

        ImageButton TransHistBtn = view.findViewById(R.id.TransHistBtn);
        View.OnClickListener OCLTransHist = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.WalletToTransHist);
            }
        };
        TransHistBtn.setOnClickListener(OCLTransHist);

        ImageButton DriverApplyBtn = view.findViewById(R.id.DriverApplyBtn2);
        View.OnClickListener OCLDriverApply = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.WalletToDriverApply);
            }
        };
        DriverApplyBtn.setOnClickListener(OCLDriverApply);
    }
}