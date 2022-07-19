package ex01;
import java.util.Scanner;

public class MainApp {

    static boolean finder = true;
    public static void main(String[] args) {
        int number;
        Scanner scan = new Scanner(System.in);

        System.out.println("Input digit");
        if (scan.hasNextInt()) {
            number = scan.nextInt();
            number = finderPrime(number);
            if (finder){
                System.out.println("true " + number);
            }
            else {
                System.out.println("false " + number);
            }

            scan.close();
        }
    }

    public static int finderPrime(int number)
    {
        int i = 2;

        if (number <= 1) {
            System.err.println("Illegal argument");
            System.exit(-1);
        }
        while (number >= i * i)
        {
            if (number % i == 0)
            {
                finder = false;
                return (i - 1);
            }
            i++;
        }
        return (i - 1);
    }
}
