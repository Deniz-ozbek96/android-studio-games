package com.loopwiki.shadowsgame;

import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class MeyveSayfa extends AppCompatActivity {


    ImageView imagee1, imagee2, imagee3, imagee4, imageeMain;
    TextView tev_status;
    Button bu_next;


    //Renkli resimlerin listesi
    Integer[] imagees = {
            R.drawable.fruit_1,
            R.drawable.fruit_2,
            R.drawable.fruit_3,
            R.drawable.fruit_4,
            R.drawable.fruit_5,
            R.drawable.fruit_6,
    };

    //gölgeli resimlerin listesi
    Integer[] imagees_bw = {
            R.drawable.fruit_bw_1,
            R.drawable.fruit_bw_2,
            R.drawable.fruit_bw_3,
            R.drawable.fruit_bw_4,
            R.drawable.fruit_bw_5,
            R.drawable.fruit_bw_6,

    };
    //Tüm resimler için sayılar listesi
    Integer[] imagees_numbers = {0,1,2,3,4,5};
    int turn = 1;
    int correctAnswer =0;
    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sayfameyve);

        imagee1 = (ImageView) findViewById(R.id.imagee1);
        imagee2 = (ImageView) findViewById(R.id.imagee2);
        imagee3 = (ImageView) findViewById(R.id.imagee3);
        imagee4 = (ImageView) findViewById(R.id.imagee4);
        imageeMain = (ImageView) findViewById(R.id.imageeMain);

        tev_status = (TextView) findViewById(R.id.tev_status);

        bu_next = (Button) findViewById(R.id.bu_next);

        //rastgele görüntüleri karıştırır
        Collections.shuffle(Arrays.asList(imagees_numbers));

        //görüntüleri ekrana set eder
        setImages();

        //tıklamalar

        imagee1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //doğru ya da yanlış tıklanma kontrolü
                if(correctAnswer ==1){
                    score++;
                    tev_status.setText("Correct!");
                    bu_next.setVisibility(View.VISIBLE);
                }else{
                    tev_status.setText("Wrong!");
                    bu_next.setVisibility(View.VISIBLE);
                }

                //görüntüleri devre dışı bırakma
                imagee1.setEnabled(false);
                imagee2.setEnabled(false);
                imagee3.setEnabled(false);
                imagee4.setEnabled(false);

            }
        });

        imagee2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //doğru ya da yanlış tıklanma kontrolü
                if(correctAnswer ==2){
                    score++;
                    tev_status.setText("Correct!");
                    bu_next.setVisibility(View.VISIBLE);
                }else{
                    tev_status.setText("Wrong!");
                    bu_next.setVisibility(View.VISIBLE);
                }

                //görüntüleri devre dışı bırakma
                imagee1.setEnabled(false);
                imagee2.setEnabled(false);
                imagee3.setEnabled(false);
                imagee4.setEnabled(false);

            }
        });

        imagee3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //doğru ya da yanlış tıklanma kontrolü
                if(correctAnswer ==3){
                    score++;
                    tev_status.setText("Correct!");
                    bu_next.setVisibility(View.VISIBLE);
                }else{
                    tev_status.setText("Wrong!");
                    bu_next.setVisibility(View.VISIBLE);
                }

                //görüntüleri devre dışı bırakma
                imagee1.setEnabled(false);
                imagee2.setEnabled(false);
                imagee3.setEnabled(false);
                imagee4.setEnabled(false);

            }
        });

        imagee4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //doğru ya da yanlış tıklanma kontrolü
                if(correctAnswer ==4){
                    score++;
                    tev_status.setText("Correct!");
                    bu_next.setVisibility(View.VISIBLE);
                }else{
                    tev_status.setText("Wrong!");
                    bu_next.setVisibility(View.VISIBLE);
                }

                //görüntüleri devre dışı bırakma
                imagee1.setEnabled(false);
                imagee2.setEnabled(false);
                imagee3.setEnabled(false);
                imagee4.setEnabled(false);

            }
        });

        bu_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //her işaretlemede turn birer birer artar 10 olduğu zaman oyun biter ve çıkış yapılır
                turn++;
                if(turn ==4){
                    checkEnd();
                }else {
                    setImages();

                }
            }
        });
    }


    private void setImages(){

        //hangisinin doğru cevap olduğunu belirlenmesi 1-4
        Random ra =new Random();
        correctAnswer = ra.nextInt(4) +1;


        //rastgele yanlış cevaplar üretilmesi
        int wrongAnswer1, wrongAnswer2, wrongAnswer3;

        do{
            wrongAnswer1 = ra.nextInt(6);
        }while (wrongAnswer1 ==imagees_numbers[turn]);

        do{
            wrongAnswer2 = ra.nextInt(6);
        }while (wrongAnswer2 ==imagees_numbers[turn] ||wrongAnswer2==wrongAnswer1);

        do{
            wrongAnswer3 = ra.nextInt(6);
        }while (wrongAnswer3 ==imagees_numbers[turn] ||wrongAnswer3==wrongAnswer2 ||wrongAnswer3==wrongAnswer1);

        //tüm cevaplar için görüntüleri set eder
        switch (correctAnswer){
            case 1:
                imagee1.setImageResource(imagees[imagees_numbers[turn]]);
                imagee2.setImageResource(imagees [wrongAnswer1]);
                imagee3.setImageResource(imagees [wrongAnswer2]);
                imagee4.setImageResource(imagees [wrongAnswer3]);
                break;
            case 2:
                imagee1.setImageResource (imagees [wrongAnswer1]);
                imagee2.setImageResource(imagees[imagees_numbers[turn]]);
                imagee3.setImageResource(imagees [wrongAnswer2]);
                imagee4.setImageResource(imagees [wrongAnswer3]);
                break;
            case 3:
                imagee1.setImageResource(imagees[wrongAnswer1]);
                imagee2.setImageResource(imagees [wrongAnswer2]);
                imagee3.setImageResource(imagees[imagees_numbers[turn]]);
                imagee4.setImageResource(imagees [wrongAnswer3]);
                break;
            case 4:
                imagee1.setImageResource(imagees [wrongAnswer1]);
                imagee2.setImageResource(imagees [wrongAnswer2]);
                imagee3.setImageResource(imagees [wrongAnswer3]);
                imagee4.setImageResource(imagees[imagees_numbers[turn]]);
                break;
        }

        //soru için resim set eder

        imageeMain.setImageResource(imagees_bw[imagees_numbers[turn]]);

        //nulllar
        tev_status.setText("");
        bu_next.setVisibility(View.INVISIBLE);

        imagee1.setEnabled(true);
        imagee2.setEnabled(true);
        imagee3.setEnabled(true);
        imagee4.setEnabled(true);
    }

    private void checkEnd(){
        AlertDialog.Builder alertDialogBuilderr = new AlertDialog.Builder(this);
        alertDialogBuilderr.setCancelable(false);
        alertDialogBuilderr.setMessage("Game Over! Score: " +score);
        alertDialogBuilderr.setPositiveButton("QUİT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //oyundan çıkış
                finish();

            }
        });
        AlertDialog alertDialog = alertDialogBuilderr.create();
        alertDialog.show();
    }

}

