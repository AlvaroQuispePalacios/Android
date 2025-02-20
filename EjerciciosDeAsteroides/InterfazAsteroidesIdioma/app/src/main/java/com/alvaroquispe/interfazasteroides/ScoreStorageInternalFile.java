package com.alvaroquispe.interfazasteroides;

import android.content.Context;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ScoreStorageInternalFile implements ScoreStorage {
    private static String FILE = "scores.txt";
    private Context context;

    public ScoreStorageInternalFile(Context context){
        this.context = context;
    }

    @Override
    public void storeScore(int score, String name, long date) {
        // escribe en el archivo scores.txt
        try{
            FileOutputStream file = context.openFileOutput(FILE, Context.MODE_APPEND);
            String text = score + " " + name + "\n";
            file.write(text.getBytes());
            file.close();
        }catch (Exception ex){
            Log.e("Asteroride", ex.getMessage());
        }
    }

    @Override
    public List<String> getScoreList(int maxNo) {
        List<String> result = new ArrayList<String>();
        try {
            // lee el archivo score.txt
            FileInputStream file = context.openFileInput(FILE);
            BufferedReader inReader = new BufferedReader(new InputStreamReader(file));

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
            file.close();
        }catch (Exception ex){
            Log.e("Asteroride", ex.getMessage(), ex);

        }
        return Collections.emptyList();
    }
}
