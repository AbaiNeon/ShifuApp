package com.example.shifuapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listv1 = (ListView)findViewById(R.id.listView1);
        //RecyclerView recv1 = (RecyclerView)findViewById(R.id.recView1);

        final ArrayList<String> list = new ArrayList<String>();
        list.add("Windows");
        list.add("WebOS");
        list.add("Linux");

        final String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
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


}
