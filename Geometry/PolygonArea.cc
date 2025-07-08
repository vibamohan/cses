typedef long long ll;
#include <vector>
#include <iostream>

int main() {
    std::ios::sync_with_stdio(false);
    std::cin.tie(nullptr);

    // Use the shoelace formula to calc area based on vertices
    size_t n; std::cin >> n;
    std::vector<ll> x(n);
    std::vector<ll> y(n);

    for (size_t i = 0; i < n; i++) {
        std::cin >> x[i] >> y[i];
    }

    ll area2 = 0;
    for (size_t i = 0; i < n; i++) {
        int next_index = (i + 1) % n;
        area2 += x[i] * y[next_index] - x[next_index] * y[i];
    }

    // NOTE: the question asks for TWICE the area 
    // so in other cases you would need to divide by 2
    area2 = std::abs(area2);
    std::cout << area2 << std::endl;

    return 0;
}