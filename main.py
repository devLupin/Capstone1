"""
### Capstone Design
### author : Hyuntaek Lim
### Created : 2019.04.07
### Testing
"""

#!/usr/bin/python

#-----------------------------------
# Firebase Connect Part
#-----------------------------------
from firebase import firebase
firebase = firebase.FirebaseApplication("https://capstone-776cd.firebaseio.com/", None)

#-----------------------------------
# import std lib
#-----------------------------------
import os
import subprocess
import time

def Attendance():
    CONST_NEXTLINE = 30 
    studentList = []
    cnt = 0

    command = subprocess.check_output(["iwinfo", "wlan0", "assoclist"], universal_newlines=True)
    copy = command

    first_macAddr = copy[0:17]
    studentList.append(first_macAddr)
    while True:
        end = copy.find("expected throughput: unknown")
        if(end < 0):
            break

        cnt += 1
        copy = copy[(end+CONST_NEXTLINE):]
        studentList.append(copy[0:17])
    
    print(studentList, cnt)

if __name__ == "__main__":
    Attendance()

