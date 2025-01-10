package com.alvaroquispe.actividad8_1;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    int REQUEST_CODE = 200;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        verificarPermisos();
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void verificarPermisos(){
        int PermisosSms = ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.SEND_SMS);
        if (PermisosSms == PackageManager.PERMISSION_GRANTED){
//Codi per enviar missatges
            Toast.makeText(this, "Permis per enviar SMS concedit",
                    Toast.LENGTH_SHORT).show();
        } else{
            requestPermissions(new String[] {
                    android.Manifest.permission.SEND_SMS},REQUEST_CODE);
        };
    }
}