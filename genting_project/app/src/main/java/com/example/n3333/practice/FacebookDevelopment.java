package com.example.n3333.practice;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FacebookDevelopment extends AppCompatActivity {

    private Button mBtn, mImageShare;
    private TextView mTvHaskey;
    private Switch mSwAutoLogged;
    private AppEventsLogger logger;
    private CallbackManager callbackManager;
    private LoginButton loginButton;
    private String testing;
    private EditText edt_event;
    private TextView txt_eventResult;

    JSONObject response, profile_pic_data, profile_pic_url;
    int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    ShareDialog shareDialog;   // setup for new implicit intent
    public static final int PICK_IMAGE = 1,PICK_GALLERY_IC_FRONT =2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook_development);

        mBtn = findViewById(R.id.btn);
        mTvHaskey = findViewById(R.id.txt_hashkey);
        mSwAutoLogged = findViewById(R.id.sw_autologevent);
        loginButton = findViewById(R.id.login_button);
        edt_event = findViewById(R.id.edt_event);
        txt_eventResult = findViewById(R.id.txt_event_result);
        mImageShare = findViewById(R.id.imageShare);

        mImageShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postPhoto();
            }
        });


        Button button = (Button) findViewById(R.id.button);
        shareDialog = new ShareDialog(this);  // initialize facebook shareDialog.
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (ShareDialog.canShow(ShareLinkContent.class)) {
                    ShareLinkContent linkContent = new ShareLinkContent.Builder()
                            .setContentTitle("Android Facebook Integration and Login Tutorial")
                            .setImageUrl(Uri.parse("https://www.studytutorial.in/" +
                                    "wp - content / uploads / 2017 / 02 / FacebookLoginButton - min - 300x136.png"))
                            .setContentDescription(
                                    "This tutorial explains how to integrate Facebook and Login through Android Application")
                            .setContentUrl(Uri.parse("https://www.studytutorial.in/android - facebook - integration - and - login - tutorial"))
                            .build();
                    shareDialog.show(linkContent);  // Show facebook ShareDialog
                }
            }
        });

        // check current state of a Switch (true or false).
        Boolean switchState = mSwAutoLogged.isChecked();
        logger = AppEventsLogger.newLogger(this);

        logUnlockAchievementEvent("testing123");

        callbackManager = CallbackManager.Factory.create();
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();


//
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code
                        Toast.makeText(FacebookDevelopment.this, "onSuccess", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancel() {
                        // App code
                        Toast.makeText(FacebookDevelopment.this, "onCancel", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                        Toast.makeText(FacebookDevelopment.this, "onError", Toast.LENGTH_SHORT).show();
                    }
                });


//        loginButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                LoginManager.getInstance().logInWithReadPermissions(FacebookDevelopment.this, Arrays.asList("public_profile"));
//
//            }
//        });

        mSwAutoLogged.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
//                    FacebookSdk.setAutoLogAppEventsEnabled(true);
                    logContactEvent();
//                    Toast.makeText(FacebookDevelopment.this, "On = " + FacebookSdk.getCodelessDebugLogEnabled(), Toast.LENGTH_SHORT).show();
                } else {
                    FacebookSdk.setAutoLogAppEventsEnabled(false);
                    Toast.makeText(FacebookDevelopment.this, "Off = " + logger, Toast.LENGTH_SHORT).show();
                }
            }
        });

        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                fb step 4
//                getHashKey();
//
//                testing = edt_event.getText().toString().trim();
//                txt_eventResult.setText(testing);
//
//                logCompletedRegistrationEvent("final Test completeRegister");
//
//                String message = "https://www.google.com/";
//                Intent share = new Intent(Intent.ACTION_SEND);
//                share.setType("text/plain");
//                share.putExtra(Intent.EXTRA_TEXT, message);
//
//                startActivity(Intent.createChooser(share, "facebook"));
                String link = "http://testing.com.my/test";

                ShareDialog shareDialog;
                FacebookSdk.sdkInitialize(FacebookDevelopment.this);
                shareDialog = new ShareDialog(FacebookDevelopment.this);
//                ShareLinkContent linkContent = new ShareLinkContent.Builder()
//                        .setContentUrl(Uri.parse(link)).build();
//                shareDialog.show(linkContent);
            }
        });


        // ATTENTION: This was auto-generated to handle app links.
        Intent appLinkIntent = getIntent();
        String appLinkAction = appLinkIntent.getAction();
        Uri appLinkData = appLinkIntent.getData();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void getHashKey() {
        PackageInfo info;
        try {
            info = getPackageManager().getPackageInfo("com.example.n3333.myapplication", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String something = new String(Base64.encode(md.digest(), 0));
                //String something = new String(Base64.encodeBytes(md.digest()));
                Log.i("TAGS", something);
                mTvHaskey.setText(something);
            }

            Bundle params = new Bundle();
            params.putString(AppEventsConstants.EVENT_PARAM_CONTENT, "final Test tutorial content");
            params.putString(AppEventsConstants.EVENT_PARAM_CONTENT_ID, "final Test tutorial content ID");
            params.putInt(AppEventsConstants.EVENT_PARAM_SUCCESS, true ? 1 : 0);
            logger.logEvent(AppEventsConstants.EVENT_NAME_COMPLETED_TUTORIAL, params);

        } catch (PackageManager.NameNotFoundException e1) {
            Log.e("name not found", e1.toString());
        } catch (NoSuchAlgorithmException e) {
            Log.e("no such an algorithm", e.toString());
        } catch (Exception e) {
            Log.e("exception", e.toString());
        }

    }


    /**
     * This function assumes logger is an instance of AppEventsLogger and has been
     * created using AppEventsLogger.newLogger() call.
     */
    public void logCompletedRegistrationEvent(String registrationMethod) {
        Bundle params = new Bundle();
        params.putString(AppEventsConstants.EVENT_PARAM_REGISTRATION_METHOD, registrationMethod);
        logger.logEvent(AppEventsConstants.EVENT_NAME_COMPLETED_REGISTRATION, params);
    }

    /**
     * This function assumes logger is an instance of AppEventsLogger and has been
     * created using AppEventsLogger.newLogger() call.
     */
    public void logUnlockAchievementEvent(String description) {
        Bundle params = new Bundle();
        params.putString(AppEventsConstants.EVENT_PARAM_DESCRIPTION, description);
        logger.logEvent(AppEventsConstants.EVENT_NAME_ACHIEVED_LEVEL, params);
    }

    /**
     * This function assumes logger is an instance of AppEventsLogger and has been
     * created using AppEventsLogger.newLogger() call.
     */
    public void logContactEvent() {
//        logger.logEvent(AppEventsConstants.EVENT_NAME_CONTACT);
    }

    private void postPhoto() {



//        Intent pickPass = new Intent(Intent.ACTION_PICK);
//        pickPass.setDataAndType(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
//        startActivityForResult(pickPass, PICK_GALLERY_IC_FRONT);

//        Bitmap image = BitmapFactory.decodeResource(this.getResources(), R.drawable.bg);
//        SharePhoto photo = new SharePhoto.Builder()
//                .setBitmap(image)
//                .build();
//        SharePhotoContent content = new SharePhotoContent.Builder()
//                .addPhoto(photo)
//                .build();
//        ShareDialog shareDialog;
//        shareDialog = new ShareDialog(FacebookDevelopment.this);
//        shareDialog.show(content);
    }
}
