import math
import sys

input = sys.stdin.readline

n = int(input())

points = []
nodes = []
unions = [i for i in range(n)]

for _ in range(n):
    x, y = map(float, input().split())
    points.append([x, y])

for i in range(n):
    for j in range(i+1, n):
        length = math.sqrt((points[i][0] - points[j][0]) ** 2 + (points[i][1] - points[j][1]) ** 2)
        nodes.append([i, j, length])

nodes.sort(key=lambda x:x[2])
count = 0
result = 0


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
        _count += 1
        _result += _cost
    return _count, _result


for node in nodes:
    f = node[0]
    s = node[1]
    cost = node[2]

    count, result = merge_union(f, s, cost, count, result)
    if count == n - 1:
        break


print(round(result,2))
