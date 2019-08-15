import java.io.*;
import java.util.*;


public class Main {

    public static void main(String[] args) throws Exception {

        FileReader fileReader = new FileReader("in.txt");
        Scanner scanner = new Scanner(fileReader);


        String[] numbers = scanner.nextLine().split(" ");
        int cityAmmount = Integer.parseInt(numbers[0]);
        int wayAmmount = Integer.parseInt(numbers[1]);
        int[][] ways = new int[cityAmmount][cityAmmount];
        
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < cityAmmount; ++i) {
            map.put(scanner.nextLine(), i);
        }

        for (int i = 0; i < wayAmmount; ++i) {
            String[] way = scanner.nextLine().split(" ");
            ways[map.get(way[0])][map.get(way[1])] = ways[map.get(way[1])][map.get(way[0])] = 1;
        }

        scanner.close();
        fileReader.close();



        int[][] distances = new int[cityAmmount][cityAmmount];
        distances[0][0] = 1;

        for (int i = 1; i < cityAmmount; ++i) {
            for (int j = 0; j < i + 1; ++j) {
                if (i != j) {
                    for (int k = 0; k < i; ++k) {
                        if ((ways[k][i] == 1) && (j == 0 || k != j) && distances[k][j] + 1 > distances[i][j] && distances[k][j] != 0) {
                            distances[i][j] = distances[j][i] = distances[k][j] + 1;
                        }
                    }
                } else {
                    for (int k = 0; k < i; ++k) {
                        if (ways[k][i] == 1)
                            if (distances[k][i] > distances[i][i]) {
                                distances[i][i] = distances[k][i];
                            }

                    }
                }
            }
        }

        String result = (distances[cityAmmount - 1][cityAmmount - 1] >= 2) ? Integer.toString(distances[cityAmmount - 1][cityAmmount - 1]) : "No solution";
        FileWriter fileWriter = new FileWriter("out.txt");
        fileWriter.write(result);

        fileWriter.close();
    }
}