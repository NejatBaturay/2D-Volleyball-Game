package com.matador.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        View decorView = getWindow().getDecorView();

        int uiOptions = View.SYSTEM_UI_FLAG_IMMERSIVE
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);



        getSupportActionBar().hide();



        ScaleAnimation animation = new ScaleAnimation(0.1f, 1f, 0.1f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(5000);
        findViewById(R.id.volleyBall).startAnimation(animation);

        TranslateAnimation translateAnimationY = new TranslateAnimation(0,0,-500,0);
        translateAnimationY.setDuration(5000);
        findViewById(R.id.gameName).startAnimation(translateAnimationY);

        TranslateAnimation translateAnimationY1 = new TranslateAnimation(-500,0,-500,0);
        translateAnimationY1.setDuration(5000);
        findViewById(R.id.startbutton).startAnimation(translateAnimationY1);



        Animation translateAnimationY3 = new AlphaAnimation(0,1);
        translateAnimationY3.setDuration(2500);
        findViewById(R.id.button2).startAnimation(translateAnimationY3);

        translateAnimationY.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                    animations animations = new animations();
                    animations.setRotatecenter(findViewById(R.id.gameName));
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });



    }







    public void start(View view){
        if(mediaPlayer==null) {
            //mediaPlayer = MediaPlayer.create(this, R.raw.song);
            mediaPlayer.start();
        }
        else{
            if(mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
            }
            else{
                mediaPlayer.start();
            }
        }
    }
    public void startgame(View view) {
        Intent intent = new Intent(this, IngameActivity.class);
        startActivity(intent);
    }


}