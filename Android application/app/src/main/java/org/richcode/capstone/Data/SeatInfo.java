package org.richcode.capstone.Data;

import java.io.Serializable;

public class SeatInfo implements Serializable {

    int id;
    String user_id;
    boolean status;
    String name;
    int today_seatnum;

    boolean start;

    public SeatInfo(){

    }

    public SeatInfo(int id, String user_id, boolean status, String name, int today_seatnum, boolean start) {
        this.id = id;
        this.user_id = user_id;
        this.status = status;
        this.name = name;
        this.today_seatnum = today_seatnum;
        this.start = start;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isStart() {
        return start;
    }

    public void setStart(boolean start) {
        this.start = start;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getToday_seatnum() {
        return today_seatnum;
    }

    public void setToday_seatnum(int today_seatnum) {
        this.today_seatnum = today_seatnum;
    }
}
