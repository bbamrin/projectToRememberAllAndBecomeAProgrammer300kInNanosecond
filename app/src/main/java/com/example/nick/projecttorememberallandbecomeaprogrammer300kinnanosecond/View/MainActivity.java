package com.example.nick.projecttorememberallandbecomeaprogrammer300kinnanosecond.View;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.example.nick.projecttorememberallandbecomeaprogrammer300kinnanosecond.Controller.DatabaseController;
import com.example.nick.projecttorememberallandbecomeaprogrammer300kinnanosecond.Controller.DatabaseHelper;
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
    DatabaseHelper mDbHelper;
    SQLiteDatabase db;
    DatabaseController mDbController;
    RecyclerViewAdapter adapter;
    Multiselector multiselector;
    Button addChannelButton;
    ArrayList<ChannelTemplate> channelsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        counterTextView = (TextView)findViewById(R.id.countetText);
        counterTextView.setVisibility(View.GONE);
        toolbar = (Toolbar)findViewById(R.id.newsToolBar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        addChannelButton = (Button)findViewById(R.id.addChannelButton);
        rv = (RecyclerView)findViewById(R.id.rvNews);


        mDbHelper= new DatabaseHelper(this);
        db = mDbHelper.getWritableDatabase();
        mDbController = new DatabaseController(db);
        ChannelTemplate channel = new ChannelTemplate("asdf","asdf");
        ChannelTemplate channel2 = new ChannelTemplate("asdfg","asdf");


        channelsList = new ArrayList<ChannelTemplate>();
        channelsList = mDbController.getChannelsList();
        multiselector = new Multiselector(channelsList.size());


        adapter = new RecyclerViewAdapter(ConstantsAndStaticVars.CHANNELS, channelsList,this,multiselector);
        adapter.setOnClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                if(!ConstantsAndStaticVars.IS_IN_ACTION_MODE){
                    Intent intent = new Intent(MainActivity.this,NewsActivity.class);
                    intent.putExtra(ConstantsAndStaticVars.WHAT_CHANNEL,channelsList.get(position).getChannelName());
                    startActivity(intent);
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

        addChannelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDbController.addChannel(new ChannelTemplate("asgjb","s"));
                changeChannelsList(channelsList,mDbController);

                adapter.notifyDataSetChanged();
                multiselector = new Multiselector(channelsList.size());
            }
        });

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
        addChannelButton.setEnabled(false);
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
            for(Integer i : multiselector.getSelectedItemsPositions()){
                mDbController.deleteChannel(channelsList.get(i));
            }
            channelsList.clear();
            changeChannelsList(channelsList,mDbController);
            multiselector = new Multiselector(channelsList.size());
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
        addChannelButton.setEnabled(true);
    }


    public void changeChannelsList(ArrayList list,DatabaseController mDbController){
        list.clear();
        list.addAll(mDbController.getChannelsList());
    }


}
