def solution(places):
    answer = []
    for place in places:
        tmpAnswer = 1
        goTo = [[0,-1],[0,1],[-1,0],[1,0]]
        placesPad = [["X" for i in range(9)] for j in range(9)]
        for i in range(0,5):
            for j in range(0,5):
                placesPad[i+2][j+2] = place[i][j]
        for i in range(2,7):
            for j in range(2,7):
                if placesPad[i][j] == "P":
                    tmpGo = []
                    for go in goTo:
                        tmp = placesPad[i + go[0]][j + go[1]]
                        if tmp == "P":
                            tmpAnswer = 0
                        elif tmp == "O":
                            tmpGo.append([i + go[0], j + go[1]])
                        elif tmp == "X":
                            continue
                    for secondGo in tmpGo:
                        for go in goTo:
                            x = secondGo[0] + go[0]
                            y = secondGo[1] + go[1]
                            tmp = placesPad[x][y]
                            if tmp == "P":
                                if [x,y] != [i, j]:
                                    tmpAnswer = 0
                            elif tmp == "X":
                                continue
        answer.append(tmpAnswer)
    return answer
