class CheapestFlightWithin {
    // ベルマンフォード
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        if(src == dst) {
            return 0;
        }

        int[] previous = new int[n];
        int[] current = new int[n];

        for(int i = 0; i < n; i++) {
            previous[i] = Integer.MAX_VALUE;
            current[i] = Integer.MAX_VALUE;
        }

        previous[src] = 0;

        // 問題でのkは頂点に止まる回数が渡されるため、下記がk+1となっている
        for(int i = 0; i < k + 1; i++) {
            current[src] = 0;
            for (int[] flight : flights) {
                int previousFlight = flight[0];
                int currentFlight = flight[1];
                int cost = flight[2];

                if (previous[previousFlight] < Integer.MAX_VALUE) {
                    current[currentFlight] = Math.min(current[currentFlight], previous[previousFlight] + cost);
                }
            }
            previous = current.clone();
        }
        return current[dst] == Integer.MAX_VALUE ? -1 : current[dst];
    }
}