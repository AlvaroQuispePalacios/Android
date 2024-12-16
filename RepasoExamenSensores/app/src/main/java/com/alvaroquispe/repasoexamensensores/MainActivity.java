package com.alvaroquispe.repasoexamensensores;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.MediaActionSound;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
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

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    MediaPlayer mp;
    SensorManager mSensorManager;
    Sensor sensorAcel;
    TextView tvContador;
    Button btnMostrarSegundaActivity;
    int maxSonido = 0;
    int contador = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        tvContador = (TextView) findViewById(R.id.tvContador);
        tvContador.setText("Contador: " + contador);
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorAcel = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if(sensorAcel == null){
            Log.d("acelerometro", "No hay acelerometro bro");
        }
        mSensorManager.registerListener(this, sensorAcel, SensorManager.SENSOR_DELAY_NORMAL);

        btnMostrarSegundaActivity = (Button) findViewById(R.id.btnMostrarSegundaActivity);
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

    @Override
    public void onAccuracyChanged(Sensor sensor, int precision) {

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.values[1] > 0){
            contador++;
            maxSonido = 0;
        }else if(event.values[1] < 0){
            if(contador > 0){
                contador--;
            }

        }

        if(contador == 0){
            btnMostrarSegundaActivity.setVisibility(View.VISIBLE);
            if(maxSonido == 0){
                audioAsync as = new audioAsync();
                as.execute();
                maxSonido++;
            }
        }else{
            btnMostrarSegundaActivity.setVisibility(View.INVISIBLE);
        }
        tvContador.setText("Contador: " + contador);

    }

    public void MostrarSegundaActivity(View view){
        Intent i = new Intent(this, SegundaActivity.class);
        startActivity(i);
    }

    public class audioAsync extends AsyncTask<Void, Void, Integer> {
        @Override protected void onPreExecute() {
            mp = MediaPlayer.create(MainActivity.this, R.raw.success);
        }

        @Override protected Integer doInBackground(Void... n) {
            if(mp != null){
                mp.start();
                return 1;
            }
            return 0;

        }

        @Override protected void onProgressUpdate(Void... perc) {

        }

        @Override protected void onPostExecute(Integer res) {

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
