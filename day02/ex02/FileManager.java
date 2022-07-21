package ex02;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileManager {
    private String filePath;
    private File file;

    public FileManager(String filePath, File file) {
        this.filePath = filePath;
        this.file = file;
    }

    public void menu(){
        Scanner scan = new Scanner(System.in);
        System.out.println(filePath);
        while (true) {
            String[] parseCmd;
            parseCmd = scan.nextLine().split("\\s+");
            if (parseCmd[0].equals("ls")) {
                ls();
            } else if (parseCmd[0].equals("cd")) {
                cd(parseCmd);
            } else if (parseCmd[0].equals("mv")) {
                mv(parseCmd);
            } else if (parseCmd[0].equals("exit")) {
                scan.close();
                exit();
            }
        }

    }

    public void ls(){
        Set <File> folders = Arrays.stream(file.listFiles()).collect(Collectors.toSet());
        Iterator<File> iter = folders.iterator();
        while (iter.hasNext()){
            File tmp = iter.next();
            System.out.println((tmp.getName() + " " + tmp.length() + " KB"));
        }
    }

    public void cd(String[] cmd){
        if (cmd.length == 1){
            System.err.println("Enter the name of the directory");
        } else if (cmd.length == 2) {
            File newFile = new File(cmd[1]);
            if (newFile.isDirectory()) {
                this.file = newFile;
                filePath = file.getAbsolutePath();
                System.out.println(filePath);
            } else {
                System.err.println("Directory not found or it is not directory");
            }
        }else{
            System.err.println("Enter only the name of the directory");
        }
    }

    public void mv(String[] cmd){
        if (cmd.length == 3) {
            File myFile = new File(filePath +  "/" + cmd[1]);
            File newFile = new File(filePath + "/" + cmd[2]);
            if (newFile.isDirectory()){
                if (newFile.exists()){
                    try {
                        Files.move(myFile.toPath(), newFile.toPath());
                    }
                    catch (IOException e){
                        System.err.println("File can not move");
                    }
                }else{
                    System.err.println("No such directory");
                }
            }
            else{
                if(!myFile.renameTo(newFile)){
                    System.err.println(("Rename is not success"));
                }
            }
        }
        else{
            System.err.println("Arguments non-valid");
        }
    }

    public void exit(){
        System.exit(0);
    }
}
