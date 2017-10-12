package com.example.root.myapplication;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class EventActivity extends AppCompatActivity{

    Button buttom_share;
    Button buttom_letsgo;
    String[] title = {"1", "2", "3", "4", "5", "6", "7", "8", "9","1", "2", "3", "4", "5", "6", "7", "8", "9","1", "2", "3", "4", "5", "6", "7", "8", "9","1", "2", "3", "4", "5", "6", "7", "8", "9"};
    String spinner_item;

    SpinnerAdapter adapter;
    boolean registrate = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        buttom_share = (Button) findViewById(R.id.Share);
        adapter=new SpinnerAdapter(getApplicationContext());
        buttom_letsgo = (Button) findViewById(R.id.Reg);
        buttom_share.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (registrate) {//Если вы зарегистрированы, то вы можете приглашать
                    // TODO Auto-generated method stub
                    final Dialog dialog = new Dialog(EventActivity.this);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.row_spinner);
                    dialog.setCancelable(true);

                    // set the custom dialog components - text, image and button
                    final Spinner spinner = (Spinner) dialog.findViewById(R.id.spinner);
                    final EditText edittext = (EditText) dialog.findViewById(R.id.message);
                    Button buttonshare = (Button) dialog.findViewById(R.id.share);
                    Button buttoncancle = (Button) dialog.findViewById(R.id.cancel);
                    spinner.setAdapter(adapter);
                    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            // TODO Auto-generated method stub
                            spinner_item = title[position];
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                            // TODO Auto-generated method stub

                        }
                    });

                    buttonshare.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            // TODO Auto-generated method stub
                            dialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Вы пригласили пользователя " + spinner_item, Toast.LENGTH_LONG).show();
                        }
                    });
                    buttoncancle.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            // TODO Auto-generated method stub
                            dialog.dismiss();
                        }
                    });

                    dialog.show();
                }else{//Иначе идите на хуй
                    Toast.makeText(getApplicationContext(), "Сперва зарегистрируйтесь на мероприятие", Toast.LENGTH_LONG).show();
                }
            }
        });

        buttom_letsgo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(registrate){
                    Toast.makeText(getApplicationContext(), "Вы уже зарегистрированы", Toast.LENGTH_LONG).show();
                    //либо убераем регистрацию(удаляем запись из базы)
                }
                else{
                    Toast.makeText(getApplicationContext(), "Теперь вы зарегистрированы", Toast.LENGTH_LONG).show();
                    registrate = true;//делаем флаг положи тельным(пометку в базе)
                }
            }
        });

    }

    public class SpinnerAdapter extends BaseAdapter {
        Context context;
        private LayoutInflater mInflater;

        public SpinnerAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return title.length;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final ListContent holder;
            View v = convertView;
            if (v == null) {
                mInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
                v = mInflater.inflate(R.layout.row_textview, null);
                holder = new ListContent();
                holder.text = (TextView) v.findViewById(R.id.textView1);

                v.setTag(holder);
            } else {
                holder = (ListContent) v.getTag();
            }

            holder.text.setText(title[position]);

            return v;
        }
    }

    static class ListContent {
        TextView text;
    }
}
