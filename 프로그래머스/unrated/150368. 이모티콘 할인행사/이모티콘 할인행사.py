def solution(users, emoticons):
    answer = [0, 0]
    data = [10 ,20, 30, 40]
    discount = []
    
    # 이모티콘 할인율 구하기
    def dfs(temp, depth):
        if depth == len(temp):
            discount.append(temp[:])
            return
        for d in data:
            temp[depth] += d
            dfs(temp, depth + 1)
            temp[depth] -= d
            
    dfs([0] * len(emoticons), 0)
    
    
    plus = 0
    count = 0
    
    for lst in discount:
        tmpPlus = 0
        tmpCount = 0
        for user in users:
            userBuy = 0
            for itemIdx in range(len(lst)):
                if user[0] <=lst[itemIdx]:
                    userBuy += emoticons[itemIdx] * (100-lst[itemIdx]) / 100
            if userBuy>=user[1]:
                tmpPlus +=1
            else:
                tmpCount += userBuy
        if tmpPlus > plus :
            plus = tmpPlus
            count = tmpCount
        elif tmpPlus == plus:
            if count < tmpCount:
                count = tmpCount
    return[plus,count]