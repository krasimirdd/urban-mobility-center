package com.axway.kddimitrov;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.io.*;
import java.util.Scanner;

public class Engine implements Runnable {

    private static final String server = "localhost";
    private static final int port = 21;
    private static final String user = "admin";
    private static final String pass = "admin1234";

    public void run() {
        FTPClient ftpClient = new FTPClient();
        CommandParser commandParser = new CommandParser(ftpClient);
        Scanner scanner = new Scanner(System.in);
        String option = "";

        try {
            configFtp(ftpClient);

            System.out.println("These are the files in you remote directory: \n\n");
            listFiles(ftpClient);
            System.out.println("Choose an option [ -dw | -up | -dl ]");
            option = scanner.nextLine();
            commandParser.parse(option);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeClient(ftpClient);
        }
    }

    private static void closeClient(FTPClient ftpClient) {
        try {
            if (ftpClient.isConnected()) {
                ftpClient.logout();
                ftpClient.disconnect();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static void listFiles(FTPClient ftpClient) throws IOException {
        FTPFile[] ftpFiles = ftpClient.listFiles();
        for (FTPFile file : ftpFiles) {
            System.out.println(file.getName());
        }
    }

    private static void configFtp(FTPClient ftpClient) throws IOException {
        ftpClient.connect(server, 21);
        ftpClient.login(user, pass);
        ftpClient.enterLocalPassiveMode();
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
    }
}
