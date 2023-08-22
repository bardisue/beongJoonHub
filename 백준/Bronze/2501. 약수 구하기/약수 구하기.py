n, m = map(int, input().split())

lst = []
count = 0

for i in range(1, n+1):
    if n%i == 0:
        count += 1
    if count == m:
        print(i)
        break
    if i == n:
        print(0)
        break