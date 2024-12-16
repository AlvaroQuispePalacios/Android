package com.alvaroquispe.repasoexamenlocal;

import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
    TextView tvZonaTactil;
    boolean estaEnPlay = true;
    int estadoWindance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_segunda);

        vvWindance = findViewById(R.id.vvWindance);
        tvZonaTactil = findViewById(R.id.tvZonaTactil);

        //Video
        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.windance;
        Uri videoUri = Uri.parse(videoPath);
        vvWindance.setVideoURI(videoUri);
        //Estado guardado
        if(savedInstanceState != null){
            vvWindance.seekTo(savedInstanceState.getInt("estadoVideo"));
        }
        vvWindance.start();
        vvWindance.requestFocus();
        //Tactil
        tvZonaTactil.setOnTouchListener(this);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        int accion = motionEvent.getAction();
        if(accion == MotionEvent.ACTION_DOWN){
            estaEnPlay = !estaEnPlay;
            if(estaEnPlay){
                vvWindance.start();
            }else{
                vvWindance.pause();
            }

        }else if(accion == MotionEvent.ACTION_MOVE){
            vvWindance.pause();
            vvWindance.seekTo(0);
            vvWindance.start();
        }
        return true;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        estadoWindance = vvWindance.getCurrentPosition();
        savedInstanceState.putInt("estadoVideo", estadoWindance);
        super.onSaveInstanceState(savedInstanceState);
    }
}