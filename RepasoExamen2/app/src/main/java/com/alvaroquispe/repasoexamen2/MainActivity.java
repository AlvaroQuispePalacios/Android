package com.alvaroquispe.repasoexamen2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity{
    TextView tvContadorMapa;
    TextView tvContadorVector;
    TextView tvTiempoAnimacionMain;

    Button btnMostrarMapa;
    Button btnMostrarActivityVector;
    Button btnMostrarActivityAnimacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // ------------- Variables -----------------
        btnMostrarMapa = findViewById(R.id.btnMostrarMapa);
        btnMostrarActivityVector = findViewById(R.id.btnMostrarActivityVector);
        btnMostrarActivityAnimacion = findViewById(R.id.btnMostrarActivityAnimacion);
        tvContadorMapa= findViewById(R.id.tvContadorMapa);
        tvContadorVector = findViewById(R.id.tvContadorVector);
        tvTiempoAnimacionMain = findViewById(R.id.tvTiempoAnimacionMain);

        //------------ Eventos --------------------
        btnMostrarActivityAnimacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MostrarActivityAnimacion(null);
            }
        });

        btnMostrarActivityVector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MostrarActivityVector(null);
            }
        });

        btnMostrarMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int contadorMapa = Integer.parseInt(tvContadorMapa.getText().toString());
                contadorMapa++;
                tvContadorMapa.setText(Integer.toString(contadorMapa));
                MostrarMapa(null);
            }
        });


        AplicarTema();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


    public void MostrarActivityAnimacion(View view){
        Intent i = new Intent(this, AnimacionActivity.class);
        startActivity(i);
    }

    public void MostrarActivityVector(View view){
        tvContadorVector = findViewById(R.id.tvContadorVector);
        Intent i = new Intent(this, VectorActivity.class);
        i.putExtra("contadorVector", Integer.parseInt(tvContadorVector.getText().toString())) ;
        startActivityForResult(i, 2);

    }

    public void MostrarMapa(View view){
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:39.887642,4.254319"));
        startActivity(i);
    }


    public void MostrarPreferencias(View view){
        Intent i = new Intent(this, PreferenciasActivity.class);
        startActivity(i);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id==R.id.preferencias){
            MostrarPreferencias(null);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void AplicarTema() {
        SharedPreferences obtenerPreferencias = PreferenceManager.getDefaultSharedPreferences(this);
        Boolean modoNoche = obtenerPreferencias.getBoolean("modo_noche", true);
        if (modoNoche) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    @Override protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==2 && resultCode==RESULT_OK) {
            tvContadorVector.setText(Integer.toString(data.getIntExtra("contadorVectorAumentado", 0)));

        }
    }

}