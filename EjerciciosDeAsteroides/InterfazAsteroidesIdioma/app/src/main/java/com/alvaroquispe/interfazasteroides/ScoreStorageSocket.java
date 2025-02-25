package com.alvaroquispe.interfazasteroides;

import android.os.StrictMode;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ScoreStorageSocket  implements ScoreStorage{

    public ScoreStorageSocket(MainActivity mainActivity){
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitNetwork().build());
    }

    @Override
    public void storeScore(int score, String name, long date) {
        try {
            Socket sk = new Socket("192.168.1.54", 1234);
            BufferedReader in = new BufferedReader(new InputStreamReader(sk.getInputStream()));
            PrintWriter out = new PrintWriter(new OutputStreamWriter(sk.getOutputStream()), true);
            out.println(score + " " + name);
            String answer = in.readLine();
            if(!answer.equals("OK")){
                Log.e("Asteorides", "Error: respuesta de servidor incorrecta");
            }
            sk.close();

        }catch (Exception e){
            Log.e("Asteroides", e.toString(), e);
        }
    }

    @Override
    public List<String> getScoreList(int maxNo) {
        List<String> result = new ArrayList<String>();
        try {
            Socket sk = new Socket("192.168.1.54", 1234);
            BufferedReader in = new BufferedReader(new InputStreamReader(sk.getInputStream()));
            PrintWriter out = new PrintWriter(new OutputStreamWriter(sk.getOutputStream()), true);
            out.println("SCORES");
            int n = 0;

            String answer;
            do{
                answer = in.readLine();
                if(answer != null){
                    result.add(answer);
                    n++;
                }
            }while (n < maxNo && answer != null);
            sk.close();
        }catch (Exception e){
            Log.e("Asteroides", e.toString(), e);
        }
        return result;
    }
}
