package ex02;

import java.lang.reflect.Array;
import java.util.Random;

public class Program {

    static int summaryThreads = 0;

    public static void main(String[] args) {

        int arraySize = 0;
        int threadsCount = 0;
        if (args.length == 2){
            if (args[0].startsWith("--arraySize=") && args[1].startsWith("--threadsCount=")){
                try{
                    arraySize = Integer.parseInt(args[0].substring(args[0].indexOf("=") + 1));
                    threadsCount = Integer.parseInt(args[1].substring(args[1].indexOf("=") + 1));
                }
                catch (NumberFormatException e){
                    System.err.println("Argument must be number");
                    System.exit(1);
                }
            }
        }else{
            System.err.println("Argument is not valid");
            System.exit(1);
        }

        final int ARRAY_SIZE = arraySize;
        final int THREADS_COUNT = threadsCount;
        int[] arr = new int[arraySize];

        for (int i = 0; i < arraySize; i++){
            arr[i] =  (int) (Math.random() * (2000 + 1)) - 1000;
        }

        Thread[] myThreads = new Thread[THREADS_COUNT];
        int mod = ARRAY_SIZE % THREADS_COUNT;
        int div = ARRAY_SIZE / THREADS_COUNT;
        Program program = new Program();

        for (int o: arr){
            summaryThreads += o;
        }
        System.out.println("Main thread = " + summaryThreads);
        summaryThreads = 0;

        for (int i = 0; i < THREADS_COUNT; i++){
            int[] tmp;
            final int finalI = i;
            if (i != THREADS_COUNT - 1) {
                tmp = new int[div];
                System.arraycopy(arr, (div) * i, tmp, 0,div);
            }else{
                tmp = new int[div + mod];
                System.arraycopy(arr, (div) * i, tmp, 0,(div + mod));
            }
            myThreads[i] = new Thread(() -> program.sum(tmp, finalI, div, ARRAY_SIZE, THREADS_COUNT));
        }

        for (Thread o: myThreads){
            o.start();
        }

        try{
            for (Thread o: myThreads){
                o.join();
            }
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Sum by threads: " + summaryThreads);
    }

    public void sum(int[] arr, int index, int start, int len, int countThread){
        long sum = 0;
        for (int o: arr){
            sum += o;
        }
        if (index != 0 && index != countThread - 1) {
            System.out.println("Thread " + index + ": " + "from " + (index * arr.length) + " to " + (index * arr.length + arr.length - 1) + " sum is " + sum);
        }
        else if (index == 0){
            System.out.println("Thread " + index + ": " + "from " + (index * start) + " to " + (arr.length - 1) + " sum is " + sum);
        }
        else{
            System.out.println("Thread " + index + ": " + "from " + (len - arr.length) + " to " + (len - 1) + " sum is " + sum);
        }
        summaryThreads += sum;
    }

    public void parsing(String[] args){

    }

}