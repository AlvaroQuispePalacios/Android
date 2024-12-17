package com.alvaroquispe.examen_tema_6_7_alvaroquispe;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.view.View;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.os.Bundle;
import android.view.View;
//
//@Override
//public void onSensorChanged(SensorEvent sensorEvent) {
//    if(sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
//        if(sensorEvent.values[2] > 1.0f){
//            contador += Math.round(sensorEvent.values[2]);
//            maxAudio = 0;
//        }else if(sensorEvent.values[2] < -1.0f){
//            if(contador > 0){
//                contador += Math.round(sensorEvent.values[2]);
//                if(contador < 0){
//                    contador = 0;
//                }
//            }
//        }
//        tvContador.setText("Contador: " + contador);
//        if(contador == 0){
//            btnMostrarSegundaActivity.setVisibility(View.VISIBLE);
//            if(maxAudio == 0){
//                new AudiAsync().execute();
//                maxAudio++;
//            }
//        }else{
//            btnMostrarSegundaActivity.setVisibility(View.INVISIBLE);
//        }
//    }
//}

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}