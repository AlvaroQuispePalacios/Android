package org.example.actividad9_1;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.os.SystemClock;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class ServicioMusica extends Service {
    MediaPlayer reproductor;

    @Override
    public void onCreate() {
        Toast.makeText(this, "Servicio creado", Toast.LENGTH_SHORT).show();
        reproductor = MediaPlayer.create(this, R.raw.audio);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int idArranque) {
        /* START_STICKY -> Si ocurre algun problema y el servicio no se puede ejecutar. Devolviendo
        * START_STICKY tendra el comportamiento de que si ocurre el error el sistema intentara denuevo
        * el servicio cuando disponga de momoria suficiente
        *
        * START_*NOT_*STICKY si queremos que el servicio sea creado de nuevo solo
        * cuando llegue una nueva solicitud de creación.
        */
        Toast.makeText(this, "Servicio Arrancado" + idArranque, Toast.LENGTH_SHORT).show();
        reproductor.start();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this,"Servei detingut", Toast.LENGTH_SHORT).show();
        reproductor.stop();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
