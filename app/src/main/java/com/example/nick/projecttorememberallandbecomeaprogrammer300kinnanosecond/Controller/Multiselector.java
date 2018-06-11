package com.example.nick.projecttorememberallandbecomeaprogrammer300kinnanosecond.Controller;

import com.example.nick.projecttorememberallandbecomeaprogrammer300kinnanosecond.Model.ConstantsAndStaticVars;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Multiselector {
    private Map<Integer,Integer> mSelectedItems;

    public Multiselector(int sizeOfDataSet){
        mSelectedItems = new HashMap<>();
        for(int i = 0; i < sizeOfDataSet;++i){
            mSelectedItems.put(i,ConstantsAndStaticVars.NOT_USED);
        }
    }

    public void toggleItemSelection(int position){
        if (mSelectedItems.get(position) == ConstantsAndStaticVars.NOT_USED || mSelectedItems.get(position) == ConstantsAndStaticVars.UNSELECTED  ){
            mSelectedItems.put(position,ConstantsAndStaticVars.SELECTED);
        } else{
            mSelectedItems.put(position,ConstantsAndStaticVars.UNSELECTED);
        }
    }

    public ArrayList<Integer> getSelectedItemsPositions(){
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 0; i < mSelectedItems.size();++i){
            if(mSelectedItems.get(i) == ConstantsAndStaticVars.SELECTED){
                list.add(i);
            }
        }
        return list;
    }
    public int getItemState(int position){
        return mSelectedItems.get(position);
    }
    public void unselectAllItems(){
        for(int i = 0; i < mSelectedItems.size();++i){
            mSelectedItems.put(i,ConstantsAndStaticVars.NOT_USED);
        }
    }
    public void unselectItem(int position){
        mSelectedItems.put(position,ConstantsAndStaticVars.NOT_USED);
    }

}
