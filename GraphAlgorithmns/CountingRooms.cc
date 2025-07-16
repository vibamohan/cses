#include <complex>
#include <iostream>
#include <queue>
#include <vector>

using P = std::complex<int>;

#define X real()
#define Y imag()

class Direction {
public:
  int dx_;
  int dy_;

  Direction(int dx, int dy) : dx_(dx), dy_(dy) {}

  inline bool IsValid(int x, int y, int limit_x, int limit_y) const {
    return ((dx_ + x) >= 0 && (dx_ + x) < limit_x) &&
           ((dy_ + y) >= 0 && (dy_ + y) < limit_y);
  }

  static const Direction UP;
  static const Direction DOWN;
  static const Direction LEFT;
  static const Direction RIGHT;
  static const std::vector<Direction> DIRECTIONS;
};

const Direction Direction::UP = Direction(-1, 0);
const Direction Direction::DOWN = Direction(1, 0);
const Direction Direction::LEFT = Direction(0, -1);
const Direction Direction::RIGHT = Direction(0, 1);
const std::vector<Direction> Direction::DIRECTIONS = {
    Direction::UP, Direction::DOWN, Direction::LEFT, Direction::RIGHT};

void bfs(std::vector<std::vector<char>> &grid,
         std::vector<std::vector<bool>> &visited, int start_x, int start_y) {
  std::queue<P> q;
  q.push(P(start_x, start_y));
  int n = grid.size();
  int m = grid[0].size();

  while (!q.empty()) {
    P cur_point = q.front();
    q.pop();
    int i = cur_point.X;
    int j = cur_point.Y;
    if (visited[i][j])
      continue;
    visited[i][j] = true;

    for (const Direction &dir : Direction::DIRECTIONS) {
      int translated_x = i + dir.dx_;
      int translated_y = j + dir.dy_;
      if (dir.IsValid(i, j, n, m) && !visited[translated_x][translated_y] and
          grid[translated_x][translated_y] == '.') {
        q.push(P(translated_x, translated_y));
      }
    }
  }
}

int main() {
  int n, m;
  std::cin >> n >> m;

  std::vector<std::vector<char>> grid(n, std::vector<char>(m));
  std::vector<std::vector<bool>> visited(n, std::vector<bool>(m, false));

  for (int i = 0; i < n; i++) {
    for (int j = 0; j < m; j++) {
      std::cin >> grid[i][j];
    }
  }

  int cc = 0;
  for (int i = 0; i < n; i++) {
    for (int j = 0; j < m; j++) {
      if (!visited[i][j] and grid[i][j] == '.') {
        bfs(grid, visited, i, j);
        cc++;
      }
    }
  }

  std::cout << cc << std::endl;
  return 0;
}
