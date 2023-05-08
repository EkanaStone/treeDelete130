import java.io.*;
import java.util.*;

import javax.lang.model.util.ElementScanner14;

public class Main
{
    public static void main(String[] args) throws Exception 
    {
        //Write to a file
        PrintStream console = new PrintStream(new File("output.txt"));
        System.setOut(console);

        Tree tree = new Tree("input.txt");
        tree.toStringg();
        console.close();
    }
}

class Node 
{
    public int data;
    public Node right;
    public Node left;

    public Node(int data)
    {
        this.data = data;
        right=null;
        left=null;
    }


}

class Tree
{
    private Node root;

    public Tree(String file) throws FileNotFoundException
    {
        //reads file
        Scanner scan = new Scanner(new File("input.txt"));

        //runs as long as theres a next line
        while(scan.hasNextLine())
        {
            String line = scan.nextLine();
            if(line.startsWith("delete"))
            {
                String current = line.substring("delete ".length());
                delete(Integer.parseInt(current));
            }
            else 
            {
                root = insert(root, Integer.parseInt(line));
            }
        }
    }

// DELETE SECTION ======================================================
    private void delete(int key)
    {
        if(search(root, key) != null)
        {
            root = deleting(root, key);
        }
        else 
        {
            insert(root, key);
        }
    }

    private Node deleting(Node root, int key) {
        if(root==null)
        {
            return root;
        }
        else if(key < root.data)
        {
            deleting(root.left, key);
        }
        else if(key > root.data)
        {
            deleting(root.right, key);
        }
        else 
        {
            if(root.left==null)
            {
                return root.right;
            }
            else if(root.right == null)
            {
                return root.left;
            }

            root.data = minValue(root.right);
            root.right = deleting(root.right, root.data);
        }
        return root;
    }

    private int minValue(Node right) 
    {
        int min = right.data;

        while(root.left != null)
        {
            min = root.left.data;
            root = root.left;
        }
        return min;
    }

    private Node search(Node root, int key) 
    {
        if(root == null || root.data == key)
        {
            return root;
        }
        else if(key < root.data)
        {
            return search(root.left, key);
        }
        else //if(key > root.data)
        {
            return search(root.right, key);
        }
    }

    // INSERT SECTION ======================================================
    private Node insert(Node root, int key)
    {
        if(root == null)
        {
            root = new Node(key);
            return root;
        }
        else if (key<root.data)
        {
            root.left = insert(root.left, key);
        }
        else if(root.data<key)
        {
            root.right = insert(root.left, key);
        }
        return root;
    }

    // PRINT SECTION =======================================================
    public void toStringg()
    {
        System.out.println("Unordered Tree: ");
        unordered(root);
        System.out.println("\n\nOrdered Tree");
        ordered(root);
        System.out.println("\n\nTree by level");
        level(root);
    }

    private void unordered(Node root)
    {
        if(root != null)
        {
            System.out.print(root.data + " ");
            unordered(root.left);
            unordered(root.right);
        }
    }

    private void ordered(Node root)
    {
        if(root != null )
        {
            ordered(root.left);
            System.out.print(root.data + " ");
            ordered(root.right);
        }
    }

    private void level(Node root)
    {
        int depth = maxDepth(root)+1;
        for(int level = 1; level <= depth; level++)
        {
            printLevel(root, level);
            System.out.println();
        }
    }

    private void printLevel(Node root, int level)
    {

    }

    private int maxDepth(Node root)
    {
        return 0;
    }
}

