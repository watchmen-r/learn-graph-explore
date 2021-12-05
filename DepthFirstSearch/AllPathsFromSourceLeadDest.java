import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class AllPathsFromSourceLeadDest {

    public static void main(String[] args) {

    }

    List<List<Integer>> adjacentList;
    int dest;

    public boolean dfs(int node, HashSet<Integer> seen) {
        if(seen.contains(node)) return false;
        seen.add(node);
        
        if(node == dest) return true;
        
        List<Integer> list = adjacentList.get(node);
        if(list.size() == 0) return false;

        for(int next: list) {
            HashSet<Integer> copySeen = (HashSet<Integer>)seen.clone();
            boolean result = dfs(next, copySeen);
            if(!result) return false;
        }
        return true;
    }
    
    public boolean leadsToDestination(int n, int[][] edges, int source, int destination) {
        adjacentList = new ArrayList<>();
        dest = destination;
        
        for(int i = 0; i < n; i++) {
            adjacentList.add(new ArrayList<>());
        }
        for(int i = 0; i < edges.length; i++) {
            adjacentList.get(edges[i][0]).add(edges[i][1]);
        }

        if(adjacentList.get(destination).size() > 0) return false;

        HashSet<Integer> set = new HashSet<>();
        return dfs(source, set);
    }
}