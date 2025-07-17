#include <algorithm>
#include <complex>
#include <iostream>
#include <limits>
#include <queue>
#include <vector>

using P = std::complex<int>;

#define X real()
#define Y imag()

class Direction {
public:
  int dx;
  int dy;
  char symbol;

  Direction(int dx, int dy, char symbol) : dx(dx), dy(dy), symbol(symbol) {}

  bool IsValid(int x, int y, int limit_x, int limit_y) const {
    return ((dx + x) >= 0 && (dx + x) < limit_x) &&
           ((dy + y) >= 0 && (dy + y) < limit_y);
  }

  static const Direction UP;
  static const Direction DOWN;
  static const Direction LEFT;
  static const Direction RIGHT;
  static const std::vector<Direction> DIRECTIONS;
};

const Direction Direction::UP = Direction(-1, 0, 'U');
const Direction Direction::DOWN = Direction(1, 0, 'D');
const Direction Direction::LEFT = Direction(0, -1, 'L');
const Direction Direction::RIGHT = Direction(0, 1, 'R');
const std::vector<Direction> Direction::DIRECTIONS = {
    Direction::UP, Direction::DOWN, Direction::LEFT, Direction::RIGHT};

void bfs(std::vector<std::vector<char>> &grid,
         std::vector<std::vector<int>> &distances, std::queue<P> &q) {
  int n = grid.size();
  int m = grid[0].size();

  while (!q.empty()) {
    P cur = q.front();
    q.pop();
    int x = cur.X;
    int y = cur.Y;

    for (const Direction &dir : Direction::DIRECTIONS) {
      int nx = x + dir.dx;
      int ny = y + dir.dy;
      if (dir.IsValid(x, y, n, m) && grid[nx][ny] != '#' &&
          distances[nx][ny] > distances[x][y] + 1) {
        distances[nx][ny] = distances[x][y] + 1;
        q.push(P(nx, ny));
      }
    }
  }
}

int main() {
  int n, m;
  std::cin >> n >> m;

  std::vector<std::vector<char>> grid(n, std::vector<char>(m));
  std::vector<std::vector<int>> monster_distances(
      n, std::vector<int>(m, std::numeric_limits<int>::max()));
  std::vector<std::vector<int>> person_distances(
      n, std::vector<int>(m, std::numeric_limits<int>::max()));
  std::vector<std::vector<P>> parent(n, std::vector<P>(m, P(-1, -1)));
  std::vector<std::vector<char>> direction(n, std::vector<char>(m));

  std::queue<P> monster_q, person_q;
  P person_location;

  for (int i = 0; i < n; i++) {
    for (int j = 0; j < m; j++) {
      std::cin >> grid[i][j];
      if (grid[i][j] == 'M') {
        monster_q.push(P(i, j));
        monster_distances[i][j] = 0;
      } else if (grid[i][j] == 'A') {
        person_location = P(i, j);
        person_q.push(person_location);
        person_distances[i][j] = 0;
      }
    }
  }

  bfs(grid, monster_distances, monster_q);
  bfs(grid, person_distances, person_q);

  std::queue<P> q;
  q.push(person_location);

  bool escaped = false;
  P escape_point;

  while (!q.empty()) {
    P cur = q.front();
    q.pop();
    int x = cur.X;
    int y = cur.Y;

    if (x == 0 || y == 0 || x == n - 1 || y == m - 1) {
      if (person_distances[x][y] < monster_distances[x][y]) {
        escape_point = cur;
        escaped = true;
        break;
      }
    }

    for (const Direction &dir : Direction::DIRECTIONS) {
      int nx = x + dir.dx;
      int ny = y + dir.dy;
      if (dir.IsValid(x, y, n, m) && grid[nx][ny] != '#' &&
          person_distances[nx][ny] < monster_distances[nx][ny] &&
          parent[nx][ny] == P(-1, -1)) {
        parent[nx][ny] = cur;
        direction[nx][ny] = dir.symbol;
        q.push(P(nx, ny));
      }
    }
  }

  if (!escaped) {
    std::cout << "NO\n";
  } else {
    std::string path;
    P cur = escape_point;
    while (cur != person_location) {
      path += direction[cur.X][cur.Y];
      cur = parent[cur.X][cur.Y];
    }
    std::reverse(path.begin(), path.end());
    std::cout << "YES\n";
    std::cout << path.size() << "\n";
    std::cout << path << "\n";
  }

  return 0;
}
