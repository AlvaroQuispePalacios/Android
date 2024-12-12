package com.alvaroquispe.examenrepaso;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PantallaTactilActivity extends AppCompatActivity implements View.OnTouchListener {
    TextView tvEntrada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pantalla_tactil);
        tvEntrada = (TextView) findViewById(R.id.tvEntrada);
        tvEntrada.setOnTouchListener(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public boolean onTouch(View view, MotionEvent motionEvent){
        TextView tvSalida = (TextView) findViewById(R.id.tvSalida);
        switch (motionEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                tvSalida.append("Se fue ba abajo :D" + "\n");
                break;
            default:
                tvSalida.append(motionEvent.toString() + "\n");
                break;
        }
        return true;
    }
}