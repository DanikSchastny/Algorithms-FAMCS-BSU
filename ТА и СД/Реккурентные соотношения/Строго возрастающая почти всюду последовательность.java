import java.io.*;
import java.util.*;


public class Main implements Runnable {

    @Override
    public void run() {
        try {

            FileReader fileReader = new FileReader("input.txt");
            Scanner scanner = new Scanner(fileReader);
            int size = scanner.nextInt();
            List<Integer> numbers = new ArrayList<>();
            for (int i = 0; i < size; ++i) {
                numbers.add(scanner.nextInt());
            }
            scanner.close();
            fileReader.close();
            List<Integer> rightWay = new ArrayList<>();
            Integer[] elements = new Integer[size];
            for (int i = 0; i < size; ++i) {
                rightWay.add(0);
            }
            rightWay.set(0, 1);
            elements[0] = numbers.get(0);
            int index = 0;

            for (int i = 1; i < size; ++i) {
                if (numbers.get(i) > elements[index]) {
                    elements[index + 1] = numbers.get(i);
                    index++;
                } else if (numbers.get(i) <= elements[0]) {
                    elements[0] = numbers.get(i);
                } else if (numbers.get(i) < elements[index]) {
                    int term = (Arrays.binarySearch(elements, 1, index, numbers.get(i)) + 1) * -1;
                    if (term > 0)
                        elements[term] = numbers.get(i);
                }
                rightWay.set(i, index + 1);
            }
            List<Integer> backWay = new ArrayList<>();

            for (int i = 0; i < size; ++i) {
                backWay.add(0);
                elements[i] = 0;
            }
            index = 0;
            backWay.set(size - 1, 1);
            elements[0] = numbers.get(numbers.size() - 1);
            for (int i = size - 2; i >= 0; i--) {
                if (numbers.get(i) < elements[index]) {
                    elements[index + 1] = numbers.get(i);
                    index++;
                } else if (numbers.get(i) >= elements[0]) {
                    elements[0] = numbers.get(i);
                } else if (numbers.get(i) > elements[index]) {
                    int term = (Arrays.binarySearch(elements, 1, index, numbers.get(i), Comparator.<Integer>reverseOrder()) + 1) * -1;
                    if (index > 0 && term > 0 )
                        elements[term] = numbers.get(i);
                }

                backWay.set(i, index + 1);
            }

            int result = 0;
            for (int i = 0; i < size; ++i) {
                result = Math.max(result, backWay.get(i) + rightWay.get(i));
            }

            FileWriter fileWriter = new FileWriter("output.txt");
            int length = Math.min(size, result);
            fileWriter.write(length + "");
            fileWriter.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void main(String[] args) {
        new Thread(null, new Main(), "", 64 * 1024 * 1024).start();
    }
}