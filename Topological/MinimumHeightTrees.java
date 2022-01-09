import java.util.ArrayList;

class MinimumHeightTrees {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        // 頂点が０個か１個の場合
        if(n < 2) {
            List<Integer> centroids = new ArrayList<>();
            for(int i = 0; i < n; i++) {
                centroids.add(i);
            }
            return centroids;
        }

        // 隣接リストを作成
        List<Set<Integer>> neighbors = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            neighbors.add(new HashSet<Integer>());
        }
        for(int[] edge: edges) {
            Integer start = edge[0];
            Integer end = edge[1];
            neighbors.get(start).add(end);
            neighbors.get(end).add(start);
        }

        // 隣接しているノードが１つだけのものを入れる
        List<Integer> leaves = new ArrayList<>();
        for(int i = 0; i < n; i++){
            if(neighbors.get(i).size() == 1) {
                leaves.add(i);
            }
        }

        // グラフの端から探索を始め、真ん中まで行ったらそれらが答え
        int remainingNodes = n;
        while(remainingNodes > 2) {
            remainingNodes -= leaves.size();
            List<Integer> newLeaves = new ArrayList<>();

            for(Integer leaf: leaves) {
                Integer neighbor = neighbors.get(leaf).iterator().next();

                neighbors.get(neighbor).remove(leaf);
                if(neighbors.get(neighbor).size() == 1) {
                    newLeaves.add(neighbor);
                }
            }
            leaves = newLeaves;
        }
        return leaves;
    }
}