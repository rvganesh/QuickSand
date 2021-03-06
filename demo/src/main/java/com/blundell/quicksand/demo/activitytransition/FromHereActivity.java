package com.blundell.quicksand.demo.activitytransition;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Transition;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.blundell.quicksand.Quicksand;
import com.blundell.quicksand.demo.R;
import com.blundell.quicksand.demo.UnimportantForDemoUtils;

import java.util.concurrent.TimeUnit;

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class FromHereActivity extends Activity {

    public static final String KEY_MY_ACTIVITY_TRANSITION = "MyActivityTransition";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Transition transition = getTransition();
        Quicksand.trap(KEY_MY_ACTIVITY_TRANSITION, transition);
        getWindow().setExitTransition(transition);
        setContentView(R.layout.activity_from_here);

        final ImageView carImage = (ImageView) findViewById(R.id.from_here_image);
        carImage.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(), ToHereActivity.class);
                        Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(FromHereActivity.this).toBundle();
                        startActivity(intent, bundle);
                    }
                });
        UnimportantForDemoUtils.addTouchFeedback(carImage);

        findViewById(R.id.button_reset_transition_count).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Quicksand.resetTrap(KEY_MY_ACTIVITY_TRANSITION);
                        Toast.makeText(FromHereActivity.this, R.string.message_reset_trap, Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
    }

    private Transition getTransition() {
        Transition transition = new Explode();
        transition.setDuration(TimeUnit.SECONDS.toMillis(6));
        return transition;
    }

}
