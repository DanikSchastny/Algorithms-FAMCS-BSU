import java.io.*;
import java.util.*;

public class Main implements Runnable {

    @Override
    public void run() {
        try {
            FileReader fileReader = new FileReader("input.txt");

            Scanner scanner = new Scanner(fileReader);
            int n = scanner.nextInt();
            int[] array = new int[n + 1];
            int[][] sollution = new int[n + 1][n + 1];

            int f = 0;
            while(scanner.hasNext()){
                if(f <= 1) {
                    array[f] = scanner.nextInt();
                    ++f;
                }
                else {
                    scanner.nextInt();
                    array[f] = scanner.nextInt();
                    ++f;
                }
            }

            scanner.close();
            fileReader.close();

            for (int k = 2; k < n + 1; ++k) {
                for (int i = 1; i < n - k + 2; ++i) {
                    int j = i + k - 1;
                    sollution[i][j] = Integer.MAX_VALUE;
                    for (int c = i; c < j; ++c) {
                        int term = sollution[i][c] + sollution[c + 1][j] + array[i - 1] * array[c] * array[j];
                        if(sollution[i][j] > term){
                            sollution[i][j] = term;
                        }
                    }
                }
            }


            FileWriter out = new FileWriter("output.txt");
            out.write(sollution[1][n] + "");
            out.close();
        }
        catch (Exception e){}
    }


    public static void main(String[] args) {
        new Thread(null, new Main(), "", 64 * 1024 * 1024).start();
    }
}