package com.alvaroquispe.examen10;

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
    Button btnNuevoContacto, btnVerContactos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        btnNuevoContacto = findViewById(R.id.btnNuevoContacto);
        btnVerContactos = findViewById(R.id.btnVerContactos);

        btnNuevoContacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarNuevoContacto(null);
            }
        });

        btnVerContactos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarVerContactos(null);
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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.preferencias){
            Intent i = new Intent(this, PreferenciasActivity.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }

    public void mostrarNuevoContacto(View view){
        Intent i = new Intent(this, NuevoContactoActivity.class);
        startActivity(i);
    }

    public void mostrarVerContactos(View view){
        Intent i = new Intent(this, VerContactosActivity.class);
        startActivity(i);
    }

}