import sys

input = sys.stdin.readline
limit_number = 15000
sys.setrecursionlimit(limit_number)

n, m = map(int, input().split())
unions = [i for i in range(n - 1)]
nodes = []


def find_parent(a):
    if unions[a] != a:
        return find_parent(unions[a])
    else:
        return a


def merge_union(a, b):
    a = find_parent(a)
    b = find_parent(b)
    if a != b:
        unions[max(a, b)] = min(a, b)
        return True
    else:
        return False


for _ in range(m):
    f, s = map(int, input().split())
    f -= 2
    s -= 2
    merge_union(f, s)

for i in range(0, n):
    costs = list(map(int, input().split()))
    if i == 0:
        continue
    for j in range(i + 1, n):
        nodes.append([i - 1, j - 1, costs[j]])

nodes.sort(key=lambda x: x[2])

result_cost = 0
result_node = []
count = 0

for node in nodes:
    f, s, cost = node
    if merge_union(f, s):
        result_cost += cost
        result_node.append([f, s])
        count += 1
    if count == n - 2:
        break
print(result_cost, count)

for result in result_node:
    print(result[0]+2, result[1]+2)
    