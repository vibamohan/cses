#include <algorithm>
#include <complex>
#include <iostream>
#include <set>
#include <vector>

typedef long long ll;
typedef std::complex<ll> P;
#define X real()
#define Y imag()

ll cross(const P &a, const P &b) { return std::imag(std::conj(a) * b); }

bool between(const P &a, const P &b, const P &p) {
  return std::min(a.X, b.X) <= p.X && p.X <= std::max(a.X, b.X) &&
         std::min(a.Y, b.Y) <= p.Y && p.Y <= std::max(a.Y, b.Y);
}

struct PComp {
  bool operator()(const P &a, const P &b) const {
    return (a.X < b.X) || (a.X == b.X && a.Y < b.Y);
  }
};

std::set<P, PComp> convex_hull_all(const std::vector<P> &points) {
  int n = points.size();
  if (n == 0)
    return {};

  std::vector<P> pts = points;
  // sort lexicographically by x, then y and remove duplicates
  std::sort(pts.begin(), pts.end(), [](const P &a, const P &b) {
    return (a.X < b.X) || (a.X == b.X && a.Y < b.Y);
  });

  pts.erase(std::unique(pts.begin(), pts.end(),
                        [](const P &a, const P &b) {
                          return a.X == b.X && a.Y == b.Y;
                        }),
            pts.end());

  std::vector<P> hull;

  // Lower hull
  for (const P &p : pts) {
    while (hull.size() >= 2 &&
           cross(hull.back() - hull[hull.size() - 2], p - hull.back()) < 0)
      hull.pop_back();
    hull.push_back(p);
  }

  // Upper hull
  size_t lower_size = hull.size();
  for (int i = int(pts.size()) - 2; i >= 0; i--) {
    // while the last two points in the hull and the current point make a right
    // turn, pop the last point
    while (hull.size() > lower_size &&
           cross(hull.back() - hull[hull.size() - 2], pts[i] - hull.back()) < 0)
      hull.pop_back();
    // Now you can add the current point
    hull.push_back(pts[i]);
  }

  // may be the same, remove duplicaiton
  if (hull.size() > 1 && hull.front() == hull.back())
    hull.pop_back();

  // use set to keep only unique points
  std::set<P, PComp> all_pts;

  // Add all points on the convex hull and those collinear with hull edges
  for (size_t i = 0; i < hull.size(); i++) {
    // Add the endpoints of the hull
    P a = hull[i], b = hull[(i + 1) % hull.size()];
    all_pts.insert(a);
    // Add the next point in the hull
    for (const P &p : pts) {
      // If the point is one of the endpoints, skip it
      // but if it is collinear with the edge, add it
      if (p == a || p == b)
        continue;
      if (cross(b - a, p - a) == 0 && between(a, b, p)) {
        all_pts.insert(p);
      }
    }
  }

  return all_pts;
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

  std::set<P, PComp> hull = convex_hull_all(points);

  std::cout << hull.size() << std::endl;
  for (const P &p : hull) {
    std::cout << p.X << " " << p.Y << std::endl;
  }
  return 0;
}
