package com.alvaroquispe.interfazasteroides;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    public static ScoreStorage scoreStorage = new ScoreStorageList();
    Button btnScore;
    Button btnSobre;
    Button btnJugar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        btnJugar = findViewById(R.id.btnJugar);
        btnJugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarGameActivity(null);
            }
        });

        //
        btnSobre = findViewById(R.id.btnSobre);
        btnSobre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarViewSobre(null);
            }
        });
        //

        btnScore = findViewById(R.id.btnPuntuacion);
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