package org.example.localitzacio;

import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class MainActivity extends AppCompatActivity implements LocationListener {
    static final long TEMPS_MIN = 10 * 1000; // 10 segons
    static final long DISTANCIA_MIN = 5; // 5 metres
    static final String[] A = {"n/d", "precís", "imprecís"};
    static final String[] P = {"n/d", "baix", "mitjà", "alt"};
    static final String[] E = {"fora de servei", "temporalment no disponible ", "disponible"};
    LocationManager manejador;
    String proveidor;
    TextView sortida;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sortida = findViewById(R.id.sortida);
        manejador = (LocationManager) getSystemService(LOCATION_SERVICE);
        log("Proveidores de localització: \n ");
        mostraProveidors();
        Criteria criteri = new Criteria();
        criteri.setCostAllowed(false);
        criteri.setAltitudeRequired(false);
        criteri.setAccuracy(Criteria.ACCURACY_FINE);

        proveidor = manejador.getBestProvider(criteri, true);

        log("Millor proveïdor: " + proveidor + "\n");
        log("Començam amb la darrera localització coneguda:");
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location localizacion = manejador.getLastKnownLocation(proveidor);
        mostraLocalitz(localizacion);
    }

    // Mètodes del cicle de vida de l'activitat
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onResume() {
        super.onResume();
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        manejador.requestLocationUpdates(proveidor, TEMPS_MIN, DISTANCIA_MIN, this);
    }
    @Override protected void onPause() {
        super.onPause();
        manejador.removeUpdates(this);
    }
    // Mètodes de l'interficie LocationListener
    @Override public void onLocationChanged(Location location) {
        log("Nova localitzación: ");
        mostraLocalitz(location);
    }
    @Override public void onProviderDisabled(String proveidor) {
        log("Proveïdor deshabilitat: " + proveidor + "\n");
    }
    @Override public void onProviderEnabled(String proveidor) {
        log("Proveïdor habilitat: " + proveidor + "\n");
    }
    @Override public void onStatusChanged(String proveidor, int estat,
                                          Bundle extres) {
        log("Canvi estat proveidor: " + proveidor + ", estat="
                + E[Math.max(0, estat)] + ", extres=" + extres + "\n");
    }
    // Mètodes par mostrar informació
    private void log(String cadena) {
        sortida.append(cadena + "\n");
    }
    private void mostraLocalitz(Location localitzacio) {
        if (localitzacio == null)
            log("Localització desconeguda\n");
        else
            log(localitzacio.toString() + "\n");
    }

    private void mostraProveidors() {
        log("Proveïdors de localització: \n ");
        List<String> proveidors = manejador.getAllProviders();
        for (String proveidor : proveidors) {
            mostraProveidor(proveidor);
        }
    }
    private void mostraProveidor(String proveidor) {
        LocationProvider info = manejador.getProvider(proveidor);
        log("LocationProvider[ " + "getName=" + info.getName()
                        + ", isProviderEnabled="
                        + manejador.isProviderEnabled(proveidor) + ", getAccuracy="
                        + A[Math.max(0, info.getAccuracy())] + ", getPowerRequirement="
                        + P[Math.max(0, info.getPowerRequirement())]
                        + ", hasMonetaryCost=" + info.hasMonetaryCost()
                        + ", requiresCell=" + info.requiresCell()
                        + ", requiresNetwork=" + info.requiresNetwork()
                        + ", requiresSatellite=" + info.requiresSatellite()
                        + ", supportsAltitude=" + info.supportsAltitude()
                        + ", supportsBearing=" + info.supportsBearing()
                        + ", supportsSpeed=" + info.supportsSpeed() + " ]\n");
    }
}