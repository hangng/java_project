package com.practice.genting_test.components;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;

import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

public class FirebaseService {
    private static FirebaseFirestore fbStore;


    public static boolean initFirebase(Context context) {
        FirebaseApp.initializeApp(context);

        final boolean[] firebaseInitialized = {false};
        FirebaseApp.getInstance().getApplicationContext()
                .getContentResolver().registerContentObserver(
                        Settings.Secure.getUriFor(Settings.Secure.LOCATION_MODE), false, new ContentObserver(new Handler()) {
                            @Override
                            public void onChange(boolean selfChange) {
                                super.onChange(selfChange);
                                if (FirebaseApp.getInstance() != null) {
                                    fbStore = FirebaseFirestore.getInstance();
                                    firebaseInitialized[0] = true;
                                }
                            }
                        });

        // Wait for Firebase to initialize
        while (!firebaseInitialized[0]) {
            try {
                Thread.sleep(100);
                Log.i("TAG","waiting for firebase.....");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return firebaseInitialized[0];
    }


    public void UpdateData() {

    }

    public static void CreateData() {
        fbStore.collection("users")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Log.d("TAG", document.getId() + " => " + document.getData());
                        }
                    } else {
                        Log.d("TAG", "Error getting documents: ", task.getException());
                    }
                });

    }


}
