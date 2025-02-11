package org.example.examentema8_9_alvaroquispe;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener, GoogleMap.OnCameraMoveListener {
    GoogleMap mapa;
    LatLng Ramis = new LatLng(39.88806226896328, 4.254715356374438);

    Location localizacionRamis = new Location("Ramis");
    Location localizacionMarcador = new Location("nueva_localizacion");
    MyReceiver myReceiver = new MyReceiver();

    static final String PRIMARY_CANAL = "TTMATUTINO";
    NotificationManager nm;
    int NOTIFICACION_ID = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //
        SupportMapFragment mapaFragmento = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapa);
        mapaFragmento.getMapAsync(this);

        localizacionRamis.setLatitude(Ramis.latitude);
        localizacionRamis.setLongitude(Ramis.longitude);

        // Filtro para el broadcast y registramos el broadcast con los filtros
        IntentFilter filter = new IntentFilter(MyReceiver.ACTION);
        filter.addCategory(Intent.CATEGORY_DEFAULT);
        LocalBroadcastManager.getInstance(this).registerReceiver(myReceiver, filter);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Creamos el canal de notificacion
        crearCanalNotificacion();
    }

    public void crearCanalNotificacion(){
        nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel nc = new NotificationChannel(PRIMARY_CANAL, "Hemisferio", NotificationManager.IMPORTANCE_HIGH);
            nm.createNotificationChannel(nc);
        }
    }

    public NotificationCompat.Builder crearNotificacion(String mensaje){
        NotificationCompat.Builder nb = new NotificationCompat.Builder(this, PRIMARY_CANAL)
                .setSmallIcon(R.drawable.ic_map)
                .setContentTitle("Hemisferio")
                .setContentText(mensaje);
        return nb;
    }

    public void enviarNotificacion(String mensaje){
        NotificationCompat.Builder nb = crearNotificacion(mensaje);
        nm.notify(NOTIFICACION_ID, nb.build());
    }

    @Override
    public void onMapClick(@NonNull LatLng latLng) {
        // Agregamos el nuevo marcador
        mapa.addMarker(new MarkerOptions().position(latLng));
        // Obtener la localizacion del marcador
        localizacionMarcador.setLatitude(latLng.latitude);
        localizacionMarcador.setLongitude(latLng.longitude);

        float distancia = localizacionRamis.distanceTo(localizacionMarcador);
        // Enviar el mensaje al broadcast
        Intent i = new Intent(MyReceiver.ACTION);
        i.putExtra("resultado", distancia);
        LocalBroadcastManager.getInstance(this).sendBroadcast(i);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

        mapa = googleMap;
        mapa.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        mapa.moveCamera(CameraUpdateFactory.newLatLng(Ramis));
        mapa.setOnMapClickListener(this);
        mapa.setOnCameraMoveListener(this);
    }

    @Override
    public void onCameraMove() {
        //Enviar notificacion si esta en el hemisferio norte o en el hemisferio sur
        LatLng posicionCamara = mapa.getCameraPosition().target;
        int contador = 0;
        if(posicionCamara.latitude > 0){
            enviarNotificacion("Bienvenido al hemisferio Norte");
        }

        if(posicionCamara.latitude < 0){
            enviarNotificacion("Bienvenido al hemisferio Sur");

        }
    }
}