package org.example.usarpermis;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button btn;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                veureVideo(null);
            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        verificarPermisos();
    }

    public void veureVideo (View view){
        Intent i = new Intent();
        i.setClassName("com.payperview.serveis",
                "com.payperview.serveis.VeureVideo");
        startActivity(i);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void verificarPermisos(){
        int REQUEST_CODE = 200;
        int PermisosVideo = ContextCompat.checkSelfPermission(this,
                "com.payperview.serveis.VEURE_VIDEOS");
        if (PermisosVideo != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[] {
                    "com.payperview.serveis.VEURE_VIDEOS"},REQUEST_CODE);
        };
    }
}