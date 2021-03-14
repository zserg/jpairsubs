package com.zserg.jpairsubs.service;

import com.ibm.icu.text.CharsetDetector;
import com.ibm.icu.text.CharsetMatch;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

public class UtilTest {
    @Test
    public void charsetDetectionTest() throws IOException {
//        File file = new File("cp1251.srt");
        File file = new File("src/test/resources/cp1251.srt");
        byte[] bytes = Files.readAllBytes(file.toPath());
        CharsetDetector detector = new CharsetDetector();
        detector.setText(bytes);
        CharsetMatch detect = detector.detect();
        String name = detect.getName();
        String s = new String(bytes, Charset.forName(name));
        assertTrue(true);

    }
}
