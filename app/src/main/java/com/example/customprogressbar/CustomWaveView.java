package com.example.customprogressbar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

public class CustomWaveView extends View {

  private Context mContext;
  private Paint stroke_paint, shadow_paint, fill_paint;

  public CustomWaveView(Context context) {
    super(context);
    init(context);
  }

  public CustomWaveView(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(context);
  }

  public CustomWaveView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context);
  }

  private void init(Context context) {
    mContext = context;

    //set stroke paint
    stroke_paint = new Paint();
    stroke_paint.setDither(true);
    stroke_paint.setAntiAlias(true);
    stroke_paint.setColor(Color.parseColor("#E1E3EF"));
    stroke_paint.setStyle(Paint.Style.STROKE);
    stroke_paint.setStrokeCap(Paint.Cap.ROUND);
    stroke_paint.setStrokeJoin(Paint.Join.ROUND);
    stroke_paint.setStrokeWidth(dpToPx(5));

    //set shadow paint
    shadow_paint = new Paint();
    shadow_paint.setDither(true);
    shadow_paint.setAntiAlias(true);
    shadow_paint.setColor(Color.parseColor("#cccccc"));
    shadow_paint.setStyle(Paint.Style.STROKE);
    shadow_paint.setStrokeCap(Paint.Cap.ROUND);
    shadow_paint.setStrokeJoin(Paint.Join.ROUND);
    shadow_paint.setStrokeWidth(dpToPx(3));

    //set fill paint
//    fill_paint = new Paint();
//    fill_paint.setDither(true);
//    fill_paint.setAntiAlias(true);
//    fill_paint.setColor(Color.parseColor("#E1E3EF"));
//    fill_paint.setStyle(Paint.Style.FILL);
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);

    //handling height
    int height = canvas.getHeight();
    int midHeight = height / 2;
    float amplitude = height / 6;

    //handling width
    int width = canvas.getWidth();
    int waveCount = ProgressBarActivity.childCount;
    int children_per_sinWave = 2;
    float sinWaveCount = (float)waveCount/children_per_sinWave;
    float sinWaveWidth = width/sinWaveCount;
    float period = (float) Math.toDegrees(2 * Math.PI); //360
    float frequency = sinWaveWidth/period;
    float time = 1 / frequency;
    float shadow_offset = dpToPx(6);


    /* border stroke loop */
    for (int i = 0; i < 180 * 6; i = i + 1) {
      float x1 = i;
      float y1 = midHeight + amplitude * (float) Math.sin(Math.toRadians(x1) * time);
      float x2 = i + 1;
      float y2 = midHeight + amplitude * (float) Math.sin(Math.toRadians(x2) * time);

      //border stroke
      canvas.drawLine(x1, y1, x2, y2, stroke_paint);

    }

    /*shadow stroke loop*/
    for (int i = 0; i < 180 * 6; i = i + 20) {
      float x1 = i;
      float y1 = midHeight + amplitude * (float) Math.sin(Math.toRadians(x1) * time);
      float x2 = i + 1;
      float y2 = midHeight + amplitude * (float) Math.sin(Math.toRadians(x2) * time);

      //shadow stroke
      canvas.drawLine(x1, y1+shadow_offset, x2, y2+shadow_offset, shadow_paint);
    }

    /*fill color loop*/
//    Path path = new Path();
//    for (int i = 0; i < 180 * 6; i++) {
//      float x1 = i;
//      float y1 = midHeight + amplitude * (float) Math.sin(Math.toRadians(x1) * time);
//      path.lineTo(x1,y1);
//      path.lineTo(i,0);
//
//      //fill path
//      canvas.drawPath(path,fill_paint);
//    }
  }

  private float dpToPx(int x){
    return TypedValue.applyDimension((int)TypedValue.COMPLEX_UNIT_DIP,x,mContext.getResources().getDisplayMetrics());
  }

}
