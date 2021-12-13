import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

class MinConstConnectAllpoints {

    // データの持ち方に困ったらこうやって持つのはとてもいいかも
    class Edge {
        int point1;
        int point2;
        int cost;
        Edge(int point1, int point2, int cost) {
            this.point1 = point1;
            this.point2 = point2;
            this.cost = cost;
        }
    }

    class UnionFind {
        int root[];
        int rank[];

        public UnionFind(int size) {
            root = new int[size];
            rank = new int[size];
            for(int i = 0; i < size; i++) {
                root[i] = i;
                rank[i] = 1;
            }
        }

        // 経路圧縮も行う(P320)
        public int find(int x) {
            if (x == root[x]) {
                return x;
            }
            // root[x] = find(root[x]);
            // return root[x];
            // 上記と同じ意味
            return root[x] = find(root[x]);
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if(rootX != rootY) {
                if(rank[rootX] > rank[rootY]) {
                    root[rootY] = rootX;
                } else if(rank[rootY] < rank[rootY]) {
                    root[rootX] = rootY;
                } else {
                    root[rootY] = rootX;
                    rank[rootX] += 1;
                }
            }
        }

        public boolean connected(int x, int y) {
            return find(x) == find(y);
        }

    }

    public int minCostConnectPoints(int[][] points) {
        if(points == null || points.length == 0) {
            return 0;
        }
        
        int size = points.length;
        PriorityQueue<Edge> pq = new PriorityQueue<>((x, y) -> x.cost - y.cost);


    }
}