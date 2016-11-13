package com.target23.ku.classcount;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class TargetFragmentActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;

    public static void start(Context context, int pos) {
        Intent intent = new Intent(context, TargetFragmentActivity.class);
        intent.putExtra("pos", pos);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target_fragment);
        mRecyclerView = (RecyclerView) findViewById(R.id.content_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new SimpleAAAdapter(20));
        final int pos = getIntent().getIntExtra("pos", 0);
        final View view = findViewById(R.id.outer_layout);
        if (view != null) {
            view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override public void onGlobalLayout() {
                    view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    runOnUiThread(new Runnable() {
                        @Override public void run() {
                            ((LinearLayoutManager) mRecyclerView.getLayoutManager()).scrollToPositionWithOffset(pos, getContentHeight());
                        }
                    });
                }
            });
        }
    }

    public int getContentHeight() {
        View view = findViewById(R.id.outer_layout);
        int height = view.getHeight();
        int result = height / 2;
        Log.d("TargetFragmentActivity", "half of screen:" + result);
        result -= 120;
        return result;
    }

    static class SimpleAAAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        List<Object> mObjectList = new ArrayList<>();

        public SimpleAAAdapter(int size) {
            for (int i = 0; i < size; i++) {
                mObjectList.add(new Object());
            }
        }

        @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_ry_layout, parent, false));
        }

        @Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ((ViewHolder) holder).tv.setText("Content:" + position);
        }

        @Override public int getItemCount() {
            return mObjectList.size();
        }

        static class ViewHolder extends RecyclerView.ViewHolder {
            TextView tv;

            public ViewHolder(View itemview) {
                super(itemview);
                tv = (TextView) itemview.findViewById(R.id.text_text);
            }
        }
    }
}
