package com.alvaroquispe.examenanyopasadotema89;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    // Mapas
    GoogleMap mapa;
    LatLng playaSonBou = new LatLng(39.89813483243601, 4.0770691616128145);
    LatLng playaPuntaPrima = new LatLng(39.8139209779112, 4.280267859656101);
    // Notificación
        // Crear canal de notificación
    static final String PRIMARY_CANAL_NOTIFICACION = "TTMatutino";
    private NotificationManager nm;
        // Crear la notificación
    private static final int NOTIFICACION_ID = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapa);
        mapFragment.getMapAsync(this);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Crear un canal de notificaciones
        crearCanalNotificaciones();
    }

    public void crearCanalNotificaciones(){
        nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel nc = new NotificationChannel(PRIMARY_CANAL_NOTIFICACION,
                    "Playa información", NotificationManager.IMPORTANCE_HIGH);
            nm.createNotificationChannel(nc);
        }
    }

    public NotificationCompat.Builder obtenerNotificacionConstructor(String titulo, Class<? extends AppCompatActivity> playa){
        Intent i = new Intent(this, playa);
        PendingIntent npi = PendingIntent.getActivity(this, NOTIFICACION_ID, i, PendingIntent.FLAG_IMMUTABLE);
        NotificationCompat.Builder nf = new NotificationCompat.Builder(this, PRIMARY_CANAL_NOTIFICACION)
                .setContentTitle(titulo)
                .setSmallIcon(R.drawable.ic_playa)
                .setContentIntent(npi)
                .setAutoCancel(true);
        return nf;
    }

    public void mandarNotificacion(String titulo, Class<? extends  AppCompatActivity> playa){
        NotificationCompat.Builder ncb = obtenerNotificacionConstructor(titulo, playa);
        nm.notify(NOTIFICACION_ID, ncb.build());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id== R.id.playaSonBou){
            moverCamaraAnimacion(playaSonBou);
            mandarNotificacion("Playa Son Bou", PlayaSonBou.class);
        }
        if(id== R.id.playaPuntaPrima){
            moverCamaraAnimacion(playaPuntaPrima);
            mandarNotificacion("Playa Punta Prima", PlayaPuntaPrima.class);

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        // Obtenemos el mapa para hacer cosas
        mapa = googleMap;
        mapa.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        mapa.addMarker(new MarkerOptions().position(playaPuntaPrima).title("Playa Punta Prima"));
        mapa.addMarker(new MarkerOptions().position(playaSonBou).title("Playa Son Bou"));
        mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(playaPuntaPrima, 15));
    }

    public void moverCamaraNormal(LatLng playa){
        mapa.moveCamera(CameraUpdateFactory.newLatLng(playa));
    }

    public void moverCamaraAnimacion(LatLng playa){
        mapa.animateCamera(CameraUpdateFactory.newLatLng(playa));
    }
}