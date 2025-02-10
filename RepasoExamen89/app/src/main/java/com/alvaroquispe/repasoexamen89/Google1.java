package com.alvaroquispe.repasoexamen89;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Google1 extends AppCompatActivity implements OnMapReadyCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_google1);


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapa);

        mapFragment.getMapAsync(this);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // Se llama a esta función cuando el mapa esta listo
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        // obtenemos el mapa
        GoogleMap mapa = googleMap;
        // Creamos y inicializamos el tipo de dato LatLng con la latitud y longitud del Ramis
        LatLng Ramis = new LatLng(39.887553816976485, 4.254823701719172);
        //Agregamos un marcador al mapa con la posicion del Ramis le damos un titulo
        mapa.addMarker(new MarkerOptions().position(Ramis).title("Marcador Ramis"));
        // Movemos la camara del mapa para que nos muestre el ramis
        mapa.moveCamera(CameraUpdateFactory.newLatLng(Ramis));

    }
}