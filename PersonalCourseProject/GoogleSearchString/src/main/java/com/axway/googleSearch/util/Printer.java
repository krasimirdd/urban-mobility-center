package com.axway.googleSearch.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Printer {
    public static void print(File resultFile, String json) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(resultFile);
        pw.write(json);
        pw.flush();
        pw.close();
    }
}
