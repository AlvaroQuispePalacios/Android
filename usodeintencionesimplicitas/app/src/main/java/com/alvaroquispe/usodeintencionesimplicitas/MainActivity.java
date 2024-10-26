package com.alvaroquispe.usodeintencionesimplicitas;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button btnWeb;
    Button btnTelefono;
    Button btnGoogleMaps;
    Button btnfoto;
    Button btnEnviarCorreo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // -------------------------------------------------------

        btnWeb = findViewById(R.id.btnWeb);
        btnTelefono = findViewById(R.id.btnTelefono);
        btnGoogleMaps = findViewById(R.id.btnGoogleMaps);
        btnfoto = findViewById(R.id.btnFoto);
        btnEnviarCorreo = findViewById(R.id.btnEnviarCorreo);

        // -------------------------------------------------------

        btnWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AbrirNavegador();
            }
        });

        btnTelefono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LlamarTelefono();
            }
        });

        btnGoogleMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AbrirGoogleMaps();
            }
        });

        btnfoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HacerFoto();
            }
        });

        btnEnviarCorreo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EnviarCorreo();
            }
        });

        // -------------------------------------------------------
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void AbrirNavegador(){
        Intent abrirN = new Intent(Intent.ACTION_VIEW);
        abrirN.setData(Uri.parse("http://www.iesjoanramis.org/"));
        startActivity(abrirN);
    }

    public void LlamarTelefono(){
        Intent llamar = new Intent(Intent.ACTION_DIAL);
        llamar.setData(Uri.parse("tel:971000000"));
        startActivity(llamar);
    }

    public void AbrirGoogleMaps(){
        Intent googleMaps = new Intent(Intent.ACTION_VIEW);
        googleMaps.setData(Uri.parse("geo:39.887642,4.254319"));
        startActivity(googleMaps);
    }

    public void HacerFoto(){
        Intent foto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivity(foto);
    }

    public void EnviarCorreo(){
        Intent correo = new Intent(Intent.ACTION_SEND);
        correo.setType("text/plain");
        correo.putExtra(Intent.EXTRA_SUBJECT, "asunto");
        correo.putExtra(Intent.EXTRA_TEXT, "texto del correo");
        correo.putExtra(Intent.EXTRA_EMAIL, new String[] {"info@iesjoanramis.org"});
        startActivity(correo);
    }
}