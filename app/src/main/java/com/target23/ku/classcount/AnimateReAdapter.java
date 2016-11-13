package com.target23.ku.classcount;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

/**
 * Created by zhengxinwei@N3072 on 2016/10/24.
 */
public class AnimateReAdapter extends RecyclerView.Adapter<AnimateReAdapter.ARViewHolder> {

    private Context mContext;
    private List<Object> mObjects;

    public AnimateReAdapter(List<Object> items) {
        mObjects = items;
    }

    @Override
    public ARViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        if (mContext == null) mContext = parent.getContext();
        View v = LayoutInflater.from(mContext)
                .inflate(R.layout.layout_fifthdp_height, parent, false);
        return new ARViewHolder(v);
    }

    final int[] colors = {Color.parseColor("#FF0000"), Color.parseColor("#00FF00"), Color.parseColor("#0000FF"),
            Color.parseColor("#00FFFF"), Color.parseColor("#FFFF00"), Color.parseColor("#FF00FF")};

    @Override
    public void onBindViewHolder(ARViewHolder holder, int position) {
        final Object item = mObjects.get(position);
        holder.itemView.setBackgroundColor(colors[position % colors.length]);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Toast.makeText(v.getContext(), "hahahahaa", Toast.LENGTH_SHORT).show();
            }
        });
    }

    static class ARViewHolder extends RecyclerView.ViewHolder {

        public ARViewHolder(View itemView) {
            super(itemView);
        }
    }

    @Override
    public int getItemCount() {
        return mObjects == null ? 0 : mObjects.size();
    }
}