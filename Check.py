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
    
    macList = []    # MAC addr list

    # Connected smartphone list
    command = subprocess.check_output(["iwinfo", "wlan0", "assoclist"], universal_newlines=True)
    copy = command      # Do not touch the original

    first_macAddr = copy[0:17] 
    macList.append(first_macAddr)   # Put it on the list
    while True:
        end = copy.find("expected throughput: unknown")     # Find each MAC address one by one.
        if(end < 0):    # If you don't have the following MAC address
            break

        copy = copy[(end+CONST_NEXTLINE):]
        macList.append(copy[0:17])
    
    return macList


#----------------------------------------------------------------------
# Look up the student number by referring to the MAC address
#----------------------------------------------------------------------
def Attandance(MAC_LIST):
    stdNum_list = []    # Attendance Check list
    limit = firebase.get('count_class/', 'user_id')
    cnt = 1
    while (cnt < limit):
        path = 'user_info/' + str(cnt)      # firebase path
        temp = firebase.get(path, 'user_mac') # Get the user mac address

        for mac in MAC_LIST:    # Sequential search
            if (mac == temp):   # If have a matching mac address
                                # Find the student number
                # Order by (cnt, mac_addr, stdNum)
                stdNum_list.append([cnt, mac, firebase.get(path, 'user_id')])
        
        cnt += 1

    return stdNum_list