import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        FileReader fileReader = new FileReader("input.txt");
        Scanner scanner = new Scanner(fileReader);
        String line = scanner.nextLine();
        String[] vertexes = line.split(" ");
        int n = Integer.parseInt(vertexes[0]);
        int m = Integer.parseInt(vertexes[1]);
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; ++i)
            for (int j = 0; j < n; ++j)
                matrix[i][j] =0;

        String graph = "Yes",
                multigraph = "Yes",
                pgraph = "Yes";

        for (int i = 0; i < m; ++i) {
            int u = scanner.nextInt() - 1;
            int v = scanner.nextInt() - 1;
            ++matrix[u][v];
            ++matrix[v][u];
            if (matrix[u][v] > 1){
                graph = "No";
            }
            if (u == v){
                graph = "No";
                multigraph = "No";
            }
        }
        scanner.close();
        fileReader.close();

        FileWriter fileWriter = new FileWriter("output.txt");
        fileWriter.write(graph + "\r\n");
        fileWriter.write(multigraph + "\r\n");
        fileWriter.write(pgraph + "");
        fileWriter.close();
    }


}