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

public class MainActivity extends AppCompatActivity {
    Button btnVerificar;
    TextView txtNombre;
    String nombre;
    TextView respuestaDesdeM2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        respuestaDesdeM2 = findViewById(R.id.respuestaDesdeM2);
        btnVerificar = findViewById(R.id.verificar);
        btnVerificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarViewActivity2(null);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void mostrarViewActivity2(View view){
        Intent i = new Intent(this, MainActivity2.class);
        txtNombre = (TextView) findViewById(R.id.editNombre);
        nombre = txtNombre.getText().toString();
        i.putExtra("nombre", nombre);
        startActivityForResult(i, 1234);
    }

    @Override protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1234 && resultCode == RESULT_OK) {
            String res = data.getStringExtra("respuesta");
            respuestaDesdeM2.setText("Respuesta " + res);
        }
    }
}