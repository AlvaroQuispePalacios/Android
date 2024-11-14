package com.alvaroquispe.examenrepaso;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    TextView tvNumeroEjecucionesVector;
    TextView tvNumeroEjecucionesMapa;
    Button btnMostrarAnimacionActivity;
    Button btnMostrarVectorActivity;
    Button btnMostrarMapaActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btnMostrarAnimacionActivity = findViewById(R.id.btnMostrarAnimacionActivity);
        btnMostrarVectorActivity = findViewById(R.id.btnMostrarVectorActivity);
        btnMostrarMapaActivity = findViewById(R.id.btnMostrarMapaActivity);
        tvNumeroEjecucionesVector = findViewById(R.id.tvNumeroEjecucionesVector);
        tvNumeroEjecucionesMapa = findViewById(R.id.tvNumeroEjecucionesMapa);

        btnMostrarAnimacionActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MostrarAnimacionActivity(null);
            }
        });

        btnMostrarVectorActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MostrarVectorActivity(null);
            }
        });

        btnMostrarMapaActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int contador = Integer.parseInt(tvNumeroEjecucionesMapa.getText().toString());
                contador++;
                tvNumeroEjecucionesMapa.setText(Integer.toString(contador));
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:39.887642,4.254319"));
                startActivity(intent);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void MostrarAnimacionActivity(View view){
        Intent i = new Intent(this, AnimacionActivity.class);
        startActivity(i);
    }
    public void MostrarVectorActivity(View view){
        Intent i = new Intent(this, VectorActivity.class);
        tvNumeroEjecucionesVector = findViewById(R.id.tvNumeroEjecucionesVector);
        i.putExtra("contadorVector", Integer.parseInt(tvNumeroEjecucionesVector.getText().toString()));
        startActivityForResult(i, 2);
    }

    @Override protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==2 && resultCode==RESULT_OK) {
            int contadorAcualizado = data.getIntExtra("contadorVector", 0);
            tvNumeroEjecucionesVector.setText(Integer.toString(contadorAcualizado));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void MostrarPreferencias(View view){
        Intent i = new Intent(this, PreferenciasActivity.class);
        startActivity(i);
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        int id = item.getItemId();
        if(id == R.id.preferences){
            MostrarPreferencias(null);

        }
        return super.onOptionsItemSelected(item);

    }

}