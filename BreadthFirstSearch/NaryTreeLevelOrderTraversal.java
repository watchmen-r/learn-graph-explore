import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};

class Solution {
    public List<List<Integer>> levelOrder(Node root) {
        if(root == null) return new ArrayList<>();
        
        List<List<Integer>> answer = new ArrayList<>();
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(root);

        while(!queue.isEmpty()) {
            List<Integer> nowLevelNodes = new ArrayList<>();
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                Node node = queue.poll();
                queue.addAll(node.children);
                nowLevelNodes.add(node.val);
            }
            answer.add(nowLevelNodes);
        }
        return answer;
    }
}