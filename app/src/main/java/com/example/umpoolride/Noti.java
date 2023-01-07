package com.example.umpoolride;

public class Noti {
    private String NotiTitle;
    private String NotiDesc;

    public Noti(String NotiTitle, String NotiDesc) {
        this.NotiTitle = NotiTitle;
        this.NotiDesc = NotiDesc;
    }

    public String getNotiTitle() {
        return NotiTitle;
    }


    public String getNotiDesc() {
        return NotiDesc;
    }
}
