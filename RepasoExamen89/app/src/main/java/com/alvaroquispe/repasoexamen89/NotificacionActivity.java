package com.alvaroquispe.repasoexamen89;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class NotificacionActivity extends AppCompatActivity {
    Button btnMandarNotificacion;
    // Canal de notificación
    static final String PRIMARY_CANAL_NOTIFICACION = "TTMatutino";
    NotificationManager nManager;
    // Notificacion ID
    private static final int NOTIFICACION_ID = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_notificacion);
        btnMandarNotificacion = findViewById(R.id.btnMandarNotificacion);

        btnMandarNotificacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarNotificacion();
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        crearCanalNotificacion();
    }

    public void crearCanalNotificacion(){
        nManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel canalNotificacion = new NotificationChannel(PRIMARY_CANAL_NOTIFICACION, "Notificacion prueba", NotificationManager.IMPORTANCE_HIGH);
            nManager.createNotificationChannel(canalNotificacion);
        }
    }

    public NotificationCompat.Builder crearNotificacion(){
        Intent i = new Intent(this, MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, NOTIFICACION_ID, i, PendingIntent.FLAG_IMMUTABLE);
        NotificationCompat.Builder ncb = new NotificationCompat.Builder(this, PRIMARY_CANAL_NOTIFICACION)
                .setSmallIcon(R.drawable.ic_php)
                .setContentTitle("Nueva notificacion")
                .setContentIntent(pi)
                .setAutoCancel(true);
        return ncb;
    }

    private void enviarNotificacion() {
        NotificationCompat.Builder ncb = crearNotificacion();
        nManager.notify(NOTIFICACION_ID, ncb.build());
    }
}