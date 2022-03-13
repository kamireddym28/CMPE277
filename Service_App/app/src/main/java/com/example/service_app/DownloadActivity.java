package com.example.service_app;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import java.io.File;

import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.OutputStream;
import java.io.FileOutputStream;

import java.net.URL;
import java.net.URLConnection;

public class DownloadActivity extends Service {

    private boolean success = false;
    public static final String NOTIFICATION = "receiver";

    //private final IBinder binder = new MyBinder();

    public class MyBinder extends Binder {
        DownloadActivity getService() {
            return DownloadActivity.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String urls = intent.getStringExtra("urls");
        String files = intent.getStringExtra("files");
        new downloadFile_URL().execute(urls,files);
        return START_STICKY;
    }

    class downloadFile_URL extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... obj) {
            int count;
            File output_path = new File(getExternalFilesDir(null).toString(),obj[1]);
            try {

                URL urls = new URL(obj[0]);
                URLConnection connection = urls.openConnection();
                connection.connect();

                InputStream input = new BufferedInputStream(urls.openStream(), 8192);
                OutputStream output = new FileOutputStream(output_path.getPath());

                byte data[] = new byte[1024];

                long total = 0;
                while ((count = input.read(data)) != -1) {
                    total += count;
                    output.write(data, 0, count);
                }
                output.flush();
                output.close();
                input.close();
                success = true;
            }catch (Exception e) {
                Log.e("Error: ", e.getMessage());
                e.printStackTrace();
            }
            fileDestination(obj[1]);
            return null;
        }

        private void fileDestination(String files) {
            Intent intent = new Intent(NOTIFICATION);
            intent.putExtra("files", files);
            sendBroadcast(intent);
        }

        @Override
        protected void onPostExecute(String urls) {
            stopSelf();
        }


    }

}