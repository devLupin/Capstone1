import socket
import RPi.GPIO as GPIO
import time
from tkinter import*


HOST='118.40.15.67'
PORT=7000
s=socket.socket(socket.AF_INET,socket.SOCK_STREAM)
print('Connecting to ' + HOST)
s.connect((HOST,PORT))

f = open("/home/pi/12345.txt",'r')
line = f.read()
f.close()

root = Tk()

def realend():
    s.send('0'.encode())

def start():
    s.send('1'.encode())
    root=Tk()
    root.title("start")
    root.geometry("400x400+300+300")

    attendance=Label(root,width=10,padx=30,text="attendance")
    attendance.grid(column=1,row=10,pady=10)
    text=Text(root,width=20,height=20)
    text.grid(column=1,row=20,padx=10,pady=10)
    text.insert(INSERT,line)

    absent=Label(root,width=10,padx=30,text="absent")
    absent.grid(column=3,row=10,pady=10)
    txt1=Text(root,width=20,height=20)
    txt1.grid(column=3,row=20,padx=0,pady=10)

    button = Button(root, text="finish", width=5,command=end)
    button.grid(column=2,row=30, pady=10)
    
    
def end():
    root=Tk()
    root.title("notice")
    root.geometry("500x400+300+300")
    
    attendance=Label(root,width=10,padx=30,text="attendance")
    attendance.grid(column=1,row=10,pady=10)
    text=Text(root,width=20,height=20)
    text.grid(column=1,row=20,padx=10,pady=10)
    text.insert(INSERT,line)

    absent=Label(root,width=10,padx=30,text="absent")
    absent.grid(column=2,row=10,pady=10)
    txt1=Text(root,width=20,height=20)
    txt1.grid(column=2,row=20,padx=10,pady=10)
    
    late=Label(root,width=10,padx=30,text="late")
    late.grid(column=3,row=10,pady=10)
    txt1=Text(root,width=20,height=20)
    txt1.grid(column=3,row=20,padx=10,pady=10)
    
    button = Button(root, text="end", width=5,command=welcome)
    button.grid(column=2,row=30, pady=10)


def notice():
    root=Tk()
    root.title("notice")
    root.geometry("400x300+300+300")

    name=Label(root,width=10,padx=0,text="TITLE  : ")
    name.grid(column=1,row=20,pady=20)
    txt=Entry(root,width=20)
    txt.grid(column=2,row=20,pady=10)
    
    Contents=Label(root,width=10,padx=30,text="Contents   :")
    Contents.grid(column=1,row=30,pady=10)
    text=Text(root,width=25,height=10)
    text.grid(column=2,row=30,padx=10,pady=10)
    
    button1 = Button(root, text="send", width=10)
    button1.grid(column=1,row=60, pady=30)
    button1 = Button(root, text="back", width=10, command=welcome)
    button1.grid(column=2,row=60, pady=30)
    
    
def main():
    
    root=Tk()
    root.title("1")
    root.geometry("300x300+300+300")
    button = Button(root, text="sign up", width=10, command=signup)
    button.pack(padx=10,pady=60)
    button = Button(root, text="login", width=10, command=login)
    button.pack(padx=10,pady=30)
    

def signup():
   
    root=Tk()
    root.title("sign up")
    root.geometry("300x300+300+300")
    
    name=Label(root,width=10,padx=20,text="name  : ")
    name.grid(column=1,row=20,pady=20)
    txt=Entry(root,width=10)
    txt.grid(column=2,row=20,pady=10)
    
    number=Label(root,width=10,text="number  : ")
    number.grid(column=1,row=30,pady=20)
    txt1=Entry(root,width=10)
    txt1.grid(column=2,row=30,pady=10)
    
    passward=Label(root,width=10,text="passward  : ")
    passward.grid(column=1,row=40,pady=20)
    txt2=Entry(root,width=10)
    txt2.grid(column=2,row=40,pady=10)
    
    button1 = Button(root, text="sumit", width=10,)
    button1.grid(column=2,row=60, pady=30)

def login():
    
    root=Tk()
    root.title("login")
    root.geometry("300x300+300+300")
    
    name=Label(root,width=10,padx=20,text="name  : ")
    name.grid(column=1,row=20,pady=20)
    txt3=Entry(root,width=10)
    txt3.grid(column=2,row=20,pady=10)
    
    number=Label(root,width=10,text="passward  : ")
    number.grid(column=1,row=30,pady=20)
    txt3=Entry(root,width=10)
    txt3.grid(column=2,row=30,pady=10)
    
    button1 = Button(root, text="login", width=10, command=welcome)
    button1.grid(column=2,row=60, pady=30)
    
def welcome():

    root=Tk()
    root.title("welcome")
    root.geometry("300x300+300+300")
    b = Button(root, text="start", width=10,command=start)
    b.pack(pady=30)
    b2 = Button(root, text="end", width=10,command=realend)
    b2.pack(pady=30)
    b3 = Button(root, text="notice", width=10,command=notice)
    b3.pack(pady=30)

main()

root.mainloop()


try:

    while True:
        try:
            s.send('1'.encode())
        except:
            GPIO.cleanup()
    
finally:
    GPIO.clenup()
    s.close()




