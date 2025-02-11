package org.example.examentema8_9_alvaroquispe;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
    public static final String ACTION = "NEW_MARKER";
    @Override
    public void onReceive(Context context, Intent intent) {
        float distancia = intent.getExtras().getFloat("resultado");

        Toast.makeText(context, distancia + "km", Toast.LENGTH_SHORT).show();
    }
}