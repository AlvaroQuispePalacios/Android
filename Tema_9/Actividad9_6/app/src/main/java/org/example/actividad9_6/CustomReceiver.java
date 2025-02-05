package org.example.actividad9_6;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class CustomReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
       String intentAction = intent.getAction();

       if(intentAction != null){
           String mensaje = "unknown intent action";
           switch (intentAction){
               case Intent.ACTION_POWER_CONNECTED:
                   mensaje = "Esta conectado :D";
                   break;
               case Intent.ACTION_POWER_DISCONNECTED:
                   mensaje = "No esta conectado D:";
                   break;
           }

           Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show();
       }
    }
}