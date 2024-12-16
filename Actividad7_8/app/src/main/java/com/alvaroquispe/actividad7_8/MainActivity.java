package com.alvaroquispe.actividad7_8;

import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_PERMISSIONS = 100;
    private static final String LOG_TAG = "Gravadora";
    private MediaRecorder mediaRecorder;
    private MediaPlayer mediaPlayer;
    private static String fitxer = Environment.getExternalStorageDirectory().getAbsolutePath()+"/audio.3gp";

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


//    Se tiene que verificar los permisos

//    private void requestPermissions() {
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED ||
//                ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//
//            ActivityCompat.requestPermissions(this, new String[]{
//                    Manifest.permission.RECORD_AUDIO,
//                    Manifest.permission.WRITE_EXTERNAL_STORAGE
//            }, REQUEST_PERMISSIONS);
//        }
//    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSIONS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permisos concedidos", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permisos denegados", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void gravar(View view) {
        try {
            //Crea un nou objecte MediaRecorder
            mediaRecorder = new MediaRecorder();
            //Estableix el fitxer declarat a l’inici com a fitxer de sortida
            mediaRecorder.setOutputFile(fitxer);
            //Estableix el micròfon com a font d'entrada d'àudio
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            //Estableix el format de sortida com a THREE_GPP
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            //Estableix el codificador com a AMR_NB
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            //Prepara i inicia la gravació
            mediaRecorder.prepare();
            mediaRecorder.start();

            Log.d(LOG_TAG,"Grabando audio");
        }catch (Exception e){
            Log.d(LOG_TAG,e.getMessage());

        }

    }

    public void aturarGravacio(View view) {
        //Atura la gravació i allibera el recurs
        if(mediaRecorder != null){
            mediaRecorder.stop();
            mediaRecorder.release();
            mediaRecorder = null;
        }

    }

    public void reproduir(View view) {
        //Crea un nou objecte MediaPlayer
        mediaPlayer = new MediaPlayer();
        //Estableix la font com el fitxer que acabes de gravar
        try{
            mediaPlayer.setDataSource(fitxer);
            mediaPlayer.prepare();
            mediaPlayer.start();
        }catch (Exception ex){

        }



    }
}