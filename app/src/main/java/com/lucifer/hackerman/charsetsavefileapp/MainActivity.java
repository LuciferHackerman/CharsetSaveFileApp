package com.lucifer.hackerman.charsetsavefileapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.text.MessageFormat;

public class MainActivity extends AppCompatActivity {
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
        String textData = inputText.getText().toString();
        showText(textData);

        File path = new File(getExternalFilesDir(null) + "/lab");
        path.mkdirs();

        WriteCharsetFile wcf = new WriteCharsetFile();
        wcf.setPathFilenameData(FileName, textData, path);
        wcf.writeCharsetFile();

        TextView pathToFile = (TextView) findViewById(R.id.fileLocation);
        pathToFile.setText(MessageFormat.format("{0}/{1}", path.getAbsolutePath(), FileName));
        inputText.setText("");
    }

    private void showText(String s) {
        TextView showText = (TextView) findViewById(R.id.tvrShow);
        showText.setText(s);
    }
}