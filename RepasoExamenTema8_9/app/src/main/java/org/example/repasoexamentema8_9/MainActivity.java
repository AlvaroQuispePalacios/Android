package org.example.repasoexamentema8_9;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mapa;
    private final LatLng sonBou = new LatLng(39.89951647861435, 4.072310359694697);
    private final LatLng puntaPrima = new LatLng(39.81395394237914, 4.28033223266925);

    private static final String CANAL_ID = "canal_notificacion";
    private static final int NOTIFICACION_ID = 0;
    private NotificationManager nm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        SupportMapFragment mapFragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.mapa);
        mapFragment.getMapAsync(this);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        crearCanalNotificacion();
    }

    public void crearCanalNotificacion(){
        nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel nc = new NotificationChannel(
                    CANAL_ID,
                    "Notificación Playa",
                    NotificationManager.IMPORTANCE_HIGH
            );
            nm.createNotificationChannel(nc);
        }
    }

    private NotificationCompat.Builder getNotificationBuilder(String titulo, String texto, Class<?extends AppCompatActivity> clase){
        // Intent
        Intent notificationIntent = new Intent(this, clase);
        PendingIntent npi = PendingIntent.getActivity(this, NOTIFICACION_ID, notificationIntent, PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(
                this,
                CANAL_ID)
                .setContentTitle(titulo)
                .setContentText(texto)
                .setSmallIcon(R.drawable.ic_map)
                // Estableces que cuando se presione la notificación se haga algo
                .setContentIntent(npi)
                .setAutoCancel(true);
        return notifyBuilder;
    }

    public void enviarNotificacion(String titulo, String texto, Class<? extends AppCompatActivity> clase){
        NotificationCompat.Builder notifyBuilder = getNotificationBuilder(titulo, texto, clase);
        nm.notify(NOTIFICACION_ID, notifyBuilder.build());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.itemPlayaPuntaPrima){
            moverCamara(this.getCurrentFocus(), puntaPrima);
            enviarNotificacion("Punta Prima", "Has llegado a PuntaPrima", SobrePuntaPrima.class);
        }

        if(id == R.id.itemPlayaSonBou){
            moverCamara(this.getCurrentFocus(), sonBou);
            enviarNotificacion("Son Bou", "Has llegado a Son Bou", SobreSonBou.class);

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mapa = googleMap;
        mapa.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
//        mapa.getUiSettings().setZoomControlsEnabled(false);
        mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(sonBou, 15));

        mapa.addMarker(new MarkerOptions()
                .position(sonBou)
                .title("Son Bou")
                .snippet("Playa de Son bou")
//                .icon(BitmapDescriptorFactory.fromResource(android.R.drawable.ic_menu_compass))
//                .anchor(0.5f, 0.5f)
        );

        mapa.addMarker(new MarkerOptions()
                .position(puntaPrima)
                .title("Punta Prima")
                .snippet("Playa Punta Prima")
        );

//        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
//        == PackageManager.PERMISSION_GRANTED){
//        }
//        mapa.setMyLocationEnabled(true);
//        mapa.getUiSettings().setCompassEnabled(true);
    }

    public void moverCamara(View view, LatLng playa){
        mapa.animateCamera(CameraUpdateFactory.newLatLng(playa));
    }
}