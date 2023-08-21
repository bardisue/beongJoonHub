import math
import sys
input = sys.stdin.readline


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


while True:
    n, m = map(int, input().split())
    if n == 0 and m == 0:
        break
    count = 0
    costSum = 0
    result = 0
    nodes = []
    unions = [i for i in range(n)]
    for _ in range(m):
        f, s, cost = map(int, input().split())
        nodes.append([f, s, cost])
    nodes.sort(key=lambda x:x[2])
    for node in nodes:
        f, s, cost = node[0], node[1], node[2]
        costSum += cost
        result, count = merge_union(f, s, cost, count, result)
        if count == n-1:
            resultToMinus = result
    print(costSum - resultToMinus)