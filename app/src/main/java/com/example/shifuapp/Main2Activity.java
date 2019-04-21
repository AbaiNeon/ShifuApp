package com.example.shifuapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    DBHelper dbHelper;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tv = (TextView)findViewById(R.id.textView);
        String var = getIntent().getStringExtra("text");

        dbHelper = new DBHelper(this);
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        String query = "SELECT * FROM Addresses WHERE name='" + var + "'";
        Cursor cursor = database.rawQuery(query, null);

        int idIndex = cursor.getColumnIndex("ID");
        int nameIndex = cursor.getColumnIndex("name");
        int addressIndex = cursor.getColumnIndex("address");

        String res = null;
        if (cursor.moveToFirst()){
            res = "[" + cursor.getInt(idIndex) + ", " + cursor.getString(nameIndex) + ", " + cursor.getString(addressIndex) + "], ";
        }

        tv.setText(res);
    }
}
