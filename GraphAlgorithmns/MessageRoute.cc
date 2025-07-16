#include <bits/stdc++.h>

bool bfs(std::vector<std::vector<int>> &adj, std::vector<bool> &visited,
         std::vector<int> &parent, int start_node, int end_node) {
  std::queue<int> q;
  q.push(start_node);
  visited[start_node] = true;
  parent[start_node] = -1;

  while (!q.empty()) {
    int cur_node = q.front();
    q.pop();
    if (cur_node == end_node) {
      return true;
    }
    for (int neighbour : adj[cur_node]) {
      if (!visited[neighbour]) {
        visited[neighbour] = true;
        parent[neighbour] = cur_node;
        q.push(neighbour);
      }
    }
  }
  return false;
}

int main() {
  std::ios::sync_with_stdio(false);
  std::cin.tie(nullptr);
  int n, m;
  std::cin >> n >> m;

  const int start_point = 1;
  const int end_point = n;

  std::vector<std::vector<int>> adj(n + 1);
  std::vector<bool> visited(n + 1, false);
  std::vector<int> parent(n + 1, -1);

  // construct adjacency list (1 indexing due to range of nodes being 1..n)
  // wanted to not use map for efficiency so i just used 1-indexed vector
  for (int i = 0; i < m; i++) {
    int src, dst;
    std::cin >> src >> dst;
    adj[src].push_back(dst);
    adj[dst].push_back(src);
  }

  bool found = bfs(adj, visited, parent, start_point, end_point);
  if (!found) {
    std::cout << "IMPOSSIBLE" << std::endl;
    return 0;
  }

  std::vector<int> path;

  // output path using parent array
  for (int node = end_point; node != -1; node = parent[node]) {
    path.push_back(node);
  }
  std::reverse(path.begin(), path.end());

  std::cout << path.size() << std::endl;
  for (int node : path) {
    std::cout << node << " ";
  }
  std::cout << std::endl;

  return 0;
}
