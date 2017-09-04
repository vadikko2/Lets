package com.example.root.myapplication;

import android.app.Application;
import android.content.Intent;
import android.widget.Toast;

import com.vk.sdk.VKSdk;

/**
 * Created by root on 04.09.17.
 */

public class MainApplication extends Application {
    @Override
    public void onCreate(){
        super.onCreate();
        VKSdk.initialize(getApplicationContext());
        Toast.makeText(getApplicationContext(),
                "FFFFF", Toast.LENGTH_LONG).show();
        Intent in = new Intent(this, MainActivity.class);
        in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(in);
    }
}
