import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class NumberOfConnectedComponents {

    public int countComponents(int n, int[][] edges) {

        // 隣接状態をまとめたリストを作成する
        List<List<Integer>> adjacentList = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            adjacentList.add(new ArrayList<>());
        }
        for(int[] edge: edges) {
            adjacentList.get(edge[0]).add(edge[1]);
            adjacentList.get(edge[1]).add(edge[0]);
        }

        Stack<Integer> stack = new Stack<>();
        // 見たノードを入れていく
        Set<Integer> seen = new HashSet<>();
        stack.push(0);
        seen.add(0);

        int count = 1;

        // 見ていないノードがなくなるまで
        while(seen.size() < n) {
            while(!stack.empty()) {
                int node = stack.pop();
                for(int neighbour: adjacentList.get(node)) {
                    if(seen.contains(neighbour)) continue;
                    seen.add(neighbour);
                    stack.push(neighbour);
                }
            }
            for(int i = 0; i < n; i++) {
                if(!seen.contains(i)) {
                    stack.push(i);
                    seen.add(i);
                    count++;
                    break;
                }
            }
        }
        return count;
    }
}