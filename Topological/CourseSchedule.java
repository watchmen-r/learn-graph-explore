import java.util.ArrayDeque;
import java.util.Queue;

class CourseSchedule {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] result = new int[numCourses];
        if(numCourses == 0) {
            return result;
        }

        if(prerequisites == null || prerequisites.length == 0) {
            for(int i = 0; i < numCourses; i++) {
                result[i] = i;
            }
            return result;
        }

        // 入次数を格納する
        int[] indegree = new int[numCourses];
        for(int[] pre: prerequisites) {
            indegree[pre[0]]++;
        }

        // 入次数が0のものを格納する
        Queue<Integer> zeroDegree = new ArrayDeque<>();
        for(int i = 0; i < indegree.length; i++) {
            if(indegree[i] == 0) {
                zeroDegree.add(i);
            }
        }

        if(zeroDegree.isEmpty()) {
            return new int[0];
        }

        int index = 0;
        while(!zeroDegree.isEmpty()) {
            int course = zeroDegree.poll();
            result[index] = course;
            index++;

            for(int[] pre: prerequisites) {
                // 入次数を減らす
                if(pre[1] == course) {
                    indegree[pre[0]]--;

                    // 入次数が0になったものはqueueに追加する
                    if(indegree[pre[0]] == 0) {
                        zeroDegree.add(pre[0]);
                    }
                }
            }
        }

        for(int in: indegree) {
            // 入次数が０になってないものがある場合
            if(in != 0) {
                return new int[0];
            }
        }

        return result;
    }
}