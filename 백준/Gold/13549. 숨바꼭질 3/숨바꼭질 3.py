import sys
import heapq

input = sys.stdin.readline
INF = sys.maxsize

N, K = map(int, input().split())

queue = [(N, 0)]

counts = INF
fastest = [0] * 100001

while queue:
    node, count = queue.pop(0)
    if node < 0 or node > 100000:
        continue
    if fastest[node] == 0:
        fastest[node] = count
    else:
        if fastest[node] < count:
            continue
        else:
            fastest[node] = count
    if node == K:
        counts = min(counts, count)
        break
    elif K < node:
        queue.append((node-1, count+1))
    elif node == 0:
        queue.append((node + 1, count + 1))
    else:
        queue.append((node * 2, count))
        queue.append((node - 1, count + 1))
        queue.append((node + 1, count + 1))

print(counts)