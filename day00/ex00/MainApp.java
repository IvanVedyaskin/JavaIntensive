package ex00;

public class MainApp {
    public static void main(String[] args) {
        final int number;

        number = 479598;
        System.out.println(sumNumber(number));
    }

    public static int sumNumber(int number)
    {
        int i = 0;
        int count = 0;

        if (number == 0) {
            System.out.println("Number must be six-digit!");
            return 0;
        }
        while (number != 0)
        {
            count += number % 10;
            number /= 10;
            i++;
        }
        if (i != 6)
        {
            System.out.println("Number must be six-digit!");
            return 0;
        }
        return count;
    }
}
