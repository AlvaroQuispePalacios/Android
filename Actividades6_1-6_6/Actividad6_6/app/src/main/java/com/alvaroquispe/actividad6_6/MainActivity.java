package com.alvaroquispe.actividad6_6;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    EditText etNombreFichero;
    TextView tvMensaje;
    Button btnRealizarCopia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        etNombreFichero = findViewById(R.id.etNombreFichero);
        tvMensaje = findViewById(R.id.tvMensaje);
        btnRealizarCopia = findViewById(R.id.btnRealizarCopia);

        btnRealizarCopia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RealizarCopia(etNombreFichero.getText().toString());
            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void RealizarCopia(String nombreArchivo){
        Tarea tarea = new Tarea();
        tarea.execute(nombreArchivo);
    }

    class Tarea extends AsyncTask<String, Integer, String>{
        private ProgressDialog barraDeProgreso;
        @Override
        protected void onPreExecute(){
            barraDeProgreso = new ProgressDialog(MainActivity.this);
            barraDeProgreso.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            barraDeProgreso.setMessage("Realizando la copia de seguridad");
            barraDeProgreso.setCancelable(true);
            barraDeProgreso.setOnCancelListener(new DialogInterface.OnCancelListener()
            {
                @Override public void onCancel(DialogInterface dialog) {
                    Tarea.this.cancel(true);
                }
            });
        }

        @Override protected void onCancelled() {
            etNombreFichero.append("La copia de seguridad ha sido cancelada");
        }

        @Override protected Integer doInBackground(String... n) {
            Random numero = new Random();
            return 1;
        }

        @Override protected void onProgressUpdate(Integer... perc) {
            barraDeProgreso.setProgress(perc[0]);
        }

        @Override protected void onPostExecute(String res) {
            barraDeProgreso.dismiss();
            tvMensaje.append(res);

        }
    }
    }
}