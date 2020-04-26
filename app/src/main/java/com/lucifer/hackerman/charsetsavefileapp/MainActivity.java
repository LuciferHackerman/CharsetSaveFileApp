package com.lucifer.hackerman.charsetsavefileapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class MainActivity extends AppCompatActivity {

    final String LOG_TAG = "MY_LOG >>>"; // тэг для моего лога
    final String FileName = "Win1251_Text.txt"; // название файла

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int permissionStatus = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permissionStatus == PackageManager.PERMISSION_GRANTED) {
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
    }

    public void onClick(View view) {
        EditText inputText = (EditText) findViewById(R.id.inputText);
        String s = inputText.getText().toString();
        writeFile(s);
        inputText.setText("");
        showText();
    }

    private void showText() {
        EditText inputText = (EditText) findViewById(R.id.inputText);
        TextView showText = (TextView) findViewById(R.id.tvrShow);
        showText.setText(inputText.getText().toString());
    }

    private void writeFile(String s) {
        File sdPath = new File(getExternalFilesDir(null) + "/lab3");
        try {
            PrintWriter file = new PrintWriter(sdPath + "/" + FileName, "windows-1251");
            file.write(s);
            file.close();
            TextView pathToFile = (TextView) findViewById(R.id.fileLocation);
            pathToFile.setText(sdPath.getAbsolutePath() + "/" + FileName);
            Log.d(LOG_TAG, "File location" + sdPath.getAbsolutePath() + "/" + FileName);
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}