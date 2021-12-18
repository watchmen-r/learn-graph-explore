import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

class NetworkDelayTime {
    public int networkDelayTime(int[][] times, int n, int k) {
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();

        // mapには始点：{終点: 距離}が入っている
        for (int[] time : times) {
            map.putIfAbsent(time[0], new HashMap<>());
            map.get(time[0]).put(time[1], time[2]);
        }

        // 配列index0は距離、index1は頂点、最短距離を持っておく
        Queue<int[]> pq = new PriorityQueue<>((a, b) -> (a[0] - b[0]));
        pq.add(new int[] { 0, k });

        boolean[] visited = new boolean[n + 1];
        int res = 0;

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int currentDist = current[0];
            int currentNode = current[1];

            if (visited[currentNode]) {
                continue;
            }
                
            visited[currentNode] = true;
            res = currentDist;
            n--;
            if (map.containsKey(currentNode)) {
                for (int next : map.get(currentNode).keySet()) {
                    pq.add(new int[] { currentDist + map.get(currentNode).get(next), next });
                }
            }
        }

        // 全頂点を通っていなければ-1を返す
        return n == 0 ? res : -1;
    }
}