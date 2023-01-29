package com.example.umpoolride;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.media.AudioAttributes;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CreditCardFragment extends Fragment {

    DatabaseHelper myDB;
    private EditText cardNumberEditText;
    private EditText cvvEditText;
    private EditText expirationDateEditText;
    private Button SubmitCCBtn;

    private static final String CHANNEL_ID = "my_channel_id";

    public CreditCardFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_credit_card, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.ToolbarMain);
        toolbar.setTitle("Credit Card Details");

        myDB = new DatabaseHelper(getContext());

        String data = getArguments().getString("CC");

        cardNumberEditText = view.findViewById(R.id.card_number);
        cvvEditText = view.findViewById(R.id.cvv);
        expirationDateEditText = view.findViewById(R.id.expiration_date);
        SubmitCCBtn = view.findViewById(R.id.SubmitCCBtn);


        View.OnClickListener OCLSubmitCC = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (CreditCardValidator.isValidCardNumber(cardNumberEditText)
                        && CreditCardValidator.isValidCvv(cvvEditText)
                        && CreditCardValidator.isValidExpirationDate(expirationDateEditText)) {

                    // The credit card information is valid, send the data to your server or the payment gateway
                    // and proceed with the payment

                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy - HH:mm");
                    String currentTime = dateFormat.format(new Date());

                    double Amount = Double.parseDouble(data);
                    String username = Credentials.USERNAME;
                    String Topup = "Top up (Credit/Debit)";
                    String NotiDesc = String.format("You have Top up RM%.2f into your account.",Amount);

                    myDB.insertNoti(username,Topup,NotiDesc,currentTime);

                    boolean isInserted = myDB.insertTopup(username,Topup, Amount,currentTime);
                    if (isInserted = true)
                        Toast.makeText(getActivity(),"Top up succesful",Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(getActivity(),"Top up not succesful",Toast.LENGTH_LONG).show();

                    Bundle bundle1 = new Bundle();
                    bundle1.putString("key", data);

                    Navigation.findNavController(view).navigate(R.id.CCToTransSuccess, bundle1);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            sendNotification(Topup,NotiDesc);
                        }
                    }, 5000); // delay by 5 seconds

                } else {
                    // Show an error message or highlight the invalid fields
                    Toast.makeText(getActivity(),"Credit Card Details invalid",Toast.LENGTH_LONG).show();
                }
            }
        };
        SubmitCCBtn.setOnClickListener(OCLSubmitCC);

    }

    private void sendNotification(String NotiTitle,String NotiDesc) {
        NotificationManager notificationManager1 = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "Notifications", NotificationManager.IMPORTANCE_HIGH);
            channel.setSound(Settings.System.DEFAULT_NOTIFICATION_URI, new AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_NOTIFICATION).build());
            notificationManager1.createNotificationChannel(channel);
        }
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