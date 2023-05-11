package com.example.n3333.genting_tech_tests;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.core.content.FileProvider;

import com.example.n3333.genting_tech_tests.component.GlobalTools;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class UploadImageActivity extends Activity implements View.OnClickListener {
    public static final int PERMISSIONS_REQUEST_CAMERA = 9002;
    public static final int PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 9003;
    public static final int PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 9004;
    public static final int PERMISSIONS_REQUEST_AUDIO = 9005;
    public static final int PERMISSIONS_REQUEST_CALL_PHONE = 9006;
    private static final int SELECTED_PIC = 100;
    final int RequestPermissionCode = 1;
    ImageView imageView, img2;
    LinearLayout mLayout;
    private Intent CamIntent, GalIntent, CropIntent;
    private File file;
    private Uri uri;
    Toolbar toolbar;
    private Button btnCam, btnViewImage, btnGal;
    public static final int CAMERA_IMAGE_REQUEST = 1, PICK_GALLERY_REQUEST = 2, RECORD_VIDEO_REQUEST = 3;
    private Uri mImageFileUri;
    private String msImageFilePath = "";
    public static final int MEDIA_TYPE_IMAGE = 1, MEDIA_TYPE_VIDEO = 2;
    public static final String[] UPLOAD_TYPE = {"application/vnd.openxmlformats-officedocument.wordprocessingml.document", "application/pdf", "application/msword", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", "application/vnd.ms-excel", "image/jpeg", "image/png", "image/bmp"};
    private Toast mToastPermission;

    private String msImage1 = "";
    private String msOriImage1 = "";
    private String msNewImage1 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_upload_image);
        mLayout = findViewById(R.id.up_img);
        imageView = findViewById(R.id.profile_image);
        btnCam = findViewById(R.id.btn_camera);
        btnViewImage = findViewById(R.id.btn_view_img);
        btnGal = findViewById(R.id.btn_gallery);

        btnViewImage.setOnClickListener(this);
        btnCam.setOnClickListener(this);
        btnGal.setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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
        File mediaStorageDir = context.getCacheDir();

        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.i("TAG", "failed to create directory");
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

        Log.i("TAG", "mediaFile.getPath() = " + mediaFile.getPath());

        return mediaFile;
    }


    public Object[] captureImage(String sImageName, File image, boolean bFrontCamera) {
        Uri fileUri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".provider", image);

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (bFrontCamera) {
            takePictureIntent.putExtra("android.intent.extras.CAMERA_FACING", 1);
        }
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        startActivityForResult(takePictureIntent, CAMERA_IMAGE_REQUEST);

        return new Object[]{fileUri, image.getAbsolutePath()};
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            int iRotateDegree = GlobalTools.getExifRotationAngle(this, msImageFilePath);
            Bitmap bitmap = null;

            try {
                bitmap = GlobalTools.getBitmapFromPath(this, msImageFilePath, 640, 1280);
            } catch (OutOfMemoryError e) {
                Log.e("TAG", "OutOfMemoryError = " + e.toString());

                return;
            } catch (Exception e) {
                Log.e("TAG", "Exception", e);
                return;
            }

            if (bitmap == null) {
                Log.i("TAG", "bitmap is null");
                return;
            }

            bitmap = GlobalTools.rotate(bitmap, iRotateDegree);
            String sFileName = "";
            try {
                File file = new File(msImageFilePath);
                sFileName = file.getName();
                file.delete();
            } catch (Exception e) {
                Log.e("TAG","Error", e);
            }

            imageView.setImageBitmap(bitmap);

            setBitmapImage(bitmap,sFileName);
        } else if (requestCode == PICK_GALLERY_REQUEST && resultCode == Activity.RESULT_OK) {
            try {
                Uri selectedMediaUri = data.getData();
                String sFilePath;
                boolean mbDeleteFile = false;
                String sTempFileName = null;


                String sType =
                        Objects.requireNonNull(this.getContentResolver().getType(selectedMediaUri)).split("/")[1];
                sTempFileName = "Img_" + System.currentTimeMillis() + "." + sType;
                final File file = new File(this.getCacheDir(), sTempFileName);

                final InputStream inputStream =
                        this.getContentResolver().openInputStream(selectedMediaUri);
                OutputStream output = new FileOutputStream(file);
                final byte[] buffer = new byte[4 * 1024]; // or other buffer size
                int read;

                while ((read = inputStream.read(buffer)) != -1) {
                    output.write(buffer, 0, read);
                }

                output.flush();
                sFilePath = file.getPath();
                mbDeleteFile = true;

                if (GlobalTools.isImageFile(selectedMediaUri, this)) {
                    int iRotateDegree = GlobalTools.getExifRotationAngle(this, sFilePath);
                    Bitmap bitmap = null;
                    try {
                        Log.i("TAG", "checking sFilePath = " + sFilePath);
                        bitmap = GlobalTools.getBitmapFromPath(this, sFilePath, 1000, 2000);
                    } catch (OutOfMemoryError e) {
                        Log.i("TAG", "OutOfMemoryError e " + e);
                        return;
                    } catch (Exception e) {
                        Log.i("TAG", "Exception e " + e);
                        return;
                    }

                    bitmap = GlobalTools.rotate(bitmap, iRotateDegree);

                    GlobalTools.drawWatermark(bitmap, this);
                    imageView.setImageBitmap(bitmap);
                    setBitmapImage(bitmap,sTempFileName);
                    if (mbDeleteFile && sTempFileName != null && sFilePath.contains(sTempFileName)) {
                        File tempFile = new File(sFilePath);
                        tempFile.delete();
                    }
                } else {
                    File selectedFile = new File(sFilePath);
                    String sFileName = GlobalTools.getFileName(selectedMediaUri, this);
                    setFile(selectedFile, sFileName);

                    if (mbDeleteFile && sTempFileName != null && sFilePath.contains(sTempFileName)) {
                        File tempFile = new File(sFilePath);
                        tempFile.delete();
                    }
                }
            } catch (Exception e) {
                Log.i("TAG", "Exception e " + e);
            }


        } else
            super.onActivityResult(requestCode, resultCode, data);
    }

    private void CropImage() {
        try {
            CropIntent = new Intent("com.android.camera.action.CROP");
            CropIntent.setDataAndType(uri, "image/*");

            CropIntent.putExtra("crop", "true");
            CropIntent.putExtra("outputX", 180);
            CropIntent.putExtra("outputY", 180);
            CropIntent.putExtra("aspectX", 3);
            CropIntent.putExtra("aspectY", 4);
            CropIntent.putExtra("scaleUpIfNeeded", true);
            CropIntent.putExtra("return-data", true);

            startActivityForResult(CropIntent, 1);
        } catch (ActivityNotFoundException ex) {

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST_CAMERA) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && this.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
                } else {
//                    showUploadDialog();
                }
            } else {
                if (mToastPermission == null)
                    mToastPermission = Toast.makeText(this, getString(R.string.permission_denied), Toast.LENGTH_LONG);

                mToastPermission.show();
            }
        } else if (requestCode == PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                showUploadDialog();
            } else {
                if (mToastPermission == null)
                    mToastPermission = Toast.makeText(this, getString(R.string.permission_denied), Toast.LENGTH_LONG);

                mToastPermission.show();
            }
        } else
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onClick(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && this.checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, PERMISSIONS_REQUEST_CAMERA);
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && this.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
            } else {
//                showUploadDialog();

                if (view == btnCam) {
                    takePhoto();
                } else if (view == btnGal) {
                    Intent pickIntent = new Intent(Intent.ACTION_GET_CONTENT);
                    pickIntent.setType("*/*");
                    pickIntent.putExtra(Intent.EXTRA_MIME_TYPES, GlobalTools.UPLOAD_TYPE);
                    startActivityForResult(pickIntent, PICK_GALLERY_REQUEST);
                } else {
                    String sImage = msImage1;

                    if (sImage != null && !sImage.isEmpty()) {
                        GlobalTools.showImageDialog(UploadImageActivity.this, UploadImageActivity.this, "", GlobalTools.StringToBitMap(sImage));
                    }
                }
            }
        }
    }

    private void showUploadDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        String[] photosource;
        boolean bShowViewImage = false;

        bShowViewImage = (msImage1 != null && !msImage1.isEmpty());

        if (bShowViewImage) {
            photosource = new String[]{getString(R.string.camera), getString(R.string.document), getString(R.string.view_image)};
        } else {
            photosource = new String[]{getString(R.string.camera), getString(R.string.document)};
        }

        builder.setItems(photosource, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        takePhoto();  //Check Permission
                        break;
                    case 1:
                        Intent pickIntent = new Intent(Intent.ACTION_GET_CONTENT);
                        pickIntent.setType("*/*");
                        pickIntent.putExtra(Intent.EXTRA_MIME_TYPES, GlobalTools.UPLOAD_TYPE);
                        startActivityForResult(pickIntent, PICK_GALLERY_REQUEST);
                        break;
                    case 2:
                        String sImage = msImage1;

                        if (sImage != null && !sImage.isEmpty()) {
                            GlobalTools.showImageDialog(UploadImageActivity.this, UploadImageActivity.this, "", GlobalTools.StringToBitMap(sImage));
                        }
                        break;
                }
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void setFile(File selectedFile, String sFileName) {
        String sFileString = GlobalTools.fileToString(selectedFile);
        imageView.setImageResource(R.drawable.img_holder);
//        msBankStatementPhoto = sFileString;
//        msFileName = sFileName;

    }


    private void setBitmapImage(Bitmap bitmap, String sFileName) {
        GlobalTools.drawWatermark(bitmap,this);
        String sBitmapString = GlobalTools.BitMapToString(bitmap);
//        mIvCamera.getLayoutParams().height = mi250dp;
//        mIvCamera.getLayoutParams().width = mi310dp;
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.requestLayout();
        imageView.invalidate();
        imageView.setImageBitmap(bitmap);
        msImage1 = sBitmapString;
    }

}


