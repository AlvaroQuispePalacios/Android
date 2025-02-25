package com.alvaroquispe.interfazasteroides;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ScoreStorageSQLite extends SQLiteOpenHelper implements ScoreStorage {

    public ScoreStorageSQLite(@Nullable Context context) {
        super(context, "scores", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE scores (" + "_id INTEGER PRIMARY KEY AUTOINCREMENT, " + "score INTEGER, name TEXT, date BIGINT" + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    @Override
    public void storeScore(int score, String name, long date) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO scores VALUES ( null, "+ score+", '"+name+"', "+date+")");
        db.close();
    }

    @Override
    public List<String> getScoreList(int maxNo) {
        List<String> result = new ArrayList<String>();
        SQLiteDatabase db = getReadableDatabase();

//        Cursor cursor = db.rawQuery("SELECT score, name FROM " + "scores ORDER BY score DESC LIMIT " + maxNo, null);

//        String[] nombreColumnas = {"score", "name"};
//        Cursor cursor = db.query("scores", nombreColumnas, null, null, null, null,"scores DESC", Integer.toString(maxNo));
        String[] FIELDS = {"score", "name"};
        Cursor cursor=db.query("scores", FIELDS, null, null, null, null, "score DESC", Integer.toString(maxNo));
        while (cursor.moveToNext()){
            result.add(cursor.getInt(0) + " " + cursor.getString(1));
        }
        cursor.close();
        db.close();
        return result;
    }
}
