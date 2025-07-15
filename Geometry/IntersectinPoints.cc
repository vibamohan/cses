#include <algorithm>
#include <complex>
#include <iostream>
#include <vector>
using namespace std;

using ll = long long;
using P = complex<ll>;

#define X real()
#define Y imag()

// Fenwick Tree (BIT) Used existing implementation from online
template <typename T> class BIT {
public:
  BIT(int size) : size(size), bit(size + 2), arr(size + 1) {}

  void set(int i, T val) { add(i, val - arr[i]); }

  void add(int ind, T val) {
    arr[ind] += val;
    for (++ind; ind <= size; ind += ind & -ind)
      bit[ind] += val;
  }

  T pref_sum(int ind) {
    T total = 0;
    for (++ind; ind > 0; ind -= ind & -ind)
      total += bit[ind];
    return total;
  }

private:
  int size;
  vector<T> bit;
  vector<T> arr;
};

const int MAX_COORD = 1'000'000;

int main() {
  ios::sync_with_stdio(false);
  cin.tie(nullptr);

  int n;
  cin >> n;

  // Events = [y, type, x1, x2] * n
  // types, 1 = vertical seg.start, 2 = horiz. seg.start, 3 = vert. seg.end
  vector<array<ll, 4>> events;

  for (int i = 0; i < n; ++i) {
    ll x1, y1, x2, y2;
    cin >> x1 >> y1 >> x2 >> y2;
    P p1(x1, y1), p2(x2, y2);

    // Normalize points so (p1) <= (p2) lex order
    if (p2.X < p1.X || (p2.X == p1.X && p2.Y < p1.Y))
      std::swap(p1, p2);

    if (p1.Y == p2.Y) {
      // only add horizontal
      events.push_back({p1.Y, 2, p1.X, p2.X});
    } else {
      // remove and add ops on the events
      events.push_back({p1.Y, 1, p1.X, 0});
      events.push_back({p2.Y, 3, p1.X, 0});
    }
  }

  // Sort events by y asc. thenComparing type asc.
  std::sort(events.begin(), events.end(), [](const auto &a, const auto &b) {
    if (a[0] != b[0])
      return a[0] < b[0];
    return a[1] < b[1];
  });

  BIT<ll> bit(2 * MAX_COORD + 5);
  ll ans = 0;

  for (auto &[y, type, x1, x2] : events) {
    ll i1 = x1 + MAX_COORD + 1;
    ll i2 = x2 + MAX_COORD + 1;

    if (type == 1) {
      // add vertical segment x to BIT
      bit.add(i1, 1);
    } else if (type == 2) {
      // do prefix sum bc its horizontal segment to count encompassed vertials
      ans += bit.pref_sum(i2) - bit.pref_sum(i1 - 1);
    } else {
      // remove vertical segment x from BIT
      bit.add(i1, -1);
    }
  }

  std::cout << ans << "\n";
  return 0;
}
