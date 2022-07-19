package ex03;

import java.util.Scanner;

public class MainApp {
    static final String SCHOOL = "42";
    static final String WEEK = "Week ";
    static int numWeek = 0;

    public static void main(String[] args) {
        long grade = 0;
        String readLine;
        Scanner scan = new Scanner(System.in);

        readLine = scan.nextLine();
        while (!(readLine.equals(SCHOOL)) && numWeek < 18) {
            numWeek++;
            if (!readLine.equals(WEEK + numWeek)){
                printError();
            }
            grade = (grade * 10) + minGrade(scan);
            readLine = scan.nextLine();
        }
        printGrades(valueWithGrades(grade));
    }
    public static int minGrade(Scanner scan)
    {
        int input;
        int minGrade;

        minGrade = scan.nextInt();
        if (minGrade < 1 || minGrade > 9){
            printError();
        }
        for (int i = 0; i < 4; i++) {
            input = scan.nextInt();
            if (input < 1 || input > 9){
                printError();
            }
            if (input < minGrade){
                minGrade = input;
            }
        }
        scan.nextLine();
        return minGrade;
    }

    public static long valueWithGrades(long grade)
    {
        long newGrades = 0;

        while (grade != 0) {
            newGrades = (newGrades * 10) + (grade % 10);
            grade /= 10;
        }
        return newGrades;
    }
    public static void printError()
    {
        System.err.println("IllegalArgument");
        System.exit(-1);
    }

    public static void printGrades(long grade)
    {
        while (grade != 0) {
            for (int i = 0; i < grade % 10; i++){
                System.out.print("=");
            }
            System.out.println(">");
            grade /= 10;
        }
    }
}
