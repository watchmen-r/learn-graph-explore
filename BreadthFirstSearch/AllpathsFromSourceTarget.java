import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AllpathsFromSourceTarget {

    public static void main(String[] args) {

    }

    // targetはn-1になる(文に0~n-1のpath求めろと書いてある)
    static int target;
    public static int[][] graphs;
    static List<List<Integer>> answer;

    static void dfs(int node, LinkedList<Integer> list) {
        if(node == target) {
            // deep copyしておく
            answer.add(new ArrayList<Integer>(list));
            return;
        }

        for(int next: graphs[node]) {
            list.add(next);
            dfs(next, list);
            list.removeLast();
        }
    }

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        target = graph.length - 1;
        graphs = graph;
        answer = new ArrayList<>();

        LinkedList<Integer> path = new  LinkedList<>();
        path.addLast(0);
        // 0からn-1までの経路を求める
        dfs(0, path);

        return answer;
    }
}