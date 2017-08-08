package com.example.root.myapplication;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity{
    ArrayList arrayList = new ArrayList();
    SwipeRefreshLayout mSwipeRefreshLayout;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.activity_main_swipe_refresh_layout);
        listView = (ListView)findViewById(R.id.activity_main_listview);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
// определяем массив типа String
        Random r = new Random();
        for(int i = 0;i < 50; i++){
            arrayList.add( String.valueOf(r.nextInt()));
        }
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(adapter);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                shuffle();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position, long id) {
                TextView textView = (TextView) itemClicked;
                String strText = textView.getText().toString(); // получаем текст нажатого элемента
                Intent in=new Intent(itemClicked.getContext(), DisplayMessageActivity.class);
                in.putExtra(EXTRA_MESSAGE, strText);
                startActivity(in);
            }
        });
    }

    public void sendMap(View view){
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

    public void shuffle() {
        arrayList = new ArrayList();
        Random r = new Random();
        for (int i = 0; i < 50; i++) {
            arrayList.add(String.valueOf(r.nextInt()));
        }
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings_main:
                Toast.makeText(getApplicationContext(),
                        "You selected Settings", Toast.LENGTH_LONG).show();
                return true;
            case R.id.action_create_party:
                Intent in=new Intent(this, CreatePartyActivity.class);
                startActivity(in);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

