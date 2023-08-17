n = int(input())
m = int(input())
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


for i in range(n):
    city = list(map(int, input().split()))
    for j in range(n):
        if city[j] == 1:
            merge_union(i, j)

destinations = list(map(int, input().split()))
result = []

for destination in destinations:
    result.append(find_parent(destination-1))
set_union = set(result)

if len(set_union) == 1:
    print("YES")
else:
    print("NO")
