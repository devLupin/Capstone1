#----------------------------------------------------------------------
# Firebase Connect Part
#----------------------------------------------------------------------
from firebase import firebase
firebase = firebase.FirebaseApplication("https://capstone-776cd.firebaseio.com/", None)


CNT = 0
MAC = 1
STDNUM = 2

returnvalue = firebase.get('The_number_of_escapes/')
print(returnvalue)
