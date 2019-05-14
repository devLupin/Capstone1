"""
### main.py
### author : Hyuntaek Lim
### Created : 2019.04.07
### Testing
"""

#!/usr/bin/python

#----------------------------------------------------------------------
# import My lib
#----------------------------------------------------------------------
import cache.py
import Check.py


if __name__ == "__main__":
    #cache.NewLogFile()  # Create log file for the first time

    while True:
        student = Check.Attandance(Check.Find_MAC_Address())
        