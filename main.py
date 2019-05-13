"""
### Capstone Design
### author : Hyuntaek Lim
### Created : 2019.04.07
### Testing
"""

#!/usr/bin/python

#----------------------------------------------------------------------
# Firebase Connect Part
#----------------------------------------------------------------------
from firebase import firebase
firebase = firebase.FirebaseApplication("https://capstone-776cd.firebaseio.com/", None)

#----------------------------------------------------------------------
# import std lib
#----------------------------------------------------------------------
import os
import subprocess
import time


#----------------------------------------------------------------------
# Get the MAC address of the students.
#----------------------------------------------------------------------
def Attendance():
    CONST_NEXTLINE = 30 # Difference up to
    studentList = []    # MAC addr list
    cnt = 0             # ID sequential search count

    # Connected smartphone list
    command = subprocess.check_output(["iwinfo", "wlan0", "assoclist"], universal_newlines=True)
    copy = command      # Do not touch the original

    first_macAddr = copy[0:17] 
    studentList.append(first_macAddr)   # Put it on the list
    while True:
        end = copy.find("expected throughput: unknown")     # Find each MAC address one by one.
        if(end < 0):    # If you don't have the following MAC address
            break

        cnt += 1
        copy = copy[(end+CONST_NEXTLINE):]
        studentList.append(copy[0:17])
    
    return studentList


#----------------------------------------------------------------------
# If the MAC address of the connected smartphone matches the MAC address already stored
#----------------------------------------------------------------------
def Searching(student):
        firebasebase.get('user')



if __name__ == "__main__":
    student = Attendance()
    Searching(student)