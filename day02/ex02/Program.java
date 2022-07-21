package ex02;

import java.io.File;

public class Program {
    public static void main(String[] args) {
        String path;
        if (args.length == 1){
            if (args[0].startsWith("--current-folder=")){
                try{
                    path = args[0].substring(args[0].indexOf("=") + 1);
                    File file = new File(path);
                    FileManager fileManager = new FileManager(path, file);
                    fileManager.menu();
                }
                catch (NumberFormatException e){
                    System.err.println("Argument must be number");
                    System.exit(1);
                }
            }else{
                System.err.println("Argument is not valid");
                System.exit(1);
            }
        }else{
            System.err.println("Argument is not valid");
            System.exit(1);
        }
    }
}
