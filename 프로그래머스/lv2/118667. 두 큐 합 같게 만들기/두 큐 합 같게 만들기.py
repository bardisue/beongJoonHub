from collections import deque

def solution(queue1, queue2):
    
    queue1 = deque(queue1)
    queue2 = deque(queue2)
    
    maxMove = len(queue1) * 3
    
    answer = 0
    
    sum1 = sum(queue1)
    sum2 = sum(queue2)
    
    total = sum1+ sum2
    
    if(total % 2 == 1):
        return -1
    else:
        while(True):
            if(sum1 > sum2):
                tmp = queue1.popleft()
                queue2.append(tmp)
                sum1 -= tmp
                sum2 += tmp
                answer += 1
            elif(sum2 > sum1):
                tmp = queue2.popleft()
                queue1.append(tmp)
                sum2 -= tmp
                sum1 += tmp
                answer += 1
            else:
                break
            if answer == maxMove:
                answer = -1
                break
    
    return answer