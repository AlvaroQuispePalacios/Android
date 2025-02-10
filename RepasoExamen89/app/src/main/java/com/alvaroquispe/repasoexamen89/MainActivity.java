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