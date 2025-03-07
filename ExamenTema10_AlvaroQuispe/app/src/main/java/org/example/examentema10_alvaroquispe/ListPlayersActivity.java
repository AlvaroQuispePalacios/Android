package org.example.examentema10_alvaroquispe;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ListPlayersActivity extends AppCompatActivity {
    ListView lvJugadores;
    ArrayList<String> jugadores = new ArrayList<>();
    ArrayAdapter<String> adaptador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_players);
        lvJugadores = findViewById(R.id.lvJugadores);
        mostrarJugadores();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void mostrarJugadores(){
        DBHelper db = new DBHelper(this);
        jugadores.addAll(db.listarJugadores());
        adaptador = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, jugadores);
        lvJugadores.setAdapter(adaptador);
    }
}