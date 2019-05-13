import os
import subprocess

CONST_NEXTLINE = 30
studentList = []

command = subprocess.check_output(["iwinfo", "wlan0", "assoclist"], universal_newlines=True)
copy = command

first_macAddr = command[0:17]
studentList.append(first_macAddr)

end = copy.find("expected throughput: unknown")
copy = copy[(end+CONST_NEXTLINE):]
studentList.append(copy[0:17])
print copy + '\n' + '\n'

end = copy.find("expected throughput: unknown")
copy = copy[(end+CONST_NEXTLINE):]

end = copy.find("expected throughput: unknown")
if(end < 0):
    print ("0 down")

print copy + '\n' + '\n'
print studentList
