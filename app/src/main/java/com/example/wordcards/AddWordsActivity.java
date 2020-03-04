package com.example.wordcards;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

public class AddWordsActivity extends AppCompatActivity {
    TextView tWords;
    TextView eWords;
    Button saveBtn;
    SQLiteDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_words);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        tWords = findViewById(R.id.tWords_txt);
        eWords = findViewById(R.id.eWords_txt);
        saveBtn = findViewById(R.id.save_btn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
                System.out.println("başarılı");
            }
        });


        Intent intent = getIntent();
        String info = intent.getStringExtra("info");

        if(info.matches("new")){
            saveBtn.setVisibility(View.VISIBLE);
            saveBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    save();
                    System.out.println("başarılı");
                }
            });
        }else{
            update();
            saveBtn.setVisibility(View.VISIBLE);
            saveBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updateBtn();
                }
            });
        }

    }

    public void save(){

        String tWordsD = tWords.getText().toString();
        String eWordsD = eWords.getText().toString();
         try{
             database = this.openOrCreateDatabase("exerciseWords",MODE_PRIVATE,null);
             database.execSQL("CREATE TABLE IF NOT EXISTS words(id INTEGER PRIMARY KEY AUTOINCREMENT,türkish VARCHAR,english VARCHAR)");

             String sqlString ="INSERT INTO words(türkish,english) VALUES(?,?)";
             SQLiteStatement sqLiteStatement = database.compileStatement(sqlString);
             sqLiteStatement.bindString(1,tWordsD);
             sqLiteStatement.bindString(2,eWordsD);
             sqLiteStatement.execute();



         }catch (Exception e){

         }

finish();
    }
    public void update(){
        Intent intet = getIntent();
        int id = intet.getIntExtra("id",1);
        String tWordsD = tWords.getText().toString();
        String eWordsD = eWords.getText().toString();
        try{
            database = this.openOrCreateDatabase("exerciseWords",MODE_PRIVATE,null);
            Cursor cursor = database.rawQuery("SELECT *FROM words where id = ?",new String[]{String.valueOf(id)});
            int tWordsIx = cursor.getColumnIndex("türkish");
            int eWordsIx = cursor.getColumnIndex("english");
            while (cursor.moveToNext()) {
                tWords.setText(cursor.getString(tWordsIx));
                eWords.setText(cursor.getString(eWordsIx));
            }
        }catch (Exception e){

        }

    }
    public void updateBtn(){
        Intent intet = getIntent();
        int id = intet.getIntExtra("id",1);
        String tWordsD = tWords.getText().toString();
        String eWordsD = eWords.getText().toString();
        try{
            database = this.openOrCreateDatabase("exerciseWords",MODE_PRIVATE,null);
            database.execSQL("CREATE TABLE IF NOT EXISTS words(id INTEGER PRIMARY KEY AUTOINCREMENT,türkish VARCHAR,english VARCHAR)");

            String sqlString ="UPDATE words set türkish = ? , english = ? where id = ?";
            SQLiteStatement sqLiteStatement = database.compileStatement(sqlString);
            sqLiteStatement.bindString(3,String.valueOf(id));
            sqLiteStatement.bindString(1,tWordsD);
            sqLiteStatement.bindString(2,eWordsD);
            sqLiteStatement.execute();



        }catch (Exception e){

        }
        finish();
    }
}
