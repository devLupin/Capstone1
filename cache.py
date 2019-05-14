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

#----------------------------------------------------------------------
# import std lib
#----------------------------------------------------------------------
from datetime import datetime

now = datetime.now()
FILEPATH = "/root/log/log.txt"


#----------------------------------------------------------------------
# Create log file for the first time
# The Method is executed only once.
#----------------------------------------------------------------------
def NewLogFile():
    f = open(FILEPATH, 'w')     # log.txt file open(write mode)
    
    data = "Press ctrl + f and enter the student number. \n\n" 
    f.write(data)

    f.close()



#----------------------------------------------------------------------
# ex) 2019-01-01 01:23:45 20140000 has gone out.
# Written to text file
# file name : log.txt
#----------------------------------------------------------------------
def Log(STUDENT_NUM):
    f = open(FILEPATH, 'a')     # log.txt file open(append mode)
    
    # Get current time
    curDate = str(now.year) + "-" + str(now.month) + "-" + str(now.day) + " "
    curTime = str(now.hour) + ":" + str(now.minute) + ":" + str(now.second) + "  "
    cur = curDate + curTime

    data = cur + " " + str(STUDENT_NUM) + " has gone out. \n"
    f.write(data)

    f.close()

    print(data + '\n')
