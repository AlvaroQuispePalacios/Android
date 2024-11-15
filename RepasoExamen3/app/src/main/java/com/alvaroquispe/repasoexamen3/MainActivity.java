package com.alvaroquispe.repasoexamen3;

import android.content.Intent;
import android.content.SharedPreferences;
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

public class MainActivity extends AppCompatActivity {
    TextView tvContadorVector;
    TextView tvContadorMapa;
    TextView tvContadorAnimacion;

    Button btnMostrarMapaActivity;
    Button btnMostrarVectorActivity;
    Button btnMostrarAnimacionActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btnMostrarMapaActivity = findViewById(R.id.btnMostrarMapaActivity);
        btnMostrarVectorActivity = findViewById(R.id.btnMostrarVectorActivity);
        btnMostrarAnimacionActivity = findViewById(R.id.btnMostrarAnimacionActivity);

        btnMostrarAnimacionActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MostrarAnimacionActivity(null);
            }
        });

        btnMostrarVectorActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MostrarActivityVector(null);
            }
        });

        btnMostrarMapaActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MostrarMapaActivity(null);
            }
        });

        mostrarPreferencias();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void MostrarAnimacionActivity(View view){
        tvContadorAnimacion = findViewById(R.id.tvContadorAnimacion);
        int contador = Integer.parseInt(tvContadorAnimacion.getText().toString());
        Intent i = new Intent(this, AnimacionActivity.class);
        i.putExtra("contador", contador);
        startActivityForResult(i, 1234);
    }

    @Override protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1234 && resultCode==RESULT_OK) {
            tvContadorAnimacion = findViewById(R.id.tvContadorAnimacion);
            int resultado = data.getIntExtra("resultado", 0);
            tvContadorAnimacion.setText(Integer.toString(resultado));

        }
    }

    public void MostrarActivityVector(View view){
        tvContadorVector = findViewById(R.id.tvContadorVector);
        int contador = Integer.parseInt(tvContadorVector.getText().toString());
        contador++;
        tvContadorVector.setText(Integer.toString(contador));
        Intent i = new Intent(this, VectorActivity.class);
        startActivity(i);
    }

    public void MostrarMapaActivity(View view){
        tvContadorMapa = findViewById(R.id.tvContadorMapa);
        int contador = Integer.parseInt(tvContadorMapa.getText().toString());
        contador++;
        tvContadorMapa.setText(Integer.toString(contador));
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:39.887642,4.254319"));
        startActivity(i);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    public void MostrarPreferencias(View view){
        Intent i = new Intent(this, PreferencesActivity.class);
        startActivity(i);
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id==R.id.preferencias){
            MostrarPreferencias(null);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void mostrarPreferencias(){
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        Boolean modoNoche = pref.getBoolean("modo_noche", true);
        if(modoNoche){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        }
    }
}