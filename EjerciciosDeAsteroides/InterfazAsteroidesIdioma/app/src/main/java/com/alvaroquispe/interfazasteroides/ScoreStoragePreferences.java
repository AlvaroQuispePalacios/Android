package com.alvaroquispe.interfazasteroides;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ScoreStoragePreferences implements ScoreStorage {
    private static String PREFERENCES = "scores";
    private Context context;

    public ScoreStoragePreferences(Context context){
        this.context = context;
    }

    @Override
    public void storeScore(int score, String name, long date) {
        // Esto va a servir para guardar los puntajes en un archivo de preferencias diferentes
        SharedPreferences prefs = context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("score", score + " " + name);
        editor.commit();
    }

    @Override
    public List<String> getScoreList(int maxNo) {
        List<String> result = new ArrayList<String>();
        SharedPreferences prefs = context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
        String s = prefs.getString("score", "");
        if(!s.isEmpty()){
            result.add(s);
        }
        return result;
    }
}
