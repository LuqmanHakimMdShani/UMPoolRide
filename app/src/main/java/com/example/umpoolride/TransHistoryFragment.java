package com.example.umpoolride;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class TransHistoryFragment extends Fragment {

    DatabaseHelper myDB;

    public TransHistoryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_trans_history, container, false);
    }

    private RecyclerView recyclerView;
    private TransHistAdapter adapter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        myDB = new DatabaseHelper((MainActivity) getActivity());

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        SQLiteDatabase db = myDB.getWritableDatabase();

        String[] projection = {
                "TypeofTransaction",
                "Amount",
                "DateTime"
        };

        String sortOrder = "DateTime DESC";

        Cursor cursor = db.query(
                "transaction_table",   // The table to query
                projection,       // The columns to return
                null,             // The columns for the WHERE clause
                null,             // The values for the WHERE clause
                null,             // don't group the rows
                null,             // don't filter by row groups
                sortOrder          // The sort order
        );

        List<Trans> dataList = new ArrayList<>();
        while (cursor.moveToNext()) {
            String type = cursor.getString(cursor.getColumnIndexOrThrow("TypeofTransaction"));
            double amount = Double.parseDouble(cursor.getString(cursor.getColumnIndexOrThrow("Amount")));
            String date = cursor.getString(cursor.getColumnIndexOrThrow("DateTime"));

            dataList.add(new Trans(type, amount, date));
        }

        adapter = new TransHistAdapter(dataList);
        recyclerView.setAdapter(adapter);
    }
}
