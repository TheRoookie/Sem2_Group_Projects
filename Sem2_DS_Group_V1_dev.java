//40 30 25 15 28 35 50 45 60 55 70
//40 15 4 10 30 25 34 60 70 65 95
//100 20 10 30 200 150 300
//22 12 8 20 30 25 40
//25 15 10 4 12 22 18 24 50 35 31 44 70 66 90
//40 30 50
//Examples of Pre-order above.

// Add - Hight(down to up). and level of tree
// Higth of Left and Right subtree.
// Empty preorder retry.
// Max Min.
// Why same val.

import java.util.*;

class BST {
    int val;
    BST left;
    BST right;

    BST(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}

public class Sem2_DS_Group_V1_dev {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter pre-order traversal as a string: ");
        String preorder = sc.nextLine();
        sc.close();

        BST root = makeBST(preorder);

        printTree(root, 0, 4);

        showRoot(root);

        System.out.print("In-order traversal: ");
        inOrderTraversal(root);
        System.out.println();

        System.out.print("Pre-order traversal: ");
        preOrderTraversal(root);
        System.out.println();

        System.out.print("Post-order traversal: ");
        postOrderTraversal(root);
        System.out.println();

        System.out.println();
    }

    public static BST makeBST(String preorder) {
        if (preorder == null || preorder.isEmpty()) {
            System.out.println("Write Something!!!");
            System.exit(0);
            return null;
        }

        String[] nodes = preorder.split(" ");

        int[] index = { 0 };
        return arrangeBST(nodes, index, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private static BST arrangeBST(String[] nodes, int[] index, int min, int max) {
        // System.out.println("\n*****************\n");

        if (index[0] >= nodes.length) {
            return null;
        }
        int val = 0;
        try {
            val = Integer.parseInt(nodes[index[0]]);
            // System.out.println("Value asigned - " + val);
        } catch (Exception e) {
            System.out.println("Not Valid input.");
            System.exit(0);
        }

        // System.out.println("Min = " + min);
        // System.out.println("Max = " + max);

        // Base Case
        // System.out.println("condition will be - " + (val < min || val > max));
        if (val < min || val > max) {
            // System.out.println();
            return null;
        }

        BST node = new BST(val);
        // System.out.println("New node created = " + val);
        // System.out.println("Index is and will be +1 - " + index[0]);
        index[0]++;// 2

        // System.out.println("We are in - " + val);
        node.left = arrangeBST(nodes, index, min, val - 1);
        // System.out.println("Going to - " + val);
        node.right = arrangeBST(nodes, index, val + 1, max);

        // System.out.println("\n*****************\n");

        return node;
    }

    public static void inOrderTraversal(BST root) {
        if (root != null) { // Base Condition.
            inOrderTraversal(root.left);
            System.out.print(root.val + " , ");
            inOrderTraversal(root.right);
        }
    }

    public static void preOrderTraversal(BST root) {
        if (root != null) {
            System.out.print(root.val + " , ");
            preOrderTraversal(root.left);
            preOrderTraversal(root.right);
        }
    }

    public static void postOrderTraversal(BST root) {
        if (root != null) {
            postOrderTraversal(root.left);
            postOrderTraversal(root.right);
            System.out.print(root.val + " , ");
        }
    }

    public static void printTree(BST root, int spaces, int count) {

        // This is Inorder traversal in reverse

        if (root == null) {
            return;
        }
        spaces += count;

        printTree(root.right, spaces, count);

        for (int i = count; i < spaces; i++) {
            System.out.print(" ");
        }
        System.out.print(root.val);
        if (root.right != null || root.left != null) {
            for (int i = 1; i < count; i++) {
                System.out.print("-");
            }
            System.out.println("|");
        } else {
            System.out.println();
        }
        printTree(root.left, spaces, count);

        return;
    }

    static void showRoot(BST root) {
        System.out.println("Root - " + root.val);
    }
}
