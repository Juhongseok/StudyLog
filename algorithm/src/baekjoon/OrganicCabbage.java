package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class OrganicCabbage {

    private static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCaseCount = Integer.parseInt(br.readLine());

        for (int i = 0; i < testCaseCount; i++) {
            String[] metadata = br.readLine().split(" ");
            int col = Integer.parseInt(metadata[0]);
            int row = Integer.parseInt(metadata[1]);
            int positionCount = Integer.parseInt(metadata[2]);

            map = new int[row][col];
            for (int j = 0; j < positionCount; j++) {
                String[] cabbagePosition = br.readLine().split(" ");
                map[Integer.parseInt(cabbagePosition[1])][Integer.parseInt(cabbagePosition[0])] = 1;
            }

            int answer = 0;

            for (int j = 0; j < map.length; j++) {
                for (int k = 0; k < map[0].length; k++) {
                    if (map[j][k] == 1) {
                        map[j][k] = 0;
                        bfs(j, k);
                        answer++;
                    }
                }
            }

            System.out.println(answer);
        }
    }

    private static void bfs(int row, int col) {
        Queue<int[]> queue = new LinkedList<>();

        int[] moveX = {0, 1, 0, -1};
        int[] moveY = {1, 0, -1, 0};

        queue.add(new int[]{row, col});

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            map[current[0]][current[1]] = 0;

            for (int k = 0; k < 4; k++) {
                try {
                    if (map[current[0] + moveX[k]][current[1] + moveY[k]] == 1) {
                        queue.add(new int[]{current[0] + moveX[k], current[1] + moveY[k]});
                        map[current[0] + moveX[k]][current[1] + moveY[k]] = 0;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                }
            }
        }
    }

}
