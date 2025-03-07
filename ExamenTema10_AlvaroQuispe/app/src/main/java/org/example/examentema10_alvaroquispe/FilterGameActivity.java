package org.example.examentema10_alvaroquispe;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class FilterGameActivity extends AppCompatActivity {
    Button btnBuscar;
    EditText etJuego;

    ListView lvJugadoresFiltro;
    ArrayList<String> jugadores = new ArrayList<>();
    ArrayAdapter<String> adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_filter_game);

        btnBuscar = findViewById(R.id.btnBuscar);
        lvJugadoresFiltro = findViewById(R.id.lvJugadoresFiltro);
        etJuego = findViewById(R.id.etJuego);

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jugadores.clear();
                filtrarPorJuego(etJuego.getText().toString());
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void filtrarPorJuego(String nombreJuego){
        if(nombreJuego.trim().isEmpty()){
            Toast.makeText(this,"No esta el nombre del juego", Toast.LENGTH_SHORT).show();
            return;
        }
        DBHelper db = new DBHelper(this);
        jugadores.addAll(db.filtrarPorJuego(nombreJuego));
        adaptador = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, jugadores);
        lvJugadoresFiltro.setAdapter(adaptador);

    }
}