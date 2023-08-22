n = int(input())

max_5 = int(n/5)
flag = 0

for i in reversed(range(max_5+1)):
    if (n - i*5) % 3 == 0:
        print(i + int((n - i*5)/3))
        flag = 1
        break
if flag == 0:
    print(-1)