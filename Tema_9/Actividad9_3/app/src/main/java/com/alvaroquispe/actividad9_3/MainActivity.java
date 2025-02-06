package com.alvaroquispe.actividad9_3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class MainActivity extends AppCompatActivity {
    private EditText entrada;
    public static TextView sortida;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        entrada= (EditText) findViewById(R.id.entrada);
        sortida = (TextView) findViewById(R.id.sortida);
        IntentFilter filter = new IntentFilter(ReceptorOperacio.ACTION_RESP);
        filter.addCategory(Intent.CATEGORY_DEFAULT);
//        this.registerReceiver(new ReceptorOperacio(), filter);
        LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(this);
        lbm.registerReceiver(new ReceptorOperacio(), filter);
    }
    public void calcularOperacio(View view) {
        double n = Double.parseDouble(entrada.getText().toString());
        sortida.append(n +"^2 = ");
        Intent i = new Intent(this, ServeiOperacio.class);
        i.putExtra("numero", n);
        startService(i);
    }
}

class ReceptorOperacio extends BroadcastReceiver{
    public static final String ACTION_RESP = "com.alvaroquispe.actividad9_3.intent.action.RESPUESTA_OPERACION";
    @Override
    public void onReceive(Context context, Intent intent) {
        Double res = intent.getDoubleExtra("resultado", 0.0);
        MainActivity.sortida.append(" " + res);
    }
}