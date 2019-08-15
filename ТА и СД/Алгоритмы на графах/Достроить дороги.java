import java.io.*;
import java.util.*;


public class Main implements Runnable {
    static ArrayList<Integer>[] graph;
    static int[] marks;
    static int n;
    static int mark;
    static int svyaznost;

    @Override
    public void run(){
        try {
            FileReader fileReader = new FileReader("input.txt");
            Scanner scanner = new Scanner(fileReader);

            mark = 0;
            n = scanner.nextInt();
            graph = new ArrayList[n];
            marks = new int[n];
            svyaznost = 0;

            int m = scanner.nextInt();
            for (int i = 0; i < n; ++i) {
                marks[i] = 0;
                graph[i] = new ArrayList<>();
            }
            int a, b;
            for (int i = 0; i < m; ++i) {
                a = scanner.nextInt() - 1;
                b = scanner.nextInt() - 1;
                graph[a].add(b);
                graph[b].add(a);
            }
            fileReader.close();
            scanner.close();

        find_comps();

        FileWriter fileWriter = new FileWriter("output.txt");
        fileWriter.write(svyaznost - 1 + "");
        fileWriter.close();
        }
        catch (Exception e){}

    }

    public static void main(String[] args) throws IOException {
        new Thread(null, new Main(), "", 128 * 1024 * 1024).start();

    }

    private static void DFS(int i ){
        ++mark;
        marks[i] = mark;
        for(int j = 0; j < graph[i].size(); ++j){
            if(marks[graph[i].get(j)] == 0)
                DFS(graph[i].get(j));
        }
    }

    public static void find_comps() {
        for (int i = 0; i < n; ++i)
            if (marks[i] == 0) {
                ++svyaznost;
                DFS(i);
            }
    }
}