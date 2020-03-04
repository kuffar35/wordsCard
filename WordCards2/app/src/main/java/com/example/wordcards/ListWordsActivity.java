package com.example.wordcards;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;



public class ListWordsActivity extends AppCompatActivity {
    Button addbtn;
    ListView listView;
    ArrayList<String> tArray;
    ArrayList<String> eArray;
    ArrayList<Integer> idArray;
    ArrayList<String> wordsArray;
    ArrayAdapter arrayAdapter;
    ArrayAdapter arrayAdapter2;
    TextView wordsNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_words);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        listView= findViewById(R.id.list);
        tArray = new ArrayList<String>();
        eArray = new ArrayList<String>();
        idArray = new ArrayList<Integer>();
        wordsArray = new ArrayList<String>();
        wordsNumber = (TextView) findViewById(R.id.wordsNumber_txt);

         arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,tArray);

         listView.setAdapter((arrayAdapter));
         listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 Intent intent = new Intent(ListWordsActivity.this,AddWordsActivity.class);
                 intent.putExtra("id",idArray.get(position));
                 intent.putExtra("info","old");
                 startActivity(intent);
             }
         });


        getData();

        addbtn = (Button) findViewById(R.id.addBtn);
        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddWordsActivity.class);
                intent.putExtra("info","new");
                startActivity(intent);
            }
        });



    }
    public void getData(){
        int i =0;
        try {
            SQLiteDatabase database = this.openOrCreateDatabase("exerciseWords", MODE_PRIVATE, null);
            Cursor cursor = database.rawQuery("SELECT * FROM words", null);
            int tWordsIx = cursor.getColumnIndex("türkish");
            int eWordsIx = cursor.getColumnIndex("english");
            int idIx = cursor.getColumnIndex("id");
            while (cursor.moveToNext()) {
                tArray.add(cursor.getString(tWordsIx));
                eArray.add(cursor.getString(eWordsIx));
                idArray.add(cursor.getInt(idIx));
                /////
                // Kelime sayısı bulunacak

                i++;

            }
            System.out.println(i);
            wordsNumber.setText("kelime sayısı "+i);
            WordsRead wr = new WordsRead();

            arrayAdapter.notifyDataSetChanged();
            cursor.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
