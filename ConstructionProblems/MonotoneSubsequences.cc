#include <iostream>
#include <vector>

using ll = long long;

int main() {
  int num_tests;
  std::cin >> num_tests;

  for (size_t test_num = 0; test_num < num_tests; test_num++) {
    int n;
    std::cin >> n;
    int k;
    std::cin >> k;

    // Erdős–Szekeres theorem - if there is a sequence of 𝑛^2+1 numbers,
    // then there is either a monotonic rising subsequence of 𝑛+1 numbers
    //  or a monotonic descending subsequence of 𝑛+1 numbers.
    if (k * k < n) {
      std::cout << "IMPOSSIBLE" << std::endl;
      continue;
    }

    std::vector<int> monotone;
    int left = 1;
    int right = k;

    while (right <= n) {
      // reverse blocks of size k or lower
      // and add them into the vector
      // this works to prevent increasing subsequences
      // and creates 'blocks' of max size k in output
      for (int i = right; i >= left; i--) {
        monotone.push_back(i);
      }

      // can't create any more
      if (right >= n)
        break;

      // advance left, but limit right
      left = right + 1;
      right = std::min(n, right + k);
    }

    for (auto &i : monotone) {
      std::cout << i << " ";
    }

    std::cout << std::endl;
  }

  return 0;
}