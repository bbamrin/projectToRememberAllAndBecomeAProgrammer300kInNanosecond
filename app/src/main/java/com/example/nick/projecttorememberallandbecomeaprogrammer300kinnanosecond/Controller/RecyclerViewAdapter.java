package com.example.nick.projecttorememberallandbecomeaprogrammer300kinnanosecond.Controller;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nick.projecttorememberallandbecomeaprogrammer300kinnanosecond.Model.ChannelTemplate;
import com.example.nick.projecttorememberallandbecomeaprogrammer300kinnanosecond.Model.Constants;
import com.example.nick.projecttorememberallandbecomeaprogrammer300kinnanosecond.Model.NewsTemplate;
import com.example.nick.projecttorememberallandbecomeaprogrammer300kinnanosecond.R;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>  {

    private ArrayList<NewsTemplate> listOfNews;
    private ArrayList<ChannelTemplate> listOfChannels;
    private String whatIsHappening  = Constants.NOTHING;
    private String mchannel = Constants.NOTHING;
    public RecyclerViewAdapter(String what, ArrayList<ChannelTemplate> list){
        whatIsHappening = what;
        listOfChannels = list;
    }


    public RecyclerViewAdapter(String what, ArrayList<NewsTemplate> list,String channel){
        listOfNews = list;
        mchannel = channel;
        whatIsHappening = what;
    }



    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;


        public  ViewHolder(View itemView){
            super(itemView);
            if(whatIsHappening == Constants.CHANNELS){
                textView = (TextView)itemView.findViewById(R.id.channelTextView);

            } else if(whatIsHappening == Constants.NEWS){
                imageView = (ImageView)itemView.findViewById(R.id.newsPicture);
                textView = (TextView)itemView.findViewById(R.id.newsTextView);

            }

        }

    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        System.out.println("ya tut");
        Context ctx = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.stub,parent,false);
        if (whatIsHappening == Constants.NEWS){
            view = inflater.inflate(R.layout.news_item,parent,false);
        } else if(whatIsHappening == Constants.CHANNELS){
            view = inflater.inflate(R.layout.channel_item,parent,false);
        }

        return  new ViewHolder(view);

    }


    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position){
        System.out.println("ya tut");
        if(whatIsHappening == Constants.NEWS){
            NewsTemplate news = listOfNews.get(position);
            TextView tv = viewHolder.textView;
            tv.setText(news.getNewsEntry());

            //нужно добавить обработку картинки в новости (newsPictureAdress)
        } else if (whatIsHappening == Constants.CHANNELS){
            ChannelTemplate channel = listOfChannels.get(position);
            TextView tv = viewHolder.textView;
            tv.setText(channel.getChannelName());
            System.out.println("ya tut");
        }



    }


    @Override
    public int getItemCount(){
        if (whatIsHappening == Constants.CHANNELS){
            return listOfChannels.size();
        } else if(whatIsHappening == Constants.NEWS){
            return listOfNews.size();
        }
        return 1;
    }

}
