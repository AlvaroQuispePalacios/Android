package com.alvaroquispe.repasoexamensensores;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    SensorManager mSensorManager;
    Sensor sensorAcel;
    TextView tvContador;
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
        }else if(event.values[1] < 0){
            if(contador == 0){
                return;
            }
            contador--;

        }
        tvContador.setText("Contador: " + contador);

    }
}