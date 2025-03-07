package org.example.examentema10_alvaroquispe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button btnMostrarAddPlayerActivity, btnMostrarListPlayersActivity, btnMostrarFilterGameActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btnMostrarAddPlayerActivity = findViewById(R.id.btnMostrarAddPlayerActivity);
        btnMostrarListPlayersActivity = findViewById(R.id.btnMostrarListPlayersActivity);
        btnMostrarFilterGameActivity = findViewById(R.id.btnMostrarFilterGameActivity);


        btnMostrarAddPlayerActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MostrarAddPlayerActivity(null);
            }
        });

        btnMostrarListPlayersActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MostrarListPlayersActivity(null);
            }
        });

        btnMostrarFilterGameActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MostrarFilterGameActivity(null);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void MostrarAddPlayerActivity(View view){
        Intent i = new Intent(this, AddPlayerActivity.class);
        startActivity(i);
    }

    public void MostrarListPlayersActivity(View view){
        Intent i = new Intent(this, ListPlayersActivity.class);
        startActivity(i);
    }

    public void MostrarFilterGameActivity(View view){
        Intent i = new Intent(this, FilterGameActivity.class);
        startActivity(i);
    }
}