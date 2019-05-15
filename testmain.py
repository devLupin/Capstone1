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
    PRINT_SEPERATE()
    print('Start attendance program \n')
    print('Made by devLupin & chosun univ Capstone 8team \n')
    PRINT_SEPERATE_TWO_LINES()

    cache.NewLogFile()  # Create log file for the first time

    PRINT_SEPERATE()
    print('Start attendance \n')
    print('#Only the student who is currently being examined is considered to be present. \n')
    PRINT_SEPERATE_TWO_LINES()
    cfma = Check.Find_MAC_Address()
    STDNUM_LIST = Check.Attandance(cfma)    # late of students detection

    print('\n\n')
    print('\n\n')

    escape.Init_EscapeList(STDNUM_LIST)    # For ease of implementation


