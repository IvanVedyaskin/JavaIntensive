package ex02;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int numb;
        int count = 0;

        while ((numb = scan.nextInt()) != 42) {
            count += checkSumInPrime(sumNum(numb));
        }
        System.out.println("Count of coffee-request - " + count);
    }
    public static int sumNum(int num)
    {
        int sum = 0;

        while (num != 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }

    public static int checkSumInPrime(int sum)
    {
        int i = 2;

        while (sum >= i * i) {
            if (sum % i++ == 0) {
                return 0;
            }
        }
        return 1;
    }
}
