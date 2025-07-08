#include <iostream>
#include <complex>
 
typedef std::complex<double> P;
#define X real()
#define Y imag()
 

double crossProduct(const P &a, const P &b) {
    return (std::conj(a) * b).imag();
}

bool isOnLine(const P &p1, const P &p2, const P &p3) {
    // is p3 on the line defined by p1 and p2? 
    return (std::min(p1.X, p2.X) <= p3.X) && (std::min(p1.Y, p2.Y) <= p3.Y) 
        && (std::max(p1.X, p2.X) >= p3.X) && (std::max(p1.Y, p2.Y) >= p3.Y);
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
    if (d1 * d2 < 0 && d3 * d4 < 0) return true;

    // Case 2, one of the points lies on the other line or lines are fully collinear 
    // or if not, it will return false since there are no other case to chekc
    return (d1 == 0 && isOnLine(a, b, c)) || (d2 == 0 && isOnLine(a, b, d))
        || (d3 == 0 && isOnLine(c, d, a)) || (d4 == 0 && isOnLine(c, d, b));

}
int main() {
    std::ios::sync_with_stdio(false);
    std::cin.tie(nullptr);
 
    int kT;
    std::cin >> kT;
 
    for (size_t test_case = 0; test_case < kT; test_case++) {
        double x1, y1, x2, y2, x3, y3, x4, y4;
        std::cin >> x1 >> y1 >> x2 >> y2 >> x3 >> y3 >> x4 >> y4;
        P p1(x1, y1);
        P p2(x2, y2);
        P p3(x3, y3);
        P p4(x4, y4);

        (isIntersecting(p1, p2, p3, p4)) ? std::cout << "YES" : std::cout << "NO";
        std::cout << std::endl;
    }
 
    return 0;
}