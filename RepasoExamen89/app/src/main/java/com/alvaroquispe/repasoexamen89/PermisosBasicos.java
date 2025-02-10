package com.alvaroquispe.repasoexamen89;

import android.Manifest;
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

public class PermisosBasicos extends AppCompatActivity {
    /* Este ejemplo sirve para verificar si tengo los permisos en este caso de SMS
    *
    * */
    int REQUEST_CODE = 200;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_permisos_basicos);

        verificarPermisos();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void verificarPermisos() {
        //Miro si tengo los permisos
        int permisosSMS = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);
        // Si tengo los permisos muestro un mensaje
        if(permisosSMS == PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this, "Permisos para enviar SMS concedido", Toast.LENGTH_SHORT).show();

        }else{
            // SI no tengo los permisos los pido
            requestPermissions(new String[] {Manifest.permission.SEND_SMS}, REQUEST_CODE);
        }
    }
}