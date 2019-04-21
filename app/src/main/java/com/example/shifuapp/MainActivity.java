package com.example.shifuapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnAddList;
    Button btnRead;
    Button btnClear;
    TextView textView;
    DBHelper dbHelper;

    ArrayList<String> names = null;
    ArrayList<String> addresses = null;
    ListView listv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listv1 = (ListView)findViewById(R.id.listView1);
        //RecyclerView recv1 = (RecyclerView)findViewById(R.id.recView1);
        btnAddList = (Button)findViewById(R.id.btnAddList);
        btnRead = (Button)findViewById(R.id.btnRead);
        btnClear = (Button)findViewById(R.id.btnClear);
        textView = (TextView)findViewById(R.id.textView);

        dbHelper = new DBHelper(this);

        btnAddList.setOnClickListener(this);
        btnRead.setOnClickListener(this);
        btnClear.setOnClickListener(this);

        //создание массивов
        names = new ArrayList<String>();
        addresses = new ArrayList<String>();
        names.add("Name1");
        names.add("Name2");
        names.add("Name3");
        addresses.add("Address1");
        addresses.add("Address2");
        addresses.add("Address3");



        listv1.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
                String selectedItem = names.get(position);

                Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
                intent.putExtra("text", selectedItem);
                startActivity(intent);
            }
        });

    }

    @Override
    public  void onClick(View v){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        switch (v.getId())
        {
            //из массива в БД
            case R.id.btnAddList:
                for (int i = 0; i < names.size(); i++){
                    contentValues.put("name", names.get(i));
                    contentValues.put("address", addresses.get(i));

                    database.insert("Addresses", null, contentValues);
                    contentValues.clear();
                }

                Toast.makeText(MainActivity.this, "List added", Toast.LENGTH_SHORT).show();
                break;

            //из БД в ListView
            case R.id.btnRead:
                String query = "SELECT * FROM Addresses";
                Cursor cursor = database.rawQuery(query, null);

                if (cursor.moveToFirst()){
                    Toast.makeText(MainActivity.this, "cursor not empty", Toast.LENGTH_SHORT).show();

                    int idIndex = cursor.getColumnIndex("ID");
                    int nameIndex = cursor.getColumnIndex("name");
                    int addressIndex = cursor.getColumnIndex("address");

                    ArrayList<String> tmp = new ArrayList<String>();
                    do {
                        tmp.add(cursor.getString(nameIndex));

                    } while (cursor.moveToNext());

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tmp);
                    listv1.setAdapter(adapter);

                    cursor.moveToFirst();
                    String res = "";
                    do {
                        res = res + "[" + cursor.getInt(idIndex) + ", " + cursor.getString(nameIndex) + ", " + cursor.getString(addressIndex) + "], ";
                    }while (cursor.moveToNext());
                    textView.setText(res);
                }else   {
                    Toast toast2 = Toast.makeText(MainActivity.this, "cursor empty", Toast.LENGTH_LONG);
                    toast2.show();
                }

                cursor.close();
                break;
            case R.id.btnClear:
                database.delete("Addresses",null,null);
                listv1.setAdapter(null);
                textView.setText("");

                break;
        }

        dbHelper.close();
    }

}
