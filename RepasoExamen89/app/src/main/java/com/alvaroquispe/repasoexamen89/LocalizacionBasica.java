package com.alvaroquispe.repasoexamen89;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LocalizacionBasica extends AppCompatActivity implements LocationListener {
    static final long TEMPS_MIN = 5 * 1000;
    static final long DISTANCIA_MIN = 5;
    static final String[] A = {"n/d", "precís", "imprecís"};
    static final String[] P = {"n/d", "baix", "mitjà", "alt"};
    static final String[] E = {"fora de servei", "temporalment no disponible ", "disponible"};
    LocationListener manejador;
    String provedor;
    TextView sortida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_localizacion_basica);
        sortida = findViewById(R.id.sortida);
//        manejador = (LocationManager) getSystemService(LOCATION_SERVICE);

//        log("Provedores de localizacion: \n" );
        mostrarProvedores();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void mostrarProvedores() {
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        mostrarProvedores();
    }
}