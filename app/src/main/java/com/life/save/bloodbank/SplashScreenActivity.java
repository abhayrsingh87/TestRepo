package com.life.save.bloodbank;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class SplashScreenActivity extends Activity {

    ImageView bloodIcon;
    TextView welcomeText;

    private boolean isAnimationComplete = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        bloodIcon = (ImageView)findViewById(R.id.bloodIcon);
        welcomeText = (TextView)findViewById(R.id.welcomeText);



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isAnimationComplete = false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_splash_screen, menu);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        if(hasFocus && !isAnimationComplete)
        {
            final float screenHeight = getResources().getDisplayMetrics().heightPixels;
            final float startY = screenHeight - 50;
            final float desiredY =  screenHeight  - 3 * (screenHeight/4);
            final ObjectAnimator slidUpAnimation = ObjectAnimator.ofFloat(bloodIcon,"translationY",startY,desiredY);
            final ObjectAnimator alpha = ObjectAnimator.ofFloat(bloodIcon,"alpha",0.0f,0.3f,0.5f,0.8f,1.0f);
            final AnimatorSet set = new AnimatorSet();
            bloodIcon.setVisibility(View.VISIBLE);
            set.playTogether(slidUpAnimation,alpha);
            set.setDuration(3000);
            set.addListener(listener);
            set.start();

        }
    }

    private boolean isFinalAnimationRun = false;
    final private Animator.AnimatorListener listener = new Animator.AnimatorListener() {
        @Override
        public void onAnimationStart(Animator animation) {

        }

        @Override
        public void onAnimationEnd(Animator animation) {
            if(!isAnimationComplete)
            {
                welcomeText.setVisibility(View.VISIBLE);
                final ObjectAnimator alphaText = ObjectAnimator.ofFloat(welcomeText,"alpha",0.0f,0.3f,0.5f,0.6f,0.7f,1.0f);
                alphaText.addListener(listener);
                alphaText.setDuration(3000);
                alphaText.start();
                isAnimationComplete = true;
            }
            else
            {
                SplashScreenActivity.this.finish();
                HomeScreenActivity.startHomeScreen(SplashScreenActivity.this);
            }


        }

        @Override
        public void onAnimationCancel(Animator animation) {

        }

        @Override
        public void onAnimationRepeat(Animator animation) {

        }
    };
}
