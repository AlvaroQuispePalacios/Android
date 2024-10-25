package com.alvaroquispe.comunicacionactividades;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {
    TextView receptor;
    Button btnAceptar;
    Button btnRechazar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

        receptor = findViewById(R.id.textoReceptor);
        String nombre;
        Intent i = getIntent();
        nombre = i.getStringExtra("nombre");

        receptor.append("Bienvenido " + nombre + " aceptas las condiciones?");

        btnAceptar = findViewById(R.id.btnAceptar);
        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.putExtra("respuesta", "aceptar");
                setResult(RESULT_OK, i);
                finish();
            }
        });

        btnRechazar = findViewById(R.id.btnRechazar);
        btnRechazar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.putExtra("respuesta", "rechazar");
                setResult(RESULT_OK, i);
                finish();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
//    Hacer una funcion para devolver respuesta

}