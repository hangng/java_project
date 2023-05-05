package com.example.n3333.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.n3333.myapplication.Dialogs.DialogActivity;
import com.example.n3333.myapplication.TapTarget.TapTargetActivity;
import com.example.n3333.myapplication.TimeTutorial.TimeActivity;
import com.example.n3333.myapplication.datahelper.MyDatabaseHelper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    boolean firstStart;
    Button btnRecyclerV, btnAsyncTask, btnUploadImag, btnExpand, btnFragment, btnDialog, btnTime,
            btnCalendar, btnRvFrag, btnTabTarget, mFAB, btnSubstring, btnNavigationMenu, btnCanvas, btnTouch, btnLm,
            btnFb, btnDb, btnNotify, btnSortingRv, btnFirebase, btnSurfaceView, btnBookLoading, btnDetectInstalledApp,
            btnClientList, btnBluetooth, btnSQLLite;
    Context context;
    private static long mlLastClickTime;
    private static int miLastClickViewId;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        Toast.makeText(MainActivity.this, "CodelessDebugLogEnabled = " + FacebookSdk.getCodelessDebugLogEnabled(), Toast.LENGTH_SHORT).show();

        setContentView(R.layout.activity_main);
        btnClientList = findViewById(R.id.btn_client_list);
        btnRecyclerV = findViewById(R.id.btn_recyclerview);
        btnAsyncTask = findViewById(R.id.btn_async_task);
        btnUploadImag = findViewById(R.id.btn_upload_img);
        btnExpand = findViewById(R.id.btn_expandablelist);
        btnFragment = findViewById(R.id.btn_fragments);
        btnDialog = findViewById(R.id.btn_dialog);
        btnTime = findViewById(R.id.btn_time);
        btnCalendar = findViewById(R.id.btn_calendar);
        btnRvFrag = findViewById(R.id.btn_rv_fragment);
        btnTabTarget = findViewById(R.id.btn_tab_target);
        btnSubstring = findViewById(R.id.btn_substring);
        btnNavigationMenu = findViewById(R.id.btn_navigation);
        btnCanvas = findViewById(R.id.btn_canvas);
        btnTouch = findViewById(R.id.btn_touchEvent);
        btnLm = findViewById(R.id.btn_rv_load_more);
        btnFb = findViewById(R.id.btn_fb);
        btnDb = findViewById(R.id.btn_db);
        mFAB = findViewById(R.id.btn_fab);
        btnNotify = findViewById(R.id.btn_notify);
        btnSortingRv = findViewById(R.id.btn_sorting);
        btnFirebase = findViewById(R.id.btn_firebase);
        btnSurfaceView = findViewById(R.id.btn_surfaceview);
        btnBookLoading = findViewById(R.id.btn_bookloading);
        btnDetectInstalledApp = findViewById(R.id.btn_detectinstalledapp);
        btnBluetooth = findViewById(R.id.btn_bluetooth);
        btnSQLLite = findViewById(R.id.btn_sql);

        btnRecyclerV.setOnClickListener(this);
        btnAsyncTask.setOnClickListener(this);
        btnUploadImag.setOnClickListener(this);
        btnExpand.setOnClickListener(this);
        btnFragment.setOnClickListener(this);
        btnDialog.setOnClickListener(this);
        btnTime.setOnClickListener(this);
        btnCalendar.setOnClickListener(this);
        btnRvFrag.setOnClickListener(this);
        btnTabTarget.setOnClickListener(this);
        mFAB.setOnClickListener(this);
        btnSubstring.setOnClickListener(this);
        btnNavigationMenu.setOnClickListener(this);
        btnCanvas.setOnClickListener(this);
        btnTouch.setOnClickListener(this);
        btnLm.setOnClickListener(this);
        btnFb.setOnClickListener(this);
        btnDb.setOnClickListener(this);
        btnNotify.setOnClickListener(this);
        btnSortingRv.setOnClickListener(this);
        btnFirebase.setOnClickListener(this);
        btnSurfaceView.setOnClickListener(this);
        btnBookLoading.setOnClickListener(this);
        btnDetectInstalledApp.setOnClickListener(this);
        btnBluetooth.setOnClickListener(this);
        btnSQLLite.setOnClickListener(this);

        context = getApplicationContext();
        // Get the installed apps package name list


