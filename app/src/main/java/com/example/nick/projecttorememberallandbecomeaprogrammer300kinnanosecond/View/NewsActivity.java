package com.example.nick.projecttorememberallandbecomeaprogrammer300kinnanosecond.View;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
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
import com.example.nick.projecttorememberallandbecomeaprogrammer300kinnanosecond.Model.NewsTemplate;
import com.example.nick.projecttorememberallandbecomeaprogrammer300kinnanosecond.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class NewsActivity extends AppCompatActivity implements View.OnLongClickListener {


    Toolbar toolbar;
    boolean firsTime = true;
    ActionBar actionBar;
    TextView counterTextView;
    RecyclerView rv;
    int counter = 0;
    DatabaseHelper mDbHelper;
    SQLiteDatabase db;
    DatabaseController mDbController;
    RecyclerViewAdapter adapter;
    Multiselector multiselector;
    Button addNewsButton;
    ArrayList<NewsTemplate> newsList;
    String newsChannel;
    Set<View> selectedViews;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_activity);
        newsChannel = getIntent().getStringExtra(ConstantsAndStaticVars.WHAT_CHANNEL);
        selectedViews = new HashSet<>();
        counterTextView = (TextView)findViewById(R.id.countetText);
        counterTextView.setVisibility(View.GONE);
        toolbar = (Toolbar)findViewById(R.id.newsToolBar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        addNewsButton = (Button)findViewById(R.id.addNewsButton);
        rv = (RecyclerView)findViewById(R.id.rvNewsSecond);


        mDbHelper= new DatabaseHelper(this);
        db = mDbHelper.getWritableDatabase();
        mDbController = new DatabaseController(db);
        newsList = new ArrayList<NewsTemplate>();
        newsList.addAll(mDbController.getNews(newsChannel));
        multiselector = new Multiselector(newsList.size());


        adapter = new RecyclerViewAdapter(ConstantsAndStaticVars.NEWS, newsList,newsChannel, this,multiselector);
        adapter.setOnClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                selectedViews.add(itemView);
                if(!ConstantsAndStaticVars.IS_IN_ACTION_MODE){
                   // Intent intent = new Intent(NewsActivity.this,NewsActivity.class);
                    //intent.putExtra("whatChannel", newsList.get(position).getChannelName());
                    ///startActivity(intent);
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

        addNewsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDbController.addNews(new NewsTemplate("null","news about programming" + counter,newsChannel));
                changeNewsList(newsList,mDbController);
                ++counter;
                adapter.notifyDataSetChanged();
                multiselector = new Multiselector(newsList.size());
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
        selectedViews.add(v);
        toolbar.getMenu().clear();
        toolbar.inflateMenu(R.menu.menu_action_mode);
        counterTextView.setVisibility(View.VISIBLE);
        ConstantsAndStaticVars.IS_IN_ACTION_MODE = true;
        actionBar.setDisplayHomeAsUpEnabled(true);
        if(firsTime){
            v.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
            firsTime = false;
        }
        addNewsButton.setEnabled(false);
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

                mDbController.deleteNews(newsList.get(i));
            }
            newsList.clear();
            changeNewsList(newsList,mDbController);
            multiselector = new Multiselector(newsList.size());

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
        for (View v: selectedViews){
            v.setBackgroundColor(getResources().getColor(R.color.white));
        }
        addNewsButton.setEnabled(true);
    }


    public void changeNewsList(ArrayList list, DatabaseController mDbController){
        list.clear();
        list.addAll(mDbController.getNews(newsChannel));
    }
}
