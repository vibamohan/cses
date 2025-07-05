import java.util.*;

public class MinimalGridPathBFS {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    char[][] grid = new char[n][n];
    for (int i = 0; i < n; i++) grid[i] = scanner.next().toCharArray();

    // bfs for every level is stored here
    boolean[][] visited = new boolean[n][n];
    Queue<int[]> current = new LinkedList<>();
    current.add(new int[] {0, 0});
    visited[0][0] = true;
    StringBuilder result = new StringBuilder();
    result.append(grid[0][0]);

    int[][] dirs = {{1, 0}, {0, 1}};

    for (int step = 1; step < 2 * n - 1; step++) {
      char minChar = 'Z' + 1;
      Queue<int[]> next = new LinkedList<>();

      for (int[] cell : current) {
        int x = cell[0], y = cell[1];
        for (int[] dir : dirs) {
          int nx = x + dir[0];
          int ny = y + dir[1];
          if (nx < n && ny < n && !visited[nx][ny]) {
            if (grid[nx][ny] < minChar) minChar = grid[nx][ny];
          }
        }
      }

      for (int[] cell : current) {
        int x = cell[0], y = cell[1];
        for (int[] dir : dirs) {
          int nx = x + dir[0];
          int ny = y + dir[1];
          if (nx < n && ny < n && !visited[nx][ny] && grid[nx][ny] == minChar) {
            visited[nx][ny] = true;
            next.add(new int[] {nx, ny});
          }
        }
      }
      result.append(minChar);
      current = next;
    }
    System.out.println(result);
  }
}
