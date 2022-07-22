package ex03;

import jdk.internal.util.xml.impl.Input;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.LinkedList;

public class MyThread extends Thread{
    private int index;
    private static LinkedList<String> myFiles;
    private int fileNumber;
    private static int size;

    public MyThread(int index, LinkedList<String> myFiles) {
        this.index = index;
        MyThread.myFiles = myFiles;
        MyThread.size = myFiles.size();
    }


    @Override
    public void run(){
        while (true){
            synchronized (myFiles){
                if (myFiles.size() == 0){
                    return;
                }
            }
            try {
                URL url = new URL(getUrl());
                System.out.println("Thread-" + index + " start download file number " + fileNumber);
                InputStream urlStream = url.openStream();
                Files.copy(urlStream, Paths.get("output.txt"), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Thread-" + index + " finish download file number " + fileNumber);
            } catch (MalformedURLException e) {
                System.err.println("URL not found" + index);
            } catch (IOException e) {
                System.err.println("Stream not open " + index);
            }

        }
    }

    private String getUrl(){
        synchronized (myFiles){
            fileNumber = MyThread.size - myFiles.size() + 1;
            return myFiles.pop();
        }
    }
}
