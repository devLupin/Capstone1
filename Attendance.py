from firebase import firebase
firebase = firebase.FirebaseApplication("https://capstone-776cd.firebaseio.com/", None)


def Update_LateOfStudents(idx):
    for cnt in range(0, idx):
        path = 'The_number_of_escapes/' + str(cnt)

        if(firebase.get(path, 'IS_LATE') == True):
            t = firebase.get(path, 'CNT')
            delivery_path = 'user_info/' + str(t)
            temp = firebase.get(delivery_path, 'be_late_for_class')
            temp = temp + 1

            firebase.put(delivery_path, 'be_late_for_class', temp)

            firebase.put(path, 'IS_LATE', False)





def Update_NumberOfAbsences(idx):
    for cnt in range(0, idx):
        path = 'The_number_of_escapes/' + str(cnt)

        if(firebase.get(path, 'escapes') > 5):
            temp = firebase.get('user_info' + str(cnt), 'number_of_absences')
            temp = temp + 1

            firebase.put('user_info/' + str(cnt), firebase.get('user_info/' + str(cnt), 'number_of_absences') + 1)