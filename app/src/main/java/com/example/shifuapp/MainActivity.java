package com.example.shifuapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    DBHelper dbHelper;
    String[] values = null;
    ArrayList<String> list = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listv1 = (ListView)findViewById(R.id.listView1);
        //RecyclerView recv1 = (RecyclerView)findViewById(R.id.recView1);
        Button btnAddList = (Button)findViewById(R.id.btnAddList);

        dbHelper = new DBHelper(this);

        btnAddList.setOnClickListener(this);

        list = new ArrayList<String>();
        list.add("Windows");
        list.add("WebOS");
        list.add("Linux");

        values = new String[] { "Android", "iPhone", "WindowsMobile",
                "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
                "Linux", "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux",
                "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux", "OS/2",
                "Android", "iPhone", "WindowsMobile" };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        listv1.setAdapter(adapter);



        listv1.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
                String selectedItem = list.get(position);

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
            case R.id.btnAddList:
                for (int i = 0; i < list.size(); i++){
                    contentValues.put("name", list.get(i));
                    database.insert("Table1",null,contentValues);
                    contentValues.clear();
                }

                Toast toast = Toast.makeText(MainActivity.this, "List added", Toast.LENGTH_LONG);
                //toast.setGravity(Gravity.TOP,0,0);
                toast.show();

                break;

        }

        dbHelper.close();
    }

}
