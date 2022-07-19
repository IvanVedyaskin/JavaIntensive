package ex04;

import java.util.Scanner;

public class MainApp {
    private static final int CODE_VALUE = 65535;
    private static final int TOP_CHART = 10;

    public static void main(String[] args) {
        String readLine;
        char[]  str;
        short[] tmp;
        Scanner scan = new Scanner(System.in);

        readLine = scan.nextLine();
        str = readLine.toCharArray();
        tmp = strWithCount(str);
        printTop(tmp, setTop(tmp));
    }

    public static short [] strWithCount(char[] str) {
        short[] tmp = new short[CODE_VALUE];

        for (int i = 0; i < str.length; i++) {
            tmp[str[i]]++;
        }
        return tmp;
    }

    public static int[] setTop(short[] str) {
        int[] topChart = new int[TOP_CHART];
        int tmp;
        int min;
        int index;

        for (int i = 0; i < str.length; i++) {
            index = 0;
            if (str[i] > 0) {
                min = str[topChart[0]];
                for (int j = 1; j < topChart.length; j++) {
                    if (min > str[topChart[j]]) {
                        min = str[topChart[j]];
                        index = j;
                    }
                }
                topChart[index] = i;
            }
        }
        for (int i = 0; i < topChart.length - 1; i++) {
            for (int j = 0; j < topChart.length - 1; j++) {
                if (str[topChart[j]] < str[topChart[j + 1]]) {
                    tmp = topChart[j];
                    topChart[j] = topChart[j + 1];
                    topChart[j + 1] = tmp;
                }
            }
        }
        return topChart;
    }

    public static void printTop(short[] str, int[] topChart) {
        int max = str[topChart[0]];
        byte maxHeight = (byte) (Math.min(max, 10));
        byte totalLines = (byte) (2 + maxHeight);
        byte[] graphs = new byte[TOP_CHART];

        for (int i = 0; i < TOP_CHART; i++) {
            if (max <= 10) {
                graphs[i] = (byte) str[topChart[i]];
            } else {
                graphs[i] = (byte) (str[topChart[i]] * 10 / max);
            }
        }
        for (byte o : graphs){
            System.out.println(o);
        }
        System.out.println();
        for (int i = 0; i < totalLines; i++) {
            for (int j = 0; j < TOP_CHART; j++) {
                if (topChart[j] != 0) {
                    if (i + graphs[j] + 2 == totalLines) {
                        System.out.printf("%3d", str[topChart[j]]);
                    } else if (i == totalLines - 1) {
                        System.out.printf("%3c", topChart[j]);
                    } else if (i + graphs[j] >= maxHeight) {
                        System.out.printf("%3c", '#');
                    }
                    if (j != TOP_CHART - 1 && topChart[j + 1] != 0 && i + graphs[j + 1] >= maxHeight) {
                        System.out.printf("%c", ' ');
                    }
                }
            }
            System.out.println();
        }
    }
}
