package com.alvaroquispe.examen10;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
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
import java.util.List;

public class VerContactosActivity extends AppCompatActivity {
    ListView listaContactos;
    List<String> mLista = new ArrayList<>();
    ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ver_contactos);
        listaContactos = findViewById(R.id.listaContactos);

        cargarContactos();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void cargarContactos(){
        SharedPreferences preferencias = PreferenceManager.getDefaultSharedPreferences(this);
        String tipoGuardado = preferencias.getString("tipoGuardado", "");

        // Obtener los contactos

        if(tipoGuardado.equals("1")){
            // Recuperar los contactos por preferencias
            SharedPreferences prefs = this.getSharedPreferences("contactos", Context.MODE_PRIVATE);
            for (int i = 0; i < 10; i++) {
                mLista.add(prefs.getString("contacto" + i, ""));

            }
        } else if (tipoGuardado.equals("2")) {
            // Obtener por fichero interno
            try {
                FileInputStream fis = this.openFileInput("contactos");
                BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                String linea;
                while((linea = br.readLine()) != null){
                    mLista.add(linea);
                }
                br.close();
                fis.close();
            }catch (Exception e){

            }
        } else if (tipoGuardado.equals("3")) {
            try {
                File f = new File(this.getExternalFilesDir(null), "contactos.txt");
                FileInputStream fis = new FileInputStream(f);
                BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                String linea;
                while((linea = br.readLine()) != null){
                    mLista.add(linea);
                }
                br.close();
                fis.close();
            }catch (Exception e){

            }
        }else if(tipoGuardado.equals("4")){
            try {
                File f = new File(this.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), "contactos.txt");
                FileInputStream fis = new FileInputStream(f);
                BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                String linea;
                while((linea = br.readLine()) != null){
                    mLista.add(linea);
                }
                br.close();
                fis.close();
            }catch (Exception e){

            }
        }else if(tipoGuardado.equals("5")){
            SQLite db = new SQLite(this);
            ArrayList<String> listaContactos = db.verTodosLosContactos();
            mLista.addAll(listaContactos);
        }

        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mLista);
        listaContactos.setAdapter(mAdapter);

    }
}