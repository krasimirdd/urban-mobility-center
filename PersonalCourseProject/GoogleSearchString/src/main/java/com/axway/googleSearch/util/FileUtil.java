package com.axway.googleSearch.util;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static com.axway.googleSearch.constants.Constants.FILE_NAME;

public class FileUtil {

    public static File getResultFile(String parent, String child) {
        File resultDir = new File(parent, child);
//        try {
//            FileUtils.deleteDirectory(resultDir);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        File resultFile = new File(resultDir, FILE_NAME);

        try {
            Files.createDirectory(resultDir.toPath());
            Files.createFile(resultFile.toPath()).toFile();
        } catch (IOException e) {
            System.out.println("file already exists, will override it");
        }
        return resultFile;
    }
}