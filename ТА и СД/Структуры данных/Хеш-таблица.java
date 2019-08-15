import java.io.FileReader;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) throws IOException {
        FileReader fileReader = new FileReader("input.txt");
        Scanner scanner = new Scanner(fileReader);
        int m, c, N;
        m = scanner.nextInt(); c = scanner.nextInt(); N = scanner.nextInt();
        int[] table = new int[m];
        for(int i = 0; i < m; ++i){
            table[i] = -1;
        }
        for (int i = 0; i < N; ++i){
            int element = scanner.nextInt();
            int j = 0; boolean f = true;
            while (f){
                int idx = ((element % m) + c * j) % m;
                if (table[idx] == element || table[idx] == -1){
                    f = false;
                    table[idx] = element;
                }
                ++j;
            }
        }
        scanner.close();
        FileWriter fileWriter = new FileWriter("output.txt");
        for(int j = 0; j < m; ++j){
            fileWriter.write(table[j] + " ");
        }

        fileWriter.close();

    }
}