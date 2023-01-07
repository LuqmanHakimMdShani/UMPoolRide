package com.example.umpoolride;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.RingtoneManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NotificationFragment extends Fragment {

    DatabaseHelper myDB;

    public NotificationFragment() {
        // Required empty public constructor
    }

   public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notification, container, false);
    }

    private RecyclerView recyclerView;
    private NotificationAdapter adapter;
    private static final String CHANNEL_ID = "my_channel_id";

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        myDB = new DatabaseHelper((MainActivity) getActivity());

        recyclerView = view.findViewById(R.id.recycle1);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        SQLiteDatabase db = myDB.getWritableDatabase();

        String[] projection = {
                "NotificationTitle",
                "NotificationDescription",
                "DateTime"
        };

        String sortOrder = "DateTime DESC";

        Cursor cursor = db.query(
                "notification_table",   // The table to query
                projection,       // The columns to return
                null,             // The columns for the WHERE clause
                null,             // The values for the WHERE clause
                null,             // don't group the rows
                null,             // don't filter by row groups
                sortOrder          // The sort order
        );

        List<Noti> dataList = new ArrayList<>();

        dataList.clear();

        while (cursor.moveToNext()) {
            String NotiTitle = cursor.getString(cursor.getColumnIndexOrThrow("NotificationTitle"));
            String NotiDesc = cursor.getString(cursor.getColumnIndexOrThrow("NotificationDescription"));

            dataList.add(new Noti(NotiTitle, NotiDesc));
        }

        adapter = new NotificationAdapter(dataList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        ImageButton AddNotiBtn = view.findViewById(R.id.AddNotiBtn);

        View.OnClickListener OCLAddNoti = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy - HH:mm");
                String currentTime = dateFormat.format(new Date());
                String username = "Ali";
                String NotiTitle = "Top up";
                String NotiDesc = "RM 5.00";

                boolean isInserted = myDB.insertNoti(username,NotiTitle,NotiDesc,currentTime);
                if (isInserted = true)
                    Toast.makeText(getActivity(),"Notification Inserted",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(getActivity(),"Notification not Inserted",Toast.LENGTH_LONG).show();

                sendNotification(NotiTitle,NotiDesc);
            }
        };
        AddNotiBtn.setOnClickListener(OCLAddNoti);

    }

    private void sendNotification(String NotiTitle,String NotiDesc) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getActivity(), CHANNEL_ID)
                .setSmallIcon(R.drawable.notificationicon)
                .setContentTitle(NotiTitle)
                .setContentText(NotiDesc)
                .setPriority(NotificationCompat.PRIORITY_HIGH);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getActivity());
        notificationManager.notify(1, builder.build());
    }
}