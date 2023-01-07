package com.example.umpoolride;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder> {

    private List<Noti> dataList;

    public NotificationAdapter(List<Noti> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.notify_item, parent, false);
        return new NotificationViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {
        Noti notification = dataList.get(position);
        holder.rowNotiTitle.setText(notification.getNotiTitle());
        holder.rowNotiDesc.setText(notification.getNotiDesc());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class NotificationViewHolder extends RecyclerView.ViewHolder {
        public TextView rowNotiTitle;
        public TextView rowNotiDesc;

        public NotificationViewHolder(View view) {
            super(view);
            rowNotiTitle = view.findViewById(R.id.row_title);
            rowNotiDesc = view.findViewById(R.id.row_description);
        }
    }

}
