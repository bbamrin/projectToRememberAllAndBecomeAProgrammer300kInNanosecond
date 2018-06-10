package com.example.nick.projecttorememberallandbecomeaprogrammer300kinnanosecond.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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
        ArrayList<ChannelTemplate> list = new ArrayList<ChannelTemplate>();
        ChannelTemplate channel = new ChannelTemplate("asdf","asdf");
        list.add(channel);
        list.add(channel);
        list.add(channel);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(Constants.CHANNELS,list);
        RecyclerView rv = (RecyclerView)findViewById(R.id.rvNews);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));



    }
}