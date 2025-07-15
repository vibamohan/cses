#include <algorithm>
#include <iostream>
#include <vector>

using ll = long long;

struct Line {
  ll a_num, b;
  Line(ll a_num = 0, ll b = 0) : a_num(a_num), b(b) {}
};

int main() {
  std::ios::sync_with_stdio(false);
  std::cin.tie(nullptr);

  int kSegments;
  int kMaxX;
  std::cin >> kSegments >> kMaxX;

  std::vector<Line, kSegments> lines;
  for (int i = 0; i < kSegments; i++) {
    ll y1, y2;
    std::cin >> y1 >> y2;
    lines[i] = Line(y2 - y1, y1);
  }
}