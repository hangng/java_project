package fragments;

import android.Manifest;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.n3333.myapplication.R;
import com.example.n3333.myapplication.component.GlobalTools;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;

public class CameraFr extends Fragment implements View.OnClickListener {
    public static final String SHARE_PERMISSION = "SHARE_PERMISSION";

    private boolean mbPermissionGranted;
    private Button mBtnCamera;
    private ImageView mIvImg;
    private Context mContext;
    public static final int CAMERA_IMAGE_REQUEST = 1;

    public static CameraFr newInstance() {
        Bundle bundle = new Bundle();
        CameraFr cameraFr = new CameraFr();
        cameraFr.setArguments(bundle);
        return cameraFr;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putBoolean(SHARE_PERMISSION, mbPermissionGranted);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle;

        if (savedInstanceState != null) {
            bundle = savedInstanceState;
        } else {
            bundle = getArguments();
        }

        if (bundle != null) {
            mbPermissionGranted = bundle.getBoolean(SHARE_PERMISSION);
        } else {
            mbPermissionGranted = false;
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag1, container, false);
        mContext = getActivity();

        if (mContext != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && mContext.checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.CAMERA}, GlobalTools.PERMISSIONS_REQUEST_CAMERA);
            }
//            else {
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && mContext.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//                    requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, GlobalTools.PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
//                } else {
////                showUploadDialog();
//                }
//            }
        }


        mIvImg = view.findViewById(R.id.iv_image);
        mBtnCamera = view.findViewById(R.id.btn_camera);

        mBtnCamera.setOnClickListener(this);


        return view;
    }


    @Override
    public void onClick(View view) {
        if (view == mBtnCamera) {
            GlobalTools.showDialog1Btn(getActivity(), getString(R.string.ok), getString(R.string.ok));
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i("TAG", "requestCode = " + requestCode + " | resultCode = " + resultCode);
//        if (requestCode == CAMERA_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
//            int iRotateDegree = GlobalTools.getExifRotationAngle(getActivity(), msImageFilePath);
//            Bitmap bitmap = null;
//            try {
//                bitmap = GlobalTools.getBitmapFromPath(mContext, msImageFilePath, 640, 1280);
//            } catch (OutOfMemoryError e) {
//                SdiDialog.showDialog1Btn(mContext, SdiDialog.DIALOG_ID_DO_NOTHING, getString(R.string.unable_to_process_your_image_please_try_again), getString(R.string.btn_ok), this);
//                return;
//            } catch (Exception e) {
//                SdiDialog.showDialog1Btn(mContext, SdiDialog.DIALOG_ID_DO_NOTHING, getString(R.string.unable_to_process_your_image_please_try_again), getString(R.string.btn_ok), this);
//                return;
//            }
//            if (bitmap == null) {
//                SdiDialog.showDialog1Btn(mContext, SdiDialog.DIALOG_ID_DO_NOTHING, getString(R.string.unable_to_process_your_image_please_try_again), getString(R.string.btn_ok), this);
//                return;
//            }
//
//            bitmap = Global.rotate(bitmap, iRotateDegree);
//            String sFileName = "";
//            try {
//                File file = new File(msImageFilePath);
//                sFileName = file.getName();
//                file.delete();
//            } catch (Exception e) {
//                Log.e("TAG","Error", e);
//            }
//
//            setBitmapImage(bitmap, sFileName);
//        }  else
        super.onActivityResult(requestCode, resultCode, data);
    }

//    private void takePhoto() {
//        String sImageName = "image_" + System.currentTimeMillis() + ".jpg";
//
//        Object[] objects = captureImage(sImageName, getOutputMediaFile(this, MEDIA_TYPE_IMAGE, sImageName), false);
//        if (objects != null) {
//            if (objects.length > 1) {
//                mImageFileUri = (Uri) objects[0];
//                msImageFilePath = (String) objects[1];
//            } else if (objects.length > 0) {
//                mImageFileUri = (Uri) objects[0];
//                msImageFilePath = mImageFileUri.getPath();
//            }
//        }
//    }
}
