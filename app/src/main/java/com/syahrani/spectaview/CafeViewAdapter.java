package com.syahrani.spectaview;


import android.widget.AdapterView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class CafeViewAdapter extends RecyclerView.Adapter<CafeViewAdapter.ViewHolder> {
    private OnItemClickCallback onItemClickCallback;
    private List<DataItem> data = new ArrayList<>();
    private OnItemLongClickListener onItemLongClickListener;

    public static void setData(List<DataItem> data){
        this.data = data;
        notifyDataSetChanged();
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener){
        this.onItemLongClickListener = onItemLongClickListener;
    }

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    @NonNull
    @Override
    public CafeViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }


    @Override
    public void onBindViewHolder(@NonNull CafeViewAdapter.ViewHolder holder, int position) {
        int pos = holder.getAdapterPosition();
        DataItem item = data.get(pos);

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
    public interface OnItemLongClickListener {
        void onItemLongClick(View v, DataItem item, int position);
    }

    public interface OnItemClickCallback {
        void onItemClicked(DataItem item);
    }
}
