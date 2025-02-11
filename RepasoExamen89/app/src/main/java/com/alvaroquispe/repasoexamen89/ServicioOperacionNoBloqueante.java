package com.alvaroquispe.repasoexamen89;

import android.app.IntentService;
import android.content.Intent;
import android.os.SystemClock;

import androidx.annotation.Nullable;

public class ServicioOperacionNoBloqueante extends IntentService {

    public ServicioOperacionNoBloqueante() {
        super("ServicioOperacionNoBloqueante");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        double n = intent.getExtras().getDouble("numero", 0.0);
        SystemClock.sleep(5000);
        ServicioNoBloqueante.sortida.append( n *n +"\n");
    }
}
