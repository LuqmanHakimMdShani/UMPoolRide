package com.example.umpoolride;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class GeneralMenuFragment extends Fragment {

    DatabaseHelper myDB;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_general_menu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.ToolbarLogin);
        toolbar.setVisibility(View.GONE);

        ImageButton SigninBtn = view.findViewById(R.id.SigninBtn);
        View.OnClickListener OCLSignin = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.GeneralToSignin);
            }
        };
        SigninBtn.setOnClickListener(OCLSignin);

        ImageButton SignupBtn = view.findViewById(R.id.SignupBtn);
        View.OnClickListener OCLSignup = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.GenMenuToSignup);
            }
        };
        SignupBtn.setOnClickListener(OCLSignup);

    }
}