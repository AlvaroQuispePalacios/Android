package org.example.contentcalllog;

import android.Manifest;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.CallLog;
import android.telecom.Call;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView salida = (TextView) findViewById(R.id.sortida);

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_CALL_LOG) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED){
            // Request permissions
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CALL_LOG, Manifest.permission.WRITE_CALL_LOG}, 100);

        }else{
            String[] tipos_llamada = {"","entrant","sortint","perduda"};
            Uri llamadaUri = CallLog.Calls.CONTENT_URI;

            // Escribir en un content provider
            ContentValues valores = new ContentValues();
            valores.put(CallLog.Calls.DATE, new Date().getTime());
            valores.put(CallLog.Calls.DURATION, "55");
            valores.put(CallLog.Calls.NUMBER, "555555555");
            valores.put(CallLog.Calls.TYPE, CallLog.Calls.INCOMING_TYPE);
            Uri nuevoElemento = getContentResolver().insert(CallLog.Calls.CONTENT_URI, valores);


            // eliminar un elemento creado en el contentProvider
//            int filasEliminadas = getContentResolver().delete(CallLog.Calls.CONTENT_URI, "number = ?", new String[]{"555555555"});
//            Log.e("Filas Eliminadas", "Se ha eliminado" + filasEliminadas, null);


            // Modificar Columnas
            ContentValues valoresModificacion = new ContentValues();
            valoresModificacion.put(CallLog.Calls.NUMBER, "444444444");
            int filasModificadas = getContentResolver().update(CallLog.Calls.CONTENT_URI, valoresModificacion, "number='555555555'", null);

            String[] columnas = new String[]{CallLog.Calls.DATE, CallLog.Calls.DURATION, CallLog.Calls.NUMBER, CallLog.Calls.TYPE};
            String[] parametros = new String[]{"444444444"};
            Cursor c = getContentResolver().query(
                    llamadaUri, // Uri del ContentProvider
                    columnas, // Columnes que ens interessen
                    "number = ?", // consulta WHERE
                    parametros, // paràmetres de la consulta anterior
                    "date DESC"
            ); // Ordenat per data, ordre ascendent

            while (c.moveToNext()) {
                long fecha = c.getLong(c.getColumnIndexOrThrow(CallLog.Calls.DATE));
                String fechaFormateada = DateFormat.getDateTimeInstance().format(new Date(fecha));

                String duracion = c.getString(c.getColumnIndexOrThrow(CallLog.Calls.DURATION));
                String numero = c.getString(c.getColumnIndexOrThrow(CallLog.Calls.NUMBER));

                int tipo = c.getInt(c.getColumnIndexOrThrow(CallLog.Calls.TYPE));
                String tipoDeLaLlamada = (tipo >= 1 && tipo <= 3) ? tipos_llamada[tipo] : "Desconocido";

                salida.append("\n" + fechaFormateada + "\n" + duracion + "\n" + numero + "\n" + tipoDeLaLlamada);

            }
            c.close();
        }


    }
}
