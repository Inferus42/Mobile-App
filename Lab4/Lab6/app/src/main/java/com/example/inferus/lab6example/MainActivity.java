package com.example.inferus.lab6example;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new DrawView(this));
    }

    class DrawView extends View {

        Paint p;
        float[] star;

        public DrawView(Context context) {
            super(context);
            p = new Paint();
            star = new float[]{400,1000,550,500,550,500,700,1000,700,1000,300,700,
                                300,700,800,700,800,700,400,1000};
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawARGB(80, 102, 204, 255);

            p.setColor(Color.RED);
            p.setStrokeWidth(10);

            canvas.drawLines(star,p);

            Path path = new Path();

            path.moveTo(100, 1400);
            path.lineTo(100, 1300);
            path.lineTo(200, 1200);
            path.lineTo(300, 1300);
            path.lineTo(300, 1400);
            path.close();

            path.moveTo(600, 1400);
            path.lineTo(600, 1300);
            path.lineTo(700, 1200);
            path.lineTo(800, 1300);
            path.lineTo(800, 1400);
            path.lineTo(700, 1500);
            path.close();

            p.setColor(Color.GRAY);
            canvas.drawPath(path, p);



        }
    }

}