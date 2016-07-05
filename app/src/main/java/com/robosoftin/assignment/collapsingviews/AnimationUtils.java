package com.robosoftin.assignment.collapsingviews;

import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.LinearLayout;

/**
 * Created by aishwarya on 13/6/16.
 */
public class AnimationUtils {

    private static final String TAG = AnimationUtils.class.getSimpleName();

    public static void expandView(final View view, int animDuration, Animation.AnimationListener animationListener) {
        Log.d(TAG, "expand()");
        view.measure(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        final int targetHeight = view.getMeasuredHeight();
        // Older versions of android (pre API 21) cancel animations for views with a height of 0.
        view.getLayoutParams().height = 1;
        view.setVisibility(View.VISIBLE);
        Animation expandAnimation = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                Log.d(TAG, "expand: interpolatedTime: " + interpolatedTime);
                view.getLayoutParams().height = interpolatedTime == 1
                        ? LinearLayout.LayoutParams.WRAP_CONTENT
                        : (int) (targetHeight * interpolatedTime);
                view.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };
        expandAnimation.setDuration(animDuration);
        expandAnimation.setAnimationListener(animationListener);
        view.startAnimation(expandAnimation);
    }

    public static void collapseView(final View view, int animDuration, Animation.AnimationListener animationListener) {
        Log.d(TAG, "collapse()");
        final int initialHeight = view.getMeasuredHeight();
        Animation collapseAnimation = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                Log.d(TAG, "collapse: interpolatedTime: " + interpolatedTime);
                if (interpolatedTime == 1) {
                    view.setVisibility(View.GONE);
                } else {
                    view.getLayoutParams().height = initialHeight - (int) (initialHeight * interpolatedTime);
                    view.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };
        collapseAnimation.setDuration(animDuration);
        collapseAnimation.setAnimationListener(animationListener);
        view.startAnimation(collapseAnimation);
    }
}
