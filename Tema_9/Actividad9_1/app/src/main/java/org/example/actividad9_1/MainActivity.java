package org.example.actividad9_1;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button boton_arrancar;
    Button boton_detener;
//    private static final String PRIMARY_CHANNEL_ID = "primary_notification_channel";
//    private NotificationManager mNotifyManager;
//    private static final int NOTIFICATION_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        // Un servicio es bloqueante
        // Un IntentService no es bloqueante
        boton_arrancar = findViewById(R.id.boton_arrancar);
        boton_detener = findViewById(R.id.boton_detener);

        boton_arrancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startService(new Intent(MainActivity.this, ServicioMusica.class));
            }
        });

        boton_detener.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopService(new Intent(MainActivity.this, ServicioMusica.class));
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

//        createNotificationChannel();
    }
//
//    /**
//     * Para crear una notifiación se declara el ID del canal
//     * y un NotificationManager donde se obtendra el sistema el servicio de notificación
//     * Luego se comprueba la API ya que solo esta disponible desde la versión 26 en adelante
//     *
//     */
//
//    public void createNotificationChannel(){
//        mNotifyManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//        // Comprueba la version de la API sea mayor o igual 26
//        if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O){
//            // Creamos el canal de notificación
//            NotificationChannel notificationChannel = new NotificationChannel(PRIMARY_CHANNEL_ID, "Musica notificación", NotificationManager.IMPORTANCE_HIGH);
//            // Comportamientos de la notificación
//            notificationChannel.enableLights(true);
//            notificationChannel.setLightColor(Color.RED);
//            notificationChannel.enableVibration(true);
//            notificationChannel.setDescription("Creando el servicio de música");
//            // Creamos el canal de notificación
//            mNotifyManager.createNotificationChannel(notificationChannel);
//        }
//    }
//
//    // Crear la notificación
//    private NotificationCompat.Builder getNoticationBuilder (){
//        NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(this, PRIMARY_CHANNEL_ID)
//                .setContentTitle("Servicio de Música")
//                .setContentText("Creado el servicio de música")
//                .setSmallIcon(R.drawable.ic_music);
//
//        return notifyBuilder;
//    }
//
//    // Envia la notificación
//    private void sendNotification(){
//        NotificationCompat.Builder notifyBuilder = getNoticationBuilder();
//        mNotifyManager.notify(NOTIFICATION_ID, notifyBuilder.build());
//    }

}