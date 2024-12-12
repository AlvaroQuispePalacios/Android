package com.alvaroquispe.examenrepaso;

import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Teclas extends AppCompatActivity {
    TextView tvTecla;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_teclas);

        tvTecla = (TextView) findViewById(R.id.tvTecla);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public boolean onKeyDown(int keyCode, KeyEvent event){
        super.onKeyDown(keyCode, event);
        boolean processed = true;
        switch (keyCode){
            case KeyEvent.KEYCODE_DPAD_UP:
                tvTecla.append("flecha arriba");
                break;
            case KeyEvent.KEYCODE_DPAD_DOWN:
                tvTecla.append("flecha abajo");
                break;
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                tvTecla.append("flecha derecha");
                break;
            case KeyEvent.KEYCODE_DPAD_LEFT:
                tvTecla.append("flecha izquierda");
                break;
            default:
                processed = false;
                break;
        }
        return processed;
    }
}