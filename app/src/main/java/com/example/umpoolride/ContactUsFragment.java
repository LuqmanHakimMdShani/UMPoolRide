package com.example.umpoolride;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ContactUsFragment extends Fragment {

    public ContactUsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact_us, container, false);
    }

    BottomNavigationView bottomNavigationView,driverbottomNavigationView;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.ToolbarMain);
        toolbar.setVisibility(View.VISIBLE);
        toolbar.setTitle("Contact Us");

        bottomNavigationView = getActivity().findViewById(R.id.bottom_nav_view);
        driverbottomNavigationView = getActivity().findViewById(R.id.driver_bottom_nav_view);
        bottomNavigationView.setVisibility(View.GONE);
        driverbottomNavigationView.setVisibility(View.GONE);

    }
}