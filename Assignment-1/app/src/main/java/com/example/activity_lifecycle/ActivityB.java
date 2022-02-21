package com.example.activity_lifecycle;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import static com.example.activity_lifecycle.MainActivity.counter;

public class ActivityB extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        counter++;
    }
    @Override
    protected void onPause() {
        super.onPause();
    }

    public void endActivityB(View view) {
        ActivityB.this.finish();
    }

}