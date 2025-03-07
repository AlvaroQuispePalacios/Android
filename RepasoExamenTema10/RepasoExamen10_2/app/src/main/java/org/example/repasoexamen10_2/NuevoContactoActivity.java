package org.example.repasoexamen10_2;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;
import java.io.FileOutputStream;

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
        String nombreFichero = "contactos.txt";
        String nombre = etNombre.getText().toString();
        String telefono = etTelefono.getText().toString();
        String poblacion = etPoblacion.getText().toString();
        String data = nombre + " " + telefono + " " + poblacion;

        switch (tipoGuardado){
            case "0":
                SharedPreferences prefs = this.getSharedPreferences("contactos", Context.MODE_PRIVATE);
                int numeroContacto = prefs.getAll().size() + 1;
                Toast.makeText(this, "Numero del contacto: " + numeroContacto, Toast.LENGTH_SHORT).show();
                SharedPreferences.Editor editor  = prefs.edit();
                editor.putString("contacto" + numeroContacto, data);
                editor.commit();
                break;
            case "1":
                try {
                    FileOutputStream file = this.openFileOutput(nombreFichero, Context.MODE_APPEND);
                    file.write((data + "\n").getBytes());
                    file.close();
                }catch (Exception e){
                    Log.e("Contactos", e.getMessage(), e);
                }

                break;
            case "2":
                try {
                    File archivo = new File(this.getExternalFilesDir(null), nombreFichero);
                    FileOutputStream fos = new FileOutputStream(archivo, true);
                    fos.write((data + "\n").getBytes());
                    fos.close();
                }catch (Exception e){
                    Log.e("Contactos", e.getMessage(), e);

                }
                break;
            case "3":
                try {
                    File archivo = new File(this.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), nombreFichero);
                    FileOutputStream fos = new FileOutputStream(archivo, true);
                    fos.write((data + "\n").getBytes());
                    fos.close();
                }catch (Exception e){
                    Log.e("Contactos", e.getMessage(), e);

                }
                break;
            case "4":
                MySQLite db = new MySQLite(this);
                db.guardarContacto(nombre, telefono, poblacion);

            default:

                break;

        }
        Toast.makeText(this, "Contacto: " + data, Toast.LENGTH_SHORT).show();
        this.finish();

    }

}