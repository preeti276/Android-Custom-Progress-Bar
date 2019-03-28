package com.example.customprogressbar;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ProgressBarActivity extends AppCompatActivity implements FragmentView.OnFragmentInteractionListener {

    /*todo : set childCount = value of icon you want on progress bar*/
    //should be equal to number of fragments to view
    public static final int childCount = 3;

    //to be used if each fragment for activity is same, otherwise ignore
    public static int id = 1;
    private int curr = 0;

    private FragmentManager mFragmentManager;
    private CustomProgressViewGroup layout_animation;
    private Animation mBounceAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    @SuppressLint("NewApi")
    private void init() {
        layout_animation = findViewById(R.id.animationView);
        mFragmentManager = getSupportFragmentManager();
        mBounceAnimation = AnimationUtils.loadAnimation(this, R.anim.bounce_animation);

        //adding icon to progress bar
        for(int i = 0; i < childCount; i++){
            View view = getLayoutInflater().inflate(R.layout.layout_animation_icon,layout_animation,false);
            layout_animation.addView(view);
        }

        int iconResID, textResID = 0;
        //setting animation layout
        for (int i = 1; i < layout_animation.getChildCount(); i++) {
            LinearLayout linearLayout = (LinearLayout) layout_animation.getChildAt(i);
            linearLayout.setId(i);
            TextView textView = linearLayout.findViewById(R.id.textView);
            textResID = getResources().getIdentifier("text"+i,"string",getPackageName());
            textView.setText(textResID);
            GradientDrawable rect = new GradientDrawable();
            rect.setShape(GradientDrawable.RECTANGLE);
            rect.setCornerRadius(dpToPx(4));
            rect.setColor(Color.parseColor("#E1E3EF"));
            textView.setBackground(rect);

            ImageView imageView = (ImageView) linearLayout.findViewById(R.id.imageView);
            GradientDrawable circle = new GradientDrawable();
            circle.setShape(GradientDrawable.OVAL);
            circle.setColor(Color.parseColor("#FFFFFF"));
            imageView.setBackground(circle);
            iconResID = getResources().getIdentifier("icon"+i,"drawable",getPackageName());
            Drawable icon = ContextCompat.getDrawable(this, iconResID);
            imageView.setImageDrawable(icon);
        }

        showFragments(id);
    }


    private void setAnimationView(int fragmentId) {

        for (int i = 1; i < layout_animation.getChildCount(); i++) {
            LinearLayout currentLayout = (LinearLayout) layout_animation.getChildAt(i);
            TextView textView = (TextView) currentLayout.findViewById(R.id.textView);
            ImageView imageView = (ImageView) currentLayout.findViewById(R.id.imageView);

            if (currentLayout.getId()==fragmentId) {
                textView.setVisibility(View.VISIBLE);
                imageView.getDrawable().clearColorFilter();
                currentLayout.setAnimation(mBounceAnimation);
                currentLayout.startAnimation(mBounceAnimation);
            } else {
                textView.setVisibility(View.INVISIBLE);
                imageView.getDrawable().setColorFilter(Color.parseColor("#cccccc"), PorterDuff.Mode.SRC_ATOP);
            }
        }
    }

    private void showFragments(int fragmentId) {

        Fragment fragment;

        fragment = mFragmentManager.findFragmentById(fragmentId);
        if (fragment == null && id <= childCount) {
            fragment = new FragmentView();
            fragmentId = id;
            Bundle bundle = new Bundle();
            bundle.putInt("ID_KEY", fragmentId);
            fragment.setArguments(bundle);

          mFragmentManager
            .beginTransaction()
            .setCustomAnimations(R.anim.animation_enter, R.anim.animation_exit)
            .replace(R.id.frame_layout_fragment_container, fragment, Integer.toString(fragmentId))
            .addToBackStack(Integer.toString(id))
            .commit();

          curr = fragmentId;
          setAnimationView(curr);
          id++;
        }
    }

    private float dpToPx(int x){
        return TypedValue.applyDimension((int)TypedValue.COMPLEX_UNIT_DIP,x,getResources().getDisplayMetrics());
    }

    @Override
    public void onBackPressed() {

        if (mFragmentManager != null && mFragmentManager.getBackStackEntryCount() > 0) {

            if (mFragmentManager.getBackStackEntryCount() == 1) {
                finish();
            } else {
                /*handle animation on fragment changed*/
                int prevFragmentId = --curr;
                id--;
                setAnimationView(prevFragmentId);
                mFragmentManager.popBackStack();
            }
        }
    }

    @Override
    public void onFragmentInteraction(int fragmentId) {
        showFragments(fragmentId);
    }

}
