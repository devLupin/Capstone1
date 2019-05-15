# Capstone Design
<hr>

![Python](https://img.shields.io/badge/Python-3.7-brightgreen.svg)
![OpenWrt](https://img.shields.io/badge/OpenWrt-blue.svg)
![Raspbian](https://img.shields.io/badge/Raspbian-4.14-yellow.svg)


✨ **Attendance & Smart Alarm**



**Author** : devLupin, luckyquit49@gmail.com

Supervisor : Wooyeol Choi

Starting Project : 2019.03.07



# Project summary
<hr>

## Attendance

- **Purpose**
The method of present attendance is name calling, QR code, certification number system, and so on. First, the name calling method is very inefficient in the classrooms accommodating a large number of students because it is required to call every one of them. Secondly, the QR code method can save time, but if you take a QR code, it will be in attendance so you can attend it if you have a copy. The third authentication number scheme has the same disadvantage as the QR code.

- **Content**
The primary authentication method used by our team is to make Raspberry PI a single router using OpenWrt, a Linux-based open source. Connect multiple Mobile to Raspberry PI (RPI). The student cell phone must always be directly connected to the server RPI. Then, we use the MAC address of the connected mobile to verify the individual. 

The second secondary authentication method uses a fingerprint sensor at the door to manage access records separately. This is to control the time students leave the classroom in the middle of the class. Students register their fingerprints when they enter the classroom. And fingerprints are registered even when going out. If the outing time exceeds a certain time, record it as a break-out person.

Students use Application (hereinafter referred to as App) for functional convenience. In the App, you can access the membership function, login, your attendance information, and announcements.

From the point of view of the user, the RPI operation process is as follows.


## Notifier

- **Purpose**
The school delivers all announcements using paid MMS. At the level of astronomical costs when one year's message is calculated. So the team devised a way to solve these problems.


- **Content**
If you have installed the application developed by our team, you will receive a message from the professor in the form of a bulletin board. At this time, RPI becomes a server, and each student's mobile becomes a client. The specific function process is as follows.

The process from the point of view of the user is as follows.
When the professor delivers the message, there is a bulletin board which can receive the announcement in one of the App bulletin boards installed in the Student Device. You can get a message there. At this time, any data network or Wifi must be connected.


The process from the system point of view is as follows.
The professor delivers the message. - The message is forwarded to the server. - Each student's app reads its value from the server.



# Install package list
<hr>

- **Luci web**
    ```
    root@OpenWrt:~# opkg update
    root@OpenWrt:~# opkg install luci
    ```

- **Wireless configuration**
    ```
    root@OpenWrt:~# uci set wireless.@wifi-device[0].disabled=0; uci commit wireless; wifi
    root@OpenWrt:~# uci show wireless | grep disabled
    ```

- **Configure WPA2 (PSK)**
    ```
    root@OpenWrt:~# uci set wireless.@wifi-iface[0].encryption=psk2
    root@OpenWrt:~# uci set wireless.@wifi-iface[0].key="your_password"
    root@OpenWrt:~# uci commit wireless
    root@OpenWrt:~# wifi  
    ```


#  Requirements(Only linux OS)
<hr>

- **[OpenWrt](https://openwrt.org/toh/views/toh_fwdownload?dataflt%5B0%5D=supported%20current%20rel_%3D18.06.2)**
    ```
    Find the correct version.
    Unzip the file and upload it to Microsd card
    ```

- **[Python](https://www.python.org/)**
    ```
    root@OpenWrt:~# opkg update
    root@OpenWrt:~# opkg install python-light
    ```

- **python-pip**
    ```
    root@OpenWrt:~# opkg install python-pip
    root@OpenWrt:~# pip install --upgrade pip
    ```

- **[The Python Standard Library](https://docs.python.org/2/library/)**
    ```
    root@OpenWrt:~# pip install [lib_name]
    ```

- **[Firebase](https://firebase.google.com/)**
    ```
    root@OpenWrt:~# pip install python-firebase
    root@OpenWrt:~# firebase install
    ```


# Reference
<hr>

- **[Wireless configuration](https://oldwiki.archive.openwrt.org/doc/uci/wireless)**

- **[LuCI essentials](https://openwrt.org/docs/guide-user/luci/luci.essentials)**

- **[Configure WiFi encryption](https://openwrt.org/docs/guide-user/network/wifi/encryption)**

- **[Installing Python](https://oldwiki.archive.openwrt.org/doc/software/python)**



# Community
<hr>

- **[OpenWrt Forun](https://forum.openwrt.org/)**

- **[OpenWrt Project](https://openwrt.org/)**

- **[Stack Overflow](https://stackoverflow.com/)**


# License
<hr>

**Attendance & Smart Alarm** is under MIT License. See the [LICENSE](LICENSE) file for more info.
