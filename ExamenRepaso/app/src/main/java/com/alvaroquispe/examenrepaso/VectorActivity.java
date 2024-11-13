package com.alvaroquispe.examenrepaso;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class VectorActivity extends AppCompatActivity {
    Button btnVolverAlInicio;
    int contador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_vector);

        btnVolverAlInicio = findViewById(R.id.btnVolverAlInicio);
        btnVolverAlInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contador = getIntent().getIntExtra("contadorVector", 0);
                contador++;
                Intent retornarContador = new Intent();
                retornarContador.putExtra("contadorVector", contador);
                setResult(RESULT_OK, retornarContador);
                finish();
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void MostrarMainActivity(View view){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }


}