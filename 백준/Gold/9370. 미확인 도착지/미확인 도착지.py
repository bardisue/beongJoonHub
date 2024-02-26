# This is a sample Python script.
import sys
import heapq

input = sys.stdin.readline
INF = sys.maxsize

inp = int(input())

for _ in range(inp):
    n, m, t = map(int, input().split())
    s, g, h = map(int, input().split())

    graph = [[] for _ in range(n + 1)]
    dp = [INF] * (n + 1)

    for _ in range(m):
        a, b, d = map(int, input().split())
        if (a == g and b == h) or (a == h and b == g):
            graph[a].append((d-0.1, b))
            graph[b].append((d-0.1, a))
        else:
            graph[a].append((d, b))
            graph[b].append((d, a))

    queue = []
    lst = []
    #print(graph, dp, queue, lst)


    def djikstar(start):
        dp[start] = 0
        heapq.heappush(queue, (0, start))
        while queue:
            wei, node, = heapq.heappop(queue)

            #print(wei, node, already)
            if dp[node] < wei:
                continue
            else:
                for _w, next_node in graph[node]:
                    next_wei = _w + wei
                    if next_wei < dp[next_node]:
                        dp[next_node] = next_wei
                        heapq.heappush(queue, (next_wei, next_node))


    djikstar(s)
    result = []
    for _ in range(t):
        _t = int(input())
        fastest = dp[_t]
        if fastest == INF:
            continue
        if type(fastest) == float:
            result.append(_t)
    result.sort()
    print(*result)