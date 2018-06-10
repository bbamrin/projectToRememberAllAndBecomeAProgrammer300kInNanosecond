package com.example.nick.projecttorememberallandbecomeaprogrammer300kinnanosecond.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.nick.projecttorememberallandbecomeaprogrammer300kinnanosecond.Controller.RecyclerViewAdapter;
import com.example.nick.projecttorememberallandbecomeaprogrammer300kinnanosecond.Model.ChannelTemplate;
import com.example.nick.projecttorememberallandbecomeaprogrammer300kinnanosecond.Model.Constants;
import com.example.nick.projecttorememberallandbecomeaprogrammer300kinnanosecond.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ArrayList<ChannelTemplate> list = new ArrayList<ChannelTemplate>();
        ChannelTemplate channel = new ChannelTemplate("asdf","asdf");
        list.add(channel);
        list.add(channel);
        list.add(channel);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(Constants.CHANNELS,list);
        adapter.setOnClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                String channel = list.get(position).getChannelName();
                System.out.println(channel + " " + position);
            }
        });
        RecyclerView rv = (RecyclerView)findViewById(R.id.rvNews);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));



    }
}
