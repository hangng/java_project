package com.example.n3333.practice.Dialogs;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.n3333.practice.R;

import java.util.ArrayList;

public class DialogActivity extends AppCompatActivity {
    private TextView tv, tv2;
    private EditText edt;
    private Button btn, showDialog;
    int counter = 0;
    private ScrollView svBg;
    private Switch swBtn;

    private Dialog dialog;
    private ArrayList<String> values = new ArrayList<String>();
    private Spinner mSpTesting;
    private EditText mEtTxtOne, mEtTxtTwo;
    private RecyclerView mRvTest;
    private LinearLayoutManager mLlManager;
    private ArrayAdapter<String> mAdpTesting;
    private ArrayList<String> mAryTest = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        mSpTesting = findViewById(R.id.sp_testing);
        mEtTxtTwo = findViewById(R.id.edt_text2);
        mRvTest = findViewById(R.id.rv_testing);

        tv = findViewById(R.id.dialog_text);
        edt = findViewById(R.id.edt_text);
        btn = findViewById(R.id.btn);
        showDialog = findViewById(R.id.show_dialog);
        tv2 = findViewById(R.id.dialog_2);
        svBg = findViewById(R.id.dialog_bg);
        swBtn = findViewById(R.id.sw);

        tv.setTextIsSelectable(true);

        swBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    Animation animFadeIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
                    svBg.setBackground(ContextCompat.getDrawable(DialogActivity.this, R.drawable.bg));
//                  svBg.setBackgroundColor(Color.BLACK);
                    svBg.setAnimation(animFadeIn);
                } else {
                    Animation animFadeOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
                    svBg.setBackground(ContextCompat.getDrawable(DialogActivity.this, R.drawable.black));
//                  svBg.setBackgroundColor(Color.WHITE);
                    svBg.setAnimation(animFadeOut);
                }
            }
        });

        Boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getBoolean("isFirstRun", true);

        if (isFirstRun) {
            Toast.makeText(this, "First Run", Toast.LENGTH_SHORT).show();
            tv.setText("first run");
            getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putBoolean("isFirstRun", false).apply();
        } else {
            tv.setText("second  run");
        }
        showDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* String a = edt.getText().toString().trim();
                MyCustomDialog();
                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("hello", a);
                clipboardManager.setPrimaryClip(clipData);
                Toast.makeText(DialogActivity.this, "inside a: " + clipData, Toast.LENGTH_SHORT).show();
*/
                MyCustomDialog mcd = new MyCustomDialog();
                mcd.show(getSupportFragmentManager(), "MyCustomDialog");
                Toast.makeText(DialogActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
            }
        });


       /* edt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String a = edt.getText().toString();
                tv.setText(a);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
*/
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* String a = edt.getText().toString();
                tv.setText(a);*/
//                dialog.cancel();

                Toast.makeText(DialogActivity.this, "Clicked", Toast.LENGTH_SHORT).show();


                SharedPreferences getData = getApplicationContext().getSharedPreferences("Hello", MODE_PRIVATE); // 0 - for private mode
                SharedPreferences.Editor sendData = getData.edit();

                sendData.putString("name", "Dino"); // Storing string
                sendData.putInt("id", 2); // Storing integer
                sendData.apply();


                Log.d("sp", "name = " + getData.getString("name", null));
                Log.d("sp", "id = " + getData.getInt("id", 0));

              /*  if (edt.length() >= 12) {
                    String enter = edt.getText().toString();
                    String value = enter.substring(0, 7);
                    Toast.makeText(DialogActivity.this, "value is = " + value, Toast.LENGTH_SHORT).show();
                    tv.setText(value);
                }*/

//                SharedPreferences preferences = getSharedPreferences("HI",MODE_PRIVATE);
//
//                    String restoreText = preferences.getString("name",null);
//                    int restoreInt = preferences.getInt("id",1);
//                    tv.setText(restoreText);
//                    tv2.setText(restoreInt);


//                Intent intent = new Intent(DialogActivity.this, MainActivity.class);
//                startActivity(intent);


                String data = "", data2 = edt.getText().toString();
                if (!values.contains(data)) {
                    values.add(data2);

                }
                for (int i = 0; i < values.size(); i++) {
                    Log.i("AAA", "answer = " + values.get(i));
                }

            }
        });


    }

    public void MyCustomDialog() {
        dialog = new Dialog(DialogActivity.this);
        dialog.setContentView(R.layout.activity_custom_dialog);
        dialog.setTitle("Mudkips");
        dialog.show();
        btn = dialog.findViewById(R.id.close_dialog);


    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("Methods", "onRestart method called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Methods", "onStop method called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Methods", "onPause method called");
//        counter++;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("counter", counter);
        Log.d("Methods", "onSaveInstanceState method called");
        Toast.makeText(this, "onSaveInstanceState" + counter + "was Saved", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
//        counter = savedInstanceState.getInt("counter");
        Log.d("Methods", "onRestoreInstanceState method called");
//        Toast.makeText(this, "onSaveInstanceState" + counter + "was Restored", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Methods", "onPause method called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Methods", "onDestroy method called");
    }

}
