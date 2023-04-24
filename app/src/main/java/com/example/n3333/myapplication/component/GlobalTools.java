package com.example.n3333.myapplication.component;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.hardware.Camera;
import android.media.ExifInterface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.util.Base64;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.VideoView;

import androidx.appcompat.app.ActionBar;
import androidx.core.content.FileProvider;

import com.example.n3333.myapplication.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class GlobalTools {

    public static final int PERMISSIONS_REQUEST_CAMERA = 9002;
    public static final int PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 9003;
    public static final int PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 9004;
    public static final int PERMISSIONS_REQUEST_AUDIO = 9005;
    public static final int PERMISSIONS_REQUEST_CALL_PHONE = 9006;
    private static final int SELECTED_PIC = 100;
    final int RequestPermissionCode = 1;
    public static final int MEDIA_TYPE_IMAGE = 1, MEDIA_TYPE_VIDEO = 2;
    public static final String[] UPLOAD_TYPE = {"application/vnd.openxmlformats-officedocument.wordprocessingml.document", "application/pdf", "application/msword", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", "application/vnd.ms-excel", "image/jpeg", "image/png", "image/bmp"};
    private static AlertDialog alertDialogTemp;
    static AlertDialog.Builder alertDialog;

    public static void showImageDialog(Context p_context, Activity activity, String p_strTitle, int image_id, boolean showCloseButton) {
        try {

            AlertDialog.Builder alertDialog = new AlertDialog.Builder(p_context);
            alertDialog.setCancelable(true);

            LayoutInflater inflater = activity.getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.dialog_image, null);
            alertDialog.setView(dialogView);

            ImageView imageView = dialogView.findViewById(R.id.iv_image);
            Button btnClose = dialogView.findViewById(R.id.btn_close);
            imageView.setImageResource(image_id);
//            alertDialog.setPositiveButton(p_strBtnText, new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    p_iSdiDialogListener.aDialogMid(p_intDialogId, null);
//                }
//            });

            if (showCloseButton) {
                btnClose.setVisibility(View.VISIBLE);
            }
            final AlertDialog alertDialogTemp = alertDialog.show();
            alertDialogTemp.setCancelable(!showCloseButton);
            alertDialogTemp.getWindow().setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
            alertDialogTemp.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

            btnClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialogTemp.dismiss();
                }
            });

        } catch (Exception e) {
            Log.e("TAG", "showDialog1Btn :" + e.toString());
        }
    }


    public static void showImageDialog(Context p_context, Activity activity, String p_strTitle, Bitmap bitmap) {
        try {

            AlertDialog.Builder alertDialog = new AlertDialog.Builder(p_context);
            alertDialog.setCancelable(true);

            LayoutInflater inflater = activity.getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.dialog_image, null);
            alertDialog.setView(dialogView);

            ImageView imageView = dialogView.findViewById(R.id.iv_image);
            imageView.setImageBitmap(bitmap);
//            alertDialog.setPositiveButton(p_strBtnText, new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    p_iSdiDialogListener.aDialogMid(p_intDialogId, null);
//                }
//            });
            AlertDialog alertDialogTemp = alertDialog.show();
            alertDialogTemp.getWindow().setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);

        } catch (Exception e) {
            Log.e("TAG", "showDialog1Btn :" + e.toString());
        }
    }


