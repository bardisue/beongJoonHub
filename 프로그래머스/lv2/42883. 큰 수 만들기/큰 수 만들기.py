def solution(number, k):
    stack = []
    count = 0
    for i in range(len(number)):
        num = int(number[i])
        if len(stack) == 0:
            stack.append(num)
            continue
        while stack[len(stack)-1] < num and count < k:
            stack.pop()
            count += 1
            if len(stack) == 0:
                break
        stack.append(num)
    while count < k:
        stack.pop()
        count += 1
    return ''.join(str(s) for s in stack)
