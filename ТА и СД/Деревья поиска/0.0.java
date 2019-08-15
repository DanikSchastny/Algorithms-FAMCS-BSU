import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main implements Runnable {

    public void run(){
        try{
            Tree tree = new Tree();
            FileReader fin = new FileReader("input.txt");
            Scanner in = new Scanner(fin);

            while (in.hasNext()){
                tree.add(in.nextInt());
            }
            in.close(); fin.close();

           // tree.delete(term);
            FileWriter fileWriter = new FileWriter("output.txt");
            fileWriter.write(tree.sum + "");
          //  tree.leftReorder(tree.getRoot(), fileWriter);
            fileWriter.close();
        }
        catch (Exception e){}
    }

    public static void main(String[] args) {
        new Thread(null, new Main(), "", 64*1024*1024).start();
    }
}

class Tree {
    public class Node {
        public int data, height, amountOfChildren;
        public Node left, right;


        public Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
            this.height = -1;
            this.amountOfChildren = 0;
        }
    }
    public long sum = 0;
    private Node root;
    private int maxHeight;

    public Node getRoot(){
        return this.root;
    }
    public Tree() {
        this.root = null;
    }

    public void add(int value) {
        Node newVertex = null;
        Node base = root;
        while (base != null) {
            newVertex = base;
            if (base.data > value) {
                base = base.left;
            } else {
                if (base.data < value) {
                    base = base.right;
                } else {
                    return;
                }
            }
        }

        Node node = new Node(value);
        if (newVertex == null) {
            root = node;
        } else {
            if (newVertex.data > value) {
                newVertex.left = node;
            } else {
                newVertex.right = node;
            }
        }

        sum += value;

    }

    public void delete(int value) {
        Node current = root, parent = root;
        boolean isLeftChild = true;

        while (current.data != value) {
            parent = current;
            if (value < current.data) {
                isLeftChild = true;
                current = current.left;
            } else {
                isLeftChild = false;
                current = current.right;
            }

            if (current == null) {
                return;
            }
        }

        if (current.left == null && current.right == null) {
            if (current == root)
                root = null;
            else if (isLeftChild)
                parent.left = null;
            else
                parent.right = null;
        }
        if (current.left != null && current.right == null){
            if (current == root)
                root = current.left;
            else{
                if (isLeftChild){
                    parent.left = current.left;
                }
                else{
                    parent.right = current.left;
                }
            }
        }
        if (current.left == null && current.right != null){
            if (current == root)
                root = current.right;
            else{
                if (isLeftChild){
                    parent.left = current.right;
                }
                else{
                    parent.right = current.right;
                }
            }
        }
        if (current.left != null && current.right != null){
             Node curr = current.right;
             Node min = curr;
             while(curr!=null){
                 min = curr;
                 curr = curr.left;
             }
             int x = min.data;
             delete(x);
             current.data = x;
            }
    }

    public void leftReorder(Node node, FileWriter fileWriter) throws IOException {
        if(node != null) {
            fileWriter.write(node.data + " \n");
            leftReorder(node.left, fileWriter);
            leftReorder(node.right, fileWriter);
        }
    }


}