//    public static void showVideoDialog(final Context p_context, Activity activity, File videoFile) {
//        try {
//
//            AlertDialog.Builder alertDialog = new AlertDialog.Builder(p_context);
//            alertDialog.setCancelable(true);
//
//            LayoutInflater inflater = activity.getLayoutInflater();
//            final View dialogView = inflater.inflate(R.layout.item_alertdialog_with_video, null);
//            alertDialog.setView(dialogView);
//
//            final VideoView video = dialogView.findViewById(R.id.vv_video);
//
////            alertDialog.setPositiveButton(p_strBtnText, new DialogInterface.OnClickListener() {
////                @Override
////                public void onClick(DialogInterface dialog, int which) {
////                    p_iSdiDialogListener.aDialogMid(p_intDialogId, null);
////                }
////            });
//            final AlertDialog alertDialogTemp = alertDialog.show();
//            alertDialogTemp.getWindow().setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
//
//            video.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//                @Override
//                public void onCompletion(MediaPlayer mp) {
//                    alertDialogTemp.dismiss();
//                }
//            });
////            video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
////
////                @Override
////                public void onPrepared(MediaPlayer mp) {
////                    // // Auto-generated method stub
////                    mp.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
////                        @Override
////                        public void onVideoSizeChanged(MediaPlayer mp,
////                                                       int width, int height) {
////                            /*
////                             * add media controller
////                             */
////                            MediaController mc = new MediaController(p_context);
////                            video.setMediaController(mc);
////                            /*
////                             * and set its position on screen
////                             */
////                            mc.setAnchorView(video);
////
////                            ((ViewGroup) mc.getParent()).removeView(mc);
////
////                            ((FrameLayout) dialogView.findViewById(R.id.videoViewWrapper)).addView(mc);
////                            mc.setVisibility(View.VISIBLE);
////                        }
////                    });
////                }
////            });
//
//            video.setVideoURI(Uri.fromFile(videoFile));
//            video.start();
//
//        } catch (Exception e) {
//            SdiLog.e("showDialog1Btn :" + e.toString());
//        }
//    }

    public Context mContext;


    public static File getOutputMediaFile(Context context, int type, String... sFileName) {
        if (!Environment.getExternalStorageState().equalsIgnoreCase(Environment.MEDIA_MOUNTED)) {
            return null;
        }

//        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "MYTHEO");
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

    public static Bitmap StringToBitMap(String sData) {
        byte[] imageAsBytes = Base64.decode(sData, Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length);

        return bitmap;
    }

    public static int getExifRotationAngle(Activity activity, String sImagePath) {
        int iRotateDegree = 0;

        try {
            ExifInterface exifInterface = new ExifInterface(sImagePath);
            int iOrientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            switch (iOrientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    iRotateDegree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    iRotateDegree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    iRotateDegree = 270;
                    break;
            }
            Log.i("TAG", "iRotateDegree: " + iRotateDegree);
        } catch (IOException e) {
            Log.e("TAG", "[IOException] ExifInterface: ", e);

            iRotateDegree = getRotationAngle(activity, Camera.CameraInfo.CAMERA_FACING_BACK);
        }

        return iRotateDegree;
    }

    public static int getRotationAngle(Activity mContext, int cameraId) {
        android.hardware.Camera.CameraInfo info = new android.hardware.Camera.CameraInfo();
        android.hardware.Camera.getCameraInfo(cameraId, info);
        int rotation = mContext.getWindowManager().getDefaultDisplay().getRotation();
        int degrees = 0;
        switch (rotation) {
            case Surface.ROTATION_0:
                degrees = 0;
                break;
            case Surface.ROTATION_90:
                degrees = 90;
                break;
            case Surface.ROTATION_180:
                degrees = 180;
                break;
            case Surface.ROTATION_270:
                degrees = 270;
                break;
        }
        int result;
        if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
            result = (info.orientation + degrees) % 360;
            result = (360 - result) % 360; // compensate the mirror
        } else { // back-facing
            result = (info.orientation - degrees + 360) % 360;
        }
        return result;
    }

    public static Bitmap getBitmapFromPath(Context context, String sPath, int iMaxSize, int iIgnoreSampleLogicMaxSize) {
        Bitmap bitmap = null;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(sPath, options);

        int imageHeight = options.outHeight;
        int imageWidth = options.outWidth;

        if (imageHeight < iIgnoreSampleLogicMaxSize && imageWidth < iIgnoreSampleLogicMaxSize) {

            bitmap = BitmapFactory.decodeFile(sPath);
            return getResizedMutableBitmap(context, bitmap, iMaxSize);
        } else {

            int inSampleSize = 1;
            if (imageHeight > iIgnoreSampleLogicMaxSize || imageWidth > iIgnoreSampleLogicMaxSize) {

                final int halfHeight = imageHeight / 2;
                final int halfWidth = imageWidth / 2;

                // Calculate the largest inSampleSize value that is a power of 2 and keeps both
                // height and width larger than the requested height and width.
                while ((halfHeight / inSampleSize) >= iIgnoreSampleLogicMaxSize
                        || (halfWidth / inSampleSize) >= iIgnoreSampleLogicMaxSize) {
                    inSampleSize *= 2;
                }
            }

            options.inJustDecodeBounds = false;
            options.inSampleSize = inSampleSize;
            bitmap = BitmapFactory.decodeFile(sPath, options);

            return getResizedMutableBitmap(context, bitmap, iMaxSize);

        }
    }

    public static Bitmap getResizedMutableBitmap(Context context, Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        if (width < maxSize && height < maxSize) {
            return GlobalTools.convertToMutable(context, image);
        }
        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }

        image = Bitmap.createScaledBitmap(image, width, height, true);
        return GlobalTools.convertToMutable(context, image);

        //return  Bitmap.createScaledBitmap(image, width, height, true);
    }

    /**
     * Converts a immutable bitmap to a mutable bitmap. This operation doesn't allocates
     * more memory that there is already allocated.
     *
     * @param imgIn - Source image. It will be released, and should not be used more
     * @return a copy of imgIn, but muttable.
     */
    public static Bitmap convertToMutable(Context context, Bitmap imgIn) {
        try {
            //this is the file going to use temporally to save the bytes.
            // This file will not be a image, it will store the raw image data.
            File file = new File(context.getCacheDir() + File.separator + "temp.tmp");

            //Open an RandomAccessFile
            //Make sure you have added uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"
            //into AndroidManifest.xml file
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");

            // get the width and height of the source bitmap.
            int width = imgIn.getWidth();
            int height = imgIn.getHeight();
            Bitmap.Config type = imgIn.getConfig();

            //Copy the byte to the file
            //Assume source bitmap loaded using options.inPreferredConfig = Config.ARGB_8888;
            FileChannel channel = randomAccessFile.getChannel();
            MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_WRITE, 0, imgIn.getRowBytes() * height);
            imgIn.copyPixelsToBuffer(map);
            //recycle the source bitmap, this will be no longer used.
            imgIn.recycle();
            System.gc();// try to force the bytes from the imgIn to be released

            //Create a new bitmap to load the bitmap again. Probably the memory will be available.
            imgIn = Bitmap.createBitmap(width, height, type);
            map.position(0);
            //load it back from temporary
            imgIn.copyPixelsFromBuffer(map);
            //close the temporary file and channel , then delete that also
            channel.close();
            randomAccessFile.close();

            // delete the temp file
            file.delete();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return imgIn;
    }


    public static void showDialog1Btn(Context context, String sTitle, String sPositive) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(sTitle)
                .setPositiveButton(sPositive, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton(R.string.cancel, null)
                .setCancelable(false)
                .show();

    }


    public static Bitmap rotate(Bitmap bitmap, int degree) {
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();

        Matrix mtx = new Matrix();
        mtx.postRotate(degree);
//        mtx.setRotate(degree);

        return Bitmap.createBitmap(bitmap, 0, 0, w, h, mtx, true);
    }

    public static void drawWatermark(Bitmap bitmap, Context context) {
        int iSize = 18;
        int iTextSize = convertDpToPixel(context, iSize);
        int iPadding = convertDpToPixel(context, 10);
        String sWatermark = "For Personal use only";
        Canvas canvas = new Canvas(bitmap);

        Paint paint = new Paint();
        Rect mrcTemp = new Rect();
        Rect mrcBackGround = new Rect();
        paint.setTextSize(iTextSize); // Text Size
        paint.getTextBounds(sWatermark, 0, sWatermark.length(), mrcTemp);

        while(mrcTemp.width() > bitmap.getWidth() && iSize > 0 ) {
            iSize--;
            iTextSize = convertDpToPixel(context, iSize);
            paint.setTextSize(iTextSize); // Text
            paint.getTextBounds(sWatermark, 0, sWatermark.length(), mrcTemp);
        }

        paint.setColor(Color.argb(70,0,0,0));

        mrcBackGround.set(0,
                bitmap.getHeight() - mrcTemp.height() - iPadding - iPadding,
                bitmap.getWidth(),
                bitmap.getHeight() - iPadding);
        canvas.drawRect(mrcBackGround,paint);


        paint.setColor(Color.argb(70,255,255,255)); // Text Color

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER)); // Text Overlapping Pattern
        // some more settings...

        canvas.drawBitmap(bitmap, 0, 0, paint);

        int iX = bitmap.getWidth()/2 - mrcTemp.width()/2;
        canvas.drawText(sWatermark, iX, bitmap.getHeight() - mrcTemp.height(), paint);
    }

    public static int convertDpToPixel(Context context, int idp) {
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, (float) idp, context.getResources().getDisplayMetrics()));
    }


    public static boolean isImageFile(Uri selectedMediaUri,Context context) {

        String sMimeType = Objects.requireNonNull(context.getContentResolver().getType(selectedMediaUri)).split("/")[0];
        if (sMimeType.toLowerCase().equals("image")) {
            return true;
        }

        return false;
    }

    public static String fileToString(File file) {
        String sFileString = null;
        try {
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            for (int readNum; (readNum = fis.read(buf)) != -1; ) {
                baos.write(buf, 0, readNum); //no doubt here is 0
            }
            byte[] bytes = baos.toByteArray();
            sFileString = Base64.encodeToString(bytes, Base64.DEFAULT);
        } catch (Exception e) {
            Log.i("TAG","fileToString Exception e " + e);
        }
        return sFileString;
    }

    public static String getFileName(Uri uri, Activity activity) {
        try {
            String result = null;
            if (uri.getScheme().equals("content")) {
                Cursor cursor = activity.getContentResolver().query(uri, null, null, null, null);
                try {
                    if (cursor != null && cursor.moveToFirst()) {
                        result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                    }
                } finally {
                    cursor.close();
                }
            }
            if (result == null) {
                result = uri.getPath();
                int cut = result.lastIndexOf('/');
                if (cut != -1) {
                    result = result.substring(cut + 1);
                }
            }
            return result;
        } catch (Exception e) {
            Log.i("TAG","getFileName Exception e " + e);
            return "";
        }
    }

    public static String BitMapToString(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] b = baos.toByteArray();
        String sPicture = Base64.encodeToString(b, Base64.DEFAULT);

        return sPicture;
    }
}
