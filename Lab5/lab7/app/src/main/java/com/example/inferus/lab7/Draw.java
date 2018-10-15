package com.example.inferus.lab7;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

public class Draw extends View {

    Paint paint;
    Path path;
    private float scaleFactor = 1.0f;
    private ScaleGestureDetector scaleGestureDetector;

    public Draw(Context context) {
        super(context);
        setFocusable(true);
        paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        path = new Path();
        scaleGestureDetector = new ScaleGestureDetector(context, new ScaleListener());
    }

    @Override
    protected void onDraw(Canvas canvas) {

        float mid = getWidth() / 2;
        float min = Math.min(getWidth(), getHeight());
        float half = min / 2;
        paint.setStyle(Paint.Style.FILL);


        mid = mid - half;
        path.reset();

        path.moveTo(mid + half * 0.5f, half * 0.8f);

        path.lineTo(mid + half * 1.0f, half * 0.4f);
        path.lineTo(mid + half * 1.5f, half * 0.8f);
        path.lineTo(mid + half * 1.5f, half * 1.2f);
        path.lineTo(mid + half * 0.5f, half * 1.2f);

        canvas.save();
        canvas.scale(scaleFactor, scaleFactor);
        canvas.drawPath(path, paint);
        canvas.restore();
        path.close();

        super.onDraw(canvas);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        scaleGestureDetector.onTouchEvent(event);
        invalidate();
        return true;
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            scaleFactor *= detector.getScaleFactor();
            scaleFactor = Math.max(0.1f, Math.min(scaleFactor, 10.0f));
            invalidate();
            return true;
        }
    }
}