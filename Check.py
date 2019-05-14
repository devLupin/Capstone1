"""
### Check.py
### author : Hyuntaek Lim
### Created : 2019.04.07
"""

"""
Openwrt is used to obtain the MAC address and 
compares it with the stored information of firebase.


Use this method to develop proven attendance methods.
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
import collections

#----------------------------------------------------------------------
# Find a connected smartphone Mac address
#----------------------------------------------------------------------
def Find_MAC_Address():
    CONST_NEXTLINE = 30 # Difference up to
    studentList = []    # MAC addr list

    # Connected smartphone list
    command = subprocess.check_output(["iwinfo", "wlan0", "assoclist"], universal_newlines=True)
    copy = command      # Do not touch the original

    first_macAddr = copy[0:17] 
    studentList.append(first_macAddr)   # Put it on the list
    while True:
        end = copy.find("expected throughput: unknown")     # Find each MAC address one by one.
        if(end < 0):    # If you don't have the following MAC address
            break

        copy = copy[(end+CONST_NEXTLINE):]
        studentList.append(copy[0:17])
    
    return studentList


#----------------------------------------------------------------------
# Look up the student number by referring to the MAC address
#----------------------------------------------------------------------
def Attandance(STUDENT):
    check_list = [" ",]    # Attendance Check list
    cnt = 0         # For sequential search count

    while True:
        if (cnt > 99): # While loop escape condition
            break

        path = 'user_info/' + str(cnt)      # firebase path
        temp = firebase.get(path, 'user_mac') # Get the user mac address

        for i in STUDENT:   # Sequential search
            if (i == temp):  # If have a matching mac address
                # Find the student number
                check_list.append(firebase.get(path, 'user_id')
                continue
        
        cnt += 1

    return check_list