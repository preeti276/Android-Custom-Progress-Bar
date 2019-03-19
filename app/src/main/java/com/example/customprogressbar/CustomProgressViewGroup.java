package com.example.customprogressbar;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CustomProgressViewGroup extends ViewGroup {

  Context mContext;
  int mWidth, mHeight;
  boolean parent = false;

  public CustomProgressViewGroup(Context context) {
    super(context);
    init(context);
  }

  public CustomProgressViewGroup(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(context);
  }

  public CustomProgressViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context);
  }

  private void init(Context context) {
    mContext = context;
  }

  @Override
  protected void onLayout(boolean changed, int l, int t, int r, int b) {
    final int count = getChildCount();
    parent = true;

    //get size of parent view
    if (parent) {
      mHeight = this.getMeasuredHeight();
      mWidth = this.getMeasuredWidth();
      parent = false;
    }

    //get the available size of child view from onMeasure
    final int childLeft = this.getPaddingLeft();
    final int childTop = this.getPaddingTop();
    final int childRight = this.getMeasuredWidth() - this.getPaddingRight();
    final int childBottom = this.getMeasuredHeight() - this.getPaddingBottom();
    final int childWidth = childRight - childLeft;
    final int childHeight = childBottom - childTop;

    int offset_w, offset_top, offset_bottom, layout_x, layout_y;

    //handling height
    int midHeight = mHeight / 2;
    float amplitude = mHeight / 6;

    //handling width
    int waveCount = count - 1; //subtracting the background view child
    int children_per_sinWave = 2;
    float sinWaveCount = (float)waveCount/children_per_sinWave;
    float sinWaveWidth = mWidth/sinWaveCount;
    float half_wave_point = (float)sinWaveWidth/4;

    for (int i = 0; i < count; i++) {
      View child = getChildAt(i);

      //handling case of background view
      if(i == 0){
        child.layout(0,0, childWidth, childHeight);
      }
      //handling case of linear layout child views
      else{
        LinearLayout linearChild = (LinearLayout) child;
        View textView = (TextView) linearChild.getChildAt(0);
        View imageView = (ImageView) linearChild.getChildAt(1);

        final int imageTop = imageView.getPaddingTop();
        final int imageBottom = imageView.getMeasuredHeight() - imageView.getPaddingBottom();
        final int imageHeight = imageBottom - imageTop;

        final int textTop = textView.getPaddingTop();
        final int textBottom = textView.getMeasuredHeight() - textView.getPaddingBottom();
        final int textHeight = textBottom - textTop;

        if (child.getVisibility() == GONE)
          return;

        offset_w = childWidth / 2;
        offset_top = textHeight + imageHeight / 2 + dpToPx(6);
        offset_bottom = imageHeight / 2 + dpToPx(6) ;

        layout_x = (int)half_wave_point * ((2*(i-1))+1);
        //alternate wave logic
        if (i % 2 == 0) {
          layout_y = (int) (midHeight - amplitude);
        } else {
          layout_y = (int) (midHeight + amplitude);
        }
        child.layout(layout_x - offset_w, layout_y - offset_top, layout_x + offset_w, layout_y + offset_bottom);
      }
    }
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    int count = getChildCount();
    for (int i = 0; i < count; i++) {
      final View child = getChildAt(i);
      measureChild(child, widthMeasureSpec, heightMeasureSpec);
    }
  }

  private int dpToPx(int x){
    return (int)TypedValue.applyDimension((int)TypedValue.COMPLEX_UNIT_DIP,x,mContext.getResources().getDisplayMetrics());
  }
}
