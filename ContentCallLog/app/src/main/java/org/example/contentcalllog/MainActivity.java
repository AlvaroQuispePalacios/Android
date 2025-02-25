package org.example.contentcalllog;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DateFormat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] TIPUS_CRIDADA = {"","entrant","sortint","perduda"};

        TextView salida = (TextView) findViewById(R.id.sortida);
        Uri cridades = Uri.parse("content://call_log/calls");

        String[] projeccio = new String[] {CallLog.Calls.DATE, CallLog.Calls.DURATION, CallLog.Calls.NUMBER, CallLog.Calls.TYPE };
        String[] argsSelecc = new String[] {"1"};
        Cursor c = managedQuery(
                trucades, // Uri del ContentProvider
                projecció, // Columnes que ens interessen
                "type = ?", // consulta WHERE
                argsSelecc, // paràmetres de la consulta anterior
                "date DESC"
        ); // Ordenat per data, ordre ascendent
        while (c.moveToNext()) {
            salida.append("\n"+ DateFormat.format("dd/MM/yy k:mm (", c.getLong(c.getColumnIndex(CallLog.Calls.DATE)))+c.getString(c.getColumnIndex(CallLog.Calls.DURATION)) + ") " + c.getString(c.getColumnIndex(CallLog.Calls.NUMBER)) + ", " + TIPUS_CRIDADA[Integer.parseInt(c.getString(c.getColumnIndex(CallLog.Calls.TYPE)))]);
        }
    }
}
