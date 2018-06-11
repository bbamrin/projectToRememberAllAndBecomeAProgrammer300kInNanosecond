package com.example.nick.projecttorememberallandbecomeaprogrammer300kinnanosecond.View;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


import com.example.nick.projecttorememberallandbecomeaprogrammer300kinnanosecond.Controller.Multiselector;
import com.example.nick.projecttorememberallandbecomeaprogrammer300kinnanosecond.Controller.RecyclerViewAdapter;
import com.example.nick.projecttorememberallandbecomeaprogrammer300kinnanosecond.Model.ChannelTemplate;
import com.example.nick.projecttorememberallandbecomeaprogrammer300kinnanosecond.Model.ConstantsAndStaticVars;
import com.example.nick.projecttorememberallandbecomeaprogrammer300kinnanosecond.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnLongClickListener {

    Toolbar toolbar;
    boolean firsTime = true;
    ActionBar actionBar;
    TextView counterTextView;
    RecyclerView rv;
    RecyclerViewAdapter adapter;
    Multiselector multiselector;
    final ArrayList<ChannelTemplate> list = new ArrayList<ChannelTemplate>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        counterTextView = (TextView)findViewById(R.id.countetText);
        counterTextView.setVisibility(View.GONE);
        toolbar = (Toolbar)findViewById(R.id.newsToolBar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        ChannelTemplate channel = new ChannelTemplate("asdf","asdf");
        list.add(channel);
        list.add(channel);
        list.add(channel);
        multiselector = new Multiselector(list.size());
        adapter = new RecyclerViewAdapter(ConstantsAndStaticVars.CHANNELS,list,this,multiselector);
        adapter.setOnClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                if(!ConstantsAndStaticVars.IS_IN_ACTION_MODE){
                    String channel = list.get(position).getChannelName();
                    System.out.println(channel + " " + position);
                } else{
                    if(!firsTime){
                        if (multiselector.getItemState(position) == ConstantsAndStaticVars.NOT_USED ||multiselector.getItemState(position) == ConstantsAndStaticVars.UNSELECTED ){
                            itemView.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                        } else{
                            itemView.setBackgroundColor(getResources().getColor(R.color.white));
                        }

                        multiselector.toggleItemSelection(position);
                        int count = multiselector.getSelectedItemsPositions().size();

                        counterTextView.setText(Integer.toString(count) + " items selected");
                    } else {
                        multiselector.toggleItemSelection(position);
                        int count = multiselector.getSelectedItemsPositions().size();

                        counterTextView.setText(Integer.toString(count) + " items selected");
                    }

                }

            }
        });
        rv = (RecyclerView)findViewById(R.id.rvNews);
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
        actionBar.setDisplayHomeAsUpEnabled(true);
        if(firsTime){
            v.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
            firsTime = false;
        }
        return false;
    }

    @Override
    public void onBackPressed(){
        if(ConstantsAndStaticVars.IS_IN_ACTION_MODE){
            clearActionMode();
        } else{
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == R.id.item_delete){
            clearActionMode();
        } else if (item.getItemId() == android.R.id.home){
            clearActionMode();
        }

        return super.onOptionsItemSelected(item);
    }


    public void clearActionMode(){
        counterTextView.setVisibility(View.GONE);
        toolbar.getMenu().clear();
        toolbar.inflateMenu(R.menu.menu_activity_main);
        ConstantsAndStaticVars.IS_IN_ACTION_MODE = false;
        multiselector.unselectAllItems();
        counterTextView.setText("1 items selected");
        actionBar.setDisplayHomeAsUpEnabled(false);
        firsTime = true;
        adapter.notifyDataSetChanged();
    }


}
