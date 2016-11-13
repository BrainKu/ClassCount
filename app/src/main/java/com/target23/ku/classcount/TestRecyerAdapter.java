package com.target23.ku.classcount;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by zhengxinwei@N3072 on 2016/9/22.
 */
public class TestRecyerAdapter extends RecyclerView.Adapter<TestRecyerAdapter.TestHolder> {

    private Context mContext;
    private List<Object> mObjects;

    public TestRecyerAdapter(List<Object> items) {
        mObjects = items;
    }

    @Override
    public TestHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType) {
        if (mContext == null) mContext = parent.getContext();
        View v = LayoutInflater.from(mContext)
                .inflate(R.layout.layout_margin_gone, parent, false);
        return new TestHolder(v);
    }

    @Override
    public void onBindViewHolder(TestHolder holder, int position) {
        final Object item = mObjects.get(position);
        ViewGroup group = ((ViewGroup) holder.itemView);
        for (int i = 0; i < group.getChildCount(); i++) {
            group.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    v.setVisibility(View.GONE);
                }
            });
        }
    }

    static class TestHolder extends RecyclerView.ViewHolder {

        public TestHolder(View itemView) {
            super(itemView);
        }
    }

    @Override
    public int getItemCount() {
        return mObjects == null ? 0 : mObjects.size();
    }
}