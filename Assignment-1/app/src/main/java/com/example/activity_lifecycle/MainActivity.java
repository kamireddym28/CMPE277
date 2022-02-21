package com.example.activity_lifecycle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.service.quicksettings.Tile;
import android.view.View;
import android.widget.TextView;
import android.os.Bundle;
import android.view.Gravity;

public class MainActivity extends AppCompatActivity {
    public static int counter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        counter = counter+1;
        TextView threadCounter = (TextView)findViewById(R.id.threadCounter);
        String increment = String.format("%04d",counter);
        threadCounter.setText("Thread Counter: "+increment);
    }
    @Override
    protected void onStart() {
        super.onStart();
    }
    @Override
    protected void onPause() {
        super.onPause();
    }
    @Override
    protected void onResume() {
        super.onResume();
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        counter = counter+1;
        TextView threadCounter = (TextView)findViewById(R.id.threadCounter);
        String increment = String.format("%04d",counter);
        threadCounter.setText("Thread Counter: "+increment);
    }
    @Override
    protected void onStop() {
        super.onStop();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    public void checkActivityB(View view){
        Intent intent = new Intent(MainActivity.this, ActivityB.class);
        startActivity(intent);
    }

    public void checkDialog(View view){
        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this, R.style.MyAlertDialogTheme);
        dialog.setTitle("Simple Dialog");
        dialog.setNeutralButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface simpleDialog, int which) {
                        simpleDialog.cancel();
                    }
                }
        );
        AlertDialog showDialog = dialog.create();;
        showDialog.show();
    }

    public void endMainActivity(View view) {
        MainActivity.this.finish();
    }

}