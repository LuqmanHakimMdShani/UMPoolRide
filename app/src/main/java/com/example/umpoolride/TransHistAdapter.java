package com.example.umpoolride;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TransHistAdapter extends RecyclerView.Adapter<TransHistAdapter.TransHistViewHolder> {

    private List<Trans> dataList;

    public TransHistAdapter(List<Trans> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public TransHistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.trans_history_item, parent, false);
        return new TransHistViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TransHistViewHolder holder, int position) {
        Trans transaction = dataList.get(position);
        holder.rowType.setText(transaction.getType());
        holder.rowAmount.setText(String.format("+ RM %.2f   ", transaction.getAmount()));
        holder.rowDate.setText(transaction.getDate());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class TransHistViewHolder extends RecyclerView.ViewHolder {
        public TextView rowType;
        public TextView rowAmount;
        public TextView rowDate;

        public TransHistViewHolder(View view) {
            super(view);
            rowType = view.findViewById(R.id.row_type);
            rowAmount = view.findViewById(R.id.row_amount);
            rowDate = view.findViewById(R.id.row_date);
        }
    }
}
