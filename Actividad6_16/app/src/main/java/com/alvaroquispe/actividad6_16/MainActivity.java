package com.alvaroquispe.actividad6_16;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private List<Sensor> llistaSensors;
    private TextView aTextView[][] = new TextView[20][6];
    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout arrel = (LinearLayout) findViewById(R.id.arrel);
        SensorManager sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        llistaSensors = sm.getSensorList(Sensor.TYPE_ALL);
        int n = 0;
        for (Sensor sensor : llistaSensors) {
            if( n >= 20) break;
            TextView mTextView = new TextView(this);
            mTextView.setText(sensor.getName());
            arrel.addView(mTextView);
            LinearLayout nLinearLayout = new LinearLayout(this);
            arrel.addView(nLinearLayout);
            for (int i = 0; i < 6; i++) {
                aTextView[n][i] = new TextView(this);
                aTextView[n][i].setText("?");
                aTextView[n][i].setWidth(87);

            }
            TextView xTextView = new TextView(this);
            xTextView.setText(" X: ");
            nLinearLayout.addView(xTextView);
            nLinearLayout.addView(aTextView[n][0]);
            TextView yTextView = new TextView(this);
            yTextView.setText(" Y: ");
            nLinearLayout.addView(yTextView);
            nLinearLayout.addView(aTextView[n][1]);
            TextView zTextView = new TextView(this);
            zTextView.setText(" Z: ");
            nLinearLayout.addView(zTextView);
            nLinearLayout.addView(aTextView[n][2]);
            sm.registerListener(this, sensor,
                    SensorManager.SENSOR_DELAY_UI);
            n++;
        }
    }
    @Override public void onAccuracyChanged(Sensor sensor, int
            accuracy) {}

    @Override public void onSensorChanged(SensorEvent event) {
        synchronized (this) {
            int n = 0;
            for (Sensor sensor: llistaSensors) {
                if (event.sensor == sensor) {
                    for (int i=0; i<event.values.length; i++) {
                        aTextView[n][i].setText(Float.toString(event.values[i]));
                    }
                }
                n++;
            }
        }
    }
}