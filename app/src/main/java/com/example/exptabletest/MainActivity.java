package com.example.exptabletest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView list = findViewById(R.id.list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        list.setLayoutManager(layoutManager);
        ExpTableAdapter adapter = new ExpTableAdapter(readExpTable());
        list.setAdapter(adapter);
    }

    public List<String> readExpTable() {
        List<String> expStrings = new ArrayList<String>();
        ExpTableHelper helper = new ExpTableHelper(MainActivity.this);
        SQLiteDatabase db = helper.getReadableDatabase();
        String query = "select * from exp;";
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            Integer lv = cursor.getInt(0);
            Integer totalExp = cursor.getInt(1);
            Integer nextTotalExp = 0;
            if (i + 1 < cursor.getCount()) {
                cursor.moveToPosition(i + 1);
                nextTotalExp = cursor.getInt(1) - totalExp;
                cursor.moveToPosition(i);
            }
            expStrings.add("LV; " + lv + " 累計経験値; " + totalExp + " 次経験値; " + nextTotalExp);
            cursor.moveToNext();
        }
        return expStrings;
    }
}
