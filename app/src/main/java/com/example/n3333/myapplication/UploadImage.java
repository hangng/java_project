package com.example.n3333.myapplication;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;

public class UploadImage extends Activity {

    private static final int SELECTED_PIC = 100;
    final int RequestPermissionCode=1;
    ImageView imageView, img2;
    LinearLayout mLayout;
    private Intent CamIntent, GalIntent, CropIntent;
    private File file;
    private Uri uri;
    Toolbar toolbar;
    private Button btnCam, btnCrop, btnGal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_upload_image);
        mLayout = findViewById(R.id.up_img);
        imageView = findViewById(R.id.profile_image);
        btnCam = findViewById(R.id.btn_camera);
        btnCrop = findViewById(R.id.btn_crop);
        btnGal = findViewById(R.id.btn_gallery);


        btnCam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CameraOpen();
            }
        });

        btnGal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GalleryOpen();
            }
        });
    }

    public void btnClick(View v) {

        int permissionCheck = ContextCompat.checkSelfPermission(UploadImage.this, Manifest.permission.CAMERA);
        if (permissionCheck == PackageManager.PERMISSION_DENIED) {
            RequestRuntimePermission();
        }

        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, SELECTED_PIC);
    }

    private void RequestRuntimePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(UploadImage.this, Manifest.permission.CAMERA)) {
            Toast.makeText(this, "Allow Access Camera Permission", Toast.LENGTH_SHORT).show();
        } else {
            ActivityCompat.requestPermissions(UploadImage.this, new String[]{Manifest.permission.CAMERA}, RequestPermissionCode);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.btn_camera) {
            CameraOpen();
        } else if (item.getItemId() == R.id.btn_gallery) {
            GalleryOpen();

        }
        return true;
    }

    private void CameraOpen() {
        CamIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        file = new File(Environment.getExternalStorageDirectory(),
                "file"+String.valueOf(System.currentTimeMillis())+".jpg");
        uri = Uri.fromFile(file);
        CamIntent.putExtra(MediaStore.EXTRA_OUTPUT,uri);
        CamIntent.putExtra("return-data",true);
        startActivityForResult(CamIntent,0);
    }

    private void GalleryOpen() {
        GalIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(Intent.createChooser(GalIntent, "Select Picture from Gallery"), 2);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == 0) {
            CropImage();
        }else if (requestCode == 2){
            if(data != null){
                uri = data.getData();
                CropImage();
            }else if(requestCode ==1){
                if (data != null){
                    Bundle bundle = data.getExtras();
                    Bitmap bitmap = bundle.getParcelable("data");
                    imageView.setImageBitmap(bitmap);
                }
            }
        }
    }

    private void CropImage() {
        try{
            CropIntent = new Intent("com.android.camera.action.CROP");
            CropIntent.setDataAndType(uri,"image/*");

            CropIntent.putExtra("crop","true");
            CropIntent.putExtra("outputX",180);
            CropIntent.putExtra("outputY",180);
            CropIntent.putExtra("aspectX",3);
            CropIntent.putExtra("aspectY",4);
            CropIntent.putExtra("scaleUpIfNeeded",true);
            CropIntent.putExtra("return-data",true);

            startActivityForResult(CropIntent,1);
        }
        catch (ActivityNotFoundException ex)
        {

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode)
        {
            case RequestPermissionCode:
            {
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    Toast.makeText(this,"Permission Granted",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(this,"Permission Canceled",Toast.LENGTH_SHORT).show();
            }
            break;
        }
    }

    /*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (resultCode == RESULT_OK && requestCode == SELECTED_PIC && null != data) {

                Uri uri = data.getData();
                String[] projection = {MediaStore.Images.Media.DATA};

                Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(projection[0]);
                String filepath = cursor.getString(columnIndex);
                cursor.close();

                imageView = findViewById(R.id.profile_image);
                //imageView.setImageURI(uri);
                imageView.setImageBitmap(BitmapFactory.decodeFile(filepath));
                Toast.makeText(this, "Image Displayed", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            Toast.makeText(this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
        }
    }
*/

}
