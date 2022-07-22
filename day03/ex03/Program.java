package ex03;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class Program {
    public static void main(String[] args) {
        int count = 0;
        LinkedList<String> myFiles = new LinkedList<>();
        if (args.length == 1){
            if (args[0].startsWith("--threadsCount=")){
                try{
                    count = Integer.parseInt(args[0].substring(args[0].indexOf("=") + 1));
                    try (BufferedReader bufferedReader = new BufferedReader(new FileReader("/Users/mmeredit/IdeaProjects/day03/src/main/java/ex03/files_urls.txt"))){
                        String tmp;
                        while ((tmp = bufferedReader.readLine()) != null){
                            myFiles.add(tmp.substring(tmp.indexOf("http")));
                        }
                    }
                    catch (IOException e) {
                        System.err.println("File not open");
                        System.exit(1);
                    }
                }
                catch (NumberFormatException e){
                    System.err.println("Argument is non-valid!");
                    System.exit(1);
                }
            }else{
                System.exit(1);
            }
        }else {
            System.exit(1);
        }

        LinkedList<MyThread> myThreads = new LinkedList<>();
        for (int i = 0; i < count; i++){
            myThreads.add(new MyThread(i + 1,myFiles));
        }
        for (int i = 0; i < count; i++){
            myThreads.get(i).start();
        }
        try{
            for (int i = 0; i < count; i++){
                myThreads.get(i).join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("END");
    }
}
