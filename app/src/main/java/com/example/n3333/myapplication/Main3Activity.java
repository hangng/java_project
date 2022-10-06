package com.example.n3333.myapplication;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

import java.util.ArrayList;
import java.util.Set;

public class Main3Activity extends AppCompatActivity {

    SharedPreferences shared;
    EditText edt1;
    TextView tx1, tx2, tx3;
    String a;
    Button btn;
    DataHelper d;
    ImageView imgl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        shared = getSharedPreferences("DATA", MODE_PRIVATE);
        Set<String> set = shared.getStringSet("name", null);
        ArrayList<String> ary = new ArrayList<>();
        ary.addAll(set);

        Toast.makeText(this, "list = " + set, Toast.LENGTH_SHORT).show();

        edt1 = findViewById(R.id.edt);
        tx1 = findViewById(R.id.tv1);
        tx2 = findViewById(R.id.tv2);
        tx3 = findViewById(R.id.tv3);
        btn = findViewById(R.id.btnss);

        imgl = findViewById(R.id.corcle_img);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.background);
        RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(), bitmap);
        roundedBitmapDrawable.setCircular(true);
        imgl.setImageDrawable(roundedBitmapDrawable);


        d = new DataHelper();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a = edt1.getText().toString();
                Toast.makeText(Main3Activity.this, "a = " + a, Toast.LENGTH_SHORT).show();
                /*String b = a.substring(0, 7);
                String c = a.substring(8, 13);
                String d = b +c;*/
                d.setA(a);

                tx1.setText(d.getA());

                /*tx2.setText(c);
                tx3.setText(b + c);*/

//                String f = d;
//                edt1.setText(f);

            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Check", "OnResume Called...");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Check", "onPause Called...");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Check", "onDestroy Called...");
    }


}
