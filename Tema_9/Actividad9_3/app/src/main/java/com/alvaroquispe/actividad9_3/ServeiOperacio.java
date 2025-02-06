package com.alvaroquispe.actividad9_3;

import android.app.IntentService;
import android.content.Intent;
import android.os.SystemClock;

import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class ServeiOperacio extends IntentService {

    public ServeiOperacio (){
        super("ServeiOperacio");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        double n = intent.getExtras().getDouble("numero");
        SystemClock.sleep(5000);

        Intent i = new Intent();
        i.setAction(ReceptorOperacio.ACTION_RESP);
        i.addCategory(Intent.CATEGORY_DEFAULT);
        i.putExtra("resultado", n*n);

//        sendBroadcast(i);
        LocalBroadcastManager.getInstance(this).sendBroadcast(i);
    }
}
