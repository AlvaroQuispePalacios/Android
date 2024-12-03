package com.alvaroquispe.interfazasteroides;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    public static ScoreStorage scoreStorage = new ScoreStorageList();
    TextView textTitulo;
    Button btnConfigurar;
    Button btnScore;
    Button btnSobre;
    Button btnJugar;
    MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

//        mp= MediaPlayer.create(this, R.raw.audio);
//        mp.start();

        btnJugar = findViewById(R.id.btnJugar);
        btnSobre = findViewById(R.id.btnSobre);
        btnScore = findViewById(R.id.btnPuntuacion);
        btnConfigurar = findViewById(R.id.btnConfigurar);
        textTitulo = findViewById(R.id.title);

        Animation giroConZoom = AnimationUtils.loadAnimation(this, R.anim.gir_amb_zoom);
        Animation aparecer = AnimationUtils.loadAnimation(this, R.anim.apareixer);
        Animation desplazar = AnimationUtils.loadAnimation(this, R.anim.despl_dreta);
        Animation aparecerDesplazar = AnimationUtils.loadAnimation(this, R.anim.aparecer_desplazamiento);

        textTitulo.startAnimation(giroConZoom);
        btnJugar.startAnimation(aparecer);
        btnConfigurar.startAnimation(desplazar);
        btnSobre.startAnimation(aparecerDesplazar);
        btnScore.startAnimation(aparecerDesplazar);

        btnJugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarGameActivity(null);
            }
        });

        btnSobre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnSobre.startAnimation(giroConZoom);
                mostrarViewSobre(null);
            }
        });

        btnScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showScores(null);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public void onResume(){
        super.onResume();
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        boolean musica = pref.getBoolean("musica", true);
        mp = MediaPlayer.create(this, R.raw.audio);
        if(musica){
            mp.start();
        }else{
            mp.stop();
        }
    }

    @Override
    public void onStop(){
        super.onStop();
        mp.stop();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    // ---------------------------------------------------------
    public void mostrarGameActivity(View view){
        Intent i = new Intent(this, GameActivity.class);
        startActivity(i);
    }

    public void showScores(View view){
        Intent i = new Intent(this, Scores.class);
        startActivity(i);
    }

    public void mostrarViewSobre(View view){
        Intent i = new Intent(this, Sobre.class);
        startActivity(i);
    }

    public void launchPreferences(View view){
        Intent i = new Intent(this, PreferencesActivity.class);
        startActivity(i);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id==R.id.preferences){
            //arrancar activitat preferències
            launchPreferences(null);
            return true;
        }
        if (id == R.id.about){
            //arrancar activitat sobre...
            mostrarViewSobre(null);
        }
        return super.onOptionsItemSelected(item);
    }

    // -------------------------------------------------------------



}