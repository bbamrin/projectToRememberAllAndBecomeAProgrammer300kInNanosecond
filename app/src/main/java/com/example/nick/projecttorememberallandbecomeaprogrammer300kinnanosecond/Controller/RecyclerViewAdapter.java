package com.example.nick.projecttorememberallandbecomeaprogrammer300kinnanosecond.Controller;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
    public RecyclerViewAdapter(String what, ArrayList<ChannelTemplate> list, MainActivity activity){
        whatIsHappening = what;
        listOfChannels = list;
        this.mainActivity = activity;
        selectedItems = new SparseBooleanArray();
    }


    public RecyclerViewAdapter(String what, ArrayList<NewsTemplate> list,String channel, MainActivity activity){
        this.mainActivity = activity;
        listOfNews = list;
        mchannel = channel;
        whatIsHappening = what;
        selectedItems = new SparseBooleanArray();
    }

    public boolean isSelected(int position){
        return getSelectedItems().contains(position);
    }

    public void toggleSelection(int position){
        if(selectedItems.get(position,false)){
            selectedItems.delete(position);
        } else{
            selectedItems.put(position,true);
        }
        notifyItemChanged(position);
    }

    public void clearSelection(){
        List<Integer> selection = getSelectedItems();
        selectedItems.clear();
        for(Integer i:selection){
            notifyItemChanged(i);
        }
    }

    public int getSelectedItemCount() {
        return selectedItems.size();
    }


    public List<Integer> getSelectedItems() {
        List<Integer> items = new ArrayList<>(selectedItems.size());
        for (int i = 0; i < selectedItems.size(); ++i) {
            items.add(selectedItems.keyAt(i));
        }
        return items;
    }





    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;


        public  ViewHolder(final View itemView){
            super(itemView);
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
        System.out.println("ya tut");
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
        System.out.println("ya tut");
        if(whatIsHappening == ConstantsAndStaticVars.NEWS){
            NewsTemplate news = listOfNews.get(position);
            TextView tv = viewHolder.textView;
            tv.setText(news.getNewsEntry());

            //нужно добавить обработку картинки в новости (newsPictureAdress)
        } else if (whatIsHappening == ConstantsAndStaticVars.CHANNELS){
            ChannelTemplate channel = listOfChannels.get(position);
            TextView tv = viewHolder.textView;
            tv.setText(channel.getChannelName());
            System.out.println("ya tut");
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
