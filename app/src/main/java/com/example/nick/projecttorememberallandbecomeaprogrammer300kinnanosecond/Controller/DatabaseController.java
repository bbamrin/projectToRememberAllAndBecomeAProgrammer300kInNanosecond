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
        ContentValues values = new ContentValues();
        values.put(ContractClass.NewsDataBase.NEWS_CHANNEL,newsTemplate.getNewsChannel());
        values.put(ContractClass.NewsDataBase.NEWS_ENTRY,newsTemplate.getNewsEntry());
        values.put(ContractClass.NewsDataBase.NEWS_PICTURE,newsTemplate.getNewsPictureAdress());
        long id = db.insert(ContractClass.NewsDataBase.TABLE_NAME,null,values);
        newsTemplate.setNewsID(Long.toString(id));


    }


    public ArrayList<NewsTemplate> getNews(String channel){
        ArrayList<NewsTemplate> news = new ArrayList<>();

        String projection[] = {
            ContractClass.NewsDataBase.NEWS_CHANNEL,
                    ContractClass.NewsDataBase.NEWS_ENTRY,
                    ContractClass.NewsDataBase.NEWS_PICTURE,
                    ContractClass.NewsDataBase._ID
        };
        String selection = ContractClass.NewsDataBase.NEWS_CHANNEL + " = ?";
        String[] selectionArgs = {channel};

        Cursor cursor = db.query(
                ContractClass.NewsDataBase.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        while (cursor.moveToNext()){
            long id = cursor.getLong(
                    cursor.getColumnIndexOrThrow(ContractClass.NewsDataBase._ID)
            );
            String entry = cursor.getString(
                    cursor.getColumnIndexOrThrow(ContractClass.NewsDataBase.NEWS_ENTRY)
            );
            String mChannel = cursor.getString(
                    cursor.getColumnIndexOrThrow(ContractClass.NewsDataBase.NEWS_CHANNEL)
            );
            String picture = cursor.getString(
                    cursor.getColumnIndexOrThrow(ContractClass.NewsDataBase.NEWS_PICTURE)
            );
            NewsTemplate mNews = new NewsTemplate(Long.toString(id), picture,entry,mChannel);
            if (!mNews.getNewsEntry().equals(ConstantsAndStaticVars.NOT_A_NEWS)){
                news.add(mNews);
            }

        }
        return news;
    }


    public void deleteAllChannels(){

    }


    public void deleteChannel(ChannelTemplate channel){
        String selection = ContractClass.NewsDataBase.NEWS_CHANNEL + " LIKE ?";
        String[] selectionArgs = {channel.getChannelName()};
        int deletedRows = db.delete(ContractClass.NewsDataBase.TABLE_NAME,selection,selectionArgs);

    }

    public void deleteNews(NewsTemplate news){
        String selection = ContractClass.NewsDataBase._ID + " LIKE ?";
        String[] selectionArgs = {news.getNewsID()};
        int deletedRows = db.delete(ContractClass.NewsDataBase.TABLE_NAME,selection,selectionArgs);
    }

    public ArrayList<ChannelTemplate> getChannelsList(){
        String[] projection = {
                ContractClass.NewsDataBase.NEWS_CHANNEL,
                ContractClass.NewsDataBase.NEWS_ENTRY
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
            String news = cursor.getString(cursor.getColumnIndexOrThrow(ContractClass.NewsDataBase.NEWS_ENTRY));
            ChannelTemplate channel =  new ChannelTemplate(channelName, ConstantsAndStaticVars.PROCEED_TO_THIS_CHANNEL);
            if (news.equals(ConstantsAndStaticVars.NOT_A_NEWS) ){
                channels.add(channel);
            }

        }
        return channels;

    }


}
