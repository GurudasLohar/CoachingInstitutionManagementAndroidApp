package com.example.myattendance;

public class enquiryDetails {

    private String name,phoneNo,role,course,enquiryDate,enquiryBy;

    public enquiryDetails(){
    }

    public enquiryDetails(String name, String phoneNo, String role, String course, String enquiryDate,String enquiryBy) {
        this.name = name;
        this.phoneNo = phoneNo;
        this.role = role;
        this.course = course;
        this.enquiryDate = enquiryDate;
        this.enquiryBy = enquiryBy;
    }

    public String getEnquiryBy() {
        return enquiryBy;
    }

    public void setEnquiryBy(String enquiryBy) {
        this.enquiryBy = enquiryBy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getEnquiryDate() {
        return enquiryDate;
    }

    public void setEnquiryDate(String enquiryDate) {
        this.enquiryDate = enquiryDate;
    }
}
