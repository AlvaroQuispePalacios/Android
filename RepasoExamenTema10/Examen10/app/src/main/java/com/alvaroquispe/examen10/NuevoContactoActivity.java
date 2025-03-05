package com.alvaroquispe.examen10;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import kotlin.reflect.KFunction;

public class NuevoContactoActivity extends AppCompatActivity {
    Button btnGuardarContacto;
    EditText etNombre, etTelefono, etPoblacion;
    Spinner spPoblacion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_nuevo_contacto);

        btnGuardarContacto = findViewById(R.id.btnGuardarContacto);
        etNombre = findViewById(R.id.etNombre);
        etTelefono = findViewById(R.id.etTelefono);
        etPoblacion = findViewById(R.id.etPoblacion);
        spPoblacion = findViewById(R.id.spPoblacion);

        btnGuardarContacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardarDatosContacto();
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void guardarDatosContacto(){
        if(etNombre.getText().toString().isEmpty() || etTelefono.getText().toString().isEmpty() || etPoblacion.getText().toString().isEmpty()){
            Toast.makeText(this, "Es necesario completar todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        String nombre = etNombre.getText().toString();
        String telefono = etTelefono.getText().toString();
        String poblacion = etPoblacion.getText().toString();
        String datos = nombre + " " + telefono + " " + poblacion;

        // Obtenemos como se quieren guardar los datos
        SharedPreferences preferencias = PreferenceManager.getDefaultSharedPreferences(this);
        String tipoGuardado = preferencias.getString("tipoGuardado", "?");

        switch (tipoGuardado){
            case "1":
                SharedPreferences prefs = this.getSharedPreferences("contactos", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                for (int i = 9; i > 0; i--) {
                    editor.putString("contacto" + i, prefs.getString("contacto" + (i - 1), ""));
                }
                editor.putString("contacto0" , datos);
                editor.commit();
                break;
            case "2":
                try {
                    FileOutputStream fos = this.openFileOutput("contactos", Context.MODE_APPEND);
                    fos.write((datos + "\n").getBytes());
                    fos.close();
                }catch (Exception e){
                    Log.e("Crear Nuevo contacto", "Fichero interno fallo " + e.getMessage(), e);
                }

                break;
            case "3":
                try {
                    File f = new File(this.getExternalFilesDir(null), "contactos.txt");
                    FileOutputStream fos = new FileOutputStream(f, true);
                    fos.write((datos + "\n").getBytes());
                    fos.close();
                }catch (Exception e){
                    Log.e("Crear Nuevo contacto", "Fichero Externo fallo " + e.getMessage(), e);
                }
                break;
            case "4":
                try {
                    File f = new File(this.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), "contactos.txt");
                    FileOutputStream fos = new FileOutputStream(f, true);
                    fos.write((datos + "\n").getBytes());
                    fos.close();
                }catch (Exception e){
                    Log.e("Crear Nuevo contacto", "Fichero en documentos fallo" + e.getMessage(), e);

                }
                break;
            case "5":
                SQLite db = new SQLite(this);
                db.guardarContacto(nombre, telefono, poblacion);
                break;
            default:
                break;
        }
        Toast.makeText(this, datos, Toast.LENGTH_SHORT).show();

        this.finish();

    }
}