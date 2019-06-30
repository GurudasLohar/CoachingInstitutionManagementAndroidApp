package com.example.myattendance;

public class meetingDetails {

    String meetingAgenda,meetingDate,meetingTime;

    public meetingDetails(){}

    public meetingDetails(String meetingAgenda, String meetingDate, String meetingTime) {
        this.meetingAgenda = meetingAgenda;
        this.meetingDate = meetingDate;
        this.meetingTime = meetingTime;
    }

    public String getMeetingAgenda() {
        return meetingAgenda;
    }

    public void setMeetingAgenda(String meetingAgenda) {
        this.meetingAgenda = meetingAgenda;
    }

    public String getMeetingDate() {
        return meetingDate;
    }

    public void setMeetingDate(String meetingDate) {
        this.meetingDate = meetingDate;
    }

    public String getMeetingTime() {
        return meetingTime;
    }

    public void setMeetingTime(String meetingTime) {
        this.meetingTime = meetingTime;
    }
}
