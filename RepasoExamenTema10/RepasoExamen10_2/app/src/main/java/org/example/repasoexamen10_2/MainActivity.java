package org.example.repasoexamen10_2;

import android.content.Context;
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
            public void onClick(View v) {
                mostrarNuevoContactoActivity(null);
            }
        });

        btnVerContactos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarVerContactosActivity(null);
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
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.preferencias){
            mostrarPreferenciasActivity(null);
        }
        return super.onOptionsItemSelected(item);
    }

    public void mostrarPreferenciasActivity(View view) {
        Intent i = new Intent(this, PreferenciasActivity.class);
        startActivity(i);
    }

    public void mostrarNuevoContactoActivity(View view){
        Intent i = new Intent(this, NuevoContactoActivity.class);
        startActivity(i);
    }

    public void mostrarVerContactosActivity(View view){
        Intent i = new Intent(this, VerContactosActivity.class);
        startActivity(i);
    }



}