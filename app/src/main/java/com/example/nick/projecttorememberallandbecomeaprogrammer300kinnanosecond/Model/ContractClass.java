package com.example.nick.projecttorememberallandbecomeaprogrammer300kinnanosecond.Model;

import android.provider.BaseColumns;

public final  class ContractClass {

    private ContractClass(){

    }



    public static class NewsDataBase implements BaseColumns {

        public static final String TABLE_NAME = "NewsTable";
        public static final String NEWS_PICTURE = "NewsId";
        public static final String NEWS_ENTRY = "NewsEntry";

    }

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + NewsDataBase.TABLE_NAME + " (" +
            NewsDataBase._ID + " INTEGER PRIMARY KEY," +
            NewsDataBase.NEWS_ENTRY + " TEXT," +
            NewsDataBase.NEWS_PICTURE + " TEXT)";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + NewsDataBase.TABLE_NAME;

}
