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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpFragment extends Fragment {

    EditText ETFullName,ETSiswamailSignup,ETMatricNumber,ETPasswordSignup,ETConfirmPassword;
    DatabaseHelper myDB;

    public SignUpFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.ToolbarLogin);
        toolbar.setTitle("");
        toolbar.setVisibility(View.VISIBLE);

        myDB = new DatabaseHelper(getContext());

        ETFullName = view.findViewById(R.id.ETFullName);
        ETSiswamailSignup = view.findViewById(R.id.ETSiswamailSignup);
        ETMatricNumber = view.findViewById(R.id.ETMatricNumber);
        ETPasswordSignup = view.findViewById(R.id.ETPasswordSignup);
        ETConfirmPassword = view.findViewById(R.id.ETConfirmPassword);

        ImageButton SignupBtn2 = view.findViewById(R.id.SignupBtn2);

        View.OnClickListener OCLSignupBtn2 = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String FullName = String.valueOf(ETFullName.getText());
                String Siswamail = String.valueOf(ETSiswamailSignup.getText());
                String MatricNumber = String.valueOf(ETMatricNumber.getText());
                String Password = String.valueOf(ETPasswordSignup.getText());
                String CPassword = String.valueOf(ETConfirmPassword.getText());

                if(FullName.equals("")||Siswamail.equals("")||MatricNumber.equals("")||Password.equals("")){

                    Toast.makeText(getActivity(),"Please fill up all fields",Toast.LENGTH_LONG).show();

                }else{

                    if(isEmailValid(Siswamail)){

                        if(Password.equals(CPassword)){

                            boolean isInserted = myDB.insertUser(FullName,Siswamail,Password,MatricNumber);
                            if (isInserted = true) {
                                Toast.makeText(getActivity(), "Sign up succesful.", Toast.LENGTH_LONG).show();
                                Navigation.findNavController(view).navigate(R.id.SignupToGenMenu);
                            }else {
                                Toast.makeText(getActivity(), "Sign up not succesful.", Toast.LENGTH_LONG).show();
                            }

                        }else{
                            Toast.makeText(getActivity(),"Password not similar.",Toast.LENGTH_LONG).show();
                        }

                    }else{
                        Toast.makeText(getActivity(),"Invalid Email.",Toast.LENGTH_LONG).show();
                    }
                }

            }
        };
        SignupBtn2.setOnClickListener(OCLSignupBtn2);

    }

    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}