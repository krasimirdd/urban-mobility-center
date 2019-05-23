package com.axway.kddimitrov;

import org.apache.commons.net.ftp.FTPClient;

import java.io.*;
import java.util.Scanner;

public class CommandParser {

    private final FTPClient client;
    private final Scanner scanner;

    public CommandParser(FTPClient ftpClient) {
        this.client = ftpClient;
        this.scanner = new Scanner(System.in);
    }

    public void parse(String option) throws IOException {

        if ("-dw".equals(option)) {
            System.out.println("Enter file: ");
            download(scanner.nextLine());

        } else if ("-up".equals(option)) {
            System.out.println("Enter file/location : ");
            upload(scanner.nextLine());

        } else if ("-dl".equals(option)) {
            System.out.println("Enter file: ");
            delete(scanner.nextLine());

        } else {
            System.out.println("Invalid input!!!");
        }
    }

    private void delete(String fileName) throws IOException {
        String remoteFile = fileName;

        boolean deleteSuccess = this.client.deleteFile(remoteFile);
        if (deleteSuccess) {
            System.out.println(remoteFile + " is deleted successfully");
        }
    }

    private void upload(String fileName) throws IOException {
        InputStream inputStream;
        OutputStream outputStream;

        File localFile = new File(fileName);
        String remoteFile = "testUpload" + localFile.getName().substring(localFile.getName().indexOf("."));
        inputStream = new FileInputStream(localFile);

        System.out.println("Start uploading file");
        outputStream = client.storeFileStream(remoteFile);
        byte[] uBuff = new byte[4096];
        int uRead = 0;

        while ((uRead = inputStream.read(uBuff)) != -1) {
            outputStream.write(uBuff, 0, uRead);
        }
        inputStream.close();
        outputStream.close();

        boolean completed = client.completePendingCommand();
        if (completed) {
            System.out.println(localFile.getName() + " is uploaded successfully as " + remoteFile);
        }
    }

    private void download(String fileName) throws IOException {
        OutputStream outputStream;
        InputStream inputStream;

        String remoteFile = fileName;
        File downloadFile = new File("C:\\Users\\kddun\\IdeaProjects\\ftpUtil\\src\\main\\resources\\result"
                + remoteFile.substring(remoteFile.indexOf(".")));

        outputStream = new BufferedOutputStream(new FileOutputStream(downloadFile));
        inputStream = client.retrieveFileStream(remoteFile);
        byte[] buff = new byte[4096];
        int read = -1;
        while ((read = inputStream.read(buff)) != -1) {
            outputStream.write(buff, 0, read);
        }

        boolean success = client.completePendingCommand();
        if (success) {
            System.out.println("File " + remoteFile + " has been downloaded successfully as " + downloadFile.getName());
        }
        outputStream.close();
        inputStream.close();
    }

}
