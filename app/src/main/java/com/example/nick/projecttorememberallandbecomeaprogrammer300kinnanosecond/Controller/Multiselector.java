package com.example.nick.projecttorememberallandbecomeaprogrammer300kinnanosecond.Controller;

import android.util.SparseBooleanArray;

public class Multiselector {
    private SparseBooleanArray mSelectedPositions = new SparseBooleanArray();
    private boolean mIsSelectable = false;

    private void setItemChecked(int position, boolean isChecked) {
        mSelectedPositions.put(position, isChecked);
    }

    private boolean isItemChecked(int position) {
        return mSelectedPositions.get(position);
    }

    private void setSelectable(boolean selectable) {
        mIsSelectable = selectable;
    }

    private boolean isSelectable() {
        return mIsSelectable;
    }
}
