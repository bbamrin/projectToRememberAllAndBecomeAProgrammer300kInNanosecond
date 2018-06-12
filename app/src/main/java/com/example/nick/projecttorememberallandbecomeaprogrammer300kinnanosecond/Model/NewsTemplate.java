package com.example.nick.projecttorememberallandbecomeaprogrammer300kinnanosecond.Model;

public class NewsTemplate {
    private String newsID;
    private String newsPictureAdress;
    private String newsEntry;
    private String newsChannel;

    public NewsTemplate(String newsPictureAdress,String newsEntry, String newsChannel){
        this.newsChannel = newsChannel;
        this.newsEntry = newsEntry;
        this.newsPictureAdress = newsPictureAdress;

    }

    public NewsTemplate(String newsID,String newsPictureAdress,String newsEntry, String newsChannel){
        this.newsChannel = newsChannel;
        this.newsID = newsID;
        this.newsEntry = newsEntry;
        this.newsPictureAdress = newsPictureAdress;

    }

    public String getNewsChannel() {
        return newsChannel;
    }

    public void setNewsChannel(String newsChannel) {
        this.newsChannel = newsChannel;
    }

    public String getNewsID() {
        return newsID;
    }

    public void setNewsID(String newsID) {
        this.newsID = newsID;
    }

    public String getNewsPictureAdress() {
        return newsPictureAdress;
    }

    public void setNewsPictureAdress(String newsPictureAdress) {
        this.newsPictureAdress = newsPictureAdress;
    }

    public String getNewsEntry() {
        return newsEntry;
    }

    public void setNewsEntry(String newsEntry) {
        this.newsEntry = newsEntry;
    }




}
