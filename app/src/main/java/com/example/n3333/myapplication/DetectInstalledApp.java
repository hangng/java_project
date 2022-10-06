package com.example.n3333.myapplication;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DetectInstalledApp extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnDetect;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detect_installed_app);

        mBtnDetect = findViewById(R.id.btn_detect);
        mBtnDetect.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        mContext = getApplicationContext();
        boolean isInstalled = isPackageInstalled("com.sdi.theomy", mContext.getPackageManager());
        if (isInstalled) {
            Toast.makeText(mContext, "installed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(mContext, "not installed", Toast.LENGTH_SHORT).show();
        }
    }

    public static boolean isPackageInstalled(String packageName, PackageManager packageManager) {
        try {
            return packageManager.getApplicationInfo(packageName, 0).enabled;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }
}
