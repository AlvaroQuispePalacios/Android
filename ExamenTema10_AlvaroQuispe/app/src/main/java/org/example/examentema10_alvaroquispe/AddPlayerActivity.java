package org.example.examentema10_alvaroquispe;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddPlayerActivity extends AppCompatActivity {
    EditText etNombre, etNickName, etEdad, etPais, etPuntuacion, etJuegoFavorito;
    Button btnGuardar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_player);

        etNombre = findViewById(R.id.etNombre);
        etNickName = findViewById(R.id.etNickName);
        etEdad = findViewById(R.id.etEdad);
        etPais = findViewById(R.id.etPais);
        etPuntuacion = findViewById(R.id.etPuntuacion);
        etJuegoFavorito = findViewById(R.id.etJuegoFavorito);

        btnGuardar = findViewById(R.id.btnGuardar);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarJugador();
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void guardarJugador(){
        String nombre = etNombre.getText().toString();
        String nickname = etNickName.getText().toString();
        String edad = etEdad.getText().toString();
        String pais = etPais.getText().toString();
        String puntuacion = etPuntuacion.getText().toString();
        String juegoFavorito = etJuegoFavorito.getText().toString();

        DBHelper db = new DBHelper(this);
        db.guardarJugador(nombre, nickname, edad, pais, puntuacion, juegoFavorito);

        Toast.makeText(this, "El jugador fue guardado", Toast.LENGTH_SHORT).show();
        this.finish();
    }
}