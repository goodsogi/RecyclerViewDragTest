package com.example.johney.recyclerviewdragtest;

import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> implements CityNameItemTouchHelperCallback.OnItemMoveListener {

    private final OnStartDragListener mStartDragListener;

    public interface OnStartDragListener {
       void onStartDrag(ViewHolder viewHolder);
   }


    private List<String> mValues = new ArrayList<>();

    public RecyclerViewAdapter(List<String> cityNames, OnStartDragListener startDragListener) {
        mValues = cityNames;
        mStartDragListener = startDragListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        String cityName = mValues.get(position);

        holder.mCityNameView.setText(cityName);

        holder.mDragHandler.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    mStartDragListener.onStartDrag(holder);
                }

                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mValues, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return false;
    }


    static class ViewHolder extends RecyclerView.ViewHolder {


        public final TextView mCityNameView;
        public final View mDragHandler;

        public ViewHolder(View view) {
            super(view);
            mDragHandler = view.findViewById(R.id.drag_handler);

            mCityNameView = view.findViewById(R.id.city_name);

        }

        @Override
        public String toString() {
            return super.toString() + " '" + mCityNameView.getText() + "'";
        }
    }
}
