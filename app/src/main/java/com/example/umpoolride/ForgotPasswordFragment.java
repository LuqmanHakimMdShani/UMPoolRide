package com.example.umpoolride;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ForgotPasswordFragment extends Fragment {

    public ForgotPasswordFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_forgot_password, container, false);
    }

    EditText ETSiswamailForgot;
    Button ConfirmVerifyEmailBtn;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ETSiswamailForgot = view.findViewById(R.id.ETSiswamailForgot);
        ConfirmVerifyEmailBtn = view.findViewById(R.id.ConfirmVerifyEmailBtn);

        ConfirmVerifyEmailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = String.valueOf(ETSiswamailForgot.getText());

                new Thread(new Runnable() {

                    public void run() {

                        try {

                            GMailSender sender = new GMailSender(

                                    "",

                                    "");

                            sender.sendMail("Test mail", "This mail has been sent from android app along with attachment",

                                    "",

                                    "");

                            getActivity().runOnUiThread(new Runnable() {
                                public void run() {
                                    Toast.makeText(getActivity(), "Email Sent", Toast.LENGTH_SHORT).show();
                                }
                            });

                        } catch (Exception e) {

                            getActivity().runOnUiThread(new Runnable() {
                                public void run() {
                                    Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                                }
                            });

                        }

                    }

                }).start();
            }
        });

    }
}