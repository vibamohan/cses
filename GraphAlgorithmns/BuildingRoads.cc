#include <bits/stdc++.h>

void dfs(std::vector<std::vector<int>> &adj, std::vector<bool> &visited,
         int node) {
  visited[node] = true;
  for (int &neighbour : adj[node]) {
    if (not visited[neighbour]) {
      dfs(adj, visited, neighbour);
    }
  }
}

int main() {
  std::ios::sync_with_stdio(false);
  std::cin.tie(nullptr);
  int n, m;
  std::cin >> n >> m;

  std::vector<std::vector<int>> adj(n + 1);
  std::vector<bool> visited(n);

  for (int i = 0; i < m; i++) {
    int src, dst;
    std::cin >> src >> dst;
    adj[src].push_back(dst);
    adj[dst].push_back(src);
  }

  // detect unvisited nodes
  std::vector<int> new_edges;
  for (int i = 1; i <= n; i++) {
    if (not visited[i]) {
      new_edges.push_back(i);
      dfs(adj, visited, i);
    }
  }

  // if we connect all the unvisited nodes to one another one after the other we
  // can connect them in order to each other. then we can visit every node since
  // it is connected.
  std::cout << new_edges.size() - 1 << std::endl;
  for (int i = 0; i < new_edges.size() - 1; i++) {
    std::cout << new_edges[i] << " " << new_edges[i + 1] << std::endl;
  }
  return 0;
}