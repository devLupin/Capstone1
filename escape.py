#----------------------------------------------------------------------
# Firebase Connect Part
#----------------------------------------------------------------------
from firebase import firebase
firebase = firebase.FirebaseApplication("https://capstone-776cd.firebaseio.com/", None)


#----------------------------------------------------------------------
# import My lib
#----------------------------------------------------------------------
import cache


#----------------------------------------------------------------------
# Role of Constants
#----------------------------------------------------------------------
CNT = 0
MAC = 1
STDNUM = 2
STATUS_LATE_STUDENT = "LATE"

idx = 0     # Convenience of implementation




#----------------------------------------------------------------------
# First-time attendance check of the day
#----------------------------------------------------------------------
def Init_EscapeList(STDNUM_LIST):
    global idx

    for student in STDNUM_LIST:
        path = 'The_number_of_escapes/' + str(idx)
        firebase.put(path, 'STDNUM', student[STDNUM])
        firebase.put(path, 'CNT', student[CNT])
        firebase.put(path, 'MAC', student[MAC])
        firebase.put(path, 'ESCAPES', 0)
        firebase.put(path, 'STATUS', False)
        firebase.put(path, 'IS_LATE', False)

        idx += 1



#----------------------------------------------------------------------
# Add late of students
#----------------------------------------------------------------------
def Add_EscapeList(ADD_LIST):
    global idx
    
    path = 'The_number_of_escapes/' + str(idx)

    firebase.put(path, 'STDNUM', ADD_LIST[STDNUM])
    firebase.put(path, 'CNT', ADD_LIST[CNT])
    firebase.put(path, 'MAC', ADD_LIST[MAC])
    firebase.put(path, 'ESCAPES', 0)
    firebase.put(path, 'STATUS', True)
    firebase.put(path, 'IS_LATE', True)

    idx += 1

    print("late of student : " + ADD_LIST[STDNUM])


#----------------------------------------------------------------------
# Detecting people who ran away during class
#----------------------------------------------------------------------
def EscapeList(STDNUM_LIST):    # STDNUM_LIST : Order by (cnt, mac, stdNum)
    global idx

    for student in STDNUM_LIST:
        if(idx == 0):
            Add_EscapeList(student)
        else:
            for cnt in range(0, idx):
                path = 'The_number_of_escapes/' + str(cnt)
                temp = firebase.get(path, 'MAC')
                
                if(student[MAC] == temp):
                    firebase.put(path, 'STATUS', True)
                    break
                
                Add_EscapeList(student)



    for cnt in range(0, idx):
        path = 'The_number_of_escapes/' + str(cnt)
        temp = firebase.get(path, 'STATUS')

        if not(temp):  # Logic for Student who ran away
            tmp = firebase.get(path, 'ESCAPES')
            tmp += 1
            firebase.put(path, 'ESCAPES', tmp)

            cache.Log(firebase.get(path, 'STDNUM'))     # Write to the log file

    # status init
    for cnt in range(0, idx):
        path = 'The_number_of_escapes/' + str(cnt)
        firebase.put(path, 'STATUS', False)

    return idx