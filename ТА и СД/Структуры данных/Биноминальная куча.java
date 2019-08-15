import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        FileReader fileReader = new FileReader("input.txt");
        Scanner scanner = new Scanner(fileReader);

        long term = scanner.nextLong();
        scanner.close();
        fileReader.close();
        if (term == 0) {
            FileWriter fileWriter = new FileWriter("output.txt");
            fileWriter.write(-1 + "");
            fileWriter.close();
        } else {
            FileWriter fileWriter = new FileWriter("output.txt");
            int index = 0;
            while (term > 0) {
                if (term % 2 == 1)
                    fileWriter.write(index + "\r\n");

                index++;
                term /= 2;
            }
            fileWriter.close();
        }
    }
}