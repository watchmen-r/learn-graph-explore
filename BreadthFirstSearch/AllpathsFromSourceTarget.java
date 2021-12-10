import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class AllpathsFromSourceTarget {

    public static void main(String[] args) {

    }

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> answer = new ArrayList<>();
        if(graph == null || graph.length == 0) {
            return answer;
        }

        // ArrayDequeは両端キューFIFOでもLIFOでもできる
        Queue<List<Integer>> queue = new ArrayDeque<>();
        List<Integer> path = new ArrayList<>();
        path.add(0);
        queue.add(path);

        while(!queue.isEmpty()) {
            List<Integer> currentList = queue.poll();
            // list内の一番上が現在のノード、他の値はそこに行くまでのパス
            int node = currentList.get(currentList.size() - 1);
            
            for(int nextNode: graph[node]) {
                List<Integer> tmpList = new ArrayList<>(currentList);
                tmpList.add(nextNode);
                if(nextNode == graph.length - 1) {
                    answer.add(new ArrayList<>(tmpList));
                } else {
                    queue.add(new ArrayList<>(tmpList));
                }
            }
        }
        return answer;
    }
}