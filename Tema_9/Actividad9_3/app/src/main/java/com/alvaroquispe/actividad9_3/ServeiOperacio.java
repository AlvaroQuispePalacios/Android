package com.alvaroquispe.actividad9_3;

import android.app.IntentService;
import android.content.Intent;
import android.os.SystemClock;

import androidx.annotation.Nullable;

public class ServeiOperacio extends IntentService {

    public ServeiOperacio (){
        super("ServeiOperacio");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        double n = intent.getExtras().getDouble("numero");
        SystemClock.sleep(5000);
        MainActivity.sortida.append(n * n + "\n");
    }
}
