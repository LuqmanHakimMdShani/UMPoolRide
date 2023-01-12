package com.example.umpoolride;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SettingFragment extends Fragment {

    public SettingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false);
    }

    TextView TVFullName,TVEmail;
    Button LogoutBtn;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TVFullName = view.findViewById(R.id.TVFullName);
        TVEmail = view.findViewById(R.id.TVEmail);
        LogoutBtn = view.findViewById(R.id.LogoutBtn);

        TVEmail.setText(Credentials.USERNAME);
        TVFullName.setText(Credentials.FULLNAME);

        LogoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Credentials.USERNAME = "";
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);

            }
        });

    }
}