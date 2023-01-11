package com.example.umpoolride;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TopupSourceFragment extends Fragment {

    DatabaseHelper myDB;
    private static final String CHANNEL_ID = "my_channel_id";

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

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.ToolbarMain);
        toolbar.setTitle("Top Up Sources");

        myDB = new DatabaseHelper(getContext());

        String data = getArguments().getString("TopupAmount");

        Button BankTransferBtn = view.findViewById(R.id.BankTransferBtn);

        View.OnClickListener OCLBankTransfer = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy - HH:mm");
                String currentTime = dateFormat.format(new Date());

                double Amount = Double.parseDouble(data);
                String username = Credentials.USERNAME;
                String Topup = "Top up (Bank Transfer)";
                String NotiDesc = String.format("You have Top up RM%.2f into your account.",Amount);

                myDB.insertNoti(username,Topup,NotiDesc,currentTime);

                boolean isInserted = myDB.insertTopup(username,Topup, Amount,currentTime);
                if (isInserted = true)
                    Toast.makeText(getActivity(),"Top up succesful",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(getActivity(),"Top up not succesful",Toast.LENGTH_LONG).show();

                Bundle bundle1 = new Bundle();
                bundle1.putString("key", data);
                Navigation.findNavController(view).navigate(R.id.TopupSourcesToTransSuccess, bundle1);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        sendNotification(Topup,NotiDesc);
                    }
                }, 5000); // delay by 5 seconds
            }
        };

        BankTransferBtn.setOnClickListener(OCLBankTransfer);

        Button CreditDebitBtn = view.findViewById(R.id.CreditDebitBtn);

        View.OnClickListener OCLCreditDebit = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle1 = new Bundle();
                bundle1.putString("CC", data);

                Navigation.findNavController(view).navigate(R.id.TopupSourcesToCC, bundle1);
            }
        };
        CreditDebitBtn.setOnClickListener(OCLCreditDebit);


    }

    private void sendNotification(String NotiTitle,String NotiDesc) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getActivity(), CHANNEL_ID)
                .setSmallIcon(R.drawable.notificationicon)
                .setContentTitle(NotiTitle)
                .setContentText(NotiDesc)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(NotiDesc))
                .setPriority(NotificationCompat.PRIORITY_HIGH);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getActivity());
        notificationManager.notify(1, builder.build());
    }

}