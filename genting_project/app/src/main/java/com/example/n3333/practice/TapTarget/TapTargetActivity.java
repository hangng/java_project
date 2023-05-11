package com.example.n3333.practice.TapTarget;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.n3333.practice.VolleyHandling;
import com.example.n3333.practice.R;
import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetSequence;
import com.getkeepsafe.taptargetview.TapTargetView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class TapTargetActivity extends AppCompatActivity {
    TapTargetSequence sequence;
    FloatingActionButton btnFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target_tap);

        btnFab = findViewById(R.id.fab1);
        btnFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TapTargetActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
                Intent a = new Intent(TapTargetActivity.this, VolleyHandling.class);
                startActivity(a);
            }
        });

        SharedPreferences sharedPref = this.getSharedPreferences("SINGLE_TAP_TARGET", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPref.edit();


        boolean isSingleTapFinished = sharedPref.getBoolean("isFinished", false);
        if (!isSingleTapFinished) {


            TapTargetView.showFor(this,
                    TapTarget.forView(findViewById(R.id.fab1),
                            "This is a single tap target",
                            "You can access your dashboard anytime by tapping this button.")
                            .transparentTarget(true),
                    new TapTargetView.Listener() {          // The listener can listen for regular clicks, long clicks or cancels
                        @Override
                        public void onTargetClick(TapTargetView view) {
                            super.onTargetClick(view);      // This call is optional

                            editor.putBoolean("isFinished", true);
                            editor.commit();
                        }
                    });

        }

        findViewById(R.id.fab1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                startActivity(new Intent(TapTargetActivity.this,DashboardActivity.class));
            }
        });

    }
}
