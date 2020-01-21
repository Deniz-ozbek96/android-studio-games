package com.loopwiki.shadowsgame;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class IkinciSayfa extends AppCompatActivity {

    ImageView image1, image2, image3, image4, imageMain;
    TextView tv_status;
    Button b_next;

    //Renkli resimlerin listesi
    Integer[] images = {
            R.drawable.animal_1,
            R.drawable.animal_2,
            R.drawable.animal_3,
            R.drawable.animal_4,
            R.drawable.animal_5,
            R.drawable.animal_6,
            R.drawable.animal_7,
            R.drawable.animal_8,
            R.drawable.animal_9,
            R.drawable.animal_10,
            R.drawable.animal_11,
            R.drawable.animal_12,
            R.drawable.animal_13,
            R.drawable.animal_14,
            R.drawable.animal_15,
            R.drawable.animal_16,
            R.drawable.animal_17,
            R.drawable.animal_18,
            R.drawable.animal_19,
            R.drawable.animal_20,
            R.drawable.animal_21,
            R.drawable.animal_22,

    };

    //gölgeli resimlerin listesi
    Integer[] images_bw = {
            R.drawable.animal_bw_1,
            R.drawable.animal_bw_2,
            R.drawable.animal_bw_3,
            R.drawable.animal_bw_4,
            R.drawable.animal_bw_5,
            R.drawable.animal_bw_6,
            R.drawable.animal_bw_7,
            R.drawable.animal_bw_8,
            R.drawable.animal_bw_9,
            R.drawable.animal_bw_10,
            R.drawable.animal_bw_11,
            R.drawable.animal_bw_12,
            R.drawable.animal_bw_13,
            R.drawable.animal_bw_14,
            R.drawable.animal_bw_15,
            R.drawable.animal_bw_16,
            R.drawable.animal_bw_17,
            R.drawable.animal_bw_18,
            R.drawable.animal_bw_19,
            R.drawable.animal_bw_20,
            R.drawable.animal_bw_21,
            R.drawable.animal_bw_22,

    };
    //Tüm resimler için sayılar listesi
    Integer[] images_numbers = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21};
    int turn = 1;
    int correctAnswer =0;
    int score = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sayfaa);

        image1 = (ImageView) findViewById(R.id.image1);
        image2 = (ImageView) findViewById(R.id.image2);
        image3 = (ImageView) findViewById(R.id.image3);
        image4 = (ImageView) findViewById(R.id.image4);
        imageMain = (ImageView) findViewById(R.id.imageMain);

        tv_status = (TextView) findViewById(R.id.tv_status);

        b_next = (Button) findViewById(R.id.b_next);

        //rastgele görüntüleri karıştırır
        Collections.shuffle(Arrays.asList(images_numbers));

        //görüntüleri ekrana set eder
        setImages();

        //tıklamalar

        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //doğru ya da yanlış tıklanma kontrolü
                if(correctAnswer ==1){
                    score++;
                    tv_status.setText("Correct!");
                    b_next.setVisibility(View.VISIBLE);
                }else{
                    tv_status.setText("Wrong!");
                    b_next.setVisibility(View.VISIBLE);
                }

                //görüntülerin tıklanmasını devre dışı bırakma
                image1.setEnabled(false);
                image2.setEnabled(false);
                image3.setEnabled(false);
                image4.setEnabled(false);

            }
        });

        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //doğru ya da yanlış tıklanma kontrolü
                if(correctAnswer ==2){
                    score++;
                    tv_status.setText("Correct!");
                    b_next.setVisibility(View.VISIBLE);
                }else{
                    tv_status.setText("Wrong!");
                    b_next.setVisibility(View.VISIBLE);
                }

                //görüntülerin tıklanmasını devre dışı bırakma
                image1.setEnabled(false);
                image2.setEnabled(false);
                image3.setEnabled(false);
                image4.setEnabled(false);

            }
        });

        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //doğru ya da yanlış tıklanma kontrolü
                if(correctAnswer ==3){
                    score++;
                    tv_status.setText("Correct!");
                    b_next.setVisibility(View.VISIBLE);
                }else{
                    tv_status.setText("Wrong!");
                    b_next.setVisibility(View.VISIBLE);
                }

                //görüntülerin tıklanmasını devre dışı bırakma
                image1.setEnabled(false);
                image2.setEnabled(false);
                image3.setEnabled(false);
                image4.setEnabled(false);

            }
        });

        image4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //doğru ya da yanlış tıklanma kontrolü
                if(correctAnswer ==4){
                    score++;
                    tv_status.setText("Correct!");
                    b_next.setVisibility(View.VISIBLE);
                }else{
                    tv_status.setText("Wrong!");
                    b_next.setVisibility(View.VISIBLE);
                }

                //görüntülerin tıklanmasını devre dışı bırakma
                image1.setEnabled(false);
                image2.setEnabled(false);
                image3.setEnabled(false);
                image4.setEnabled(false);

            }
        });

        b_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //her işaretlemede turn birer birer artar 10 olduğu zaman oyun biter ve çıkış yapılır
                turn++;
                if(turn ==10){
                    checkEnd();
                }else {
                    setImages();

                }
            }
        });
    }


    private void setImages(){

        //hangisinin doğru cevap olduğunu belirlenmesi 1-4 (1 tane cevap 4 tane tahmin için)
        Random r =new Random();
        correctAnswer = r.nextInt(4) +1; //doğru cevaba random seçilenlerden birini atar


        //rastgele yanlış cevaplar üretilmesi
        int wrongAnswer1, wrongAnswer2, wrongAnswer3;

        do{
            wrongAnswer1 = r.nextInt(22);
        }while (wrongAnswer1 ==images_numbers[turn]); //yanlış cevap doğru(dönen resmin numarasına) resme eşit olamaz

        do{
            wrongAnswer2 = r.nextInt(22);
        }while (wrongAnswer2 ==images_numbers[turn] ||wrongAnswer2==wrongAnswer1); //yanlış cevap seçeneği birinci yanlış cevap seçeneğiyle aynı olamaz

        do{
            wrongAnswer3 = r.nextInt(22);
        }while (wrongAnswer3 ==images_numbers[turn] ||wrongAnswer3==wrongAnswer2 ||wrongAnswer3==wrongAnswer1);

        //tüm cevaplar için görüntüleri set eder
        switch (correctAnswer){
            case 1:
                image1.setImageResource(images[images_numbers[turn]]);
                image2.setImageResource(images [wrongAnswer1]);
                image3.setImageResource(images [wrongAnswer2]);
                image4.setImageResource(images [wrongAnswer3]);
                break;
            case 2:
                image1.setImageResource (images [wrongAnswer1]);
                image2.setImageResource(images[images_numbers[turn]]);
                image3.setImageResource(images [wrongAnswer2]);
                image4.setImageResource(images [wrongAnswer3]);
                break;
            case 3:
                image1.setImageResource(images[wrongAnswer1]);
                image2.setImageResource(images [wrongAnswer2]);
                image3.setImageResource(images[images_numbers[turn]]);
                image4.setImageResource(images [wrongAnswer3]);
                break;
            case 4:
                image1.setImageResource(images [wrongAnswer1]);
                image2.setImageResource(images [wrongAnswer2]);
                image3.setImageResource(images [wrongAnswer3]);
                image4.setImageResource(images[images_numbers[turn]]);
                break;
        }

        //soru için resim set eder

        imageMain.setImageResource(images_bw[images_numbers[turn]]); //gölgeli resimlerin içinden seçer ve set eder

        //nulllar (soru çıktığı an da olanlar doğru yanlış da bir şey yazmaz next butonu görünür değil ve imagelere tıklanabiliyor
        tv_status.setText("");
        b_next.setVisibility(View.INVISIBLE);

        image1.setEnabled(true);
        image2.setEnabled(true);
        image3.setEnabled(true);
        image4.setEnabled(true);
    }

    private void checkEnd(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setMessage("Game Over! Score: " +score );
        alertDialogBuilder.setPositiveButton("QUİT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //oyundan çıkış
                finish();

            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

}

