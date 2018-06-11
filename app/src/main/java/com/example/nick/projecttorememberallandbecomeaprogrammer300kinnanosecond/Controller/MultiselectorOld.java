package com.example.nick.projecttorememberallandbecomeaprogrammer300kinnanosecond.Controller;

import android.util.SparseBooleanArray;

import java.util.ArrayList;

public class MultiselectorOld {
    private SparseBooleanArray mSelectedPositions = new SparseBooleanArray();
    private boolean mIsSelectable = false;

    private void setItemChecked(int position, boolean isChecked) {
        mSelectedPositions.put(position, isChecked);
    }

    public void toogleItemSelection(int position){
        if (mSelectedPositions.get(position)) {
            mSelectedPositions.put(position,false);
        } else{
            mSelectedPositions.put(position,true);
        }
    }
    public ArrayList<Integer> getSelectedItemsPositions(){
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 0; i < mSelectedPositions.size();++i){
            if(mSelectedPositions.get(i)){
                list.add(i);
            }
        }
        return list;
    }

    public boolean isItemChecked(int position) {
        return mSelectedPositions.get(position);
    }

    public void setSelectable(boolean selectable) {
        mIsSelectable = selectable;
    }

    public boolean isSelectable() {
        return mIsSelectable;
    }
}
