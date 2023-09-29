//40 30 25 15 28 35 50 45 60 55 70
//40 15 4 10 30 25 34 60 70 65 95
//100 20 10 30 200 150 300
//22 12 8 20 30 25 40 57734 3453 35654 345 not valid
//25 15 10 4 12 22 18 24 50 35 31 44 70 66 90
//40 30 50
//Examples of Pre-order above.

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

public class Sem2_DS_Group_V3 {

    static int index = 0;
    static String[] nodes;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String preorder;
        BST root;
        while (true) {

            while (true) {
                System.out.print("Enter pre-order traversal as a string: ");
                preorder = sc.nextLine();

                if (preorder == null || preorder.isEmpty()) {
                    System.out.println("Input is Empty. Try Again.");
                } else {
                    break;
                }
            }

            nodes = preorder.split(" ");

            root = makeBST(Integer.MIN_VALUE, Integer.MAX_VALUE);

            if (index < nodes.length) {
                System.out.println("BST PreOrder is not valid. Try again");
            } else {
                break;
            }
        }

        sc.close();

        printTree(root, 0, 4);

        System.out.println("\n");

        showRoot(root);

        System.out.print("In-order traversal:\t");
        inOrderTraversal(root);
        System.out.println();

        System.out.print("Pre-order traversal:\t");
        preOrderTraversal(root);
        System.out.println();

        System.out.print("Post-order traversal:\t");
        postOrderTraversal(root);
        System.out.println();

        System.out.println();
    }

    private static BST makeBST(int min, int max) {

        if (index >= nodes.length) {
            return null;
        }
        int val = 0;
        try {
            val = Integer.parseInt(nodes[index]);
        } catch (Exception e) {
            System.out.println("Not Valid input. Try Again.");
            main(nodes);
        }

        if (val < min || val > max) {
            return null;
        }

        BST node = new BST(val);

        index++;

        node.left = makeBST(min, val - 1);

        node.right = makeBST(val + 1, max);

        return node;
    }

    public static void inOrderTraversal(BST root) {
        if (root != null) {
            inOrderTraversal(root.left);
            System.out.print(root.val + " ");
            inOrderTraversal(root.right);
        }
    }

    public static void preOrderTraversal(BST root) {
        if (root != null) {
            System.out.print(root.val + " ");
            preOrderTraversal(root.left);
            preOrderTraversal(root.right);
        }
    }

    public static void postOrderTraversal(BST root) {
        if (root != null) {
            postOrderTraversal(root.left);
            postOrderTraversal(root.right);
            System.out.print(root.val + " ");
        }
    }

    public static void printTree(BST root, int spaces, int count) {

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
