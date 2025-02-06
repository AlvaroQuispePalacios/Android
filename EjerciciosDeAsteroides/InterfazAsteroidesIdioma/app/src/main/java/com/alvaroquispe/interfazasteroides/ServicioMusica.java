package com.alvaroquispe.interfazasteroides;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class ServicioMusica extends Service {
    MediaPlayer reproductor;

    @Override
    public void onCreate() {
        reproductor = MediaPlayer.create(this, R.raw.audio);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        reproductor.start();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        reproductor.stop();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
