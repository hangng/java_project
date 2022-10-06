package com.example.n3333.myapplication;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
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
import androidx.core.content.FileProvider;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
    public static final int CAMERA_IMAGE_REQUEST = 1, PICK_GALLERY_REQUEST = 2, RECORD_VIDEO_REQUEST = 3;
    private Uri mImageFileUri;
    private String msImageFilePath = "";
    public static final int MEDIA_TYPE_IMAGE = 1, MEDIA_TYPE_VIDEO = 2;

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
                takePhoto();
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
//            CameraOpen();
            takePhoto();
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

    private void takePhoto() {
        String sImageName = "image_" + System.currentTimeMillis() + ".jpg";

        Object[] objects = captureImage(sImageName, getOutputMediaFile(this, MEDIA_TYPE_IMAGE, sImageName), false);
        if (objects != null) {
            if (objects.length > 1) {
                mImageFileUri = (Uri) objects[0];
                msImageFilePath = (String) objects[1];
            } else if (objects.length > 0) {
                mImageFileUri = (Uri) objects[0];
                msImageFilePath = mImageFileUri.getPath();
            }
        }
    }

    public static File getOutputMediaFile(Context context, int type, String... sFileName) {
        if (!Environment.getExternalStorageState().equalsIgnoreCase(Environment.MEDIA_MOUNTED)) {
            return null;
        }

//        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "MYTHEO");
        File mediaStorageDir = context.getCacheDir();

        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.i("TAG","failed to create directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE) {
            String sName;
            if (sFileName.length > 0 && sFileName[0] != null && !sFileName[0].isEmpty()) {
                sName = sFileName[0];
            } else {
                sName = "IMG_" + timeStamp + ".jpg";
            }
            mediaFile = new File(mediaStorageDir.getPath() + File.separator + sName);
        } else if (type == MEDIA_TYPE_VIDEO) {
            String sName;
            if (sFileName.length > 0 && sFileName[0] != null && !sFileName[0].isEmpty()) {
                sName = sFileName[0];
            } else {
                sName = "VID_" + timeStamp + ".mp4";
            }
            mediaFile = new File(mediaStorageDir.getPath() + File.separator + sName);
        } else {
            return null;
        }

        Log.i("TAG","mediaFile.getPath() = " + mediaFile.getPath());

        return mediaFile;
    }


    public Object[] captureImage(String sImageName, File image, boolean bFrontCamera) {
        // Creating folders for Image
//        String sImageFolderPath = ISubject.getInstance(mContext).getImageFilePath();
//        File imagesFolder = new File(sImageFolderPath);
//        imagesFolder.mkdirs();
//
//        // Creating image here
//        File image = new File(sImageFolderPath, sImageName);

        Uri fileUri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".provider", image);

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (bFrontCamera) {
            takePictureIntent.putExtra("android.intent.extras.CAMERA_FACING", 1);
        }
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        startActivityForResult(takePictureIntent, CAMERA_IMAGE_REQUEST);

        return new Object[]{fileUri, image.getAbsolutePath()};
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
