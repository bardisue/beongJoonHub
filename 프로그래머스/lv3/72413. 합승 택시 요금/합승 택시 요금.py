import heapq

def solution(n, s, a, b, fares):
    a, b, s = a-1, b-1, s-1
    moneys = [[0 for i in range(n)] for j in range(n)]
    for fare in fares:
        x, y, cost = fare
        moneys[x-1][y-1] = cost
        moneys[y-1][x-1] = cost
    result = []
    for i in range(n):
        if i != s:
            result.append(dijkstra(moneys, s, i, n) + dijkstra(moneys, i, a, n)+ dijkstra(moneys, i, b, n))
        else:
            result.append(dijkstra(moneys, i, a, n) + dijkstra(moneys, i, b, n))
    return min(result)


def dijkstra(graph, start, end, n):
    distances = {node: float('inf') for node in range(n)}  # start로 부터의 거리 값을 저장하기 위함
    #print(distances)
    distances[start] = 0  # 시작 값은 0이어야 함
    queue = []
    heapq.heappush(queue, [distances[start], start])  # 시작 노드부터 탐색 시작 하기 위함.

    while queue:  # queue에 남아 있는 노드가 없으면 끝
        current_distance, current_destination = heapq.heappop(queue)  # 탐색 할 노드, 거리를 가져옴.
        #print(current_distance, current_destination)

        if distances[current_destination] < current_distance:  # 기존에 있는 거리보다 길다면, 볼 필요도 없음
            continue

        for i in range(len(graph[current_destination])):
            new_destination, new_distance = i, graph[current_destination][i]
            if new_distance == 0:
                continue
            distance = current_distance + new_distance  # 해당 노드를 거쳐 갈 때 거리
            if distance < distances[new_destination]:  # 알고 있는 거리 보다 작으면 갱신
                #print(new_destination, new_distance, "add")
                distances[new_destination] = distance
                heapq.heappush(queue, [distance, new_destination])  # 다음 인접 거리를 계산 하기 위해 큐에 삽입

    return distances[end]