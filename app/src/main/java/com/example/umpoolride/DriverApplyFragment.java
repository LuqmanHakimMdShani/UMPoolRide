package com.example.umpoolride;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

public class DriverApplyFragment extends Fragment {

    public DriverApplyFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_driver_apply, container, false);
    }

    Button DriverApplySubmit;
    CheckBox CBTC1,CBTC2;
    DatabaseHelper myDB;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.ToolbarMain);
        toolbar.setVisibility(View.VISIBLE);
        toolbar.setTitle("Driver Application Form");

        DriverApplySubmit = view.findViewById(R.id.DriverApplySubmit);
        CBTC1 = view.findViewById(R.id.CBTC1);
        CBTC2 = view.findViewById(R.id.CBTC2);
        myDB = new DatabaseHelper(getContext());

        DriverApplySubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (CBTC1.isChecked()&&CBTC2.isChecked()){
                    Boolean checkuserpass = myDB.UpdateTypeofUser();
                    if (checkuserpass == true){
                        Navigation.findNavController(view).navigate(R.id.DriverApplyToMain);

                        Toast.makeText(getActivity(),"Driver application succesful.", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(getActivity(),"Apply fail.", Toast.LENGTH_LONG).show();
                    }

                }else{
                    Toast.makeText(getActivity(),"Please check all the Term&Conditions.", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}