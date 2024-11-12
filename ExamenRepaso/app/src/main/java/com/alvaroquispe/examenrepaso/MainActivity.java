package com.alvaroquispe.examenrepaso;

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

        btnMostrarAnimacionActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MostrarAnimacionActivity(null);
            }
        });

        btnMostrarVectorActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnMostrarMapaActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
//    public void MostrarVectorActivity(View view){
//        Intent i = new Intent(this, .class);
//        startActivity(i);
//    }
//    public void MostrarAnimacionActivity(View view){
//        Intent i = new Intent(this, AnimacionActivity.class);
//        startActivity(i);
//    }

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