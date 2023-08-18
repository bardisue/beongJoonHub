import sys
limit_number = 10**6
sys.setrecursionlimit(limit_number)
input = sys.stdin.readline

n, m = map(int, input().split())
union = [i for i in range(n)]


def find_parent(a):
    if union[a] != a:
        return find_parent(union[a])
    else:
        return a


def merge_union(a, b):
    a = find_parent(a)
    b = find_parent(b)
    union[max(a, b)] = union[min(a, b)]


node_list = []

for _ in range(m):
    node_list.append(list(map(int, input().split())))

node_list.sort(key=lambda x: x[2])

result = 0
node_count = 0

for node in node_list:
    x = node[0]-1
    y = node[1]-1
    if find_parent(x) != find_parent(y):
        merge_union(x, y)
        result += node[2]
        node_count += 1
    if node_count == n-1:
        print(result)
        break
