package programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class TravelRoute {

    public static void main(String[] args) {
        String[][] tickets = {
                {"ICN", "SFO"},
                {"ICN", "ATL"},
                {"SFO", "ATL"},
                {"ATL", "ICN"},
                {"ATL", "SFO"}
        };

        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.solution(tickets)));

        Solution2 solution2 = new Solution2();
        System.out.println(Arrays.toString(solution2.solution(tickets)));
    }

    static class Solution2 {
        private Map<String, LinkedList<String>> graph = new HashMap<>();
        private List<String> answer = new ArrayList<>();

        public String[] solution(String[][] tickets) {
            Arrays.sort(tickets, Comparator.comparing(o -> o[1]));

            for (String[] ticket : tickets) {
                String dep = ticket[0];
                String des = ticket[1];

                graph.computeIfAbsent(dep, k -> new LinkedList<>()).add(des);
            }
            dfs("ICN");
            Collections.reverse(answer);

            return answer.toArray(new String[0]);
        }

        private void dfs(String dep) {
            while (graph.containsKey(dep) && !graph.get(dep).isEmpty()) {
                dfs(graph.get(dep).poll());
            }

            answer.add(dep);
        }
    }

    static class Solution {
        private Queue<String> queue = new PriorityQueue<>();
        private boolean[] visited;

        public String[] solution(String[][] tickets) {
            visited = new boolean[tickets.length];
            dfs("ICN", "ICN", tickets, 0);

            return queue.peek().split(" ");
        }

        private void dfs(String start, String route, String[][] tickets, int count) {
            if (count == tickets.length) {
                queue.add(route);
                return;
            }

            for (int i = 0; i < tickets.length; i++) {
                if (start.equals(tickets[i][0]) && !visited[i]) {
                    visited[i] = true;
                    dfs(tickets[i][1], route + " " + tickets[i][1], tickets, count + 1);
                    visited[i] = false;
                }
            }
        }
    }

}
