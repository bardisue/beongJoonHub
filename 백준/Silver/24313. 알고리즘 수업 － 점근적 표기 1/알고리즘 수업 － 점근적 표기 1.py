n, m = map(int, input().split())
c = int(input())
n0 = int(input())

if n*n0+m > c*n0:
    print(0)
else:
    if n <= c:
        print(1)
    else:
        print(0)