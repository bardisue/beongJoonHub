import math

def solution(fees, records):
    answer = []
    carTime = [0 for i in range(10000)]
    carSum = [0 for i in range(10000)]
    
    for record in records:
        time, carNum, io = record.split(" ")
        carNum = int(carNum)
        hour, minute = map(int, time.split(":"))
        sumMinute = hour*60 + minute
        if io == "IN":
            carTime[carNum] = sumMinute + 1
        else:
            carSum[carNum] += sumMinute - carTime[carNum] +1
            carTime[carNum] = 0
    for carNum in range(len(carTime)):
        if carTime[carNum] != 0:
            carSum[carNum] += 24*60 - carTime[carNum]
            carTime[carNum] = 0
    for car in carSum:
        if car == 0:
            continue
        if car > fees[0]:
            tmp = fees[1] + math.ceil((car - fees[0])/fees[2]) *fees[3]
            answer.append(tmp)
        elif car <= fees[0] :
            answer.append(fees[1])
    return answer