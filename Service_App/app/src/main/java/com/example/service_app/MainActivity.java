package com.example.service_app;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.os.Environment;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    EditText pdf1, pdf2, pdf3, pdf4, pdf5;
    String url1, url2, url3, url4, url5;
    String d1, d2, d3, d4, d5;
    static String path;

    String[] pdf = {
            "https://static.googleusercontent.com/media/research.google.com/en//pubs/archive/45530.pdf",
            "https://hadoop.apache.org/docs/r1.2.1/hdfs_design.pdf",
            "https://pages.databricks.com/rs/094-YMS-629/images/LearningSpark2.0.pdf",
            "https://docs.aws.amazon.com/wellarchitected/latest/machine-learning-lens/wellarchitected-machine-learning-lens.pdf",
            "https://developers.snowflake.com/wp-content/uploads/2020/09/SNO-eBook-7-Reference-Architectures-for-Application-Builders-MachineLearning-DataScience.pdf"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pdf1 = (EditText) findViewById(R.id.pdf1);
        pdf1.setText(pdf[0]);

        pdf2 = (EditText) findViewById(R.id.pdf2);
        pdf2.setText(pdf[1]);

        pdf3 = (EditText) findViewById(R.id.pdf3);
        pdf3.setText(pdf[2]);

        pdf4 = (EditText) findViewById(R.id.pdf4);
        pdf4.setText(pdf[3]);

        pdf5 = (EditText) findViewById(R.id.pdf5);
        pdf5.setText(pdf[4]);

        path = Environment.DIRECTORY_DOWNLOADS;
        registerReceiver(receiver, new IntentFilter(DownloadActivity.NOTIFICATION));

        Button buttonDownload = (Button)findViewById(R.id.startDownload);
        buttonDownload.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            url1 = pdf1.getText().toString();
            d1 = url1.substring(url1.lastIndexOf('/') + 1);

            url2 = pdf2.getText().toString();
            d2 = url2.substring(url2.lastIndexOf('/') + 1);

            url3 = pdf3.getText().toString();
            d3 = url3.substring(url3.lastIndexOf('/') + 1);

            url4 = pdf4.getText().toString();
            d4 = url4.substring(url4.lastIndexOf('/') + 1);

            url5 = pdf5.getText().toString();
            d5 = url5.substring(url5.lastIndexOf('/') + 1);

            downloadFiles(v);
        }
    });
    }
    public void downloadFiles(View v) {
        Toast.makeText(this, "Downloading...", Toast.LENGTH_SHORT).show();
        startDownload(url1,d1);
        startDownload(url2,d2);
        startDownload(url3,d3);
        startDownload(url4,d4);
        startDownload(url5,d5);
    }

    private void startDownload(String urls, String files) {
        Intent intent = new Intent(getBaseContext(), DownloadActivity.class);
        intent.putExtra("urls", urls);
        intent.putExtra("files",files);
        startService(intent);
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                String file = bundle.getString("files");
                Toast.makeText(getBaseContext(), file+" Download Successful!!",Toast.LENGTH_LONG).show();
            }
        }
    };

}
