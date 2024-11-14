package com.alvaroquispe.examenrepaso;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AnimacionActivity extends AppCompatActivity {
    Button btnComenzarAnimacion;
    Button btnDetenerAnimacion;
    Button btnVolverAlInicioAnimacion;
    TextView tvContadorAnimacion;

    AnimationDrawable animacion;
    ImageView animacionImagenes;

    Boolean a = false;
    Long tiempoTotal = (long) 0;
    Long tiempoInicio;
    Long tiempoFinal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_animacion);

        animacionImagenes = findViewById(R.id.animacionImagenes);
        btnComenzarAnimacion = findViewById(R.id.btnComenzarAnimacion);
        btnDetenerAnimacion = findViewById(R.id.btnDetenerAnimacion);
        btnVolverAlInicioAnimacion = findViewById(R.id.btnVolverAlInicioAnimacion);
        tvContadorAnimacion = findViewById(R.id.tvContadorAnimacion);

        animacion = (AnimationDrawable) ContextCompat.getDrawable(this, R.drawable.animacion);
        animacionImagenes.setImageDrawable(animacion);

        btnComenzarAnimacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!animacion.isRunning()){
                    animacion.start();
                    tiempoInicio = System.currentTimeMillis();
                }
            }
        });
        btnDetenerAnimacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(animacion.isRunning()){
                    animacion.stop();
                    tiempoFinal = System.currentTimeMillis();
                    tiempoTotal = (tiempoTotal + (tiempoFinal - tiempoInicio)) / 1000;
                    tvContadorAnimacion.setText(tiempoTotal + "");
                }

            }
        });

        btnVolverAlInicioAnimacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VolverAlInicioAnimacion(null);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void VolverAlInicioAnimacion(View view){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

}
