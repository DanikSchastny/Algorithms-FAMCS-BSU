import java.io.*;
import java.util.*;

public class Main implements Runnable {

    @Override
    public void run() {
        try {
            FileReader fileReader = new FileReader("input.txt");


            Scanner scanner = new Scanner(fileReader);
            int n = scanner.nextInt();
            int[] array = new int[n];

            for(int i = 0; i < n; ++i){
                array[i] = scanner.nextInt();
            }

            fileReader.close();

            boolean flag = true;

            for(int i = 0; i < n; ++i){
                if(i % 2 == 0 && i != 0){
                    if(array[i] < array[i / 2 - 1])
                        flag = false;

                }
                if(i % 2 == 1 && i != 0){
                    if(array[i] < array[i / 2])
                        flag = false;
                }
            }


            String result = ((flag == true) ? "Yes" : "No");

            FileWriter fileWriter = new FileWriter("output.txt");
            fileWriter.write(result);
            fileWriter.close();
        }
        catch (Exception e){}
    }


    public static void main(String[] args) {
        new Thread(null, new Main(), "", 64 * 1024 * 1024).start();
    }
}