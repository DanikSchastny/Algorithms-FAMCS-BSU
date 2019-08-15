import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        FileReader fileReader = new FileReader("input.txt");
        Scanner scanner = new Scanner(fileReader);

        int n = scanner.nextInt();
        if(n == 1){
            int term = scanner.nextInt();
            scanner.close();
            fileReader.close();
            FileWriter fileWriter = new FileWriter("output.txt");
            fileWriter.write(term + "");
            fileWriter.close();
        }else  if(n == 2){
            FileWriter fileWriter = new FileWriter("output.txt");
            fileWriter.write(-1 + "");
            fileWriter.close();
        }
        else { int[] arr = new int[n + 1];
            int[] d = new int[n + 1];

            for (int i = 0; i < n; ++i) {
                arr[i] = scanner.nextInt();
                d[i] = -1;
            }

            scanner.close();
            fileReader.close();
            d[0] = arr[0];
            d[1] = Integer.MIN_VALUE;
            d[2] = arr[0] + arr[2];
            d[3] = arr[0] + arr[3];

            for (int i = 4; i <= n; ++i) {
                if (i - 3 >= 1) {
                    if (d[i - 2] >= d[i - 3]) {
                        d[i] = d[i - 2] + arr[i];
                    } else {
                        d[i] = d[i - 3] + arr[i];
                    }
                }
            }


            FileWriter fileWriter = new FileWriter("output.txt");
            fileWriter.write(d[n - 1] + "");
            fileWriter.close();
        }
    }
}