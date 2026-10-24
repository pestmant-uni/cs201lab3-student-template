import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Q1Test {
    public static void main(String[] args) {

        String result, traversal1, traversal2, traversal3;

        traversal1 = "1-2";
        traversal2 = "2-1-3";
        traversal3 = "1-3-2";
        result = verify(traversal1, traversal2, traversal3);
        System.out.println("Traversal 1 : " + traversal1);
        System.out.println("Traversal 2 : " + traversal2);
        System.out.println("Traversal 3 : " + traversal3);
        System.out.println("Expected Result : Invalid traversals");
        System.out.println("  Actual Result : " + result);
        System.out.println();

        traversal1 = "1-2-3";
        traversal2 = "2-1-3";
        traversal3 = "1-3-2";
        result = verify(traversal1, traversal2, traversal3);
        System.out.println("Traversal 1 : " + traversal1);
        System.out.println("Traversal 2 : " + traversal2);
        System.out.println("Traversal 3 : " + traversal3);
        System.out.println(
            "Expected Result : Traversal 1 - Inorder, Traversal 2 - Preorder, Traversal 3 - Postorder");
        System.out.println("  Actual Result : " + result);
        System.out.println();

        traversal1 = "1-2-3";
        traversal2 = "3-2-1";
        traversal3 = "2-3-1";
        result = verify(traversal1, traversal2, traversal3);
        System.out.println("Traversal 1 : " + traversal1);
        System.out.println("Traversal 2 : " + traversal2);
        System.out.println("Traversal 3 : " + traversal3);
        System.out.println("Expected Result : Invalid traversals");
        System.out.println("  Actual Result : " + result);
        System.out.println();

        traversal1 = "3-1-2-5-4";
        traversal2 = "2-1-4-5-3";
        traversal3 = "1-2-3-4-5";
        result = verify(traversal1, traversal2, traversal3);
        System.out.println("Traversal 1 : " + traversal1);
        System.out.println("Traversal 2 : " + traversal2);
        System.out.println("Traversal 3 : " + traversal3);
        System.out.println(
            "Expected Result : Traversal 1 - Preorder, Traversal 2 - Postorder, Traversal 3 - Inorder");
        System.out.println("  Actual Result : " + result);
        System.out.println();

        traversal1 = "10-20-30-40-50";
        traversal2 = "20-10-30-40-50";
        traversal3 = "10-50-40-30-20";
        result = verify(traversal1, traversal2, traversal3);
        System.out.println("Traversal 1 : " + traversal1);
        System.out.println("Traversal 2 : " + traversal2);
        System.out.println("Traversal 3 : " + traversal3);
        System.out.println(
            "Expected Result : Traversal 1 - Inorder, Traversal 2 - Preorder, Traversal 3 - Postorder");
        System.out.println("  Actual Result : " + result);
        System.out.println();
    } 

    //-----------------------------------------------------------------------------
    private static int[] stringToInt(String traversal) {
        String[] numbers = traversal.split("-");
        int[] integers = new int[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            integers[i] = Integer.parseInt(numbers[i]);
        }

        return integers;
    }
    //-----------------------------------------------------------------------------
    private static class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
        }
    }
    //-----------------------------------------------------------------------------
    private static Node insert(Node root, int value) {
    if (root == null) {
        return new Node(value);
    }

    Node current = root;

    while (true) {
        if (value < current.value) {
            if (current.left == null) {
                current.left = new Node(value);
                break;
            }
            current = current.left;

        } else if (value > current.value) {
            if (current.right == null) {
                current.right = new Node(value);
                break;
            }
            current = current.right;

        } else {
            break;
        }
    }

    return root;
    }
    //-----------------------------------------------------------------------------
    private static Node fillBST(int[] values) {
        Node root = null;

        for (int value : values) {
            root = insert(root, value);
        }

          return root;
    }
    //-----------------------------------------------------------------------------
    private static List<Integer> inOrder(Node root) {
        List<Integer> result = new ArrayList<>();
        Deque<Node> stack = new ArrayDeque<>();

        Node current = root;

        while (current != null || !(stack.isEmpty())) {

            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            current = stack.pop();
            result.add(current.value);

            current = current.right;
        }

        return result;
    }
    //-----------------------------------------------------------------------------
    private static List<Integer> preOrder(Node root) {
    List<Integer> result = new ArrayList<>();
    Deque<Node> stack = new ArrayDeque<>();

    if (root == null) {
        return result;
    }

    stack.push(root);

    while (!stack.isEmpty()) {
        Node current = stack.pop();

        result.add(current.value);

        if (current.right != null) {
            stack.push(current.right);
        }

        if (current.left != null) {
            stack.push(current.left);
        }
    }

    return result;
    }
    //-----------------------------------------------------------------------------
    private static List<Integer> postOrder(Node root) {
    List<Integer> result = new ArrayList<>();
    Deque<Node> stack1 = new ArrayDeque<>();
    Deque<Node> stack2 = new ArrayDeque<>();

    if (root == null) {
        return result;
    }

    stack1.push(root);

    while (!stack1.isEmpty()) {
        Node current = stack1.pop();

        stack2.push(current);

        if (current.left != null) {
            stack1.push(current.left);
        }

        if (current.right != null) {
            stack1.push(current.right);
        }
    }

    while (!stack2.isEmpty()) {
        result.add(stack2.pop().value);
    }

    return result;
    }
    //-----------------------------------------------------------------------------
    private static boolean matches(List<Integer> actual, int[] expected) {
    if (actual.size() != expected.length) {
        return false;
    }

    for (int i = 0; i < expected.length; i++) {
        if (actual.get(i) != expected[i]) {
            return false;
        }
    }

    return true;
    }   
    //-----------------------------------------------------------------------------
    public static String verify(String traversal1, String traversal2, String traversal3){
        int[][] traversals = {stringToInt(traversal1),stringToInt(traversal2),stringToInt(traversal3)};
    
    for (int preIndex = 0; preIndex < 3; preIndex++) {

        Node root = fillBST(traversals[preIndex]);

        List<Integer> preorder = preOrder(root);
        List<Integer> inorder = inOrder(root);
        List<Integer> postorder = postOrder(root);

        if (!matches(preorder, traversals[preIndex])) {
            continue;
        }

        for (int inIndex = 0; inIndex < 3; inIndex++) {

            if (inIndex == preIndex) {
                continue;
            }

            for (int postIndex = 0; postIndex < 3; postIndex++) {

                if (postIndex == preIndex ||
                    postIndex == inIndex) {
                    continue;
                }

                if (matches(inorder, traversals[inIndex]) && matches(postorder, traversals[postIndex])) {

                    String[] labels = new String[3];

                    labels[preIndex] = "Preorder";
                    labels[inIndex] = "Inorder";
                    labels[postIndex] = "Postorder";

                    return "Traversal 1 - " + labels[0] + ", Traversal 2 - " + labels[1] + ", Traversal 3 - " + labels[2];
                }
            }
        }
    }

    return "Invalid traversals";
    }
}