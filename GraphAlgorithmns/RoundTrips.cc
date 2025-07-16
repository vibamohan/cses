#include <iostream>
#include <vector>
// Basic Union Find template
// class UnionFind {
//     public:
//         UnionFind(int n) {
//             rank = vector<int>(n, 1);
//             find = vector<int>(n);
//         }

//         int find(int node) {
//             if (node == find[node]) return node;
//             return find[x] = find(find[x]);
//         }

//         void union(int node1, int node2) {
//             int find_1 = find[node1];
//             int find_2 = find[node2];

//             if (find_1 == find_2) return;

//             // pick smaller one as root
//             if (rank[find_1] > rank[find_2]) {
//                 std::swap(find_1, find_2);
//             }

//             // make find_1 the root of find_2
//             find[find_1] = find_2;
//             // if we both have the same rank, use find_2 as root and increase
//             its rank if (rank[find_1] == rank[find_2]) rank[find_2]++;

//         }
//     private:
//         // rank is basically the onward depth of the tree at root x
//         // find keeps track of the tree of DSU
//         std::vector<int> rank, find;

// }

// Union Find Altered for this problem statement

class UnionFind {
public:
  UnionFind(int n) {
    rank.resize(n + 1, 1);
    parent.resize(n + 1);
    for (int i = 0; i <= n; i++) {
      parent[i] = i;
    }
  }

  int find(int a) {
    if (a == parent[a])
      return a;
    // path compression, recursively find parent,
    // but also update it inplace so that we just have the root in parent
    // instead of layers of links. This is what we need and reduces time taken
    // in future runs
    return parent[a] = find(parent[a]);
  }

  bool union_sets(int a, int b) {
    int find_a = find(a), find_b = find(b);

    // Cycle because they are both part of the same tree
    if (find_a == find_b)
      return false;

    // pick smaller rank for time complexity later
    if (rank[find_a] < rank[find_b]) {
      std::swap(find_a, find_b);
    }

    // join
    parent[find_b] = find_a;

    // if they are the same rank just increase depth by 1
    // i.e attach it right before the root to only increase depth by 1
    if (rank[find_a] == rank[find_b])
      rank[find_a]++;
    return true;
  }

private:
  std::vector<int> parent, rank;
};

std::vector<std::vector<int> > adj;
std::vector<int> path;
std::vector<int> parent;
std::vector<bool> visited;
bool cycled = false;

void dfs(int start, int prev) {
  // I think we could also do this in bfs, but its more logical in dfs and i
  // like this way more
  if (cycled)
    return;
  visited[start] = true;
  parent[start] = prev;
  for (int neighbour : adj[start]) {
    if (neighbour == prev)
      continue;
    // we found a cycle so we're backtraversing
    if (visited[neighbour]) {
      // push neighbour as it is our src
      path.push_back(neighbour);
      int cur = start;

      // keep traversing till we meet neighbour again (avoid infinite cycle
      // traversing)
      while (cur != neighbour) {
        path.push_back(cur);
        cur = parent[cur];
      }

      // add neighbour back since it is a cycle (round trip)
      // we also reverse since we have going from last seen node -> beginning
      // which is reverse of the order the question wants
      path.push_back(neighbour);
      std::reverse(path.begin(), path.end());
      cycled = true;
      return;
    } else {
      // dfs and return if cycle
      dfs(neighbour, start);
      if (cycled)
        return;
    }
  }
}

int main() {
  std::ios::sync_with_stdio(false);
  std::cin.tie(nullptr);

  int n, m;
  std::cin >> n >> m;

  UnionFind uf(n);
  adj.resize(n + 1);
  parent.resize(n + 1);
  visited.resize(n + 1);

  for (int i = 0; i < m; i++) {
    int s, e;
    std::cin >> s >> e;

    adj[s].push_back(e);
    adj[e].push_back(s);

    if (!uf.union_sets(s, e)) {
      // reset states
      std::fill(parent.begin(), parent.end(), -1);
      std::fill(visited.begin(), visited.end(), false);
      path.clear();
      cycled = false;

      // traverse with no parent
      dfs(s, -1);

      // print out path if we encounter a cycle
      // we check 4 according to problem statement
      if (cycled && path.size() >= 4) {
        std::cout << path.size() << std::endl;
        for (int node : path) {
          std::cout << node << " ";
        }
        std::cout << std::endl;
        return 0;
      } else {
        std::cout << "IMPOSSIBLE" << std::endl;
        return 0;
      }
    }
  }

  std::cout << "IMPOSSIBLE" << std::endl;
  return 0;
}