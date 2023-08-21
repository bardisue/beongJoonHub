import math
import sys

input = sys.stdin.readline

n, m = map(int, input().split())

points = []
nodes = []
unions = [i for i in range(n)]


def find_parent(a):
    if unions[a] != a:
        return find_parent(unions[a])
    else:
        return a


def merge_union(a, b, _cost, _count, _result):
    a = find_parent(a)
    b = find_parent(b)
    if a != b:
        unions[max(a, b)] = unions[min(a, b)]
        _result += _cost
        _count += 1
    return _result, _count


def check_union(lst):
    for index in lst:
        if find_parent(index) != find_parent(lst[0]):
            return False
    return True


def pre_merge(a, b, _count):
    a = find_parent(a)
    b = find_parent(b)
    if a != b:
        unions[max(a, b)] = unions[min(a, b)]
        _count += 1
    return _count


for _ in range(n):
    x, y = map(int, input().split())
    points.append([x, y])

for i in range(n):
    for j in range(i + 1, n):
        length = math.sqrt((points[i][0] - points[j][0]) ** 2 + (points[i][1] - points[j][1]) ** 2)
        nodes.append([i, j, length])

count = 0
result = 0

for _ in range(m):
    o, p = map(int, input().split())
    o -= 1
    p -= 1
    count = pre_merge(o, p, count)

nodes.sort(key=lambda x:x[2])

for node in nodes:
    f = node[0]
    s = node[1]
    cost = node[2]
    result, count = merge_union(f, s, cost, count, result)
    if count == n-1:
        break
print('%.2f' % result)
