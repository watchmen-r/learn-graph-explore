import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class GraphValidTree {

    // 推奨されていた深さ優先で解いている(union findの章)
    public boolean validTree(int n, int[][] edges) {
        // もらうedgeの数はn-1でないといけない
        if (edges.length != n - 1) return false;

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
        // 作成したlistのnodeを一つずつみるはず。見たものを記録していく
        Set<Integer> seen = new HashSet<>();
        stack.push(0);
        seen.add(0);

        while(!stack.empty()) {
            int node = stack.pop();
            for(int neighbour: adjacentList.get(node)) {
                if(seen.contains(neighbour)) continue;
                seen.add(neighbour);
                stack.push(neighbour);
            }
        }
        // seenに入るものはnになれば良い
        // 不要なものが入っていれば最初のifで弾かれるはずなので
        return seen.size() == n;
    }
}