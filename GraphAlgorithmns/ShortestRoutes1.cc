#include <algorithm>
#include <iostream>
#include <limits>
#include <queue>
#include <vector>

using ll = long long;

constexpr int start = 1;
// implements dijstrika since all edges are +vely weighted
void dijkstra(const std::vector<std::vector<std::pair<int, int>>> &adj,
              std::vector<ll> &distances) {
  std::priority_queue<std::pair<ll, int>, std::vector<std::pair<ll, int>>,
                      std::greater<std::pair<ll, int>>>
      pq;

  // Note we are using the format of {weight, node} to take advantage of c++
  // lexicographic sorting of pairs. not standard, but it avoids making custom
  // comparator
  pq.push({0, start});
  // idea is kind of a greedy bfs, we use a priority queue to keep pulling
  // minimum distance nodes, out, and only process/push nodes if it is more
  // optimal than what we have already found.
  while (!pq.empty()) {
    std::pair<ll, int> cur_connection = pq.top();
    pq.pop();
    ll weight = cur_connection.first;
    int dst_node = cur_connection.second;
    if (weight > distances[dst_node])
      continue;
    distances[dst_node] = weight;

    for (const auto &[edge_weight, neighbour] : adj[dst_node]) {
      ll cur_total_weight = weight + edge_weight;
      if (cur_total_weight < distances[neighbour]) {
        distances[neighbour] = cur_total_weight;
        pq.push({cur_total_weight, neighbour});
      }
    }
  }
}
int main() {
  std::ios::sync_with_stdio(false);
  std::cin.tie(nullptr);
  std::cout.tie(nullptr);

  int cities, routes;
  std::cin >> cities >> routes;
  std::vector<std::vector<std::pair<int, int>>> adj(cities + 1);
  std::vector<ll> distances(cities + 1, std::numeric_limits<ll>::max());
  distances[1] = 0;

  for (int i = 0; i < routes; i++) {
    int city1, city2, weight;
    std::cin >> city1 >> city2 >> weight;
    adj[city1].push_back({weight, city2});
  }

  dijkstra(adj, distances);

  for (int i = 1; i <= cities; i++) {
    std::cout << distances[i] << " ";
  }
  std::cout << std::endl;
}