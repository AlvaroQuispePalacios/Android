package com.alvaroquispe.repasoexamen89;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ServicioNoBloqueante extends AppCompatActivity {
    public static TextView sortida;
    private EditText entrada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_servicio_no_bloqueante);

        entrada = (EditText) findViewById(R.id.entrada);
        sortida = (TextView) findViewById(R.id.sortida);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void calcularOperacion(View view){
        double n = Double.parseDouble(entrada.getText().toString());
        sortida.append( n + "^2 = ");
        Intent i = new Intent(this, ServicioOperacionNoBloqueante.class);
        i.putExtra("numero", n);
        startService(i);
    }
}