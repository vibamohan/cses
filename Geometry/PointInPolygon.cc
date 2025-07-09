#include <complex>
#include <iostream>
#include <vector>

typedef std::complex<double> P;
#define X real()
#define Y imag()

double crossProduct(const P &a, const P &b) {
  return (std::conj(a) * b).imag();
}

bool isOnLine(const P &p1, const P &p2, const P &p3) {
  // is p3 on the line defined by p1 and p2?
  double cross = crossProduct(p2 - p1, p3 - p1);
  if (std::abs(cross) > 1e-9)
    return false;

  return (std::min(p1.X, p2.X) <= p3.X) && (std::min(p1.Y, p2.Y) <= p3.Y) &&
         (std::max(p1.X, p2.X) >= p3.X) && (std::max(p1.Y, p2.Y) >= p3.Y);
}

bool isIntersecting(const P &a, const P &b, const P &c, const P &d) {
  // where is c with respect to ab
  double d1 = crossProduct(b - a, c - a);
  // where is d with respect to ab
  double d2 = crossProduct(b - a, d - a);
  // where is a with respect to cd
  double d3 = crossProduct(d - c, a - c);
  // where is b with respect to cd
  double d4 = crossProduct(d - c, b - c);

  // Case 1, normal intersection
  if (d1 * d2 < 0 && d3 * d4 < 0)
    return true;

  // Case 2, one of the points lies on the other line or lines are fully
  // collinear or if not, it will return false since there are no other case to
  // chekc
  return (d1 == 0 && isOnLine(a, b, c)) || (d2 == 0 && isOnLine(a, b, d)) ||
         (d3 == 0 && isOnLine(c, d, a)) || (d4 == 0 && isOnLine(c, d, b));
}

bool rayOnSegment(P &a, P &b, P &p) {
  // is a ray extending from P -> +inf(x) on segment AB?
  if (a.Y > b.Y)
    return rayOnSegment(b, a, p);

  if (std::abs(p.Y - a.Y) < 1e-9 || std::abs(p.Y - b.Y) < 1e-9) {
    P shift = P(p.X, p.Y + 1e-6);
    return rayOnSegment(a, b, shift);
  }

  if (p.Y < a.Y || p.Y > b.Y)
    return false;

  // find the x coordinate of the intersection by
  // using the slope of the line segment AB and y of P
  // if the intersection is to the left of P, then it is not on the segment
  double xIntersect = a.X + (double)(b.X - a.X) * (p.Y - a.Y) / (b.Y - a.Y);
  return xIntersect > p.X;
}

int main() {
  std::ios::sync_with_stdio(false);
  std::cin.tie(nullptr);

  size_t kVertices, kPoints;
  std::cin >> kVertices >> kPoints;
  std::vector<P> vertices_list(kVertices);

  for (size_t i = 0; i < kVertices; i++) {
    int cur_x, cur_y;
    std::cin >> cur_x >> cur_y;
    vertices_list[i] = P(cur_x, cur_y);
  }

  for (size_t i = 0; i < kPoints; i++) {
    int cur_x, cur_y;
    std::cin >> cur_x >> cur_y;
    P cur_point = P(cur_x, cur_y);

    bool is_intersecting = false;
    uint64_t intersections = 0;
    for (size_t j = 0; j < kVertices; j++) {
      size_t next_index = (j + 1) % kVertices;
      if (isOnLine(vertices_list[next_index], vertices_list[j], cur_point)) {
        is_intersecting = true;
        break;
      } else if (rayOnSegment(vertices_list[next_index], vertices_list[j],
                              cur_point)) {
        intersections++;
      }
    }

    if (is_intersecting) {
      std::cout << "BOUNDARY" << std::endl;
    } else if (intersections % 2 == 1) {
      std::cout << "INSIDE" << std::endl;
    } else {
      std::cout << "OUTSIDE" << std::endl;
    }
  }

  // the trick is to use ray casting
  // from a point p, cast an arbitrary ray
  // if it crosses the polygon even num of times, it is not inside the polygon
  // otherwise it is on the polygon
  // imagine a square or a pentagon and this will make sense.
  return 0;
}