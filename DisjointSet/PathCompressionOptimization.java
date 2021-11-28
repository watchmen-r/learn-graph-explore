// class UnionFind {

//     private int[] root;

//     public void getRoot() {
//         for(int a: root) {
//             System.out.println(a);
//         }
//     }

//     // コンストラクタ
//     public UnionFind(int size) {
//         root = new int[size];
//         for(int i = 0; i < size; i++) {
//             root[i] = i;
//         }
//     }

//     public int find(int x) {
//         // 自分がrootだった場合
//         if(x == root[x]) {
//             return x;
//         }
//         // root[x] = find(root[x]);
//         // return root[x];
//         // 上記と同じ意味
//         return root[x] = find(root[x]);
//     }

//     public void union(int x, int y) {
//         int rootX = find(x);
//         int rootY = find(y);
//         if(rootX != rootY) {
//             root[rootY] = rootX;
//         }
//     }

//     public boolean connected(int x, int y) {
//         return find(x) == find(y);
//     }
// }

public class PathCompressionOptimization {
    public static void main(String[] args) {
        UnionFind uf = new UnionFind(10);
        // 1-2-5-6-7 3-8-9 4
        uf.union(1, 2);
        uf.union(2, 5);
        uf.union(5, 6);
        uf.union(6, 7);
        uf.union(3, 8);
        uf.union(8, 9);

        // この時のrootを見てみる
        // uf.getRoot();
        System.out.println(uf.connected(1, 5)); // true
        System.out.println(uf.connected(5, 7)); // true
        System.out.println(uf.connected(4, 9)); // false
        // 1-2-5-6-7 3-8-9-4
        uf.union(9, 4);
        System.out.println(uf.connected(4, 9)); // true
    }



}