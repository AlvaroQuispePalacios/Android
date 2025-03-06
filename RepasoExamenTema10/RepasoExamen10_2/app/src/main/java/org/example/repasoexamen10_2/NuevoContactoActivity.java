package org.example.repasoexamen10_2;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class NuevoContactoActivity extends AppCompatActivity {
    EditText etNombre, etTelefono, etPoblacion;
    Button btnGuardarContacto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_nuevo_contacto);
        etNombre = findViewById(R.id.etNombre);
        etTelefono = findViewById(R.id.etTelefono);
        etPoblacion = findViewById(R.id.etPoblacion);
        btnGuardarContacto = findViewById(R.id.btnGuardarContacto);

        SharedPreferences prefsDefault = PreferenceManager.getDefaultSharedPreferences(this);
        String tipoDeGuardado = prefsDefault.getString("tipoGuardado", "");

        btnGuardarContacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarContacto(tipoDeGuardado);
            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void guardarContacto(String tipoGuardado){
        String nombre = etNombre.getText().toString();
        String telefono = etTelefono.getText().toString();
        String poblacion = etPoblacion.getText().toString();
        String data = nombre + telefono + poblacion;

        switch (tipoGuardado){
            case "0":
                SharedPreferences prefs = this.getSharedPreferences("contactos", Context.MODE_PRIVATE);
                int numeroContacto = prefs.getAll().size();
                Toast.makeText(this, numeroContacto, Toast.LENGTH_SHORT).show();
                SharedPreferences.Editor editor  = prefs.edit();
//                editor.putString("contacto" + numeroContacto, data);
//                editor.commit();
                break;
            default:

                break;

        }

    }

}