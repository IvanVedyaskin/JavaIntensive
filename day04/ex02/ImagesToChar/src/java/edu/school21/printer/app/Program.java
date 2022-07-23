package ex02.ImagesToChar.src.java.edu.school21.printer.app;

import com.diogonunes.jcdp.color.ColoredPrinter;
import com.diogonunes.jcdp.color.api.Ansi;

import ex02.ImagesToChar.src.java.edu.school21.printer.logic.ConverterToArray;


import java.awt.*;

public class Program {
    public static void main(String[] args) {
        if (args.length == 2 && args[0].startsWith("--white=") && args[1].startsWith("--black=")){
            String white = null;
            String black = null;
            try{
                white = args[0].substring(args[0].indexOf("=") + 1);
                black = args[1].substring(args[1].indexOf("=") + 1);
                try{
                    Ansi.BColor.valueOf(white);
                    Ansi.BColor.valueOf(black);
                }
                catch (IllegalArgumentException e){
                    System.err.println("This color not found");
                    System.exit(1);
                }
            }
            catch (NumberFormatException e){
                System.err.println("Argument is not int");
                System.exit(1);
            }
            ColoredPrinter ce = new ColoredPrinter();
            ConverterToArray converterToArray = new ConverterToArray("target/resources/it.bmp", '0', '.');
            char[][] arr = converterToArray.toArray();
            for (int i = 0; i < arr[0].length; i++){
                for (int j = 0; j < arr.length; j++){
                    if (arr[j][i] == '.'){
                        ce.print(" ", Ansi.Attribute.NONE, Ansi.FColor.NONE, Ansi.BColor.valueOf(white));
                    }
                    else{
                        ce.print(" ", Ansi.Attribute.NONE, Ansi.FColor.NONE, Ansi.BColor.valueOf(black));
                    }
                }
                System.out.println();
            }
        }
        else{
            System.err.println("Arguments are non-valid");
            System.exit(1);
        }
    }
}
