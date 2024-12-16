package com.alvaroquispe.repasoexamensensores2;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PersistableBundle;
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
    TextView tvContador;
    Button btnMostrarSegundaActivity;
    int contador;
    int maxAudio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        if(savedInstanceState != null){
            contador = savedInstanceState.getInt("estado_contador");
        }

        tvContador = findViewById(R.id.tvContador);
        btnMostrarSegundaActivity = findViewById(R.id.btnMostrarSegundaActivity);
        SensorManager mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
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
        savedInstanceState.putInt("estado_contador", contador);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            if(sensorEvent.values[1] > 0){
                contador++;
                maxAudio = 0;
            }else if(sensorEvent.values[1] < 3){
                if(contador > 0){
                    contador--;
                }
            }
            tvContador.setText("Contador: " + contador);

            if(contador > 0){
                btnMostrarSegundaActivity.setVisibility(View.INVISIBLE);
            }else{
                btnMostrarSegundaActivity.setVisibility(View.VISIBLE);
                if(maxAudio == 0){
                    new AudioAsync().execute();
                    maxAudio++;
                }

            }
        }
    }



    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    class AudioAsync extends AsyncTask<Void, Void, Void>{
        private MediaPlayer mp;
        protected void onPreExecute(){
            mp = MediaPlayer.create(MainActivity.this, R.raw.success);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mp.start();
            return null;
        }
    }
}