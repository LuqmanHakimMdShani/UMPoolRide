package com.example.umpoolride;

import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

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

    private BottomNavigationView bottomNavigationView;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bottomNavigationView = getActivity().findViewById(R.id.bottom_nav_view);
        bottomNavigationView.setVisibility(View.VISIBLE);

        double sum = 0;

        myDB = new DatabaseHelper((MainActivity) getContext());

        ImageButton TopupBtn = view.findViewById(R.id.TopupBtn);

        TextView TVAmount = view.findViewById(R.id.TVAmount);

        Cursor cursor = myDB.SumAmount("Ali");
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

        Button NotificationBtn = view.findViewById(R.id.NotificationBtn);

        View.OnClickListener OCLNotification = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.WalletToNoti);
            }
        };
        NotificationBtn.setOnClickListener(OCLNotification);

    }
}