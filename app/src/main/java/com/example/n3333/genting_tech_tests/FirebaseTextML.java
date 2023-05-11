package com.example.n3333.genting_tech_tests;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
import com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FirebaseTextML extends AppCompatActivity implements View.OnClickListener {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final String TAG = "FirebaseTextML";
    ImageView mImageView;
    TextView cameraBtn;
    TextView detectBtn;
    Bitmap imageBitmap;
    TextView textView1, textView2, textView3, storeageBtn;
    RelativeLayout mRlView;
    LinearLayout mLlView;
    Context mContext;
    Activity mActivity;
    String msText1, msText2, msText3;
    ArrayList<String> mAry = new ArrayList<>();
    Uri imageUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_text_ml);

        storeageBtn = findViewById(R.id.storeButton);
        mImageView = findViewById(R.id.iv_img);
        cameraBtn = findViewById(R.id.cameraButton);
        detectBtn = findViewById(R.id.detectButton);
        textView1 = findViewById(R.id.tv_show_text_1);
        textView2 = findViewById(R.id.tv_show_text_2);
        textView3 = findViewById(R.id.tv_show_text_3);
        mRlView = findViewById(R.id.rl_ml_view);
        mLlView = findViewById(R.id.ll_text);

        cameraBtn.setOnClickListener(this);
        detectBtn.setOnClickListener(this);
        storeageBtn.setOnClickListener(this);

        if(imageBitmap == null){
            imageBitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.ic);
            mImageView.setImageBitmap(imageBitmap);
        }

    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            mImageView.setImageBitmap(imageBitmap);
        }
    }

    @Override
    public void onClick(View view) {
        if (view == cameraBtn) {
            dispatchTakePictureIntent();
        } else if (view == detectBtn) {
            detectText();
        }else if (view == storeageBtn) {
            storagText();
        }
    }

    private  void storagText(){
        Bitmap bmp = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + File.separator + "testing.png");
        mImageView.setImageBitmap(bmp);
    }

    private void detectText() {
        FirebaseVisionImage image = FirebaseVisionImage.fromBitmap(imageBitmap);
//        create instance of firebase detector
        FirebaseVisionTextRecognizer detector = FirebaseVision.getInstance().getOnDeviceTextRecognizer();
        detector.processImage(image).addOnSuccessListener(new OnSuccessListener<FirebaseVisionText>() {
            @Override
            public void onSuccess(FirebaseVisionText firebaseVisionText) {
                processText(firebaseVisionText);
            }
        });
    }

    private void processText(FirebaseVisionText text) {
//        mLlView.removeAllViews();
//        create a list to store the textblocks
        List<FirebaseVisionText.TextBlock> blocks = text.getTextBlocks();
        if (blocks.size() == 0) {
            Toast.makeText(this, "empty text", Toast.LENGTH_SHORT).show();
            return;
        }


        mContext = this;
        for (FirebaseVisionText.TextBlock textBlock : text.getTextBlocks()) {
            String sText = textBlock.getText();
            TextView tv = new TextView(mContext);

//            tv.setText(sText);

            mAry.add(sText);

//            mLlView.addView(tv);
        }

    }
}


