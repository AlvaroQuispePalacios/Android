package org.example.actividad9_1;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.os.SystemClock;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class ServicioMusica extends Service {
    MediaPlayer reproductor;

    private static final String PRIMARY_CHANNEL_ID = "primary_notification_channel";
    private NotificationManager mNotifyManager;
    private static final int NOTIFICATION_ID = 0;

    @Override
    public void onCreate() {
        Toast.makeText(this, "Servicio creado", Toast.LENGTH_SHORT).show();
        reproductor = MediaPlayer.create(this, R.raw.audio);
        createNotificationChannel();
        sendNotification();
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


    /**
     * Para crear una notifiación se declara el ID del canal
     * y un NotificationManager donde se obtendra el sistema el servicio de notificación
     * Luego se comprueba la API ya que solo esta disponible desde la versión 26 en adelante
     *
     */

    public void createNotificationChannel(){
        mNotifyManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // Comprueba la version de la API sea mayor o igual 26
        if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O){
            // Creamos el canal de notificación
            NotificationChannel notificationChannel = new NotificationChannel(PRIMARY_CHANNEL_ID, "Musica notificación", NotificationManager.IMPORTANCE_HIGH);
            // Comportamientos de la notificación
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription("Creando el servicio de música");
            // Creamos el canal de notificación
            mNotifyManager.createNotificationChannel(notificationChannel);
        }
    }

    // Crear la notificación
    private NotificationCompat.Builder getNoticationBuilder (){

        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent notificationPendingIntent = PendingIntent.getActivity(this, NOTIFICATION_ID, notificationIntent, PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(this, PRIMARY_CHANNEL_ID)
                .setContentTitle("Servicio de Música")
                .setContentText("Creado el servicio de música")
                .setSmallIcon(R.drawable.ic_music)
                .setContentIntent(notificationPendingIntent)
                .setAutoCancel(true);

        return notifyBuilder;
    }

    // Envia la notificación
    private void sendNotification(){
        NotificationCompat.Builder notifyBuilder = getNoticationBuilder();
        mNotifyManager.notify(NOTIFICATION_ID, notifyBuilder.build());
    }
}
