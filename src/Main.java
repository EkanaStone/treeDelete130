import java.io.*;
import java.util.*;

public class Main
{
    public static void main(String[] args) throws Exception 
    {
        //Write to a file
        PrintStream console = new PrintStream(new File("output.txt"));
        System.setOut(console);

        Tree tree = new Tree("src/input.txt");
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
        Scanner scan = new Scanner(new File("src/input.txt"));

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
        else //if(root.data < key)
        {
            return search(root.right, key);
        }
    }

    private Node deleting(Node root, int key) {
        if(root==null)
        {
            return root;
        }
        else if(key < root.data)
        {
            root.left = deleting(root.left, key);
        }
        else if(root.data < key)
        {
            root.right = deleting(root.right, key);
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
        int min = root.data;

        while(root.left != null)
        {
            min = root.left.data;
            root = root.left;
        }
        return min;
    }

    // INSERT SECTION ======================================================
    private Node insert(Node root, int key)
    {
        if(root == null)
        {
            root = new Node(key);
            return root;
        }
        else if (key < root.data)
        {
            root.left = insert(root.left, key);
        }
        else if(root.data < key)
        {
            root.right = insert(root.right, key);
        }
        return root;
    }

    // PRINT SECTION =======================================================
    public void toStringg()
    {
        System.out.println("Tree Preorder: ");
        preorder(root);
        System.out.println("\n\nTree Inorder: ");
        inorder(root);
        System.out.println("\n\nTree by level: ");
        level(root);
    }

    private void preorder(Node root)
    {
        if(root != null)
        {
            System.out.print(root.data + " ");
            preorder(root.left);
            preorder(root.right);
        }
    }

    private void inorder(Node root)
    {
        if(root != null )
        {
            inorder(root.left);
            System.out.print(root.data + " ");
            inorder(root.right);
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
        if(root == null)
        {
            System.out.print("");
            return;
        }
        else if(level == 1)
        {
            System.out.print(root.data + " ");
        }
        else if(level > 1)
        {
            printLevel(root.left, level-1);
            printLevel(root.right, level-1);
        }
    }

    private int maxDepth(Node root)
    {
        if(root == null)
        {
            return -1;
        }
        else 
        {
            int leftDepth = maxDepth(root.left);
            int rightDepth = maxDepth(root.right);
            if(leftDepth > rightDepth)
            {
                return leftDepth+1;
            }
            else
            {
                return rightDepth+1;
            }
        }
    }
}

