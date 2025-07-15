#include <iostream>

using ll = long long;

int main() {
  std::ios::sync_with_stdio(false);
  std::cin.tie(nullptr);

  size_t n;
  ll k;
  std::cin >> n >> k;

  long largest_remaining_number = n;
  long smallest_remaining_number = 1;

  for (int i = n - 1; i >= 0; i--) {
    if (i <= k) {
      std::cout << largest_remaining_number-- << " ";
      k -= i;
    } else {
      std::cout << smallest_remaining_number++ << " ";
    }
  }

  return 0;
}