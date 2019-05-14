"""
### cache.py
### author : Hyuntaek Lim
### created : 2019-05-01
"""

"""
Leave all the records in the text file.

If the administrator uses the student number to search the log file, 
he can see the time and number of times he escaped.
"""

#!/usr/bin/python

import datetime
now = datetime.now()

FILEPATH = "/root/log/log.txt"

def NewLogFile():
    f = open(FILEPATH, 'w')
    
    data = "Press ctrl + f and enter the student number. \n\n" 
    f.write(data)

    f.close()



def Log(STUDENT_NUM):
    f = open(FILEPATH, 'a')
    
    curDate = now.year + "-" + now.month + "-" + now.day + " "
    curTime = now.hour + ":" + now.minute + ":" + now.sec + "  "
    cur = curDate + curTime

    data = cur + STUDENT_NUM + " has gone out. \n"
    f.write(data)

    f.close()
