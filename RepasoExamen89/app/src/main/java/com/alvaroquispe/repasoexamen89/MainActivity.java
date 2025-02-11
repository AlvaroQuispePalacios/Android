package com.alvaroquispe.repasoexamen89;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button btnPermisosBasicos;
    Button btnPermisosUsuario;
    Button btnLocalizacionBasica;
    Button btnLocalizacionGoogle;
    Button btnLocalizacionGoogle2;
    Button btnExamenRepaso;
    Button btnServicioBloqueante;
    Button btnServicioNoBloqueante;
    Button btnNotificacion;
    Button btnBroadcast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        btnPermisosBasicos = findViewById(R.id.btnPermisosBasicos);
        btnPermisosUsuario = findViewById(R.id.btnPermisosUsuario);
        btnLocalizacionBasica = findViewById(R.id.btnLocalizacionBasica);
        btnLocalizacionGoogle = findViewById(R.id.btnLocalizacionGoogle);
        btnLocalizacionGoogle2 = findViewById(R.id.btnLocalizacionGoogle2);
        btnExamenRepaso = findViewById(R.id.btnExamenRepaso);
        btnServicioBloqueante = findViewById(R.id.btnServicioBloqueante);
        btnServicioNoBloqueante = findViewById(R.id.btnServicioNoBloqueante);
        btnNotificacion = findViewById(R.id.btnNotificacion);
        btnBroadcast = findViewById(R.id.btnBroadcast);

        btnBroadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarBroadcastActivity(null);
            }
        });

        btnNotificacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarNotificacionActivity(null);
            }
        });

        btnServicioNoBloqueante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarServicioNoBloqueante(null);
            }
        });

        btnServicioBloqueante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarServicioBloqueante(null);
            }
        });

        btnExamenRepaso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarExamenRepaso(null);
            }
        });

        btnLocalizacionGoogle2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarLocalizarGoogle2(null);
            }
        });
        btnPermisosBasicos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarPermisosBasicos(null);
            }
        });

        btnPermisosUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarPermisosUsuarios(null);
            }
        });

        btnLocalizacionBasica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarLocalizacionBasica(null);
            }
        });

        btnLocalizacionGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarGoogle1(null);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void mostrarBroadcastActivity(View view) {
        Intent i = new Intent(this, BroadcastActivity.class);
        startActivity(i);
    }

    private void mostrarNotificacionActivity(View view) {
        Intent i = new Intent(this, NotificacionActivity.class);
        startActivity(i);
    }

    private void mostrarServicioNoBloqueante(View view){
        Intent i = new Intent(this, ServicioNoBloqueante.class);
        startActivity(i);
    }

    private void mostrarServicioBloqueante(View view){
        Intent i = new Intent(this, ServicioBloqueante.class);
        startActivity(i);
    }

    private void mostrarExamenRepaso(View view) {
        Intent i = new Intent(this, ExamenRepaso.class);
        startActivity(i);
    }

    private void mostrarLocalizarGoogle2(View view) {
        Intent i = new Intent(this, Google2.class);
        startActivity(i);
    }

    private void mostrarGoogle1(View view) {
        Intent i = new Intent(this, Google1.class);
        startActivity(i);
    }

    private void mostrarLocalizacionBasica(View view) {
        Intent i = new Intent(this, LocalizacionBasica.class);
        startActivity(i);
    }

    private void mostrarPermisosUsuarios(View view) {
        Intent i = new Intent(this, PermisosUsuario.class);
        startActivity(i);
    }

    private void mostrarPermisosBasicos(View view) {
        Intent i = new Intent(this, PermisosBasicos.class);
        startActivity(i);
    }
}