import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


import static java.lang.Integer.max;


public class Main implements Runnable {

    public void run(){
        try{
            Tree tree = new Tree();
            FileReader fin = new FileReader("in.txt");
            Scanner in = new Scanner(fin);

            while (in.hasNext()){
                tree.add(in.nextInt());
            }
            in.close(); fin.close();


            tree.postOrder(tree.getRoot());
           // tree.rightOrder(tree.getRoot(),(tree.getRoot()).height % 2 == 1 ? (tree.getRoot().height - 1 )  : tree.getRoot().height );
           // tree.ppreOrder(tree.getRoot(), 0,(tree.getRoot()).height % 2 == 1 ? (tree.getRoot().height - 1 )  : tree.getRoot().height);
            tree.ppreOrder(tree.getRoot(),0,tree.getRoot().height);
            if(tree.suitiableVertexes % 2 == 1){
                tree.delete(tree.getRoot(), tree.suitiableVertexes / 2 + 1);
            }

            FileWriter fileWriter = new FileWriter("out.txt");
            tree.leftReorder(tree.getRoot(),fileWriter);
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
        public int data, deep;
        public Node left, right;
        int vertexAmmount = 1, height, index = 0;


        public Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
            this.vertexAmmount = 1;
            this.height = 0;
        }
    }

    public int suitiableVertexes = 0;

    private Node root;


    public Node getRoot() {
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



    }

    private void deleteVertex(Node parent, Node current, boolean isLeftChild){
        if (current.left == null && current.right == null) {
            if (current == root)
                root = null;
            else if (isLeftChild)
                parent.left = null;
            else
                parent.right = null;
        }
        if (current.left != null && current.right == null) {
            if (current == root)
                root = current.left;
            else {
                if (isLeftChild) {
                    parent.left = current.left;
                } else {
                    parent.right = current.left;
                }
            }
        }
        if (current.left == null && current.right != null) {
            if (current == root)
                root = current.right;
            else {
                if (isLeftChild) {
                    parent.left = current.right;
                } else {
                    parent.right = current.right;
                }
            }
        }
        if (current.left != null && current.right != null) {
            Node curr = current.right;
            Node min = curr;
            while (curr != null) {
                min = curr;
                curr = curr.left;
            }
            int x = min.data;
            delete(x);
            current.data = x;
        }
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

        deleteVertex(parent,current,isLeftChild);
    }

    public void leftReorder(Node node, FileWriter fileWriter) throws IOException {
        if (node != null) {
           /* fileWriter.write("data:" + " " + node.data
                    + " ammount: " + node.vertexAmmount
                    + " height: " + node.height
                    + " index: " + node.index
                    + " deep :" + node.deep+  "\n");

 */
            fileWriter.write(node.data + "\n");
            leftReorder(node.left, fileWriter);
            leftReorder(node.right, fileWriter);
        }
    }

    public void postOrder(Node node) {
        if (node == null) return;
        postOrder(node.left);
        postOrder(node.right);

        if (node.left != null || node.right != null)
            node.height = max((node.left == null ? 0 : node.left.height), node.right == null ? 0 : node.right.height) + 1;

        node.vertexAmmount += node.left == null ? 0 : node.left.vertexAmmount;
        node.vertexAmmount += node.right == null ? 0 : node.right.vertexAmmount;
    }

    public void rightOrder(Node node, int height) {
        if (node == null) return;

       /*
        if(node.right != null && node.left != null) {

            if ((height - node.height) == height / 2 && node.left.vertexAmmount - node.right.vertexAmmount > 0) {
                suitiableVertexes++;
                node.index = suitiableVertexes;
            }
        }
        else if(node.left != null && node.right == null)
            if(height - node.height == height / 2){
            suitiableVertexes++;
            node.index = suitiableVertexes;
        }
        */

        rightOrder(node.left, height);
        rightOrder(node.right, height);
    }

    public void delete(Node node, int index) {
        if (node == null) return;
        if (node.left != null) {
            if (node.left.index == index) {
                deleteVertex(node, node.left, true);
                return;
            }
        }
        if (node.right != null) {
            if (node.right.index == index) {
                deleteVertex(node, node.right, false);
                return;
            }
        }
        delete(node.left, index);
        delete(node.right, index);
    }


    public void ppreOrder(Node node, int deep, int height){
        if (node == null) return;
        node.deep = deep;


        if(node.right != null && node.left != null) {
            if ((root.height - node.deep) == height / 2 && node.left.vertexAmmount - node.right.vertexAmmount > 0) {
                suitiableVertexes++;
                node.index = suitiableVertexes;
            }
        }
        else if(node.left != null && node.right == null)
            if(root.height - node.deep == height / 2){
                suitiableVertexes++;
                node.index = suitiableVertexes;
            }

        ppreOrder(node.left,deep + 1, height);
        ppreOrder(node.right, deep + 1, height);
    }
}