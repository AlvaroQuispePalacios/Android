package com.alvaroquispe.repasoexamensensores2;

import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SegundaActivity extends AppCompatActivity implements View.OnTouchListener {
    VideoView vvWindance;
    TextView tvSensor;
    boolean estaEnPlay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_segunda);



        vvWindance = findViewById(R.id.vvWindance);
        tvSensor = findViewById(R.id.tvSensor);
        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.windance;
        Uri videoUri = Uri.parse(videoPath);
        vvWindance.setVideoURI(videoUri);
        if(savedInstanceState != null){
            vvWindance.seekTo(savedInstanceState.getInt("estado_video"));

        }
        vvWindance.start();
        vvWindance.requestFocus();
        estaEnPlay = true;
        tvSensor.setOnTouchListener(this);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        vvWindance.pause();
        savedInstanceState.putInt("estado_video", vvWindance.getCurrentPosition());
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
            estaEnPlay = !estaEnPlay;
            if(!estaEnPlay){
                vvWindance.pause();
            }else{
                vvWindance.start();
            }
        }else if(motionEvent.getAction() == MotionEvent.ACTION_MOVE){
            vvWindance.pause();
            vvWindance.seekTo(0);
            vvWindance.start();
        }
        return true;
    }
}