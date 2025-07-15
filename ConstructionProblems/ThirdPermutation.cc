#include <iostream>
#include <set>
#include <vector>

int main() {
  /*
      I initially tried a greedy approach of maintaining a set of numbers
      that we haven't tried yet and selecting one of them for ci, that works for
     that i. However, it failed on some test cases because the local optima does
     not necessarily solve the global problem. insight: in problems like this,
     greedy doesn't work since choices depend on each other in a way that there
     is no 'best' at each layer. basically, picking 1 number now (even if it
     works) doesn't guarentee you success later in the problem.

      Now I'm using a cycle based strategy, since backtracking worst case could
     be very high for 10^5. Cycle through te cycles of a and see if it works
     with b, if it does we have a solution. If it never does, we don't. This is
     because we are considering distinct permutations and comparing it against
     only one array. This also does not work in some cases.

     Finally arriving upon a greedy first, then swap last num stratagey.
   */
  std::ios::sync_with_stdio(false);
  std::cin.tie(nullptr);

  int n;
  std::cin >> n;

  // quick note: better to do this than push_back since this allocates memory at
  // once rather than mallocing at multiple steps.
  std::vector<int> a(n);
  for (int i = 0; i < n; i++) {
    std::cin >> a[i];
  }

  std::vector<int> b(n);
  for (int i = 0; i < n; i++) {
    std::cin >> b[i];
  }

  std::vector<int> c(n, 0);
  std::set<int> unused;
  for (int i = 0; i < n; i++) {
    unused.emplace(i + 1);
  }

  for (int i = 0; i < n - 1; i++) {
    for (int x : unused) {
      if (x != a[i] and x != b[i]) {
        c[i] = x;
        break;
      }
    }

    if (c[i] == 0) {
      std::cout << "IMPOSSIBLE" << std::endl;
      return 0;
    }
    unused.erase(c[i]);
  }

  int last_value = *unused.rbegin();

  if (last_value != b[n - 1] and last_value != a[n - 1]) {
    c[n - 1] = last_value;
  } else {
    bool fixed = false;
    for (int i = 0; i < n - 1; i++) {
      int temp = c[i];
      if (temp != a[n - 1] and temp != b[n - 1] and last_value != a[i] and
          last_value != b[i]) {
        c[n - 1] = temp;
        c[i] = last_value;
        fixed = true;
        break;
      }
    }
    if (!fixed) {
      std::cout << "IMPOSSIBLE\n";
      return 0;
    }
  }

  for (int &x : c) {
    std::cout << x << " ";
  }
  std::cout << std::endl;
}
