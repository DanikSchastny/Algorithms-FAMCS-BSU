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

        for(int i = 0; i < n; ++i){
            for(int j = 0; j < n; ++j){
                if(scanner.nextInt() == 1){
                    P[j] = i + 1;
                }
            }
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