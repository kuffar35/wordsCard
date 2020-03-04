package com.example.wordcards;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.ActionBar;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Typeface tf1;
    TextView Header;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tf1 = Typeface.createFromAsset(getAssets(),"font/BalooBhai.ttf");

        Header = (TextView) findViewById(R.id.Header);
        Header.setTypeface(tf1);

        ListView listView = findViewById(R.id.listview);
        final ArrayList<String> words = new ArrayList<>();
        words.add("türkçe - ingilizce");
        words.add("english-türkish");
        words.add("kelime ekleme");

        ArrayAdapter arrayAdapter =new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_1,words);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (words.get(position).equals("kelime ekleme")) {
                    Intent intent = new Intent(getApplicationContext(), ListWordsActivity.class);
                    startActivity(intent);
                }
                if (words.get(position).equals("türkçe - ingilizce")) {
                    Intent intent = new Intent(getApplicationContext(), TEWordsActivity.class);
                    startActivity(intent);
                }
                if (words.get(position).equals("english-türkish")) {
                    Intent intent = new Intent(getApplicationContext(), ETWordsActivity.class);
                    startActivity(intent);
                }



            }
        });


    }

}

