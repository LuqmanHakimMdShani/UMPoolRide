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
import android.widget.TextView;

public class TransactionSuccessfulFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_transaction_successful, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        double data = Double.parseDouble(getArguments().getString("key"));

        TextView TopupAmountTV = view.findViewById(R.id.TopupAmountTV);

        TopupAmountTV.setText(String.format("+    RM%.2f",data));

        Button DoneTopupBtn = view.findViewById(R.id.DoneTopup);

        View.OnClickListener OCLDoneTopup = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.TransSuccessfulToWallet);
            }
        };
        DoneTopupBtn.setOnClickListener(OCLDoneTopup);


    }
}