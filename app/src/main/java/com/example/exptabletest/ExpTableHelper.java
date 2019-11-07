package com.example.exptabletest;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ExpTableHelper extends SQLiteOpenHelper {
    // データベースの情報
    private static final int VERSION = 1;
    private static final String TABLE_NAME = "exp";
    private static final String DB_NAME = "test.db";

    // テーブルに使う定数
    private static final int MAX_LEVEL = 1000;

    public ExpTableHelper(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createQuery = "create table " + TABLE_NAME + " (" +
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
        // 経験値テーブルの作成
        // 必要経験値が初期値の5から2ずつ増えていく
        // lv1の必要経験値0で、lv2の必要経験値は初期値の5なので
        // (lv - 2) * 2 + 5 + 前の必要経験値
        // 5 -> 12 -> 21 -> 32

        int preExp = 0;
        for (int i = 1; i <= MAX_LEVEL; i++) {
            ContentValues values = new ContentValues();
            values.put("lv", i);

            if (i > 1) {
                int exp = (i - 2) * 2 + 5 + preExp;
                values.put("total_exp", exp);
                preExp = exp;
            } else {
                values.put("total_exp", 0);
            }

            db.insert("exp", null, values);
        }
    }
}
