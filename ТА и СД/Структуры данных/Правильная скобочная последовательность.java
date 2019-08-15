import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

public class Main{
    public static void main(String[] args) throws IOException {
        FileReader fileReader = new FileReader("input.txt");
        Scanner scanner = new Scanner(fileReader);
        int index = 0;
        String result = "YES";
            char[] line = scanner.nextLine().toCharArray();
            Stack<Character>  left = new Stack<>();
            for(int i = 0; i < line.length; ++i){
                if(line[i] == '{' || line[i] == '[' || line[i] == '(')
                    left.push(line[i]);
                else {
                    if (left.empty()) {
                        if (index == 0)
                            index = i;
                        result = "NO";
                        break;
                    }
                    char term = left.pop();
                    if (line[i] == '}' && term != '{' || line[i] == ']' && term != '[' || line[i] == ')' && term != '(') {
                        if (index == 0)
                            index = i;
                        result = "NO";
                        break;

                    }
                }
            }

            if(result == "YES" && !left.empty()){
                index = line.length ;
                result = "NO";
            }

        FileWriter fileWriter = new FileWriter("output.txt");
        if(result.equals("NO")) {
            fileWriter.write(result + "\r\n");
            fileWriter.write(index + "");
        }
        else
            fileWriter.write("YES");
        fileWriter.close();
    }
}