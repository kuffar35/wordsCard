package com.example.wordcards;

public class WordsRead {
    private int id=0;
    private int totalWords;

    public int getTotalWords() {
        return totalWords;
    }

    public void setTotalWords(int totalWords) {
        this.totalWords = totalWords;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public WordsRead(int id,int totalWords) {
        this.id = id;
        this.totalWords=totalWords;
    }
    public WordsRead(){

    }


}
