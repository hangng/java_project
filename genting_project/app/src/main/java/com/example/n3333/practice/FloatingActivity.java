package com.example.n3333.practice;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationManagerCompat;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import de.hdodenhof.circleimageview.CircleImageView;

public class FloatingActivity extends AppCompatActivity {

    private Spinner spn;
    private Spinner spn2;
    private ArrayList<NameList> arNL = new ArrayList<>();
    private NameList mNameList;
    SharedPreferences shared;
    private Button btn2;
    private TextView txt2;
    private DataHelper dh;
    private String storeData;
    private CircleImageView circleImageView;
    private EditText passwordEditText;
    private TextView textView, tvSeekbarMin, tvSeekbarMax;
    private String sMessageTitle, sMessageBody;
    private Button btnCn1, btnCn2;
    private EditText edtTitle, edtMsg;
    private NotificationManagerCompat notificationManager;
    public static final String CHANNEL_1_ID = "channel1";
    public static final String CHANNEL_2_ID = "channel2";
    public static final String CHANNEL_ID = "ID 123";
    public static final String CHANNEL_NAME = "Name";
    public static final String CHANNEL_DESC = "Desc";
    private int iUniqueId = 0;
    private SeekBar sampleSizeSeekBar;
    private int increment = 0, max = 10;


    ArrayList<String> dummy = new ArrayList<>();

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
//        outPersistentState.putString("aaa", dh.getA());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (dh.getA() != null) {
            txt2.setText(savedInstanceState.getString("aaa"));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floating);

        notificationManager = NotificationManagerCompat.from(this);
        spn = findViewById(R.id.spn1);
        spn2 = findViewById(R.id.spn2);
        btn2 = findViewById(R.id.btn2);
        txt2 = findViewById(R.id.m2txt);
        btnCn1 = findViewById(R.id.btn_chn1);
        btnCn2 = findViewById(R.id.btn_chn2);
        edtTitle = findViewById(R.id.edt_title);
        edtMsg = findViewById(R.id.edt_msg);
        tvSeekbarMin = findViewById(R.id.seekbar_min);
        tvSeekbarMax = findViewById(R.id.seekbar_max);
        circleImageView = findViewById(R.id.profile_image);


        sampleSizeSeekBar = findViewById(R.id.sampleSizeSeekBar);
        sampleSizeSeekBar.setMax(max-1);
        final int min = 1;

        final int sampleSizeSeekBarCorrection = 1; //e.g., 95 <--> 100
        int realValueFromPersistentStorage = 1; //Get initial value from persistent storage, e.g., 100
        sampleSizeSeekBar.setProgress(realValueFromPersistentStorage - sampleSizeSeekBarCorrection); //E.g., to convert real value of 100 to SeekBar value of 95.

        tvSeekbarMin.setText("1" + "/" + max);
        sampleSizeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                iUniqueId = progress + min; //e.g., to convert SeekBar value of 95 to real value of 100
                increment = progress;

//                iUniqueId = progress +;
                tvSeekbarMin.setText(iUniqueId + "/" + max);
                Toast.makeText(FloatingActivity.this, "val = ", Toast.LENGTH_SHORT).show();
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        btnCn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (increment < 9) {
                    increment += 1;
                    sampleSizeSeekBar.setProgress(increment);
                }
//                String msg = edtTitle.getText().toString();
//                String title = edtMsg.getText().toString();
//                    Notification notification = new NotificationCompat.Builder(FloatingActivity.this, App.CHANNEL_1_ID)
//                            .setSmallIcon(R.drawable.happy)
//                            .setContentTitle(title)
//                            .setContentText(msg)
//                            .build();
//
//                    notificationManager.notify(1, notification);

            }
        });

        btnCn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (increment > 0) {
                    increment -= 1;
                    sampleSizeSeekBar.setProgress(increment);
                }
