package com.example.umpoolride;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
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

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.ToolbarMain);
        toolbar.setTitle("Notifications");

        myDB = new DatabaseHelper(getContext());

        recyclerView = view.findViewById(R.id.recycle1);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        SQLiteDatabase db = myDB.getWritableDatabase();

        String[] projection = {
                "NotificationTitle",
                "NotificationDescription",
                "DateTime"
        };

        String condition = Credentials.USERNAME;

        String sortOrder = "DateTime DESC";

        Cursor cursor = db.query(
                "notification_table",   // The table to query
                projection,       // The columns to return
                "Email LIKE ?",             // The columns for the WHERE clause
                new String[]{condition},             // The values for the WHERE clause
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

    }

}