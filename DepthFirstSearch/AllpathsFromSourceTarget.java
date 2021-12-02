import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AllpathsFromSourceTarget {

    public static void main(String[] args) {

    }

    public static int[][] graphs;

    static List<List<Integer>> answer;
    static List<List<

    static void dfs(int node, List<Integer> list) {
        list.add(node);
        for(int next: graphs[node]) {
            if(graphs[next].length == 0 && list.size() >= 1) {
                list.add(next);
                answer.add(list);
            } else if(graphs[next].length == 0) {
                return;
            }
            List<Integer> tmp = list.stream().collect(Collectors.toList());
            dfs(next, tmp);
        }
        
    }

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        graphs = graph;
        answer = new ArrayList<>();

        for(int i = 0; i < graph.length; i++) {
            List<Integer> path = new ArrayList<>();
            dfs(i, path);
        }

        return answer;
    }
}