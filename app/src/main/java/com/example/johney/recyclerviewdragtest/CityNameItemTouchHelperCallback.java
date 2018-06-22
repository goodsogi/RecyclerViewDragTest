package com.example.johney.recyclerviewdragtest;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

public class CityNameItemTouchHelperCallback extends ItemTouchHelper.Callback {

    private final OnItemMoveListener mItemMoveListener;


    public interface OnItemMoveListener {
        boolean onItemMove(int fromPosition, int toPosition);
    }


    public CityNameItemTouchHelperCallback(OnItemMoveListener listener) {
        mItemMoveListener = listener;
    }


    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {

        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
        //swipe 액션이 되지 않게 하고 싶은 경우 swipeFlags 대신 0을 넘김
        return makeMovementFlags(dragFlags, swipeFlags);

    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {

        mItemMoveListener.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

    }
}
