package com.alvaroquispe.ejerciciostema5;

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
    Button btnArosOlimpicos;
    Button btnActividad52;
    Button btnActividad53;
    Button btnActividad55;
    Button btnActividad56;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btnArosOlimpicos = findViewById(R.id.btnArosOlimpicos);
        btnActividad52 = findViewById(R.id.btnActividad52);
        btnActividad53 = findViewById(R.id.btnActividad53);
        btnActividad55 = findViewById(R.id.btnActividad55);
        btnActividad56 = findViewById(R.id.btnActividad56);

        btnArosOlimpicos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MostrarArosOlimpicos(null);
            }
        });

        btnActividad52.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MostrarActividad52(null);
            }
        });

        btnActividad53.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MostrarActividad53(null);
            }
        });

        btnActividad55.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnActividad56.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void MostrarArosOlimpicos(View view){
        Intent i = new Intent(this, ArosOlimpicos.class);
        startActivity(i);
    }

    public void MostrarActividad52(View view){
        Intent i = new Intent(this, Actividad5_2.class);
        startActivity(i);
    }

    public void MostrarActividad53(View view){
        Intent i = new Intent(this, Actividad5_3.class);
        startActivity(i);
    }
}