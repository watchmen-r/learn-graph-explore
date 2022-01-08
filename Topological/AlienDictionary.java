import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

class AlienDictionary {
    public String alienOrder(String[] words) {
        // データ構造を作成する
        Map<Character, List<Character>> adjList = new HashMap<>();
        // 入次数を格納する用
        Map<Character, Integer> counts = new HashMap<>();
        for (String word : words) {
            for (char c : word.toCharArray()) {
                counts.put(c, 0);
                adjList.put(c, new ArrayList<>());
            }
        }

        // すべての辺を見つける
        for (int i = 0; i < words.length - 1; i++) {
            String word1 = words[i];
            String word2 = words[i + 1];
            // ２つの単語から、word2がword1のprefixになっていないか確認する
            if (word1.length() > word2.length() && word1.startsWith(word2)) {
                return "";
            }
            // 左から各単語を比較して最初に一致していない文字を取得する
            for (int j = 0; j < Math.min(word1.length(), word2.length()); j++) {
                if (word1.charAt(j) != word2.charAt(j)) {
                    adjList.get(word1.charAt(j)).add(word2.charAt(j));
                    counts.put(word2.charAt(j), counts.get(word2.charAt(j)) + 1);
                    break;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        Queue<Character> queue = new ArrayDeque<>();
        for (Character c : counts.keySet()) {
            // トポロジカルソート：入次数が０のものをqueueに格納する
            if (counts.get(c).equals(0)) {
                queue.add(c);
            }
        }
        while (!queue.isEmpty()) {
            Character c = queue.remove();
            sb.append(c);
            for (Character next : adjList.get(c)) {
                counts.put(next, counts.get(next) - 1);
                // トポロジカルソート：入次数が０になったものを格納する
                if (counts.get(next).equals(0)) {
                    queue.add(next);
                }
            }
        }

        if (sb.length() < counts.size()) {
            return "";
        }
        return sb.toString();
    }
}