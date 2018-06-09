package com.example.nick.projecttorememberallandbecomeaprogrammer300kinnanosecond.Controller;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nick.projecttorememberallandbecomeaprogrammer300kinnanosecond.Model.newsTemplate;
import com.example.nick.projecttorememberallandbecomeaprogrammer300kinnanosecond.R;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>  {

    private ArrayList<newsTemplate> listOfNews;


    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;
        Button button;


        public  ViewHolder(View itemView){
            super(itemView);
            imageView = (ImageView)itemView.findViewById(R.id.newsPicture);
            textView = (TextView)itemView.findViewById(R.id.newsTextView);
            button = (Button)itemView.findViewById(R.id.newsButton);
        }

    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        Context ctx = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.news_item,parent,false);
        return  new ViewHolder(view);

    }


    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position){
        newsTemplate news = listOfNews.get(position);
        TextView tv = viewHolder.textView;
        tv.setText(news.getNewsEntry());
        Button btn = viewHolder.button;
        btn.setText("read full article");
        //нужно добавить обработку картинки в новаости (newsPictureAdress)


    }

}
