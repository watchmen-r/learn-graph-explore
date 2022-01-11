import java.util.ArrayDeque;
import java.util.Queue;

class ParallelCources {

    public int minimumSemesters(int n, int[][] relations) {
        // 入次数を格納する
        int[] indegree = new int[n + 1];
        for (int[] relation : relations) {
            indegree[relation[1]]++;
        }

        // 入次数が0のものを格納する
        Queue<Integer> zeroDegree = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) {
                zeroDegree.add(i);
            }
        }

        if (zeroDegree.isEmpty()) {
            return -1;
        }

        int index = 0;
        while (!zeroDegree.isEmpty()) {
            index++;
            int queueNum = zeroDegree.size();
            for (int i = 0; i < queueNum; i++) {
                int edge = zeroDegree.poll();
                for (int[] relation : relations) {
                    if (edge == relation[0]) {
                        indegree[relation[1]]--;

                        // 入次数が０になった場合
                        if (indegree[relation[1]] == 0) {
                            zeroDegree.add(relation[1]);
                        }
                    }
                }
            }
        }

        // 入次数が全て0になっていることを確認
        for (int in : indegree) {
            if (in != 0) {
                return -1;
            }
        }
        return index;
    }
}