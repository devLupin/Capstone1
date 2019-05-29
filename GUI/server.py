import socket
import RPi.GPIO as GPIO
import time

GPIO.setmode(GPIO.BCM)
GPIO.setup(18,GPIO.OUT)

def open1():
    GPIO.output(18,True)
def close1():
    GPIO.output(18,False)

HOST ='118.40.15.67'
PORT = 7000

s=socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.bind((HOST, PORT))
s.listen(1)
connection, addr=s.accept()
print('connected with '+addr[0])


try:
    while True:
        data=connection.recv(1024)
        if data.decode()== '1':
            open1()
            time.sleep(0.1)
        if data.decode()!='1':
            close1()
            time.sleep(0.1)

    
finally:
    GPIO.cleanup()
    connection.close()