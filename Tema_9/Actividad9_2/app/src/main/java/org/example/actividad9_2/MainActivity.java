package org.example.actividad9_2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private EditText entrada;
    public static TextView sortida;
    Button btnCalcularOperacion;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        entrada= (EditText) findViewById(R.id.entrada);
        sortida = (TextView) findViewById(R.id.sortida);
        btnCalcularOperacion = findViewById(R.id.btnCalcularOperacion);
        btnCalcularOperacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calcularOperacio(null);
            }
        });
    }
    public void calcularOperacio(View view) {
        double n = Double.parseDouble(entrada.getText().toString());
        sortida.append(n +"^2 = ");
        Intent i = new Intent(this, ServeiOperacion.class);
        i.putExtra("numero", n);
        startService(i);
    }
}