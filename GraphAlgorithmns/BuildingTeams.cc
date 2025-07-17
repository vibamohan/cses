#include <iostream>
#include <queue>
#include <vector>

enum Colors { TEAM_1 = 0, TEAM_2 = 1, UNVISITED = 2 };

// This version of BFS serves as a bipartite graph check (i.e can be 2 colored
//   without adjacent edges having the same color)
// This is because if we keep coloring alternate ways but then encounter
// an invalid color in a neighbour, it is not bipartite.
bool bfs(std::vector<std::vector<int>> &adj, std::vector<Colors> &colors,
         int start_node) {
  std::queue<int> q;
  q.push(start_node);
  colors[start_node] = Colors::TEAM_1;

  while (!q.empty()) {
    int cur_node = q.front();
    q.pop();
    Colors cur_color =
        colors[cur_node] == Colors::TEAM_1 ? Colors::TEAM_2 : Colors::TEAM_1;
    for (int neighbour : adj[cur_node]) {
      if (colors[neighbour] == Colors::UNVISITED) {
        // if we didnt see it yet, set correct color and continue
        colors[neighbour] = cur_color;
        q.push(neighbour);
      } else if (colors[neighbour] != cur_color) {
        // if our neighbour isnt in our color,
        // then we cannot do complementary coloring without invalidating cur's
        // color
        return false;
      }
    }
  }

  return true;
}

int main() {
  std::ios::sync_with_stdio(false);
  std::cin.tie(nullptr);
  std::cout.tie(nullptr);

  int pupils, friendships;
  std::cin >> pupils >> friendships;

  std::vector<std::vector<int>> adj(pupils + 1);
  std::vector<Colors> colors(pupils + 1, Colors::UNVISITED);
  for (int i = 0; i < friendships; i++) {
    int p1, p2;
    std::cin >> p1 >> p2;
    adj[p1].push_back(p2);
    adj[p2].push_back(p1);
  }

  bool possible_way = false;
  // go through all unconnected components
  // and check if we can color
  for (int i = 1; i <= pupils; i++) {
    if (colors[i] == Colors::UNVISITED) {
      possible_way = bfs(adj, colors, i);
      if (!possible_way) {
        break;
      }
    }
  }

  if (possible_way) {
    for (int i = 1; i <= pupils; i++) {
      std::cout << (colors[i] + 1) << " ";
    }
    std::cout << std::endl;
  } else {
    std::cout << "IMPOSSIBLE" << std::endl;
  }
}