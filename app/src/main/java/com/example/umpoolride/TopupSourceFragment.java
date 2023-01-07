package com.example.umpoolride;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TopupSourceFragment extends Fragment {

    DatabaseHelper myDB;

    public TopupSourceFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_topup_source, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        myDB = new DatabaseHelper((MainActivity) getContext());

        String data = getArguments().getString("TopupAmount");

        Button BankTransferBtn = view.findViewById(R.id.BankTransferBtn);

        View.OnClickListener OCLBankTransfer = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy - HH:mm");
                String currentTime = dateFormat.format(new Date());

                boolean isInserted = myDB.insertTopup("Ali","Top up", Double.parseDouble(data),currentTime);
                if (isInserted = true)
                    Toast.makeText(getActivity(),"Data Inserted",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(getActivity(),"Data not Inserted",Toast.LENGTH_LONG).show();

                Bundle bundle1 = new Bundle();
                bundle1.putString("key", data);
                Navigation.findNavController(view).navigate(R.id.action_topupSourceFragment_to_transactionSuccessfulFragment, bundle1);
            }
        };
        BankTransferBtn.setOnClickListener(OCLBankTransfer);

    }

}