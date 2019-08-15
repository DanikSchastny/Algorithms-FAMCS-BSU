import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main{
    public static void main(String[] args) throws IOException {
        FileReader fileReader = new FileReader("input.txt");
        Scanner scanner = new Scanner(fileReader);

        int n = scanner.nextInt(), first, second;
        int[] P = new int[n];
        for(int i = 0; i < n; ++i){
            P[i] = 0;
        }
        for(int i = 0; i < n - 1; ++i){
            first = scanner.nextInt();
            second = scanner.nextInt();

            P[second - 1] = first;
        }
        scanner.close();
        fileReader.close();

        FileWriter fileWriter = new FileWriter("output.txt");
        for(int i = 0; i < P.length; ++i){
            fileWriter.write(P[i] + " ");
        }


        fileWriter.close();
    }

    }