package com.example.umpoolride;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class TopupSourceFragment extends Fragment {

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

        String data = getArguments().getString("TopupAmount");

        TextView TopupTV = view.findViewById(R.id.TopupTV);

        TopupTV.setText(data);

        Button BankTransferBtn = view.findViewById(R.id.BankTransferBtn);

        View.OnClickListener OCLBankTransfer = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle1 = new Bundle();
                bundle1.putString("key", data);

                Navigation.findNavController(view).navigate(R.id.action_topupSourceFragment_to_transactionSuccessfulFragment, bundle1);
            }
        };
        BankTransferBtn.setOnClickListener(OCLBankTransfer);

    }
}