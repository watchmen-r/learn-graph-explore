import java.util.Arrays;


class TheEarliestMomentWhenEveryoneBecomeFriend {
    public class UnionFind {

        private int[] root;
        private int res;
    
        // コンストラクタ
        public UnionFind(int size) {
            root = new int[size];
            for(int i = 0; i < size; i++) {
                root[i] = i;
            }
            res = size;
        }

        // ここのfindはpath compressionの時と同じ
        public int find(int x) {
            // 自分がrootだった場合
            if(x == root[x]) {
                return x;
            }
            root[x] = find(root[x]);
            return root[x];
        }

        // ここはunion rankと同じ
        // unionするときにその頂点がどの高さにいるのか記録する
        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            if(rootX != rootY) {
                root[rootX] = rootY;
                // unionが起こったら、デクリメントする
                res--;
            }
        }

        public boolean connected(int x, int y) {
            return find(x) == find(y);
        }
    }


    public int earliestAcq(int[][] logs, int n) {
        // TODO ここの書き方
        Arrays.sort(logs, (a, b) -> (a[0] - b[0]));
        
        UnionFind uf = new UnionFind(n);

        for(int[] log : logs) {
            uf.union(log[1], log[2]);
            if(uf.res == 1) {
                return log[0];
            }
        }
        return -1;
    }
}