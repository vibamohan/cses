#include <algorithm>
#include <iostream>
#include <vector>

using ll = long long;

struct Line {
  ll slope, b;
  Line(ll slope = 0, ll b = 0) : slope(slope), b(b) {}
};

// Check if l3 makes l2 unnecessary between l1 and l3
bool bad(const Line &l1, const Line &l2, const Line &l3) {
  return (l3.b - l1.b) * (l1.slope - l2.slope) <=
         (l2.b - l1.b) * (l1.slope - l3.slope);
}

int main() {
  std::ios::sync_with_stdio(false);
  std::cin.tie(nullptr);

  int n, m;
  std::cin >> n >> m;
  std::vector<Line> lines(n);
  for (int i = 0; i < n; i++) {
    ll y1, y2;
    std::cin >> y1 >> y2;
    lines[i] = Line(y2 - y1, y1);
  }

  std::sort(lines.begin(), lines.end(), [](const Line &l1, const Line &l2) {
    if (l1.slope != l2.slope)
      return l1.slope < l2.slope;
    return l1.b < l2.b;
  });

  // Remove duplicates with same slope and smaller intercept
  // bc they are not optimal or useful
  std::vector<Line> filtered;
  for (auto &line : lines) {
    if (!filtered.empty() && filtered.back().slope == line.slope) {
      if (filtered.back().b >= line.b)
        continue;
      else
        filtered.pop_back();
    }
    filtered.push_back(line);
  }
  lines = std::move(filtered);

  // Build hull
  std::vector<Line> hull;
  for (auto &line : lines) {
    while ((int)hull.size() >= 2 &&
           bad(hull[(int)hull.size() - 2], hull.back(), line)) {
      hull.pop_back();
    }
    hull.push_back(line);
  }

  // Query hull for each x = 0 to m
  int ptr = 0;
  for (int x = 0; x <= m; x++) {
    // while the next line in hull is better than the current one
    // and x is not m, move the pointer
    // then output the best line for the current x
    while (ptr + 1 < (int)hull.size() &&
           hull[ptr + 1].b + (hull[ptr + 1].slope * x) / m >=
               hull[ptr].b + (hull[ptr].slope * x) / m) {
      ptr++;
    }
    std::cout << hull[ptr].b + (hull[ptr].slope * x) / m
              << (x == m ? '\n' : ' ');
  }

  return 0;
}
