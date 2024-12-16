package com.alvaroquispe.repasoexamensensores;

import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SegundaActivity extends AppCompatActivity implements View.OnTouchListener {
    VideoView vvWindance;
    TextView tvSensor;
    boolean videoStart = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_segunda);

        vvWindance = (VideoView) findViewById(R.id.vvWindance);
        tvSensor = (TextView) findViewById(R.id.tvSensor);
        tvSensor.setOnTouchListener(this);


        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.windance;
        Uri videoUri = Uri.parse(videoPath);
        vvWindance.setVideoURI(videoUri);

        if(videoStart){
            vvWindance.start();
            vvWindance.requestFocus();
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        int accion = motionEvent.getAction();
        if(accion == MotionEvent.ACTION_DOWN){
            videoStart = !videoStart;
            iniciarPararVideo(videoStart);
        }else if(accion == MotionEvent.ACTION_MOVE){
            vvWindance.pause();
            vvWindance.seekTo(0);

        }

        return true;
    }

    public void iniciarPararVideo(Boolean estadoInicial){
        if(estadoInicial){
            vvWindance.start();
            vvWindance.requestFocus();
        }else{
            vvWindance.pause();
        }
    }
}