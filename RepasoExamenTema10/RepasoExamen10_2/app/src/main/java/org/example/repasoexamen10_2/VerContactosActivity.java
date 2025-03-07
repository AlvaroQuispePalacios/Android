package org.example.repasoexamen10_2;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class VerContactosActivity extends AppCompatActivity {
    ListView lvContactos;
    ArrayList<String> listaContactos = new ArrayList<>();
    ArrayAdapter<String> adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ver_contactos);
        lvContactos = findViewById(R.id.lvContactos);

        SharedPreferences prefsDefault = PreferenceManager.getDefaultSharedPreferences(this);
        String tipoGuardado = prefsDefault.getString("tipoGuardado", "");

        cargarContacto(tipoGuardado);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void cargarContacto(String tipoGuardado){
        String nombreFichero = "contactos.txt";
        switch (tipoGuardado){
            case "0":
                SharedPreferences contactos = this.getSharedPreferences("contactos", Context.MODE_PRIVATE);
                for (int i = 1; i <= contactos.getAll().size(); i++) {
                    listaContactos.add(contactos.getString("contacto" + i, ""));
                }
                break;
            case "1":
                try {
                    FileInputStream fis = this.openFileInput(nombreFichero);
                    BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                    String linea;
                    while ((linea = br.readLine()) != null){
                        listaContactos.add(linea);
                    }
                    br.close();
                    fis.close();
                }catch (Exception e){
                    Log.e("Contactos", e.getMessage(), e);

                }
                break;
            case "2":
                try {
                    File fichero = new File(this.getExternalFilesDir(null), nombreFichero);
                    FileInputStream fis = new FileInputStream(fichero);
                    BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                    String linea;
                    while ((linea = br.readLine()) != null){
                        listaContactos.add(linea);
                    }
                    br.close();
                    fis.close();
                }catch (Exception e){
                    Log.e("Contactos", e.getMessage(), e);

                }
                break;
            case "3":
                try {
                    File fichero = new File(this.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), nombreFichero);
                    FileInputStream fis = new FileInputStream(fichero);
                    BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                    String linea;
                    while ((linea = br.readLine()) != null){
                        listaContactos.add(linea);
                    }
                    br.close();
                    fis.close();
                }catch (Exception e){
                    Log.e("Contactos", e.getMessage(), e);

                }
                break;
            case "4":
                MySQLite db = new MySQLite(this);
                listaContactos.addAll(db.recuperarContactos());

            default:
                break;
        }
        adaptador = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaContactos);
        lvContactos.setAdapter(adaptador);
    }
}