import sys
import heapq

input = sys.stdin.readline
INF = sys.maxsize

N, E = map(int, input().split())
graph = [[] for _ in range(N + 1)]
dpS = [INF] * (N + 1)
dpV1 = [INF] * (N + 1)
dpV2 = [INF] * (N + 1)

for i in range(E):
    a, b, c = map(int, input().split())
    graph[a].append((c, b))
    graph[b].append((c, a))


def dijkstra(start, dp):
    dp[start] = 0
    queue = []
    heapq.heappush(queue, (0, start))
    while queue:
        wei, node = heapq.heappop(queue)
        if dp[node] < wei:
            continue
        for w, next_node in graph[node]:
            next_wei = w + wei
            if next_wei < dp[next_node]:
                dp[next_node] = next_wei
                heapq.heappush(queue, (next_wei, next_node))
    return dp


v1, v2 = map(int, input().split())

dpS = dijkstra(1, dpS)
dpV1 = dijkstra(v1, dpV1)
dpV2 = dijkstra(v2, dpV2)

v1_v2 = 0
v2_v1 = 0

if dpS[v1] + dpV1[v2] != dpS[v2]:
    v1_v2 = dpS[v1] + dpV1[v2] + dpV2[N]
else:
    v1_v2 = dpS[v2] + dpV2[N]

if dpS[v2] + dpV2[v1] != dpS[v1]:
    v2_v1 = dpS[v2] + dpV2[v1] + dpV1[N]
else:
    v2_v1 = dpS[v1] + dpV1[N]

result = min(v1_v2, v2_v1)

if result >= INF:
    print(-1)
else:
    print(result)