package ex00;

import java.io.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) throws IOException {
        HashMap<String, String> inputSignature = new HashMap<>();
        StringBuilder buf = new StringBuilder();
        int x = 0;
        try {
            FileInputStream fis = new FileInputStream("/Users/mmeredit/IdeaProjects/day02/src/main/java/ex00/signatures.txt");
            while ((x = fis.read()) != -1){
                if (x == '\n' || fis.available() == 0){
                    String[] tmp = buf.toString().split(", ");
                    inputSignature.put(tmp[1].trim(), tmp[0]);
                    buf.setLength(0);
                }
                else{
                    buf.append((char) x);
                }
            }
            fis.close();
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }

        Scanner scan = new Scanner(System.in);
        try{
            FileInputStream fis;
            FileOutputStream fos = new FileOutputStream("result.txt");
            String input;
            while (true) {
                input = scan.nextLine();
                if (input.equals("42")){
                    fos.close();
                    break;
                }
                fis = new FileInputStream(input);
                for (int i = 0; i < 10; i++){
                    x = fis.read();
                    if (x <= 0){
                        break;
                    }
                    buf.append(String.format("%02X ", x));
                }
                for (String o: inputSignature.keySet()){
                    if (buf.toString().startsWith(o)){
                        fos.write(inputSignature.get(o).getBytes());
                        fos.write('\n');
                        System.out.println("PROCESSING");
                        break;
                    }
                }
                buf.setLength(0);
            }
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        scan.close();
    }
}

// /Users/mmeredit/Desktop/aaaaa.png
// /Users/mmeredit/Desktop/gul_java_02.zip