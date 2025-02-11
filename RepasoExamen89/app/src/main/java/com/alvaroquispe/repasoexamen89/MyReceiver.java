package com.alvaroquispe.repasoexamen89;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String filtro = intent.getAction();

        if(filtro != null){
            switch (filtro){
                case Intent.ACTION_AIRPLANE_MODE_CHANGED:
                    Intent i = new Intent(context, MainActivity.class);
                    i.addFlags(Intent. FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);
                    break;
                case Intent.ACTION_POWER_CONNECTED:
                    Toast.makeText(context, "Cargando", Toast.LENGTH_SHORT).show();
                    break;
            }
        }

    }
}