package ex00.ImagesToChar.src.java.edu.school21.printer.logic;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ConverterToArray {
    private String path;
    private char black;
    private char white;

    public ConverterToArray(String path, char black, char white) {
        this.black = black;
        this.white = white;
        this.path = path;
    }

    public char[][] toArray(){
        char[][] arr = null;
        try {
            BufferedImage bufferedImage = ImageIO.read(new File(path));
            arr = new char[bufferedImage.getHeight()][bufferedImage.getTileWidth()];
            for (int i = 0; i < bufferedImage.getWidth(); i++){
                for (int j = 0; j < bufferedImage.getHeight(); j++){
                    if (bufferedImage.getRGB(i,j) == Color.BLACK.getRGB()){
                        arr[i][j] = black;
                    }else if (bufferedImage.getRGB(i,j) == Color.WHITE.getRGB()){
                        arr[i][j] = white;
                    }
                    else{
                        System.out.println("Error");
                        System.exit(1);
                    }
                }
            }
        }
        catch (IOException e) {
            System.err.println("File is not read");
            System.exit(1);
        }
        return arr;
    }
}
