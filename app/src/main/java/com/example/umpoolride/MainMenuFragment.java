package com.example.umpoolride;

import android.content.Intent;
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

import com.example.umpoolride.activity.MapsActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainMenuFragment extends Fragment {

    public MainMenuFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_menu, container, false);
    }
    BottomNavigationView bottomNavigationView,driverbottomNavigationView;
    ImageButton BookBtn,TopupBtnMain,ContactBtn,DriverApplyBtn2;
    DatabaseHelper myDB;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.ToolbarMain);
        toolbar.setVisibility(View.GONE);

        bottomNavigationView = getActivity().findViewById(R.id.bottom_nav_view);
        driverbottomNavigationView = getActivity().findViewById(R.id.driver_bottom_nav_view);

        if(Credentials.TYPEUSER.equals("Driver")) {
            bottomNavigationView.setVisibility(View.GONE);
            driverbottomNavigationView.setVisibility(View.VISIBLE);
        }else{
            driverbottomNavigationView.setVisibility(View.GONE);
            bottomNavigationView.setVisibility(View.VISIBLE);
        }

        BookBtn = view.findViewById(R.id.BookBtn);
        TopupBtnMain = view.findViewById(R.id.TopupBtnMain);
        ContactBtn = view.findViewById(R.id.ContactBtn);
        DriverApplyBtn2 = view.findViewById(R.id.DriverApplyBtn2);

        double sum = 0;

        myDB = new DatabaseHelper(getContext());

        TextView TVAmount = view.findViewById(R.id.TVAmountMain);

        Cursor cursor = myDB.SumAmount();
        if (cursor.moveToFirst()) {
            sum = cursor.getDouble(0);
        }
        TVAmount.setText(String.format("RM %.2f", sum));
        cursor.close();

        DriverApplyBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.MainToDriverApply);
            }
        });

        ContactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.MainToContact);
            }
        });

        TopupBtnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.MainToWallet);
            }
        });

        BookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MapsActivity.class);
                startActivity(intent);
            }
        });

    }
}