#include <algorithm>
#include <iostream>
#include <vector>
typedef __int128 int128;
typedef long long ll;

// helper generated by perplexity
void print_int128(int128 n) {
  if (n == 0) {
    std::cout << 0;
    return;
  }
  if (n < 0) {
    std::cout << '-';
    n = -n;
  }
  char buffer[40];
  int i = 0;
  while (n > 0) {
    buffer[i++] = '0' + (n % 10);
    n /= 10;
  }
  for (int j = i - 1; j >= 0; j--)
    std::cout << buffer[j];
}

int main() {
  int n;
  std::cin >> n;

  std::vector<ll> x(n), y(n);
  for (int i = 0; i < n; i++) {
    std::cin >> x[i] >> y[i];
  }

  std::sort(x.begin(), x.end());
  std::sort(y.begin(), y.end());

  int128 total_distance = 0;
  int128 sum_x = 0;
  int128 sum_y = 0;

  for (int i = 0; i < n; i++) {
    total_distance += int128(x[i]) * i - sum_x;
    total_distance += int128(y[i]) * i - sum_y;
    sum_x += x[i];
    sum_y += y[i];
  }

  print_int128(total_distance);
  std::cout << std::endl;

  return 0;
}
