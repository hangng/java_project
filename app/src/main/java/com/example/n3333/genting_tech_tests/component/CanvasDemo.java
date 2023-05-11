package com.example.n3333.genting_tech_tests.component;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.n3333.genting_tech_tests.R;

import java.util.Random;


public class CanvasDemo extends View {
    private float mfPosX, mfPosY;
    private int miCount = 0;

    public CanvasDemo(Context context) {
        super(context);
    }

    public CanvasDemo(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mfPosX = getWidth();
        mfPosY = getHeight();
        drawBase(canvas);

    }

    public void QtyInput(int iCount) {
        miCount = iCount;

    }

    public void drawBase(Canvas canvas) {
        Rect rect = new Rect();
        Paint paint = new Paint();
        Paint paint2 = new Paint();
        Random rnd = new Random();
        paint.setStrokeWidth(5);
        paint2.setStrokeWidth(5);
        paint.setStyle(Paint.Style.STROKE);
        paint2.setStyle(Paint.Style.STROKE);
        paint.setColor(getResources().getColor(R.color.rippelColor));
        paint.setTextSize(40);

        int gap = 20;
        int RecTop = 0;
        int RectLeft = 20;
        int RectRight = 0;
        int margin = 20;

        for (int i = 0; i < miCount; i++) {
            paint.setARGB(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));

            if (gap > getWidth()) {
                gap = 20;
                RectLeft = RectRight + rect.width() + margin;
            }
            rect.set(RectLeft, RecTop + gap, RectLeft + 200, gap + 200);

            canvas.drawRect(rect.left, rect.top, rect.right, rect.bottom, paint);
            canvas.drawText(String.valueOf(i), ((rect.left + rect.right) / 2) - margin, ((rect.top + gap + rect.height()) / 2), paint);
            gap = rect.top + rect.height() + 20;
            RectRight = rect.left;
            Log.i("Test", "gap = " + gap + " | rect.left = " + rect.left + " | rect.top = " + rect.top + " | rect.right = " + rect.right + " | rect.bottom = " + rect.bottom + " | rect.height() = " + rect.height() + " | rect.width() = " + rect.width());

        }

    }

}
