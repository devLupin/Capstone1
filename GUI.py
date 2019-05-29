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




def Attendance_Start():
    global control
    messagebox.showinfo("Attendance", "start")
    control = True 

def Attendance_Stop():
    global control
    messagebox.showinfo("Attendance", "Stop")
    control = False

def Attendance():
    root = Tk()

    root.title("Attendance")
    root.geometry("400x400+300+300")

    start_button = Button(root, text="Start", width=10, command=Attendance_Start)
    start_button.pack(padx=10,pady=60)

    stop_button = Button(root, text="Stop", width=10, command=Attendance_Stop)
    stop_button.pack(padx=10,pady=30)


    

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