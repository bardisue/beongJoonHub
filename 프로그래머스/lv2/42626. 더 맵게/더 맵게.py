import heapq

    
def solution(scoville, K):
    count = 0

    pq = []

    for idx in scoville:
        heapq.heappush(pq, idx)

    while pq[0] < K:
        a = heapq.heappop(pq)
        b = heapq.heappop(pq)
        heapq.heappush(pq, a + b * 2)
        count += 1
        if  len(pq) <= 1 and pq[0] < K:
            return -1
    return count