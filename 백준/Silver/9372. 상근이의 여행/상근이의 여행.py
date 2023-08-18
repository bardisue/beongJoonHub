import sys
input = sys.stdin.readline

k = int(input())
for _ in range(k):
    n, m = map(int, input().split())
    for _ in range(m):
        x, y = map(int, input().split())
    print(n-1)