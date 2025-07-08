import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Project implements Comparable<Project> {
  long start;
  long end;
  long reward;

  Project(long start, long end, long reward) {
    this.start = start;
    this.end = end;
    this.reward = reward;
  }

  @Override
  public String toString() {
    return "Project [" + "start=" + start + ", end=" + end + ", reward=" + reward + ']';
  }

  @Override
  public int compareTo(Project other) {
    return Long.compare(this.end, other.end);
  }
}

public class Projects {
  public static void main(String[] args) throws IOException {
    // Scanner scanner = new Scanner(System.in);
    // int kProjects = scanner.nextInt();
    // Project[] projects = new Project[kProjects];

    // for (int i = 0; i < kProjects; i++) {
    //   projects[i] = new Project(scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
    // }

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int kProjects = Integer.parseInt(br.readLine());
    Project[] projects = new Project[kProjects];
    for (int i = 0; i < kProjects; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      long start = Long.parseLong(st.nextToken());
      long end = Long.parseLong(st.nextToken());
      long reward = Long.parseLong(st.nextToken());
      projects[i] = new Project(start, end, reward);
    }

    br.close();

    Arrays.sort(projects);

    long[] dp = new long[kProjects + 1];
    dp[0] = 0;

    int[] precomputedOverlapping = new int[kProjects + 1];

    for (int i = 0; i < kProjects; i++) {
      int low = 0, high = i - 1;
      int j = -1;
      while (low <= high) {
        int mid = low + (high - low) / 2;
        if (projects[mid].end < projects[i].start) {
          j = mid;
          low = mid + 1;
        } else {
          high = mid - 1;
        }
      }
      precomputedOverlapping[i] = j;
    }

    for (int i = 1; i <= kProjects; i++) {
      dp[i] = dp[i - 1];
      Project curProject = projects[i - 1];

      // Initially had binary search here but it can be precomputed linearly
      // this makes the code O(n) instead of O(n log n) ...

      int lastNonOverlappingIndex = precomputedOverlapping[i - 1];
      // If we take the current project, add its reward to the best solution of the previous
      // non-overlapping project
      if (lastNonOverlappingIndex != -1) {
        dp[i] = Math.max(dp[i], dp[lastNonOverlappingIndex + 1] + curProject.reward);
      } else {
        dp[i] = Math.max(dp[i], curProject.reward);
      }
    }

    System.out.println(dp[kProjects]);
  }
}
