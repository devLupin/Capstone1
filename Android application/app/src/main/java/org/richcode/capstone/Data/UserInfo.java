package org.richcode.capstone.Data;

import java.io.Serializable;

public class UserInfo implements Serializable {

    int id;
    int number_of_absences;
    int be_late_for_class;
    int today_seatnum;
    String user_id;
    String user_name;
    String user_position;
    String user_pw;
    String user_mac;

    public UserInfo(){

    }

    public UserInfo(int id,  String user_id, String user_name, String user_position, String user_pw, String user_mac,int number_of_absences, int be_late_for_class, int today_seatnum) {
        this.id = id;
        this.number_of_absences = number_of_absences;
        this.be_late_for_class = be_late_for_class;
        this.today_seatnum = today_seatnum;
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_position = user_position;
        this.user_pw = user_pw;
        this.user_mac = user_mac;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber_of_absences() {
        return number_of_absences;
    }

    public void setNumber_of_absences(int number_of_absences) { this.number_of_absences = number_of_absences;}

    public int getBe_late_for_class() {
        return be_late_for_class;
    }

    public void setBe_late_for_class(int be_late_for_class) {this.be_late_for_class = be_late_for_class; }

    public int getToday_seatnum() {
        return today_seatnum;
    }

    public void setToday_seatnum(int today_seatnum) { this.today_seatnum = today_seatnum;}

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_position() {
        return user_position;
    }

    public void setUser_position(String user_position) {
        this.user_position = user_position;
    }

    public String getUser_pw() {
        return user_pw;
    }

    public void setUser_pw(String user_pw) {
        this.user_pw = user_pw;
    }

    public String getUser_mac() {
        return user_mac;
    }

    public void setUser_mac(String user_mac) {
        this.user_mac = user_mac;
    }
}
