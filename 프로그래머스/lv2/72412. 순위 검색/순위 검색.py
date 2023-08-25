from bisect import bisect_left, bisect_right
from itertools import combinations

def make_all_cases(temp, cases):
    key_list = temp[:-1]
    score = temp[-1]
    for k in range(5):
        for li in combinations(key_list, k):
            case = ''.join(li)
            if case in cases:
                cases[case].append(int(score))
            else:
                cases[case] = [int(score)]
    #print(cases)
    return cases


def solution(info, query):
    info_lst = []
    for idx in info:
        info_lst.append(list(map(str, idx.split())))
    #info_lst.sort(key=lambda x:int(x[4]))

    info_score = [int(i[4]) for i in info_lst]
    result = []
    cases = {}
    for i in info_lst:
        cases = make_all_cases(i, cases)

    #print(cases)

    for case in cases:
        #print(cases[case])
        cases[case].sort()

    for tmp_query in query:
        count = 0
        query_lst = tmp_query.split()
        query_score = int(query_lst[-1])
        lst_removed = [i for i in query_lst[:-1] if i != 'and' and i != '-']
        key = ''.join(lst_removed)
        if key not in cases:
            result.append(0)
            continue
        left_index = bisect_left(cases[key], query_score)
        num = len(cases[key]) - left_index
        result.append(num)
    return result
