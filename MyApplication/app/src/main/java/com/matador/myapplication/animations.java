package com.matador.myapplication;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;

public class animations {

    public void setRotatecenter(View view) {
        Animation rotatecenter = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF,
                0.50f, Animation.RELATIVE_TO_SELF, 0.50f);
        rotatecenter.setDuration(900);

        view.startAnimation(rotatecenter);
    }

}
