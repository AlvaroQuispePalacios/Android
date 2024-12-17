package com.alvaroquispe.ultimorepasosensores;

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
    SensorManager msm;
    Sensor giroscopio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        tvContador = findViewById(R.id.tvContador);
        btnMostrarSegundaActivity = findViewById(R.id.btnMostrarSegundaActivity);
        msm = (SensorManager) getSystemService(SENSOR_SERVICE);
        giroscopio =  msm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

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
    protected void onResume() {
        super.onResume();
        msm.registerListener(this, giroscopio, SensorManager.SENSOR_DELAY_UI);

    }

    @Override
    protected void onPause() {
        super.onPause();
        msm.unregisterListener(this);

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
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        contador = savedInstanceState.getInt("estado_contador");
        super.onRestoreInstanceState(savedInstanceState);
    }

    class AudiAsync extends AsyncTask<Void, Void, Void>{
        private MediaPlayer mp;
        @Override
        protected void onPreExecute() {
            mp = MediaPlayer.create(MainActivity.this, R.raw.success);
        }
        @Override
        protected Void doInBackground(Void... voids) {
            mp.start();
            return null;
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            if(sensorEvent.values[2] > 1.0f){
                contador += Math.round(sensorEvent.values[2]);
                maxAudio = 0;
            }else if(sensorEvent.values[2] < -1.0f){
                if(contador > 0){
                    contador += Math.round(sensorEvent.values[2]);
                    if(contador < 0){
                        contador = 0;
                    }
                }
            }

            tvContador.setText("Contador: " + contador);

            if(contador == 0){
                btnMostrarSegundaActivity.setVisibility(View.VISIBLE);
                if(maxAudio == 0){
                    new AudiAsync().execute();
                    maxAudio++;
                }
            }else{
                btnMostrarSegundaActivity.setVisibility(View.INVISIBLE);
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}