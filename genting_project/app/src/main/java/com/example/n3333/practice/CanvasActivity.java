package com.example.n3333.practice;

import android.graphics.Matrix;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.n3333.practice.component.CanvasDemo;

//import com.example.n3333.myapplication.component.CanvasDemo;

public class CanvasActivity extends AppCompatActivity implements View.OnClickListener {

    private CanvasDemo canvasDemo;
    private Button mBtnCanvas;
    private EditText mEtQty;
    private int miInput;
    private RelativeLayout mRlCanvas;

    private ImageView iv;
    private Matrix matrix = new Matrix();
    private float scale = 1f;
    private float mfXPosition, mfYPosition;
    private ScaleGestureDetector SGD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas);
        mEtQty = findViewById(R.id.et_qty);
        canvasDemo = findViewById(R.id.cv_drawing);
        mBtnCanvas = findViewById(R.id.btn_canvas);
//
//
//        iv = (ImageView) findViewById(R.id.imageView);
//        SGD = new ScaleGestureDetector(this, new ScaleListener());
        mBtnCanvas.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == mBtnCanvas) {
            if (!mEtQty.getText().toString().isEmpty()) {
                miInput = Integer.parseInt(mEtQty.getText().toString());
                canvasDemo.QtyInput(miInput);
                canvasDemo.invalidate();
            }
            Toast.makeText(this, "clicked", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean onTouchEvent(MotionEvent ev) {
//        SGD.onTouchEvent(ev);

        mfXPosition = ev.getX();
        mfYPosition = ev.getY();
        return true;
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {

        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            scale *= detector.getScaleFactor();
            Log.i("TAG", "scale = " + scale);
            scale = Math.max(0, Math.min(scale, 5.0f));

            matrix.setScale(scale, scale);
            iv.setImageMatrix(matrix);
            return true;
        }
    }

//    public static void move(final TextView view){
//        ValueAnimator va = ValueAnimator.ofFloat(0f, 100f);
//        int mDuration = 3000; //in millis
//        va.setDuration(mDuration);
//        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            public void onAnimationUpdate(ValueAnimator animation) {
//                view.setTranslationX((float)animation.getAnimatedValue());
//            }
//        });
//        va.setRepeatCount(5);
//        va.start();
//    }

}

