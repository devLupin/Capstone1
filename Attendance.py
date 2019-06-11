from firebase import firebase
firebase = firebase.FirebaseApplication("https://capstone-776cd.firebaseio.com/", None)


def Update_LateOfStudents(idx):
    for cnt in range(0, idx):
        path = 'The_number_of_escapes/' + str(cnt)
        
        while(firebase.get(path, 'ESCAPES') >= 5):
            t = firebase.get(path, 'CNT')
            delivery_path = 'user_info/' + str(t)
            temp = firebase.get(delivery_path, 'be_late_for_class')
            temp = temp + 1

            firebase.put(delivery_path, 'be_late_for_class', temp)

            firebase.put(path, 'ESCAPES', (firebase.get(path, 'ESCAPES')-5))

        if(firebase.get(path, 'IS_LATE') == True):
            t = firebase.get(path, 'CNT')
            delivery_path = 'user_info/' + str(t)

            temp = firebase.get(delivery_path, 'be_late_for_class')
            temp = temp + 1

            firebase.put(delivery_path, 'be_late_for_class', temp)







def Update_NumberOfAbsences(idx):
    for cnt in range(0, idx):
        path = 'user_info/' + str(cnt)

        while(firebase.get(path, 'be_late_for_class') >= 3):
            t = firebase.get(path, 'id')
            delivery_path = 'user_info/' + str(t)

            temp = firebase.get(delivery_path, 'number_of_absences')
            temp = temp + 1

            firebase.put(path, 'be_late_for_class', (firebase.get(path, 'be_late_for_class')-3))
            firebase.put(delivery_path, 'be_late_for_class', temp)