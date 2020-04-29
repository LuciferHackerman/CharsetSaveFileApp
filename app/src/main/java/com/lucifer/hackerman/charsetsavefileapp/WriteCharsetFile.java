package com.lucifer.hackerman.charsetsavefileapp;

import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class WriteCharsetFile {
    final String LOG = "Write File Tag >>>";
    String fileName;
    String textData;
    File sdPath;

    public void setPathFilenameData(String FileName, String data, File path) {
        fileName = FileName;
        textData = data;
        sdPath = path;
    }

    public void writeCharsetFile() {
        try {
            PrintWriter file = new PrintWriter(sdPath + "/" + fileName, "windows-1251");
            file.write(textData);
            file.close();
            Log.d(LOG, sdPath.getAbsolutePath() + "/" + fileName + " <!Created!>");
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
