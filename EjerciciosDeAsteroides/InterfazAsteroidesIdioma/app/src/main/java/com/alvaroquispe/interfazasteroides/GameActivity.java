package com.alvaroquispe.interfazasteroides;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class GameActivity extends AppCompatActivity {
    private AsteroidsView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        gameView = (AsteroidsView) findViewById(R.id.AsteroidsView);
        gameView.setParent(this);
    }

    @Override protected void onPause() {
        super.onPause();
        gameView.getThread().pause();
    }

    @Override protected void onResume() {
        super.onResume();
        gameView.getThread().unpause();
    }

    @Override protected void onDestroy() {
        gameView.getThread().halt();
        gameView.deactivateSensors();
        super.onDestroy();
    }


}