package com.robosoftin.assignment.collapsingviews;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button mAnim;

    private static final int ANIMATION_DURATION = 200;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAnim = (Button) findViewById(R.id.start_anim);
        mAnim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initViews();
            }
        });
    }

    private void initViews() {
       // LinearLayout exampLayout = (LinearLayout) findViewById(R.id.example);
        View circleView = findViewById(R.id.example);
        if (circleView != null) {
            if ((circleView.getVisibility() == View.VISIBLE))
                AnimationUtils.collapseView(circleView, ANIMATION_DURATION, new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                       // mChangeBackgroundHideShowArrowImage.setRotation(NO_ROTATION);
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            else
                AnimationUtils.expandView(circleView, ANIMATION_DURATION, new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                       // mChangeBackgroundHideShowArrowImage.setRotation(ROTATE_INVERSE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
        }
    }
}
