import RPi.GPIO as GPIO
import time
from tkinter import*
from tkinter import messagebox

#----------------------------------------------------------------------
# Firebase Connect Part
#----------------------------------------------------------------------
from firebase import firebase
firebase = firebase.FirebaseApplication("https://capstone-776cd.firebaseio.com/", None)

class Gui():
    control = False
    root = Tk()

    def __init__(self):
        pass

    def Start(self):
        self.root = Tk()

        self.root.title("Attendance & Alarm for only professor")
        self.root.geometry("400x400+300+300")

        professor=Label(self.root, width=10, padx=20, text="professor number    :  ")
        professor.grid(column=1, row=20, pady=20)
        professor_txt=Entry(self.root, width=10)
        professor_txt.grid(column=2, row=20, pady=10)

        password=Label(self.root,width=10,text="password  : ")
        password.grid(column=1,row=40,pady=20)
        pw_txt=Entry(self.root,width=10)
        pw_txt.grid(column=2,row=40,pady=10)

        enter_btn = Button(self.root, text="Enter", width=10, command=self.Option)
        enter_btn.grid(column=2,row=60, pady=30)




    def Option(self):
        self.root = Tk()
        self.root.title("Option")
        self.root.geometry("300x300+300+300")
        attendance_button = Button(self.root, text="Attendance", width=10, command=self.Attendance)
        attendance_button.pack(padx=10,pady=60)
        notice_button = Button(self.root, text="Notice", width=10, command=self.Notice)
        notice_button.pack(padx=10,pady=30)




    def Attendance_Start(self):
        messagebox.showinfo("Attendance", "start")
        self.control = True 

    def Attendance_Stop(self):
        messagebox.showinfo("Attendance", "Stop")
        self.control = False

    def Attendance(self):
        self.root.title("Attendance")
        self.root.geometry("300x300+300+300")

        start_button = Button(self.root, text="Start", width=10, command=self.Attendance_Start())
        start_button.pack(padx=10,pady=60)
        stop_button = Button(self.root, text="Stop", width=10, command=self.Attendance_Stop())
        stop_button.pack(padx=10,pady=30)





    def Notice(self):
        self.root.title("Notice")
        self.root.geometry("300x300+300+300")

        content=Label(self.root,width=10,text="content  : ")
        content.grid(column=1,row=30,pady=20)
        content_txt=Entry(self.root,width=10)
        content_txt.grid(column=2,row=30,pady=10)

        noticeCnt = firebase.get('count_class', 'Notice')
        noticeCnt += 1
        firebase.put('notice_info', str(noticeCnt), content)

        send_btn = Button(self.root, text="Send", width=10)
        send_btn.grid(column=2,row=60, pady=30)



gui = Gui()
gui.root.mainloop()
gui.Start()