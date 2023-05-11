package com.example.n3333.practice;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

public class myTask extends AsyncTask<String, Integer, String> {

    int i = 0;
    TextView textView;
    Context context;
    Button btns;
    ProgressDialog progressDialog;


    myTask(Context context, TextView textView, Button button){
        this.context = context;
        this.textView = textView;
        this.btns = button;
    }

    @Override
    protected String doInBackground(String... voids) {
        synchronized (this) {
            while (i < 10) {

                try {
                    wait(1000);
                    i++;
                    publishProgress(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        
        return "OnPostUpdate Completed";
    }

    @Override
    protected void onPreExecute() {

        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("onPreExecute method  called....");
        progressDialog.setMax(10);
        progressDialog.setProgress(0);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.show();

        Log.d("TAG","doPreExecute method Called...");
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String result) {
        textView.setText(result);
        btns.setEnabled(true);
        progressDialog.hide();

        Log.d("TAG","doPostExecute method Called...");
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        int progress = values[0];
        progressDialog.setProgress(progress);
        textView.setText("OnProgressUpdate Medthd Called");
        Log.d("TAG","onProgressUpdate method Called...");
    }

}
