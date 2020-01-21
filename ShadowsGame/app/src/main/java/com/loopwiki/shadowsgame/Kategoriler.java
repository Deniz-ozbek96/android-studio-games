package com.loopwiki.shadowsgame;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Kategoriler extends AppCompatActivity {

    Button button,button2;
    Image imageView3,imageView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        button2=(Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intocan = new Intent(Kategoriler.this, IkinciSayfa.class);
                startActivity(intocan);

            }

        });
        button2.setOnClickListener(new View.OnClickListener() {

                                       @Override
                                       public void onClick(View v) {
                                           Intent ıntent = new Intent(Kategoriler.this, MeyveSayfa.class);
                                           startActivity(ıntent); }
                                   }

        );


    }
}