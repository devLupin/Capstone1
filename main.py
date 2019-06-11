"""
### main.py
### author : Hyuntaek Lim
### Created : 2019.04.07
### Testing
"""

#!/usr/bin/python
#----------------------------------------------------------------------
# import std lib
#----------------------------------------------------------------------
import time

#----------------------------------------------------------------------
# import My lib
#----------------------------------------------------------------------
import Check
import cache
import escape
import Attendance


#----------------------------------------------------------------------
# Firebase Connect Part
#----------------------------------------------------------------------
from firebase import firebase
firebase = firebase.FirebaseApplication("https://capstone-776cd.firebaseio.com/", None)



#----------------------------------------------------------------------
# Output classification
#----------------------------------------------------------------------
def PRINT_SEPERATE():
    print("------------------------------------------------------------------------------------------\n")

def PRINT_SEPERATE_TWO_LINES():
    print("------------------------------------------------------------------------------------------\n\n")


#----------------------------------------------------------------------
# This is main.
# Such as int main(void){} in C language.
#----------------------------------------------------------------------
if __name__ == "__main__":
    temp = 0

    PRINT_SEPERATE()
    print('Start attendance program \n')
    print('Made by devLupin & chosun univ Capstone 8team \n')
    PRINT_SEPERATE_TWO_LINES()

    cache.NewLogFile()  # Create log file for the first time

    PRINT_SEPERATE()
    print('Start attendance \n')
    print('#Only the student who is currently being examined is considered to be present. \n')
    PRINT_SEPERATE_TWO_LINES()

    STDNUM_LIST = Check.Attandance(Check.Find_MAC_Address())    # late of students detection
    escape.Init_EscapeList(STDNUM_LIST)    # For ease of implementation
    
    
    ## From this time on, it is treated as a late of students
    while(firebase.get('Attendance', 'control')):  
        print("---------------------------------------------Log------------------------------------------")
        STDNUM_LIST = Check.Attandance(Check.Find_MAC_Address()) # Retest
        temp = escape.EscapeList(STDNUM_LIST)
        print('\n'+ str(STDNUM_LIST) + '\n')
        print("---------------------------------------------End------------------------------------------")
        Attendance.Update_LateOfStudents(temp)
    
    
    Attendance.Update_NumberOfAbsences(temp)