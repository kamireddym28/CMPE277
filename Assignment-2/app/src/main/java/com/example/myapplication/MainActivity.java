package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText website, phone;
    Button btn_launch, btn_ring;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        website = (EditText) findViewById(R.id.editText);
        btn_launch = (Button) findViewById(R.id.btnLaunch);
        phone = (EditText) findViewById(R.id.editText1);
        btn_ring = (Button) findViewById(R.id.btnRing);

        btn_launch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = website.getText().toString();
                if(! (url.startsWith("http://") || url.startsWith("https://"))) {
                    url = "http://" + url;
                }
                if (!url.isEmpty()) {
                    Intent launchIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(launchIntent);
                }else {
                    Toast.makeText(getApplicationContext(), "URL is Invalid", Toast.LENGTH_LONG).show();
                }

            }
        });
        btn_ring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNum = phone.getText().toString();
                if (!phoneNum.isEmpty() && ((!phoneNum.startsWith("+1") && phoneNum.length() == 10)
                ||(phoneNum.startsWith("+1") && phoneNum.length() == 12))){
                    Intent ringIntent = new Intent(Intent.ACTION_DIAL);
                    ringIntent.setData(Uri.parse("tel:" + phoneNum));
                    startActivity(ringIntent);
                }else {
                    Toast.makeText(getApplicationContext(), "Phone Number is Invalid", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
    public void Finish(View v) {
        finish();
    }
}
