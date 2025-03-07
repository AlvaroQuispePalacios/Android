package org.example.examentema10_alvaroquispe;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.SimpleCursorAdapter;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "jugadores", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE jugadores(_id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, nickname TEXT, edad TEXT, pais TEXT, puntuacion TEXT, juego_favorito TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void guardarJugador(String nombre, String nickname, String edad, String pais, String puntuacion, String juego_favorito){
        SQLiteDatabase db = getWritableDatabase();
        String query = String.format("INSERT INTO jugadores VALUES(null, '%s', '%s', '%s', '%s', '%s', '%s')", nombre, nickname, edad, pais, puntuacion, juego_favorito);
        db.execSQL(query);
    }

    public ArrayList<String> listarJugadores(){
        ArrayList<String> jugadores = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT nickname, puntuacion FROM jugadores ORDER BY puntuacion DESC", null);
        while(cursor.moveToNext()){
            jugadores.add(cursor.getString(0) + " " + cursor.getString(1));

        }
        cursor.close();
        db.close();
        return jugadores;
    }

    public ArrayList<String> filtrarPorJuego(String nombreJuego){
        ArrayList<String> jugadoresPorJuego = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("SELECT nickname, puntuacion FROM jugadores WHERE juego_favorito = '%s'", nombreJuego);
        Cursor cursor = db.rawQuery(query, null);
        while(cursor.moveToNext()){
            jugadoresPorJuego.add(cursor.getString(0) + " " + cursor.getString(1));

        }
        cursor.close();
        db.close();
        return jugadoresPorJuego;
    }

}
