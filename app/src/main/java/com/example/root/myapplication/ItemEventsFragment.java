package com.example.root.myapplication;

import android.content.Intent;
import android.support.v4.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;;
import android.widget.ImageButton;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by root on 09.10.17.
 */

public class ItemEventsFragment extends ListFragment {// определяем массив типа String
    ImageButton flag;
    String[] creators={"Ander Herera","Diego Costa","Juan Mata","David De Gea","Thibaut Courtouis","Van Persie","Oscar"};
    int[] images={R.drawable.ic_action_event_empty,R.drawable.ic_action_event_empty,R.drawable.ic_action_event_empty,R.drawable.ic_action_event_empty,R.drawable.ic_action_event_empty,R.drawable.ic_action_event_empty,R.drawable.ic_action_event_empty};
    int[] imagesFlag={R.drawable.ic_action_registrate_default,R.drawable.ic_action_registrate_default,R.drawable.ic_action_registrate_default,R.drawable.ic_action_registrate,R.drawable.ic_action_registrate_default,R.drawable.ic_action_registrate_default,R.drawable.ic_action_registrate_default};
    ArrayList<HashMap<String, String>> data=new ArrayList<HashMap<String,String>>();
    SimpleAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // TODO Auto-generated method stub
        //MAP
        HashMap<String, String> map=new HashMap<String, String>();
        //FILL
        for(int i=0;i<creators.length;i++)
        {
            map=new HashMap<String, String>();
            map.put("Creator", creators[i]);
            map.put("Image", Integer.toString(images[i]));
            data.add(map);
        }
        //KEYS IN MAP
        String[] from={"Creator","Image"};
        //IDS OF VIEWS
        int[] to={R.id.Creator,R.id.imageView};
        //ADAPTER
        adapter=new SimpleAdapter(getActivity(), data, R.layout.fragment_events, from, to);
        setListAdapter(adapter);

        return super.onCreateView(inflater, container, savedInstanceState);
    }
    @Override
    public void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> av, View v, int pos,
                                    long id) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(getActivity(), EventActivity.class);
                startActivity(intent);
                Toast.makeText(getActivity(), data.get(pos).get("Player"), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

