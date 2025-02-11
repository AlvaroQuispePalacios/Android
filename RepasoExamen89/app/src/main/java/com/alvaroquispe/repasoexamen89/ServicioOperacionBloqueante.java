package com.alvaroquispe.repasoexamen89;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;

import androidx.annotation.Nullable;

public class ServicioOperacionBloqueante extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        double n = intent.getExtras().getDouble("numero", 0.0);
        SystemClock.sleep(5000);
        ServicioBloqueante.sortida.append(n * n + "\n");
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
