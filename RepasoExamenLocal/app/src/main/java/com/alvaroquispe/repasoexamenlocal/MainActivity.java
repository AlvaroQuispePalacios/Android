package com.alvaroquispe.repasoexamenlocal;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements SensorEventListener{
    int contador;
    int estadoContador;
    int maxAudio;
    SensorManager mSensorManager;
    TextView tvContador;
    Button btnMostrarSegundaActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //Si se destruye y vuelve a crear la activity cuando cambia de orientacion mejor recojo el estado del contador en el mismo onCreate
        if(savedInstanceState != null){
            contador = savedInstanceState.getInt("estadoContador");
        }

        tvContador = findViewById(R.id.tvContador);
        btnMostrarSegundaActivity = findViewById(R.id.btnMostrarSegundaActivity);
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor acelerometro = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(this, acelerometro, SensorManager.SENSOR_DELAY_NORMAL);

        btnMostrarSegundaActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MostrarSegundaActivity(null);
            }
        });



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void MostrarSegundaActivity(View view){
        Intent i = new Intent(this, SegundaActivity.class);
        startActivity(i);
    }


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        estadoContador = contador;
        savedInstanceState.putInt("estadoContador", estadoContador);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int precision) {

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){

            if(event.values[1] > 0){
                contador++;
                maxAudio = 0;
            }else{
                if(contador > 0){
                    contador--;
                }
            }

            tvContador.setText("Contador: " + contador);
            if(contador == 0){
                btnMostrarSegundaActivity.setVisibility(View.VISIBLE);
                if(maxAudio == 0){
                    new AudioAsync().execute();
                    maxAudio++;
                }
            }else{
                btnMostrarSegundaActivity.setVisibility(View.INVISIBLE);
            }

        }
    }

    class AudioAsync extends AsyncTask<Void, Void, Void> {
        private MediaPlayer mp;
        @Override protected void onPreExecute() {
            mp = MediaPlayer.create(MainActivity.this, R.raw.success);

        }
        @Override protected Void doInBackground(Void... params) {
            mp.start();
            return null;
        }

        @Override protected void onPostExecute(Void result) {

        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id==R.id.preferences){
            Intent i = new Intent(this, PreferencesActivity.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }
}