package com.example.n3333.practice;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.skyfishjy.library.RippleBackground;


public class BookLoadingTutorial extends AppCompatActivity {
    com.victor.loading.book.BookLoading mBookloading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookloading_view);
        mBookloading = findViewById(R.id.bookLoading);

        mBookloading.start();

        final RippleBackground rippleBackground=(RippleBackground)findViewById(R.id.content);
        ImageView imageView=(ImageView)findViewById(R.id.centerImage);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rippleBackground.startRippleAnimation();
            }
        });
    }
}


