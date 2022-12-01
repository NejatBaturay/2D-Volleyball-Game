package com.matador.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class IngameActivity extends AppCompatActivity  implements View.OnTouchListener {

    Button rightmove;
    Button leftmove;
    Button jumpmove;
    ImageView ball;
    ImageView surface;
    ImageView player1;
    ImageView player1rakip;
    ImageView direk;
    ImageView solyandirek;
    ImageView sagyandirek;
    TextView player1score;
    TextView player2score;
    float ballfirstX;
    float ballfirstY;

    Thread t1;


    int[] cosangles = {50,55,60};
    int[] sinangles = {40,45,50,55};

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingame);


        ball = (ImageView) findViewById(R.id.volleyballreal);
        player1 = (ImageView) findViewById(R.id.player1);
        player1rakip = (ImageView) findViewById(R.id.player1rakip);
        surface = (ImageView) findViewById(R.id.surface);
        rightmove = (Button) findViewById(R.id.rightarrow);
        leftmove = (Button) findViewById(R.id.leftarrow);
        jumpmove = (Button) findViewById(R.id.jumpbutton);
        direk = (ImageView) findViewById(R.id.direk);
        solyandirek = (ImageView) findViewById(R.id.solyandirek);
        sagyandirek = (ImageView) findViewById(R.id.sagyandirek);
        player1score = (TextView) findViewById(R.id.player1score);
        player2score = (TextView) findViewById(R.id.player2score);
        rightmove.setOnTouchListener(this);
        leftmove.setOnTouchListener(this);
        jumpmove.setOnTouchListener(this);

        AlertDialog.Builder gamefinishedalert = new AlertDialog.Builder(this);
        gamefinishedalert.setTitle("Start Game");
        gamefinishedalert.setMessage("Start The Game");

        Rect rect1 = new Rect();
        ball.getHitRect(rect1);
        Rect rect2 = new Rect();
        surface.getHitRect(rect2);
        Rect rect3 = new Rect();
        direk.getHitRect(rect3);
        Rect rect4 = new Rect();
        player1.getHitRect(rect4);
        Rect rect5 = new Rect();
        player1rakip.getHitRect(rect5);
        Rect rect6 = new Rect();
        solyandirek.getHitRect(rect6);
        Rect rect7 = new Rect();
        sagyandirek.getHitRect(rect7);

        handler.postDelayed(runnablemotion,16);
        handler.post(runnablerival);



        View decorView = getWindow().getDecorView();

        int uiOptions = View.SYSTEM_UI_FLAG_IMMERSIVE
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        getSupportActionBar().hide();


    }


    boolean rightmovee = false;
    boolean leftmovee = false;
    boolean jumpmovee = false;
    Handler handler = new Handler();
    Runnable runnablen = new Runnable() {
        @Override
        public void run() {

            if (rightmovee  && player1.getX()<direk.getX()-120) {

                player1.setX(player1.getX() + 5);
                handler.postDelayed(runnablen, 16);
            } else if (leftmovee && player1.getX()>solyandirek.getX()) {

                player1.setX(player1.getX() + -5);
                handler.postDelayed(runnablen, 16);
            }
            else if (jumpmovee) {


                handler.postDelayed(runnablejump, 16);

            }


        }

    };




    boolean toppoint = false;
    Runnable runnablejump = new Runnable() {
        @Override
        public void run() {


            Rect rect1 = new Rect();
            ball.getHitRect(rect1);
            Rect rect2 = new Rect();
            surface.getHitRect(rect2);
            Rect rect3 = new Rect();
            direk.getHitRect(rect3);
            Rect rect4 = new Rect();
            player1.getHitRect(rect4);
            Rect rect5 = new Rect();
            player1rakip.getHitRect(rect5);
            Rect rect6 = new Rect();
            solyandirek.getHitRect(rect6);
            Rect rect7 = new Rect();
            sagyandirek.getHitRect(rect7);


            if (player1.getY() >= 580 && toppoint == false) {

                player1.setY(player1.getY() - (float) 7);
                handler.postDelayed(runnablejump, 4);

            }
            else {
                toppoint = true;
                player1.setY(player1.getY() + (float) 5);
                handler.postDelayed(runnablejump, 4);
                if(rect4.intersect(rect2)){

                    toppoint=false;
                    handler.removeCallbacks(runnablejump);

                }
            }

        }
    };



    int player1point = 0;
    int player2point = 0;
    double anglecos = cosangles[(new Random()).nextInt(cosangles.length)];
    double anglesin = sinangles[(new Random()).nextInt(sinangles.length)];

    double a = 0;
    double b = 0.020;
    boolean firsttouch = false;

    Runnable runnablemotion = new Runnable() {
        @Override
        public void run() {



            Rect rect1 = new Rect();
            ball.getHitRect(rect1);
            Rect rect2 = new Rect();
            surface.getHitRect(rect2);
            Rect rect3 = new Rect();
            direk.getHitRect(rect3);
            Rect rect4 = new Rect();
            player1.getHitRect(rect4);
            Rect rect5 = new Rect();
            player1rakip.getHitRect(rect5);
            Rect rect6 = new Rect();
            solyandirek.getHitRect(rect6);
            Rect rect7 = new Rect();
            sagyandirek.getHitRect(rect7);


        if(rect4.intersect(rect1)) {
            firsttouch=true;
        }
        if(firsttouch==true){

            float vx = (float) (10 * Math.cos(anglecos * (Math.PI / (float) 180.0)));
            float vy = (float) (20 * Math.sin(anglesin * (Math.PI / (float) 180.0)));

            a = a + b;
            ball.setX(ball.getX() + (float) (vx * a));
            ball.setY(ball.getY() - (float) ((vy * a) - (0.5 * 15 * (a * a))));


            if (rect1.intersect(rect2)) {

                sidetouch=true;
                anglecos = cosangles[(new Random()).nextInt(cosangles.length)];
                anglesin = sinangles[(new Random()).nextInt(sinangles.length)];

                a = 0;
                b = 0.020;

                firsttouch=false;
                ball.setX(263);
                ball.setY(578);
                player1point++;
                player1score.setText(String.valueOf(player1point));
                if(player1point==3){
                    AlertDialog.Builder gamefinishedalert = new AlertDialog.Builder(IngameActivity.this);
                    gamefinishedalert.setTitle("Volleyball");
                    gamefinishedalert.setMessage("Oyun Bitti");
                    gamefinishedalert.setPositiveButton("Tekrarla", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            Intent intent = new Intent(IngameActivity.this,IngameActivity.class);
                            IngameActivity.this.startActivity(intent);

                        }
                    });
                    gamefinishedalert.setNegativeButton("Çık", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(IngameActivity.this,MainActivity.class);
                            IngameActivity.this.startActivity(intent);
                        }
                    });
                    gamefinishedalert.show();

                }
                handler.post(runnablemotion);

            }
            else if (rect1.intersect(rect3)) {

                sidetouch=true;

                anglecos = cosangles[(new Random()).nextInt(cosangles.length)];
                anglesin = sinangles[(new Random()).nextInt(sinangles.length)];

                a = 0;
                b = 0.020;

                firsttouch=false;
                ball.setX(263);
                ball.setY(578);

                player2point++;
                player2score.setText(String.valueOf(player2point));
                if(player2point==3) {
                    AlertDialog.Builder gamefinishedalert = new AlertDialog.Builder(IngameActivity.this);
                    gamefinishedalert.setTitle("Volleyball");
                    gamefinishedalert.setMessage("Oyun Bitti");
                    gamefinishedalert.setPositiveButton("Tekrarla", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            Intent intent = new Intent(IngameActivity.this, IngameActivity.class);
                            IngameActivity.this.startActivity(intent);

                        }
                    });
                    gamefinishedalert.setNegativeButton("Çık", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(IngameActivity.this, MainActivity.class);
                            IngameActivity.this.startActivity(intent);
                        }
                    });
                    gamefinishedalert.show();
                }
                handler.post(runnablemotion);
            }
            else if (rect5.intersect(rect1)) {



                anglecos = cosangles[(new Random()).nextInt(cosangles.length)];
                anglesin = sinangles[(new Random()).nextInt(sinangles.length)];

                a = 0;
                b = 0.020;

                handler.post(runnablemotionrival);
                handler.removeCallbacksAndMessages(runnablemotion);
            }
            else if (rect1.intersect(rect7)) {
                sidetouch=true;

                anglecos = cosangles[(new Random()).nextInt(cosangles.length)];
                anglesin = sinangles[(new Random()).nextInt(sinangles.length)];

                a = 0;
                b = 0.020;

                handler.post(runnableside);
                handler.removeCallbacksAndMessages(runnablemotion);

            }

            else {
                handler.post(runnablemotion);
            }

        }
        else{
            handler.post(runnablemotion);
        }

        }
    };

    Runnable runnablemotionrival = new Runnable() {
        @Override
        public void run() {

            Rect rect1 = new Rect();
            ball.getHitRect(rect1);
            Rect rect2 = new Rect();
            surface.getHitRect(rect2);
            Rect rect3 = new Rect();
            direk.getHitRect(rect3);
            Rect rect4 = new Rect();
            player1.getHitRect(rect4);
            Rect rect5 = new Rect();
            player1rakip.getHitRect(rect5);
            Rect rect6 = new Rect();
            solyandirek.getHitRect(rect6);
            Rect rect7 = new Rect();
            sagyandirek.getHitRect(rect7);



            float vx = (float) (10 * Math.cos(anglecos * (Math.PI / (float) 180.0)));
                float vy = (float) (20 * Math.sin(anglesin * (Math.PI / (float) 180.0)));

                a = a + b;
                ball.setX(ball.getX() - (float) (vx * a));
                ball.setY(ball.getY() - (float) ((vy * a) - (0.5 * 15 * (a * a))));


                if (rect1.intersect(rect2)) {
                    sidetouch=true;
                    anglecos = cosangles[(new Random()).nextInt(cosangles.length)];
                    anglesin = sinangles[(new Random()).nextInt(sinangles.length)];

                    a = 0;
                    b = 0.020;

                    firsttouch=false;
                    ball.setX(263);
                    ball.setY(578);

                    player2point++;

                    player2score.setText(String.valueOf(player2point));
                    if(player2point==3){
                    AlertDialog.Builder gamefinishedalert = new AlertDialog.Builder(IngameActivity.this);
                    gamefinishedalert.setTitle("Volleyball");
                    gamefinishedalert.setMessage("Oyun Bitti");
                    gamefinishedalert.setPositiveButton("Tekrarla", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            Intent intent = new Intent(IngameActivity.this,IngameActivity.class);
                            IngameActivity.this.startActivity(intent);

                        }
                    });
                    gamefinishedalert.setNegativeButton("Çık", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(IngameActivity.this,MainActivity.class);
                            IngameActivity.this.startActivity(intent);
                        }
                    });
                    gamefinishedalert.show();

                }

                    handler.post(runnablemotion);

                } else if (rect1.intersect(rect3)) {

                    sidetouch=true;
                    anglecos = cosangles[(new Random()).nextInt(cosangles.length)];
                    anglesin = sinangles[(new Random()).nextInt(sinangles.length)];

                    a = 0;
                    b = 0.020;

                    firsttouch=false;
                    ball.setX(263);
                    ball.setY(578);

                    player1point++;
                    player1score.setText(String.valueOf(player1point));
                    if(player1point==3) {
                        AlertDialog.Builder gamefinishedalert = new AlertDialog.Builder(IngameActivity.this);
                        gamefinishedalert.setTitle("Volleyball");
                        gamefinishedalert.setMessage("Oyun Bitti");
                        gamefinishedalert.setPositiveButton("Tekrarla", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                Intent intent = new Intent(IngameActivity.this, IngameActivity.class);
                                IngameActivity.this.startActivity(intent);

                            }
                        });
                        gamefinishedalert.setNegativeButton("Çık", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(IngameActivity.this, MainActivity.class);
                                IngameActivity.this.startActivity(intent);
                            }
                        });
                        gamefinishedalert.show();
                    }
                    handler.post(runnablemotion);



                }
                else if (rect4.intersect(rect1)) {

                    anglecos = cosangles[(new Random()).nextInt(cosangles.length)];
                    anglesin = sinangles[(new Random()).nextInt(sinangles.length)];

                    a = 0;
                    b = 0.020;
                    handler.post(runnablemotion);
                }
                else if (rect1.intersect(rect6)) {
                    sidetouch=true;

                    anglecos = cosangles[(new Random()).nextInt(cosangles.length)];
                    anglesin = sinangles[(new Random()).nextInt(sinangles.length)];

                    a = 0;
                    b = 0.020;

                    handler.post(runnableside);
                    handler.removeCallbacksAndMessages(runnablemotionrival);

                }
                else {
                    handler.post(runnablemotionrival);
                }

            }



    };



    boolean sidetouch = true;
    Runnable runnableside = new Runnable() {
        @Override
        public void run() {

            Rect rect1 = new Rect();
            ball.getHitRect(rect1);
            Rect rect2 = new Rect();
            surface.getHitRect(rect2);
            Rect rect3 = new Rect();
            direk.getHitRect(rect3);
            Rect rect4 = new Rect();
            player1.getHitRect(rect4);
            Rect rect5 = new Rect();
            player1rakip.getHitRect(rect5);
            Rect rect6 = new Rect();
            solyandirek.getHitRect(rect6);
            Rect rect7 = new Rect();
            sagyandirek.getHitRect(rect7);

            if(rect1.intersect(rect4)&&rect1.intersect(rect6)) {

                sidetouch=false;
                anglecos = cosangles[(new Random()).nextInt(cosangles.length)];
                anglesin = sinangles[(new Random()).nextInt(sinangles.length)];

                a = 0;
                b = 0.020;

                handler.post(runnablemotion);
            }
            else if(rect1.intersect(rect6)&&sidetouch==true){


                ball.setX(ball.getX());
                ball.setY(ball.getY() + 2);

                handler.post(runnableside);

            }

            else if(rect1.intersect(rect5)){
                sidetouch=false;
                anglecos = cosangles[(new Random()).nextInt(cosangles.length)];
                anglesin = sinangles[(new Random()).nextInt(sinangles.length)];

                a = 0;
                b = 0.020;
                handler.post(runnablemotionrival);
            }
            else if (rect7.intersect(rect1)&&sidetouch==true) {



                ball.setX(ball.getX());
                ball.setY(ball.getY() + 2);

                handler.post(runnableside);

            }

            else if (rect1.intersect(rect2)) {



                anglecos = cosangles[(new Random()).nextInt(cosangles.length)];
                anglesin = sinangles[(new Random()).nextInt(sinangles.length)];
                firsttouch=false;
                ball.setX(263);
                ball.setY(578);
                a = 0;
                b = 0.020;
                handler.post(runnablemotion);




            }
            else {
                handler.post(runnableside);
            }
        }
        };


    boolean iscenter = false;

    Runnable runnablerival = new Runnable() {
        @Override
        public void run() {

            Rect rectrival = new Rect();
            player1rakip.getHitRect(rectrival);
            Rect rectgrey = new Rect();
            direk.getHitRect(rectgrey);

            if (player1rakip.getX() > ball.getX()&&iscenter==false) {

                player1rakip.setX(player1rakip.getX() - 3);

                if (rectrival.intersect(rectgrey)) {

                    iscenter = true;
                    handler.post(runnablerival);
                }
                handler.post(runnablerival);
            }
            else if (player1rakip.getX() < ball.getX()) {

                iscenter=false;

                player1rakip.setX(player1rakip.getX() + 5);
                handler.post(runnablerival);

            }
            else{
                handler.post(runnablerival);
            }

        }


    };



    @Override
        public boolean onTouch(View v, MotionEvent motionevent) {

            if (motionevent.getAction() == motionevent.ACTION_DOWN) {


                switch (v.getId()) {
                    case R.id.rightarrow:

                        leftmovee = false;
                        rightmovee = true;
                        jumpmovee = false;
                        handler.post(runnablen);

                        break;

                    case R.id.leftarrow:


                        rightmovee = false;
                        leftmovee = true;
                        jumpmovee = false;
                        handler.post(runnablen);

                        break;

                    case R.id.jumpbutton:


                        rightmovee = false;
                        leftmovee = false;
                        jumpmovee = true;
                        handler.post(runnablen);

                        return true;
                }
            } else if (motionevent.getAction() == motionevent.ACTION_UP) {

                rightmovee = false;
                leftmovee = false;
                jumpmovee = false;
                handler.post(runnablen);
                return true;
            }


            return true;
        }
    };









