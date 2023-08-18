import sys
input = sys.stdin.readline
limit_number = 10**6
sys.setrecursionlimit(limit_number)

n, m = map(int, input().split())

union = [i for i in range(n)]


def find_parent(x):
    if union[x] != x:
        return find_parent(union[x])
    else:
        return x


def merge_union(x, y, i):
    x = find_parent(x)
    y = find_parent(y)
    union[y] = x
    if x == y:
        return i+1
    else:
        return 0


result = 0

for i in range(m):
    a, b = map(int, input().split())
    if result == 0:
        result = merge_union(a, b, i)

print(result)
