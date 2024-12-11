package com.alvaroquispe.examenrepaso;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    TextView tvNumeroEjecucionesVector;
    TextView tvNumeroEjecucionesMapa;
    TextView tvMensajeBarraProgreso;
    Button btnMostrarAnimacionActivity;
    Button btnMostrarVectorActivity;
    Button btnMostrarMapaActivity;
    Button btnBarraProgreso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btnMostrarAnimacionActivity = findViewById(R.id.btnMostrarAnimacionActivity);
        btnMostrarVectorActivity = findViewById(R.id.btnMostrarVectorActivity);
        btnMostrarMapaActivity = findViewById(R.id.btnMostrarMapaActivity);
        btnBarraProgreso = findViewById(R.id.btnBarraProgreso);
        tvNumeroEjecucionesVector = findViewById(R.id.tvNumeroEjecucionesVector);
        tvNumeroEjecucionesMapa = findViewById(R.id.tvNumeroEjecucionesMapa);
        tvMensajeBarraProgreso = findViewById(R.id.tvMensajeBarraProgreso);

        btnMostrarAnimacionActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MostrarAnimacionActivity(null);
            }
        });

        btnMostrarVectorActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MostrarVectorActivity(null);
            }
        });

        btnMostrarMapaActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int contador = Integer.parseInt(tvNumeroEjecucionesMapa.getText().toString());
                contador++;
                tvNumeroEjecucionesMapa.setText(Integer.toString(contador));
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:39.887642,4.254319"));
                startActivity(intent);
            }
        });

        btnBarraProgreso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BarraProgreso();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void MostrarAnimacionActivity(View view){
        Intent i = new Intent(this, AnimacionActivity.class);
        startActivity(i);
    }
    public void MostrarVectorActivity(View view){
        Intent i = new Intent(this, VectorActivity.class);
        tvNumeroEjecucionesVector = findViewById(R.id.tvNumeroEjecucionesVector);
        i.putExtra("contadorVector", Integer.parseInt(tvNumeroEjecucionesVector.getText().toString()));
        startActivityForResult(i, 2);
    }

    @Override protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==2 && resultCode==RESULT_OK) {
            int contadorAcualizado = data.getIntExtra("contadorVector", 0);
            tvNumeroEjecucionesVector.setText(Integer.toString(contadorAcualizado));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void MostrarPreferencias(View view){
        Intent i = new Intent(this, PreferenciasActivity.class);
        startActivity(i);
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        int id = item.getItemId();
        if(id == R.id.preferences){
            MostrarPreferencias(null);

        }
        return super.onOptionsItemSelected(item);
    }

    public void BarraProgreso(){
        asynTask barraProgreso = new asynTask();
        barraProgreso.execute(2);
    }

    private class asynTask extends AsyncTask<Integer, Integer, Integer> {
        private ProgressDialog barraProgreso;
        @Override
        protected void onPreExecute(){
            barraProgreso = new ProgressDialog(MainActivity.this);
            barraProgreso.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            barraProgreso.setMessage("Calculando");
            barraProgreso.setMax(100);
            barraProgreso.setProgress(0);
            barraProgreso.setCancelable(true);
            barraProgreso.show();
        }

        @Override
        protected Integer doInBackground(Integer... params) {
            for(int i = 1; i <= 10 ; i++){
                SystemClock.sleep(1000);
                publishProgress(i*10);
            }
            return params[0]*params[0];
        }

        @Override
        protected void onProgressUpdate(Integer... var){
            barraProgreso.setProgress(var[0]);
        }

        @Override
        protected void onPostExecute(Integer var){
            tvMensajeBarraProgreso.setText("Calculo terminado " + var);
            barraProgreso.dismiss();
        }

    }


}