//        SharedPreferences prefs = getSharedPreferences("PREFS", MODE_PRIVATE);
//        firstStart = prefs.getBoolean("hello", true);
//        if (firstStart) {
////            TapTargetView.showFor(this, TapTarget.forView(findViewById(R.id.fab1), "Button Test", "TapTarget").tintTarget(false).targetCircleColor(R.color.rippelColor));
//            SharedPreferences.Editor editor = prefs.edit();
//            editor.putBoolean("hello", false);
//            editor.commit();
//            Log.d("MainActivity","hello");
//            Toast.makeText(this, "first time", Toast.LENGTH_SHORT).show();
//
//        }



    }

    @Override
    public void onClick(View v) {
        if (isFastDoubleClick()) {
            return;
        }

        if (btnRecyclerV == v) {
            Intent recycler = new Intent(MainActivity.this, MoviesApp.class);
            startActivity(recycler);
//
//            Intent sendIntent = new Intent();
//            sendIntent.setAction(Intent.ACTION_SEND);
//            sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
//            sendIntent.setType("text/plain");
//            startActivity(sendIntent);

//            Intent sendIntent = new Intent();
//            sendIntent.setAction(Intent.ACTION_SEND);
//
////            sendIntent.setPackage("com.example.user.resume");
//            sendIntent.setPackage("com.example.myapplication");
//            sendIntent.putExtra(Intent.EXTRA_TEXT, "Hi Chin hao");
//            sendIntent.putExtra("Hi", "Hi Chin hao");
//            sendIntent.setType("text/plain");
//            startActivity(sendIntent);
//            startActivity(sendIntent);


//            Intent recycler = getPackageManager().getLaunchIntentForPackage("com.sdi.theomy");
//            startActivity(recycler);

   /*         final String appPackageName = "com.sdi.theomy"; // getPackageName() from Context or Activity object
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
            } catch (android.content.ActivityNotFoundException anfe) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                Log.i("TAG","app not found");
            }
            final PackageManager pm = getPackageManager();
//get a list of installed apps.
            List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);

            for (ApplicationInfo packageInfo : packages) {
                Log.d(TAG, "Installed package :" + packageInfo.packageName);
                Log.d(TAG, "Source dir : " + packageInfo.sourceDir);
                Log.d(TAG, "Launch Activity :" + pm.getLaunchIntentForPackage(packageInfo.packageName));
            }
*/

        } else if (btnAsyncTask == v) {
            Intent at = new Intent(MainActivity.this, AsyncTaskActivity.class);
            startActivity(at);
        } else if (btnUploadImag == v) {
            Intent ui = new Intent(MainActivity.this, UploadImageActivity.class);
            startActivity(ui);
        } else if (btnExpand == v) {
            Intent exp = new Intent(MainActivity.this, ExpendableActivity.class);
            startActivity(exp);
        } else if (btnFragment == v) {
            Intent fm = new Intent(MainActivity.this, FragmentActivity.class);
            startActivity(fm);
        } else if (btnDialog == v) {
            Intent dl = new Intent(MainActivity.this, DialogActivity.class);
            startActivity(dl);
        } else if (btnTime == v) {
            Intent tm = new Intent(MainActivity.this, TimeActivity.class);
            startActivity(tm);
        } else if (btnCalendar == v) {
            Intent ca = new Intent(MainActivity.this, CalendarActivity.class);
            startActivity(ca);
        } else if (btnRvFrag == v) {
            Intent rv = new Intent(MainActivity.this, FragmentRecyclerView.class);
            startActivity(rv);
        } else if (btnTabTarget == v) {
            Intent tt = new Intent(MainActivity.this, TapTargetActivity.class);
            startActivity(tt);
        } else if (mFAB == v) {
            Intent tt = new Intent(MainActivity.this, FloatingActivity.class);
            startActivity(tt);
        } else if (btnSubstring == v) {
            Intent ss = new Intent(MainActivity.this, Main3Activity.class);
            startActivity(ss);
        } else if (btnNavigationMenu == v) {
            Intent bn = new Intent(MainActivity.this, BottomNavigation.class);
            startActivity(bn);
        } else if (btnCanvas == v) {
            Intent cv = new Intent(MainActivity.this, CanvasActivity.class);
            startActivity(cv);
        } else if (btnTouch == v) {
            Intent te = new Intent(MainActivity.this, TouchEvent.class);
            startActivity(te);
        } else if (btnLm == v) {
            Intent lm = new Intent(MainActivity.this, LoadMoreDataToRVL.class);
            startActivity(lm);
        } else if (btnFb == v) {
            Intent fb = new Intent(MainActivity.this, FacebookDevelopment.class);
            startActivity(fb);
        } else if (btnDb == v) {
            Intent db = new Intent(MainActivity.this, DataBindingActivity.class);
            startActivity(db);
        } else if (btnNotify == v) {
            Intent db = new Intent(MainActivity.this, NotificationTest.class);
            startActivity(db);
        } else if (btnSortingRv == v) {
            Intent db = new Intent(MainActivity.this, SortingRecyclerview.class);
            startActivity(db);
        } else if (btnFirebase == v) {
            Intent db = new Intent(MainActivity.this, FirebaseTextML.class);
            startActivity(db);
        } else if (btnSurfaceView == v) {
            Intent sv = new Intent(MainActivity.this, SurfaceViewActivity.class);
            startActivity(sv);
        } else if (btnBookLoading == v) {
            Intent bl = new Intent(MainActivity.this, BookLoadingTutorial.class);
            startActivity(bl);
        } else if (btnDetectInstalledApp == v) {
            Intent bl = new Intent(MainActivity.this, DetectInstalledApp.class);
            startActivity(bl);
        }else if (btnBluetooth == v) {
            Intent bl = new Intent(MainActivity.this, BluetoothActivity.class);
            startActivity(bl);
        }else if (btnSQLLite == v) {
            Intent bl = new Intent(MainActivity.this, SQLLite.class);
            startActivity(bl);
        }
    }

    private boolean installedPackage(String packageName, PackageManager packageManager) {

        boolean found = true;

        try {
            packageManager.getPackageInfo(packageName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            found = false;
        }

        return found;
    }

    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();

        Log.i("AcBase", "Math.abs(time - mlLastClickTime) = " + Math.abs(time - mlLastClickTime));
        if (Math.abs(time - mlLastClickTime) < 500) {
            return true;
        }
        mlLastClickTime = time;
        return false;
    }

    public static boolean isFastDoubleClick(View view) {
        long time = System.currentTimeMillis();

        if (view.getId() == miLastClickViewId && Math.abs(time - mlLastClickTime) < 500) {
            return true;
        }
        mlLastClickTime = time;
        miLastClickViewId = view.getId();
        return false;
    }

}