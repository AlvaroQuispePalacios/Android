package com.alvaroquispe.interfazasteroides;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ScoreStorageExternalFile implements ScoreStorage{
    private File file;
    private Context context;

    public ScoreStorageExternalFile(Context context){
        this.context = context;
        this.file = new File(context.getExternalFilesDir(null), "scores.txt");
    }

    @Override
    public void storeScore(int score, String name, long date) {
        String estadoMemoria = Environment.getExternalStorageState();

        if(!estadoMemoria.equals(Environment.MEDIA_MOUNTED)){
            Toast.makeText(context, "No se puedo acceder al almacenamiento", Toast.LENGTH_SHORT).show();
            return;
        }

        // MEDIA_REMOVED para hacer algo si detecta que la memoria externa no esta

        try{
            FileOutputStream f = new FileOutputStream(file, true);
            String text = score + " " + name + "\n";
            f.write(text.getBytes());
            f.close();
        }catch (Exception ex){
            Log.e("Asteroride", ex.getMessage());
        }
    }

    @Override
    public List<String> getScoreList(int maxNo) {
        List<String> result = new ArrayList<String>();
        try {
            // lee el archivo score.txt
            FileInputStream f = new FileInputStream(file);
            BufferedReader inReader = new BufferedReader(new InputStreamReader(f));

            // lee linea a linea
            int n = 0;
            String line;
            do{
                line = inReader.readLine();
                if(line != null){
                    result.add(line);
                    n++;
                }
            }while(n < maxNo && line != null);
            f.close();
        }catch (Exception ex){
            Log.e("Asteroride", ex.getMessage(), ex);

        }
        return result;
    }
}
