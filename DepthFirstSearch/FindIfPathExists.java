import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class FindIfPathExists {

    public static void main(String[] args) {

    }

    public boolean validPath(int n, int[][] edges, int start, int end) {
        if(n == 1) return true;
        
        List<List<Integer>> adjacentList = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            adjacentList.add(new ArrayList<>());
        }
        for(int[] edge: edges) {
            adjacentList.get(edge[0]).add(edge[1]);
            adjacentList.get(edge[1]).add(edge[0]);
        }

        Stack<Integer> stack = new Stack<>();
        Set<Integer> seen = new HashSet<>();
        stack.push(start);
        seen.add(start);

        while(stack.size() != 0) {
            int node = stack.pop();
            List<Integer> tmpList = adjacentList.get(node);
            for(int i: tmpList) {
                if(i == end) return true;
                if(!seen.contains(i)) {
                    stack.push(i);
                    seen.add(i);
                }
            }
        }
        return false;
    }
}