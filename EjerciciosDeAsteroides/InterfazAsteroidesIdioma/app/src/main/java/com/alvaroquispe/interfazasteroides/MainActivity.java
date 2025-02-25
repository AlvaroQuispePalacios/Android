package com.alvaroquispe.interfazasteroides;

import android.content.Intent;
import android.content.IntentFilter;
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
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
    ReceptorBateria rb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        mp = MediaPlayer.create(this, R.raw.audio);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

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

        // ------------ Servicio ----------------
//        startService(new Intent(MainActivity.this, ServicioMusica.class));


        //------------- Receptor ----------------
        rb = new ReceptorBateria();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_BATTERY_LOW);
        this.registerReceiver(rb, filter);


        // ---- Almacenar datos -----
//        scoreStorage = new ScoreStoragePreferences(this);
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        String almacenar = pref.getString("storage", "?");
        switch (almacenar){
            case "0":
                Log.e("MainActivity", "Elegiste array ");
                break;
            case "1":
                scoreStorage = new ScoreStoragePreferences(this);
                break;
            case "2":
                scoreStorage = new ScoreStorageInternalFile(this);
                break;
            case "3":
                scoreStorage = new ScoreStorageExternalFile(this);
                break;
            case "4":
                scoreStorage = new ScoreStorageApplicationFile(this);
                Toast.makeText(this, "Se eligio guardar en documentos", Toast.LENGTH_SHORT).show();
                break;
            case "5":
                scoreStorage = new ScoreStorageSQLite(this);
                Toast.makeText(this, "Se eligio guardar en SQLite", Toast.LENGTH_SHORT).show();
                break;
            case "6":
                scoreStorage = new ScoreStorageSocket(this);
                Toast.makeText(this, "Se eligio guardar mediante Sockets", Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(this, "Algo paso", Toast.LENGTH_SHORT).show();

                // 10.0.2.2
        }
//        scoreStorage = new ScoreStorageInternalFile(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.unregisterReceiver(rb);
        stopService(new Intent(MainActivity.this, ServicioMusica.class));
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        boolean musica = pref.getBoolean("musica", true);
//        mp = MediaPlayer.create(this, R.raw.audio);
        if (musica) {
//            mp.start();
            startService(new Intent(MainActivity.this, ServicioMusica.class));
        } else {
//            mp.stop();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
//        mp.stop();
        stopService(new Intent(MainActivity.this, ServicioMusica.class));

    }

    @Override
    public void onSaveInstanceState(Bundle estadoInicial) {
//        int pos = mp.getCurrentPosition();
//        estadoInicial.putInt("musica", pos);
        super.onSaveInstanceState(estadoInicial);

    }

    @Override
    public void onRestoreInstanceState(Bundle estadoInicial) {
        super.onRestoreInstanceState(estadoInicial);
        if(estadoInicial != null){
//            int pos = estadoInicial.getInt("musica");
//            mp.seekTo(pos);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    // ---------------------------------------------------------
    public void mostrarGameActivity(View view) {
        Intent i = new Intent(this, GameActivity.class);
        // i put extras
//        i.putExtra("score", score);
//        startActivity(i);
        startActivityForResult(i, 1234);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1234 && resultCode==RESULT_OK && data != null){
            int score = data.getExtras().getInt("score");
            String name = "Yo";
            scoreStorage.storeScore(score, name, System.currentTimeMillis());
            showScores(null);

        }
    }

    public void showScores(View view) {
        Intent i = new Intent(this, Scores.class);
        startActivity(i);
    }

    public void mostrarViewSobre(View view) {
        Intent i = new Intent(this, Sobre.class);
        startActivity(i);
    }

    public void launchPreferences(View view) {
        Intent i = new Intent(this, PreferencesActivity.class);
        startActivity(i);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.preferences) {
            //arrancar activitat preferències
            launchPreferences(null);
            return true;
        }
        if (id == R.id.about) {
            //arrancar activitat sobre...
            mostrarViewSobre(null);
        }
        return super.onOptionsItemSelected(item);
    }

    // -------------------------------------------------------------


}