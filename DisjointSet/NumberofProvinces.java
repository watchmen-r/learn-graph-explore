public class NumberOfProvinces {

    public static int find(int[] root, int x) {
        // 自分がrootだった場合
        if(x == root[x]) {
            return x;
        }
        // root[x] = find(root[x]);
        // return root[x];
        // 上記と同じ意味
        return root[x] = find(root, root[x]);
    }

    public static void union(int[] root, int x, int y) {
        int rootX = find(root, x);
        int rootY = find(root, y);

        if(rootX != rootY) {
            root[rootX] = rootY;
        }
    }

    static int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        int[] root = new int[n];

        // rootを初期化(全部自分がrootとする)
        for(int i = 0; i < isConnected.length; i++) {
            root[i] = i;
        }

        for (int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(isConnected[i][j] == 1 && i != j) {
                    union(root, i, j);
                }
            }
        }
        int count = 0;
        for (int i = 0; i < root.length; i++) {
            if(root[i] == i) count++;
        }
        return count;
    }
}