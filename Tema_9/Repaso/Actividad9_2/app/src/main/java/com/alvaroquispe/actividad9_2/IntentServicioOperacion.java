package com.alvaroquispe.actividad9_2;

import android.app.IntentService;
import android.content.Intent;
import android.os.SystemClock;

import androidx.annotation.Nullable;

public class IntentServicioOperacion extends IntentService {

    public IntentServicioOperacion() {
        super("IntentServicioOperacion");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        double n = intent.getExtras().getDouble("numero");
        SystemClock.sleep(10000);
        MainActivity.sortida.append(n*n + "\n");
    }
}
