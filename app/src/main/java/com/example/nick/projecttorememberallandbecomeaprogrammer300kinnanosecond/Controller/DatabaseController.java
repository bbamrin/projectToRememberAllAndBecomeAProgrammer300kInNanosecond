package com.example.nick.projecttorememberallandbecomeaprogrammer300kinnanosecond.Controller;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.nick.projecttorememberallandbecomeaprogrammer300kinnanosecond.Model.ChannelTemplate;
import com.example.nick.projecttorememberallandbecomeaprogrammer300kinnanosecond.Model.ConstantsAndStaticVars;
import com.example.nick.projecttorememberallandbecomeaprogrammer300kinnanosecond.Model.ContractClass;
import com.example.nick.projecttorememberallandbecomeaprogrammer300kinnanosecond.Model.NewsTemplate;

import java.util.ArrayList;
import java.util.List;

public class DatabaseController {
    SQLiteDatabase db;

    public DatabaseController(SQLiteDatabase db){
        this.db = db;

    }


    public long addChannel(ChannelTemplate channelTemplate ){
        ContentValues values = new ContentValues();
        values.put(ContractClass.NewsDataBase.NEWS_CHANNEL, channelTemplate.getChannelName());
        values.put(ContractClass.NewsDataBase.NEWS_ENTRY,ConstantsAndStaticVars.NOT_A_NEWS);
        long newRowId = db.insert(ContractClass.NewsDataBase.TABLE_NAME,null,values);
        return  newRowId;
    }

    public void addNews(NewsTemplate newsTemplate){
        //need to make realization of this method

    }


    public void deleteAllChannels(){

    }


    public void deleteChannel(ChannelTemplate channel){
        String selection = ContractClass.NewsDataBase.NEWS_CHANNEL + " LIKE ?";
        String[] selectionArgs = {channel.getChannelName()};
        int deletedRows = db.delete(ContractClass.NewsDataBase.TABLE_NAME,selection,selectionArgs);

    }

    public void deleteNews(NewsTemplate news){

    }

    public ArrayList<ChannelTemplate> getChannelsList(){
        String[] projection = {
                ContractClass.NewsDataBase.NEWS_CHANNEL
        };
        Cursor cursor = db.query(
                ContractClass.NewsDataBase.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        ArrayList<ChannelTemplate> channels = new ArrayList<ChannelTemplate>();
        while (cursor.moveToNext()){
            String channelName = cursor.getString(
                    cursor.getColumnIndexOrThrow(ContractClass.NewsDataBase.NEWS_CHANNEL));

            ChannelTemplate channel =  new ChannelTemplate(channelName, ConstantsAndStaticVars.PROCEED_TO_THIS_CHANNEL);
            channels.add(channel);
        }
        return channels;

    }

    public ArrayList<NewsTemplate> getNews(String channel){
        return null;
    }

}
