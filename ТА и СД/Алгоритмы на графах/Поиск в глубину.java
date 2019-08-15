import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main{
    static int[][] matrix;
    static int[] marks;
    static int[] way;
    static int n;
    static int mark;

    public static void main(String[] args) throws IOException {
        FileReader fileReader = new FileReader("input.txt");
        Scanner scanner = new Scanner(fileReader);

        mark = 0;
        n = scanner.nextInt();
        matrix = new int[n][n];
        marks = new int[n];
        way = new int[n];

        for(int i = 0; i < n; ++i){
            marks[i] = 0;
        }

        for(int i = 0; i < n; ++i){
            for(int j = 0; j < n; ++j){
                matrix[i][j] = scanner.nextInt();
            }
        }
        scanner.close();
        fileReader.close();

        for(int i = 0; i < n; ++i){
            if(marks[i] == 0)
                DFS(i);
        }


        FileWriter fileWriter = new FileWriter("output.txt");
        for(int i = 0; i < marks.length; ++i){
            fileWriter.write(marks[i] + " ");
        }

        fileWriter.close();
    }

    private static void DFS(int i ){
        ++mark;
        marks[i] = mark;
        for(int j = 0; j < n; ++j){
            if(matrix[i][j] == 1 && marks[j] == 0)
                DFS(j);
        }
      }
    }