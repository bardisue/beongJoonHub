def solution(stones, k):
    left = 1
    right = 200000000
    _max = 0
    while left <= right:
        mid = (left + right) // 2
        #print(mid)
        if binary_solve(stones, k, mid):
            left = mid + 1
            _max = max(mid, _max)
        else:
            right = mid - 1
    return _max


def binary_solve(stones, k, friend):
    count = 0
    friend = int(friend)
    for stone in stones:
        if stone < friend:
            count += 1
            if count >= k:
                break
        else:
            count = 0
    if count >= k:
        return False
    else:
        return True
