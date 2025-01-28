package org.example.actividad9_2;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;

public class ServeiOperacion extends Service {
    @Override
    public int onStartCommand(Intent i, int flags, int idArranque) {
        double n = i.getExtras().getDouble("numero");
        SystemClock.sleep(25000);
        MainActivity.sortida.append(n * n + "\n");
        return START_STICKY;
    }
    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }
}