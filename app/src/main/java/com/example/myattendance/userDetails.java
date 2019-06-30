package com.example.myattendance;

public class userDetails {

    String Email,Password,Username,PhoneNo,Role,Course,JoinDate,BatchTime;

    public userDetails(){
    }

    public userDetails(String email, String password, String username, String phoneNo, String role, String course, String joinDate, String batchTime) {
        Email = email;
        Password = password;
        Username = username;
        PhoneNo = phoneNo;
        Role = role;
        Course = course;
        JoinDate = joinDate;
        BatchTime = batchTime;
    }
    public String getBatchTime() {
        return BatchTime;
    }
    public void setBatchTime(String batchTime) {
        BatchTime = batchTime;
    }
    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPhoneNo() {
        return PhoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        PhoneNo = phoneNo;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }

    public String getCourse() {
        return Course;
    }

    public void setCourse(String course) {
        Course = course;
    }

    public String getJoinDate() {
        return JoinDate;
    }

    public void setJoinDate(String joinDate) {
        JoinDate = joinDate;
    }
}
