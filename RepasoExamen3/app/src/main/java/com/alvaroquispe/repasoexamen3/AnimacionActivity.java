package com.alvaroquispe.repasoexamen3;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
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
    AnimationDrawable animacion;
    ImageView ivAnimacion;

    TextView tvTiempoAnimacion;
    Button btnIniciarAnimacion;
    Button btnDetenerAnimacion;
    Button btnVolverMainAnimacion;

    long tiempoInicio;
    long tiempoFinal;
    int tiempoTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_animacion);

        animacion = (AnimationDrawable) ContextCompat.getDrawable(this, R.drawable.animacion);
        ivAnimacion = findViewById(R.id.ivAnimacion);
        ivAnimacion.setImageDrawable(animacion);
        btnIniciarAnimacion = findViewById(R.id.btnIniciarAnimacion);
        btnDetenerAnimacion = findViewById(R.id.btnDetenerAnimacion);
        btnVolverMainAnimacion = findViewById(R.id.btnVolverMainAnimacion);
        tvTiempoAnimacion = findViewById(R.id.tvTiempoAnimacion);
        tvTiempoAnimacion.setText(Integer.toString(tiempoTotal));

        Bundle extras = getIntent().getExtras();
        tiempoTotal = extras.getInt("contador");

        btnIniciarAnimacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!animacion.isRunning()){
                    tiempoInicio = System.currentTimeMillis();
                    animacion.start();
                }
            }
        });

        btnDetenerAnimacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(animacion.isRunning()){
                    animacion.stop();
                    tiempoFinal = System.currentTimeMillis();
                    tiempoTotal = (int) ((tiempoFinal - tiempoInicio) / 1000);
                    tvTiempoAnimacion = findViewById(R.id.tvTiempoAnimacion);
                    tvTiempoAnimacion.setText(Integer.toString(tiempoTotal));
                }
            }
        });

        btnVolverMainAnimacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(animacion.isRunning()){
                    animacion.stop();
                    tiempoFinal = System.currentTimeMillis();
                    tiempoTotal = (int) ((tiempoFinal - tiempoInicio) / 1000);
                    tvTiempoAnimacion = findViewById(R.id.tvTiempoAnimacion);
                    tvTiempoAnimacion.setText(Integer.toString(tiempoTotal));
                }
                Intent i = new Intent();
                i.putExtra("resultado", tiempoTotal);
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
}