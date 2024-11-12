package com.alvaroquispe.examenrepaso;

import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AnimacionActivity extends AppCompatActivity {
    Button btnComenzarAnimacion;
    Button btnDetenerAnimacion;
    AnimationDrawable animacion;
    ImageView animacionImagenes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_animacion);
        animacionImagenes = findViewById(R.id.animacionImagenes);
        btnComenzarAnimacion = findViewById(R.id.btnComenzarAnimacion);
        btnDetenerAnimacion = findViewById(R.id.btnDetenerAnimacion);
        animacion = (AnimationDrawable) ContextCompat.getDrawable(this, R.drawable.animacion);

        animacionImagenes.setImageDrawable(animacion);
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

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


}
