package com.engappsados.engappsadosapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import java.net.*;
import java.io.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Rewards extends AppCompatActivity {
    private String url = "http://www.relato.gt/blogs/hackean-a-hbo-otra-vez-y-se-filtra-el-episodio-4-de-la-temporada-7-de-game-of-thrones";
    private String tituloURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rewards);
        EditText edit = (EditText)findViewById(R.id.recompensasEdit);
        edit.setText(getTituloURL());
        //edit.setText("orale");
    }
    public void getURLInfo() throws IOException {
        URL elUrl = new URL(url);
        String elhost = elUrl.getHost();
        Document doc = Jsoup.connect(url).get();
        String title = doc.title();
        this.tituloURL = title;
    }
    public String getTituloURL(){
        return tituloURL;
    }


}
