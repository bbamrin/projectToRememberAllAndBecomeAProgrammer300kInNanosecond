package com.example.nick.projecttorememberallandbecomeaprogrammer300kinnanosecond.Controller;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.nick.projecttorememberallandbecomeaprogrammer300kinnanosecond.Model.ChannelTemplate;
import com.example.nick.projecttorememberallandbecomeaprogrammer300kinnanosecond.Model.ConstantsAndStaticVars;
import com.example.nick.projecttorememberallandbecomeaprogrammer300kinnanosecond.Model.NewsTemplate;
import com.example.nick.projecttorememberallandbecomeaprogrammer300kinnanosecond.R;
import com.example.nick.projecttorememberallandbecomeaprogrammer300kinnanosecond.View.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>  {
    private SparseBooleanArray selectedItems;
    private ArrayList<NewsTemplate> listOfNews;
    private MainActivity mainActivity;
    private Multiselector multiselector;
    private ArrayList<ChannelTemplate> listOfChannels;
    private String whatIsHappening  = ConstantsAndStaticVars.NOTHING;
    private String mchannel = ConstantsAndStaticVars.NOTHING;
    OnItemClickListener listener;
    public interface OnItemClickListener{
        void onItemClick(View itemView,int position );
    }

    public void setOnClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
    public RecyclerViewAdapter(String what, ArrayList<ChannelTemplate> list, MainActivity activity, Multiselector multiselector){
        whatIsHappening = what;
        listOfChannels = list;
        this.multiselector = multiselector;
        this.mainActivity = activity;
        selectedItems = new SparseBooleanArray();
    }


    public RecyclerViewAdapter(String what, ArrayList<NewsTemplate> list,String channel, MainActivity activity,Multiselector multiselector){
        this.mainActivity = activity;
        listOfNews = list;
        this.multiselector = multiselector;
        mchannel = channel;
        whatIsHappening = what;
        selectedItems = new SparseBooleanArray();
    }






    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;
        View view;


        public  ViewHolder(final View itemView){
            super(itemView);
            view = itemView;
            itemView.setOnLongClickListener(mainActivity);
            if(whatIsHappening == ConstantsAndStaticVars.CHANNELS){
                textView = (TextView)itemView.findViewById(R.id.channelTextView);

            } else if(whatIsHappening == ConstantsAndStaticVars.NEWS){
                imageView = (ImageView)itemView.findViewById(R.id.newsPicture);
                textView = (TextView)itemView.findViewById(R.id.newsTextView);

            }
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    if(listener !=null){
                        int position = getAdapterPosition();
                        if(position!= RecyclerView.NO_POSITION){
                            listener.onItemClick(itemView,position);
                        }
                    }
                }
            });

        }
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        Context ctx = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.stub,parent,false);
        if (whatIsHappening == ConstantsAndStaticVars.NEWS){
            view = inflater.inflate(R.layout.news_item,parent,false);
        } else if(whatIsHappening == ConstantsAndStaticVars.CHANNELS){
            view = inflater.inflate(R.layout.channel_item,parent,false);
        }

        return  new ViewHolder(view);

    }


    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position){

        if(whatIsHappening == ConstantsAndStaticVars.NEWS){
            NewsTemplate news = listOfNews.get(position);
            TextView tv = viewHolder.textView;
            tv.setText(news.getNewsEntry());

            //нужно добавить обработку картинки в новости (newsPictureAdress)
        } else if (whatIsHappening == ConstantsAndStaticVars.CHANNELS){
            ChannelTemplate channel = listOfChannels.get(position);
            TextView tv = viewHolder.textView;
            tv.setText(channel.getChannelName());
            viewHolder.view.setBackgroundColor((viewHolder.view.getContext()).getResources().getColor(R.color.white));



        }



    }


    @Override
    public int getItemCount(){
        if (whatIsHappening == ConstantsAndStaticVars.CHANNELS){
            return listOfChannels.size();
        } else if(whatIsHappening == ConstantsAndStaticVars.NEWS){
            return listOfNews.size();
        }
        return 1;
    }

}
