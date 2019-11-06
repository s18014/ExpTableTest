package com.example.exptabletest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ExpTableAdapter extends RecyclerView.Adapter<ExpTableAdapter.ViewHolder> {
    List<String> strings;

    ExpTableAdapter(List<String> strings) {
        this.strings = strings;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView text1;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            text1 = itemView.findViewById(android.R.id.text1);
        }
    }

    @NonNull
    @Override
    public ExpTableAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpTableAdapter.ViewHolder holder, int position) {
        holder.text1.setText(strings.get(position));
    }

    @Override
    public int getItemCount() {
        return strings.size();
    }

}
