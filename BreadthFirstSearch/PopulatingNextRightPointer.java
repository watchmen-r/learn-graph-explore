import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};

public class PopulatingNextRightPointer {

    public static void main(String[] args) {

    }

    public Node connect(Node root) {
        if(root == null) return root;
        Queue<List<Node>> queue = new ArrayDeque<>();
        queue.add(Arrays.asList(root));
        while (!queue.isEmpty()) {
            List<Node> currentNodeList = queue.poll();

            List<Node> list = new ArrayList<>();

            for (int i = 0; i < currentNodeList.size(); i++) {
                if (i != currentNodeList.size() - 1) {
                    currentNodeList.get(i).next = currentNodeList.get(i + 1);
                }
                if (currentNodeList.get(i).left != null) {
                    list.add(currentNodeList.get(i).left);
                }

                if (currentNodeList.get(i).right != null) {
                    list.add(currentNodeList.get(i).right);
                }
            }
            if (list.size() > 0) {
                queue.add(list);
            }

        }
        return root;
    }
}