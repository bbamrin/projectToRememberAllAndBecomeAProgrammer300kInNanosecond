package com.example.nick.projecttorememberallandbecomeaprogrammer300kinnanosecond.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;


import com.example.nick.projecttorememberallandbecomeaprogrammer300kinnanosecond.Controller.RecyclerViewAdapter;
import com.example.nick.projecttorememberallandbecomeaprogrammer300kinnanosecond.Model.ChannelTemplate;
import com.example.nick.projecttorememberallandbecomeaprogrammer300kinnanosecond.Model.ConstantsAndStaticVars;
import com.example.nick.projecttorememberallandbecomeaprogrammer300kinnanosecond.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnLongClickListener {

    Toolbar toolbar;
    TextView counterTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        counterTextView = (TextView)findViewById(R.id.countetText);
        counterTextView.setVisibility(View.GONE);
        toolbar = (Toolbar)findViewById(R.id.newsToolBar);
        setSupportActionBar(toolbar);

        final ArrayList<ChannelTemplate> list = new ArrayList<ChannelTemplate>();
        ChannelTemplate channel = new ChannelTemplate("asdf","asdf");
        list.add(channel);
        list.add(channel);
        list.add(channel);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(ConstantsAndStaticVars.CHANNELS,list,this);
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_activity_main,menu);
        return  true;

    }

    @Override
    public boolean onLongClick(View v) {
        toolbar.getMenu().clear();
        toolbar.inflateMenu(R.menu.menu_action_mode);
        counterTextView.setVisibility(View.VISIBLE);
        ConstantsAndStaticVars.IS_IN_ACTION_MODE = true;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        return false;
    }
}
