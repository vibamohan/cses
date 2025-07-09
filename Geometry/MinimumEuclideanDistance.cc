#include <algorithm>
#include <cmath>
#include <complex>
#include <iomanip>
#include <iostream>
#include <limits>
#include <vector>

typedef long long ll;
typedef std::complex<ll> P;
#define X real()
#define Y imag()

ll dist(const P &a, const P &b) {
  ll dx = a.X - b.X;
  ll dy = a.Y - b.Y;
  return dx * dx + dy * dy;
}

bool comparatorX(const P &p1, const P &p2) { return p1.X < p2.X; }
bool comparatorY(const P &p1, const P &p2) { return p1.Y < p2.Y; }

ll closestDistance(std::vector<P> &points_x, std::vector<P> &points_y,
                   size_t left, size_t right) {
  // base case is brute force the rest
  if (right - left <= 3) {
    ll min_dist = std::numeric_limits<ll>::max();
    for (size_t i = left; i < right; i++) {
      for (size_t j = i + 1; j < right; j++) {
        min_dist = std::min(min_dist, dist(points_x[i], points_x[j]));
      }
    }
    return min_dist;
  }
  size_t mid = left + (right - left) / 2;
  ll mid_x = points_x[mid].X;

  // div + conquer between points to the left of middle, and to the right of
  // middle
  std::vector<P> points_y_left, points_y_right;
  for (const P &p : points_y) {
    if (p.X < mid_x || (p.X == mid_x && p.Y <= points_x[mid].Y))
      points_y_left.push_back(p);
    else
      points_y_right.push_back(p);
  }

  ll dl = closestDistance(points_x, points_y_left, left, mid);
  ll dr = closestDistance(points_x, points_y_right, mid, right);
  ll d = std::min(dl, dr);

  // use y sorting to build the min dist window
  std::vector<P> strip;
  for (const P &p : points_y) {
    if (std::abs(p.X - mid_x) < d)
      strip.push_back(p);
  }

  // Only check next 7 points in strip
  // geometrically proven, because you can only divide up into 8 total squares,
  // one of which is taken. (strip is Y-sorted)
  ll min_d = d;
  for (size_t i = 0; i < strip.size(); ++i) {
    for (size_t j = i + 1; j < strip.size() && j <= i + 7; ++j) {
      min_d = std::min(min_d, dist(strip[i], strip[j]));
    }
  }
  return min_d;
}

int main() {
  std::ios::sync_with_stdio(false);
  std::cin.tie(nullptr);
  int kN;
  std::cin >> kN;
  std::vector<P> points(kN);

  for (int i = 0; i < kN; i++) {
    ll cur_x, cur_y;
    std::cin >> cur_x >> cur_y;
    points[i] = P(cur_x, cur_y);
  }

  std::vector<P> points_x = points, points_y = points;
  std::sort(points_x.begin(), points_x.end(), comparatorX);
  std::sort(points_y.begin(), points_y.end(), comparatorY);

  ll min_dist = closestDistance(points_x, points_y, 0, points_x.size());

  std::cout << min_dist << std::endl;
  return 0;
}