//                String msg = edtTitle.getText().toString();
//                String title = edtMsg.getText().toString();
//
//                Notification notification = new NotificationCompat.Builder(FloatingActivity.this, App.CHANNEL_2_ID)
//                        .setSmallIcon(R.drawable.sad)
//                        .setContentTitle(title)
//                        .setContentText(msg)
//                        .setPriority(NotificationCompat.PRIORITY_LOW)
//                        .build();
//
//                notificationManager.notify(2, notification);

            }
        });


        circleImageView.setImageResource(R.drawable.background);
        dh = new

                DataHelper();

        shared = getSharedPreferences("DATA", MODE_PRIVATE);

        SharedPreferences.Editor editor = shared.edit();
        Set<String> set = new HashSet<String>();


        /* Initializing views */
        passwordEditText = (EditText)

                findViewById(R.id.password);

        textView = (TextView)

                findViewById(R.id.passwordHint);
        textView.setVisibility(View.GONE);

        /* Set Text Watcher listener */
        passwordEditText.addTextChangedListener(passwordWatcher);


        NameList a = new NameList("hang", 1);
        NameList b = new NameList("mj", 2);
        NameList c = new NameList("crystal♥", 3);

        final ArrayList<NameList> nameLists = new ArrayList<>();
        nameLists.add(a);
        nameLists.add(b);
        nameLists.add(c);

        ArrayList<String> ary = new ArrayList<>();
        for (
                int i = 0; i < nameLists.size(); i++) {
            ary.add(nameLists.get(i).getName());

            set.addAll(ary);
            editor.putStringSet("name", set);
            editor.apply();
        }

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(activity_floating.this, Main3Activity.class);
//                startActivity(intent);
//
//                NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
//                        .setSmallIcon(R.drawable.body1)
//                        .setContentTitle(sMessageTitle)
//                        .setContentText(sMessageBody)
//                        .setAutoCancel(true)
//                        .setContentIntent(pendingIntent);
//
//
//
//                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                    NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
//                            CHANNEL_NAME,
//                            NotificationManager.IMPORTANCE_DEFAULT);
//                    channel.setDescription(CHANNEL_DESC);
//                    notificationManager.createNotificationChannel(channel);
//                }
//
//                notificationManager.notify(iUniqueId, notificationBuilder.build());
            }
        });
        String d = "hang";
        ArrayAdapter adp = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, ary);
        spn2.setAdapter(adp);
        for (
                int i = 0; i < nameLists.size(); i++) {
            if (TextUtils.equals(d, nameLists.get(i).getName())) {
                spn2.setSelection(i);

                Toast.makeText(FloatingActivity.this, "Id is =" + i, Toast.LENGTH_SHORT).show();
            }

        }


        spn2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position,
                                       long id) {
                String data = parent.getItemAtPosition(position).toString();

                for (int i = 0; i < nameLists.size(); i++) {
                    if (TextUtils.equals(data, nameLists.get(i).getName())) {
                        dh.setA(nameLists.get(i).getName());
                        Toast.makeText(FloatingActivity.this, "nameLists = " + nameLists.get(i).getName(), Toast.LENGTH_SHORT).show();

                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        Boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getBoolean("isFirstRun", true);

        if (isFirstRun) {
            Toast.makeText(this, "First Run", Toast.LENGTH_SHORT).show();
            getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putBoolean("isFirstRun", false).apply();

        }

        dummy.add("ABC");
        dummy.add("DEF");
        dummy.add("GHI");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, dummy);
        spn.setAdapter(adapter);


        spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position,
                                       long id) {
                String items = parent.getItemAtPosition(position).toString();
                for (int i = 0; i < dummy.size(); i++) {
                    if (TextUtils.equals("ABC", dummy.get(i))) {
//                Log.d("dummy", "dummy.get(i) = " + dummy.get(i)+i);

                        Toast.makeText(FloatingActivity.this, "answer = " + items, Toast.LENGTH_LONG).show();
                        spn.setSelection(i);


                    }
                }
//                Toast.makeText(activity_floating.this, "Item Selected = " + items, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private final TextWatcher passwordWatcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            textView.setVisibility(View.VISIBLE);
        }

        public void afterTextChanged(Editable s) {
            if (s.length() == 0) {
                textView.setVisibility(View.GONE);
            } else {
                textView.setText("You have entered : " + passwordEditText.getText());
            }
        }
    };

}
