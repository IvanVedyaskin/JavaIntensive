package ex01;

import java.io.*;
import java.util.*;

public class Program {
    public static void main(String[] args) {
        LinkedList<Alphabet> inputAlphabet = new LinkedList<>();
        Set<String> resultAlphabet = new HashSet<>();
        String[] lineFile;

        if (args.length == 2){
            for (int i = 0; i < 2; i++) {
                System.out.println(System.getenv("PWD") + "/" + args[i]);
                try (BufferedReader readFile = new BufferedReader(new FileReader(System.getenv("PWD") + "/" + args[i]))) {
                    String reader;
                    inputAlphabet.add(new Alphabet());
                    while((reader = readFile.readLine()) != null){
                        lineFile = reader.trim().split("\\s+");
                        for (String o: lineFile){
                            if (o.matches("[a-zA-Zа-яА-Я]+")) {
                                inputAlphabet.get(i).setMyVector(o);
                                resultAlphabet.add(o);
                            }
                        }
                    }
                } catch (IOException e) {
                    System.out.println("File is can not found");
                    System.exit(-1);
                }
            }
            Double similarity;
            double numerator = 0;
            double denominator1 = 0;
            double denominator2 = 0;
            Iterator<String> iter = resultAlphabet.iterator();
            String tmp;
            while(iter.hasNext()){
                tmp = iter.next();
                numerator += inputAlphabet.get(0).getValue(tmp) * inputAlphabet.get(1).getValue(tmp);
                denominator1 += inputAlphabet.get(0).getValue(tmp) * inputAlphabet.get(0).getValue(tmp);
                denominator2 += inputAlphabet.get(1).getValue(tmp) * inputAlphabet.get(1).getValue(tmp);
            }
            similarity = numerator / (Math.sqrt(denominator1) * Math.sqrt(denominator2));
            try(FileWriter writeFile = new FileWriter("dictionary.txt")){
                writeFile.write(String.format("Similarity = %.2f", similarity));
            }
            catch (IOException e){
                System.out.println("File is can not found");
                System.exit(-1);
            }
            System.out.printf("Similarity = %.2f", similarity);
        }else{
            System.out.println(args.length);
            System.exit(1);
        }
    }}