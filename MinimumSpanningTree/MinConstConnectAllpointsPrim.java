import java.util.PriorityQueue;

class MinConstConnectAllpointsPrim {
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

    public int minCostConnectPoints(int[][] points) {
        if (points == null || points.length == 0) {
            return 0;
        }

        int size = points.length;
        PriorityQueue<Edge> pq = new PriorityQueue<>((x, y) -> x.cost - y.cost);
        boolean[] visited = new boolean[size];

        int result = 0;
        // 頂点0から全ての頂点の距離をそれぞれ計算する
        int[] coordinate1 = points[0];
        for(int i = 1; i < size; i++) {
            int[] coordinate2 = points[i];
            int cost = Math.abs(coordinate1[0] - coordinate2[0]) + 
                Math.abs(coordinate1[1] - coordinate2[1]);
            Edge edge = new Edge(0, i, cost);
            pq.add(edge);
        }

        visited[0] = true;
        int count = size - 1;
        while(!pq.isEmpty() && count > 0) {
            Edge edge = pq.poll();
            int point1 = edge.point1;
            int point2 = edge.point2;
            int cost = edge.cost;

            // point2に訪れていなければ、point2から全頂点の距離を計算してpqに入れる
            if(!visited[point2]) {
                result += cost;
                visited[point2] = true;
                for(int i = 0; i < size; i++) {
                    if(!visited[i]) {
                        int distance = Math.abs(points[point2][0] - points[i][0]) + 
                        Math.abs(points[point2][1] - points[i][1]);
                        pq.add(new Edge(point2, i, distance));
                    }
                }
                count--;
            }
        }
        return result;
     }
}