package com.example.umpoolride;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

public class SignInFragment extends Fragment {

    DatabaseHelper myDB;
    EditText Siswamail, Password;
    ImageButton LoginBtn;
    Button ForgotpassBtn;

    public SignInFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_in, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.ToolbarLogin);
        toolbar.setVisibility(View.VISIBLE);

        myDB = new DatabaseHelper((LoginActivity) getContext());

        Siswamail = view.findViewById(R.id.ETSiswamail);
        Password = view.findViewById(R.id.ETPassword);
        LoginBtn = view.findViewById(R.id.LoginBtn);
        ForgotpassBtn = view.findViewById(R.id.ForgotpassBtn);

        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = Siswamail.getText().toString();
                String pass = Password.getText().toString();

                String fullname = "";

                //for testing main activity
                //Credentials.USERNAME = email;

                //Intent intent = new Intent(getActivity(), MainActivity.class);
                //startActivity(intent);

                if (email.equals("") || pass.equals(""))
                    Toast.makeText(getActivity(), "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else {
                    Boolean checkuserpass = myDB.checkemailpassword(email, pass);
                    if (checkuserpass == true) {
                        Credentials.USERNAME = email;

                        Cursor cursor = myDB.getTypeUser(email);
                        if (cursor.moveToFirst()) {
                            Credentials.TYPEUSER = cursor.getString(0);
                        }
                        Cursor cursor1 = myDB.getFullName(email);
                        if (cursor1.moveToFirst()) {
                            Credentials.FULLNAME = cursor1.getString(0);
                        }
                        Toast.makeText(getActivity(), "Sign in successfull", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getActivity(), "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

        ForgotpassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.SigninToForgot);
            }
        });

    }
}
