"""
### GUI.py
### author : Hyuntaek Lim
### Created : 2019.05.29
### 
"""


from tkinter import*
from tkinter import messagebox

#----------------------------------------------------------------------
# Firebase Connect Part
#----------------------------------------------------------------------
#from firebase import firebase
#firebase = firebase.FirebaseApplication("https://capstone-776cd.firebaseio.com/", None)


control = False


def GetControl():
    global control
    return control




def Start():
    root = Tk()

    root.title("Attendance & Alarm for only professor")
    root.geometry("400x400+300+300")

    professor=Label(root, width=15, padx=25, text="professor number    :  ")
    professor.grid(column=1, row=20, pady=20)
    professor_txt=Entry(root, width=15)
    professor_txt.grid(column=2, row=20, pady=10)

    password=Label(root,width=20,text="password  : ")
    password.grid(column=1,row=40,pady=20)
    pw_txt=Entry(root,width=15)
    pw_txt.grid(column=2,row=40,pady=10)

    enter_btn = Button(root, text="Enter", width=20, command=Option)
    enter_btn.grid(column=2,row=60, pady=30)





def Option():
    root = Tk()

    root.title("Option")
    root.geometry("400x400+300+300")

    attendance_button = Button(root, text="Attendance", width=10, command=Attendance)
    attendance_button.pack(padx=10,pady=60)

    notice_button = Button(root, text="Notice", width=10, command=Notice)
    notice_button.pack(padx=10,pady=30)




def Attendance_Start(num):
    global control

    messagebox.showinfo("Attendance", "start")
    control = True 

    path = 'seat_info\class' + str(num)

    #firebase.put(path, start, True)

def Attendance_Stop(num):
    global control

    messagebox.showinfo("Attendance", "Stop")
    control = False

    path = 'seat_info\class' + str(num)

    #firebase.put(path, start, False)

def StartAndStop(num):
    root = Tk()

    title = 'Class' + str(num) + ' Start and Stop'

    root.title(title)
    root.geometry("400x400+300+300")

    start_button = Button(root, text="Start", width=10, command= lambda: Attendance_Start(num))
    start_button.pack(padx=10,pady=60)

    stop_button = Button(root, text="Stop", width=10, command= lambda: Attendance_Stop(num))
    stop_button.pack(padx=10,pady=30)


def Attendance():
    root = Tk()

    root.title("Attendance")
    root.geometry("400x400+300+300")

    class1 = Button(root, text="class 1", width=10, command= lambda: StartAndStop(1))
    class1.pack(padx=10,pady=10)

    class2 = Button(root, text="class 2", width=10, command= lambda: StartAndStop(2))
    class2.pack(padx=10,pady=10)

    class3 = Button(root, text="class 3", width=10, command= lambda: StartAndStop(3))    
    class3.pack(padx=10,pady=10)

    class4 = Button(root, text="class 4", width=10, command= lambda: StartAndStop(4))
    class4.pack(padx=10,pady=10)



    

def ShowOK():
    messagebox.showinfo("Send the message", "OK")

def Notice():
    root = Tk()

    root.title("Notice")
    root.geometry("400x400+300+300")

    content=Label(root,width=10,text="content  : ")
    content.grid(column=1,row=30,pady=20)
    content_txt=Text(root,width=40, height=25)
    content_txt.grid(column=2,row=30,pady=10)

    #noticeCnt = (int)(firebase.get('count_class', 'notice_id'))
    #firebase.put('notice_info', str(noticeCnt), content)

    send_btn = Button(root, text="Send", width=10, command=ShowOK)
    send_btn.grid(column=2,row=57, pady=30)

    #firebase.put('count_class', 'notice_id', str(noticeCnt+1))



Start()
Tk().mainloop()