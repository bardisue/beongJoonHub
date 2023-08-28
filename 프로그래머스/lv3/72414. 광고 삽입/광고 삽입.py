def solution(play_time, adv_time, logs):   
    play_time = time_to_second(play_time)
    adv_time = time_to_second(adv_time)
    #print(adv_time)
    time_lst = [0 for i in range(play_time+1)]
    for log in logs:
        #print(log)
        s, e = map(str, log.split('-'))
        s = time_to_second(s)
        e = time_to_second(e)
        time_lst[s] += 1
        time_lst[e] -= 1
    most_view = 0                           # 5
    max_time = 0
    for i in range(1, len(time_lst)):       # 4
        time_lst[i] = time_lst[i] + time_lst[i - 1]

    for i in range(1, len(time_lst)):       # 4
        time_lst[i] = time_lst[i] + time_lst[i - 1]

    for i in range(adv_time - 1, play_time):
        if i >= adv_time:
            if most_view < time_lst[i] - time_lst[i - adv_time]:
                most_view = time_lst[i] - time_lst[i - adv_time]
                max_time = i - adv_time + 1
        else:
            if most_view < time_lst[i]:
                most_view = time_lst[i]
                max_time = i - adv_time + 1

    return second_to_time(max_time)


def time_to_second(time):
    hour, minute, second = map(int, time.split(":"))
    return hour * 3600 + minute * 60 + second


def second_to_time(second):
    start_time_hour = int(second / 3600)
    start_time_minute = int((second % 3600) / 60)
    start_time_second = second - start_time_hour*3600 - start_time_minute*60
    start_time_hour = format(start_time_hour, '02')
    start_time_minute = format(start_time_minute, '02')
    start_time_second = format(start_time_second, '02')

    return start_time_hour + ":" + start_time_minute + ":" + start_time_second

