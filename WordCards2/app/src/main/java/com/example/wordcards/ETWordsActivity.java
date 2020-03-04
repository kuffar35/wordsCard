package com.example.wordcards;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class ETWordsActivity extends AppCompatActivity {
    WordsRead wr = new WordsRead();

    TextView textView;
    TextView textView2;
    Button showBtn;
    ArrayList<String> tArray;
    ArrayList<String> eArray;
    ArrayList<Integer> idArray;
    ArrayList<String> wordsArray;

    int id=wr.getId();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etwords);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        textView = (TextView) findViewById(R.id.tWords2);
        textView2 = (TextView) findViewById(R.id.eWords2);
        tArray = new ArrayList<String>();
        eArray = new ArrayList<String>();
        idArray = new ArrayList<Integer>();
        wordsArray = new ArrayList<String>();

        showBtn = (Button) findViewById(R.id.showBtn2);


    }
    public void leftbtn(View view){
        WordsRead wr = new WordsRead();


        if(id > 1){
            id--;
            System.out.println(id);
            geData2();
            showBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getData();
                }
            });
            textView.setText("---");
        }
    }
    public  void rightbtn(View view){
        WordsRead wr = new WordsRead();

//kaç kelime oldugunu bul
        int i=0;
        SQLiteDatabase database = this.openOrCreateDatabase("exerciseWords", MODE_PRIVATE, null);
        Cursor cursor = database.rawQuery("SELECT * FROM words", null);
        int idIx = cursor.getColumnIndex("id");
        while (cursor.moveToNext()) {
            idArray.add(cursor.getInt(idIx));
            i++;
        }

        if(id >= 0 && id<i){
            id++;
            System.out.println(id);
            geData2();
            showBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getData();
                }
            });
            textView.setText("---");
        }
    }
    public void getData(){
        try {
            SQLiteDatabase database = this.openOrCreateDatabase("exerciseWords", MODE_PRIVATE, null);
            Cursor cursor = database.rawQuery("SELECT * FROM words WHERE id = ?",new String[]{String.valueOf(id)});



            int tWordsIx = cursor.getColumnIndex("türkish");
            int eWordsIx = cursor.getColumnIndex("english");
            int idIx = cursor.getColumnIndex("id");
            while (cursor.moveToNext()) {
                System.out.println(cursor.getString(tWordsIx));
                textView.setText(cursor.getString(tWordsIx));


            }


        }catch (Exception e){

        }
    }
    public void geData2(){
        try {
            SQLiteDatabase database = this.openOrCreateDatabase("exerciseWords", MODE_PRIVATE, null);
            Cursor cursor = database.rawQuery("SELECT * FROM words WHERE id = ?",new String[]{String.valueOf(id)});

            int tWordsIx = cursor.getColumnIndex("türkish");
            int eWordsIx = cursor.getColumnIndex("english");
            int idIx = cursor.getColumnIndex("id");
            while (cursor.moveToNext()) {

                textView2.setText(cursor.getString(eWordsIx));

            }


        }catch (Exception e){

        }
    }
}
