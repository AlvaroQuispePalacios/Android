package com.alvaroquispe.examenrepaso;

import android.app.Application;

public class Global extends Application {
    private int contadorVector;
    public int getContadorVector(){
        return  this.contadorVector;
    }

    public void incrementarContadorVector(int numero){
        this.contadorVector += numero;
    }
}
