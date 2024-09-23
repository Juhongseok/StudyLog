package programmers;

import java.util.Arrays;
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
        String[] result = solution.solution(tickets);

        System.out.println(Arrays.toString(result));
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
