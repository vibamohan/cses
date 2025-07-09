#include <complex>
#include <iostream>

typedef std::complex<double> P;
#define X real()
#define Y imag()

int main() {
  std::ios::sync_with_stdio(false);
  std::cin.tie(nullptr);

  int kT;
  std::cin >> kT;

  for (size_t test_case = 0; test_case < kT; test_case++) {
    double x1, y1, x2, y2, x3, y3;
    std::cin >> x1 >> y1 >> x2 >> y2 >> x3 >> y3;
    P p1 = {x1, y1};
    P p2 = {x2, y2};
    P p3 = {x3, y3};

    // Use cross product to check point location
    auto crossProduct = imag((p2 - p1) * std::conj(p3 - p1));
    if (crossProduct > 0) {
      std::cout << "RIGHT" << std::endl;
    } else if (crossProduct == 0) {
      std::cout << "TOUCH" << std::endl;
    } else {
      std::cout << "LEFT" << std::endl;
    }
  }

  return 0;
}