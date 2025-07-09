#include <algorithm>
#include <iostream>
#include <limits>

typedef long long ll;

int main() {
  std::ios::sync_with_stdio(false);
  std::cin.tie(nullptr);

  int n;
  std::cin >> n;

  ll min_sum = std::numeric_limits<ll>::max();
  ll max_sum = std::numeric_limits<ll>::min();
  ll min_diff = std::numeric_limits<ll>::max();
  ll max_diff = std::numeric_limits<ll>::min();

  for (int i = 0; i < n; i++) {
    ll x, y;
    std::cin >> x >> y;
    ll sum = x + y;
    ll diff = x - y;

    if (i == 0) {
      min_sum = max_sum = sum;
      min_diff = max_diff = diff;
      std::cout << 0 << std::endl;
    } else {
      min_sum = std::min(min_sum, sum);
      max_sum = std::max(max_sum, sum);
      min_diff = std::min(min_diff, diff);
      max_diff = std::max(max_diff, diff);

      std::cout << std::max(max_sum - min_sum, max_diff - min_diff)
                << std::endl;
    }
  }
  return 0;
}
