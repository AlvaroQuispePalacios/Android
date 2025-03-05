package com.alvaroquispe.examen10;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SQLite extends SQLiteOpenHelper {

    public SQLite(Context context) {
        super(context, "contactos", null ,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE contactos (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nombre TEXT," +
                "telefono TEXT,"+
                "poblacion TEXT"+
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void guardarContacto(String nombre, String telefono, String poblacion){
        SQLiteDatabase db = getWritableDatabase();
        String query = String.format("INSERT INTO contactos VALUES (null, '%s', '%s', '%s')", nombre, telefono, poblacion);
        db.execSQL(query);
        db.close();
    }

    public ArrayList<String> verTodosLosContactos(){
        ArrayList<String> listaContactos = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT nombre, telefono, poblacion FROM contactos", null);
        while(cursor.moveToNext()){
            listaContactos.add(cursor.getString(0) + " " + cursor.getString(1)+ " " + cursor.getString(2));
        }
        cursor.close();
        db.close();
        return listaContactos;
    }
}
