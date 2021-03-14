package com.zserg.jpairsubs.utils;

import com.ibm.icu.text.CharsetDetector;
import com.ibm.icu.text.CharsetMatch;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;

public class TextUtils {
    public static String detectCharset(byte[] bytes){
        CharsetDetector detector = new CharsetDetector();
        detector.setText(bytes);
        CharsetMatch detect = detector.detect();
        String name = detect.getName();
        return name;
    }
}
