import java.io.*;
import java.util.*;

public class Main implements Runnable {

    @Override
    public void run() {
        try {
            FileReader fileReader = new FileReader("input.txt");
            Scanner scanner = new Scanner(fileReader);

            String[] numbers = scanner.nextLine().split(" ");
            int cityAmmount = Integer.parseInt(numbers[0]);
            int wayAmmount = Integer.parseInt(numbers[1]);
            int[][] ways = new int[cityAmmount][cityAmmount];

            for (int i = 0; i < wayAmmount; ++i) {
                int m, n;

                String[] vertexes = scanner.nextLine().split(" ");
                m = Integer.parseInt(vertexes[0]);
                n = Integer.parseInt(vertexes[1]);

                ways[m - 1][n - 1] = ways[n - 1][m - 1] = 1;
            }

            scanner.close();
            fileReader.close();

            FileWriter fileWriter = new FileWriter("output.txt");


            for(int i = 0; i < cityAmmount; ++i){
                for(int j = 0; j < cityAmmount;++j){
                    if(j < cityAmmount - 1)
                    fileWriter.write(ways[i][j] +" " );
                    else
                        fileWriter.write(ways[i][j] + "");

                }
                fileWriter.write("\n");
            }
            fileWriter.close();


        }
        catch (Exception e){}
    }


    public static void main(String[] args) {
        new Thread(null, new Main(), "", 64 * 1024 * 1024).start();
    }
}