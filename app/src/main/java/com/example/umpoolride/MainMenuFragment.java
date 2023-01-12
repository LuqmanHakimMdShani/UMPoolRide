package com.example.umpoolride;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

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

        TopupBtnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.MainToWallet);
            }
        });

    }
}