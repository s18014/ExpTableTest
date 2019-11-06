package com.example.exptabletest;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ExpTableHelper extends SQLiteOpenHelper {
    private static int VERSION = 1;
    private static String TABLE_NAME = "exp";

    public ExpTableHelper(@Nullable Context context) {
        super(context, TABLE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createQuery = "create table exp (" +
                "lv integer primary key," +
                "total_exp integer not null" +
                ");";
        db.execSQL(createQuery);
        initialize(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private void initialize(SQLiteDatabase db) {
        int preExp = 0;
        for (int i = 1; i <= 100; i++) {
            ContentValues values = new ContentValues();
            values.put("lv", i);
            if (i > 1) {
                values.put("total_exp", (i - 2) * 2 + 5 + preExp);
                preExp = (i - 2) * 2 + 5 + preExp;
            } else {
                values.put("total_exp", 0);
            }
            db.insert("exp", null, values);
        }
    }
}
