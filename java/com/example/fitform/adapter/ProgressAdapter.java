package com.example.fitform.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.fitform.R;
import java.util.List;

public class ProgressAdapter extends RecyclerView.Adapter<ProgressAdapter.ProgressViewHolder> {

    private List<String> progressList;

    public ProgressAdapter(List<String> progressList) {
        this.progressList = progressList;
    }

    @NonNull
    @Override
    public ProgressViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_progress, parent, false);
        return new ProgressViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProgressViewHolder holder, int position) {
        String progress = progressList.get(position);
        holder.tvProgressDetail.setText(progress);
        holder.itemView.setContentDescription(progress);
    }

    @Override
    public int getItemCount() {
        return progressList.size();
    }

    static class ProgressViewHolder extends RecyclerView.ViewHolder {
        TextView tvProgressDetail;

        public ProgressViewHolder(@NonNull View itemView) {
            super(itemView);
            tvProgressDetail = itemView.findViewById(R.id.tvProgressDetail);
        }
    }
}
