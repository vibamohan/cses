#include <complex>
#include <iostream>
#include <queue>
#include <vector>

using P = std::complex<int>;

#define X real()
#define Y imag()

// make an enum style direction class
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

bool bfs(std::vector<std::vector<char>> &grid,
         std::vector<std::vector<bool>> &visited,
         std::vector<std::vector<P>> &parent,
         std::vector<std::vector<char>> &direction, int start_x, int start_y,
         int &end_x, int &end_y) {
  int n = grid.size();
  int m = grid[0].size();
  std::queue<P> q;
  q.push(P(start_x, start_y));
  visited[start_x][start_y] = true;

  while (!q.empty()) {
    P cur = q.front();
    q.pop();
    int x = cur.X;
    int y = cur.Y;

    if (grid[x][y] == 'B') {
      end_x = x;
      end_y = y;
      return true;
    }

    for (const Direction &dir : Direction::DIRECTIONS) {
      int nx = x + dir.dx;
      int ny = y + dir.dy;
      if (dir.IsValid(x, y, n, m) && !visited[nx][ny] && grid[nx][ny] != '#') {
        visited[nx][ny] = true;
        parent[nx][ny] = P(x, y);
        direction[nx][ny] = dir.symbol;
        q.push(P(nx, ny));
      }
    }
  }

  return false;
}

int main() {
  int n, m;
  std::cin >> n >> m;

  std::vector<std::vector<char>> grid(n, std::vector<char>(m));
  std::vector<std::vector<bool>> visited(n, std::vector<bool>(m, false));
  std::vector<std::vector<P>> parent(n, std::vector<P>(m, P(-1, -1)));
  std::vector<std::vector<char>> direction(n, std::vector<char>(m));

  int start_x = -1, start_y = -1;
  int end_x = -1, end_y = -1;

  for (int i = 0; i < n; ++i) {
    for (int j = 0; j < m; ++j) {
      std::cin >> grid[i][j];
      if (grid[i][j] == 'A') {
        start_x = i;
        start_y = j;
      }
    }
  }

  bool found =
      bfs(grid, visited, parent, direction, start_x, start_y, end_x, end_y);
      
    // if we found it read in parent and direction for output 
    // reverse since it is in dest->src order, not src->dest
  if (!found) {
    std::cout << "NO" << std::endl;
  } else {
    std::cout << "YES" << std::endl;
    std::string path;
    int x = end_x, y = end_y;
    while (P(x, y) != P(start_x, start_y)) {
      path += direction[x][y];
      P p = parent[x][y];
      x = p.X;
      y = p.Y;
    }
    std::reverse(path.begin(), path.end());
    std::cout << path.size() << std::endl;
    std::cout << path << std::endl;
  }

  return 0;
}
