package com.alvaroquispe.repasoexamen2;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
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
    private Runnable runnable;
    private Handler handler = new Handler();
    private int contador = 0;

    ImageView ivAnimacion;
    AnimationDrawable animacion;

    Button btnComenzarAnimacion;
    Button btnDetenerAnimacion;
    Button btnVolverAMainActivityAnimacion;

    TextView tvTiempoAnimacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_animacion);

        ivAnimacion = findViewById(R.id.ivAnimacion);
        animacion = (AnimationDrawable) ContextCompat.getDrawable(this, R.drawable.animacion);
        ivAnimacion.setImageDrawable(animacion);
        btnComenzarAnimacion = findViewById(R.id.btnComenzarAnimacion);
        btnDetenerAnimacion = findViewById(R.id.btnDetenerAnimacion);
        btnVolverAMainActivityAnimacion = findViewById(R.id.btnVolverAMainActivityAnimacion);
        tvTiempoAnimacion = findViewById(R.id.tvTiempoAnimacion);

        btnComenzarAnimacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animacion.start();
            }
        });

        btnDetenerAnimacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animacion.stop();

            }
        });

        btnVolverAMainActivityAnimacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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