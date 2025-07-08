#include <iostream>
#include <complex>
#include <numeric>
#include <vector>
 
typedef long long ll;
typedef long double ld;
typedef std::complex<ll> P;


#define X real()
#define Y imag()


ll area(std::vector<P> &pts) {
    ll area2 = 0;
    // uses shoelace
    for (size_t i = 0; i < pts.size(); i++) {
        int next_index = (i + 1) % pts.size();
        area2 += pts[i].X * pts[next_index].Y - pts[next_index].X * pts[i].Y;

    }
    return std::abs(area2);
}

ll boundaryPoints(std::vector<P> &pts) {
    ll points = 0;
    int n = pts.size();
    for (int i = 0; i < n; i++) {
        ll dx = std::abs(pts[i].X - pts[(i + 1) % n].X);
        ll dy = std::abs(pts[i].Y - pts[(i + 1) % n].Y);
        points += std::gcd(dx, dy);
    }
    return points;
}

int main() {
    int kN; std::cin >> kN;
    std::vector<P> vertices_list(kN);

    for (size_t i = 0; i < kN; i++) {
        int cur_x, cur_y;
        std::cin >> cur_x >> cur_y;
        vertices_list[i] = P(cur_x, cur_y);
    }

    ll totalArea = area(vertices_list);
    ll boundaryPts = boundaryPoints(vertices_list);
    ll interiorPts = (totalArea - boundaryPts) / 2 + 1;

    std::cout << interiorPts << " " << boundaryPts << std::endl;
    return 0;
}