import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

class Solution {
    public static int[][] directions = new int[][] { { -1, 0 }, { 0, -1 }, { 0, 1 }, { 1, 0 }};

    public int orangesRotting(int[][] grid) {
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[grid.length][grid[0].length];

        // スタート位置を探す
        // また、すでに１がなければreturnする
        boolean isNoOne = true;
        for(int r = 0; r < grid.length; r++) {
            for(int c = 0; c < grid[0].length; c++) {
                if(grid[r][c] == 2) {
                    queue.add(new int[]{r, c});
                    visited[r][c] = true;
                }
                if(isNoOne && grid[r][c] == 1) isNoOne = false;
            }
        }
        if(isNoOne) return 0;
        
        int time = -1;
        while(!queue.isEmpty()) {
            int size = queue.size();
            queue.stream().forEach(s -> {System.out.print(s[0]);System.out.println(s[0]);});
            System.out.println("---");
            for(int i = 0; i < size; i++) {
                int[] nowCell = queue.poll();
                int nowRow = nowCell[0];
                int nowCol = nowCell[1];
                List<int[]> list = getNeighbours(nowRow, nowCol, grid, visited);
                queue.addAll(list);
            }
            time++;
        }

        // １に全て訪れたか確認
        for(int r = 0; r < grid.length; r++) {
            for(int c = 0; c < grid[0].length; c++) {
                if(grid[r][c] == 1 && !visited[r][c]) {
                    return -1;
                }
            }
        }
        return time;
    }

    static List<int[]> getNeighbours(int row, int col, int[][] grid, boolean[][] visited) {
        List<int[]> neighbors = new ArrayList<>();
        for (int i = 0; i < directions.length; i++) {
            int newRow = row + directions[i][0];
            int newCol = col + directions[i][1];
            if (newRow < 0 || newCol < 0 || newRow >= grid.length || newCol >= grid[0].length
                    || grid[newRow][newCol] != 1 || visited[newRow][newCol]) {
                continue;
            }
            neighbors.add(new int[] { newRow, newCol });
            visited[newRow][newCol] = true;
        }
        return neighbors;
    }
}