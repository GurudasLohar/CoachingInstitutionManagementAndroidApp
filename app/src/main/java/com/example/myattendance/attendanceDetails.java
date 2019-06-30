package com.example.myattendance;

import java.util.ArrayList;

public class attendanceDetails {

    String StudentName;
    String batchCourse,batchAttendDate,batchAttendTime,status;

    public attendanceDetails(){}

    public attendanceDetails(String studentName, String batchCourse, String batchAttendDate, String batchAttendTime, String status) {
        this.StudentName = studentName;
        this.batchCourse = batchCourse;
        this.batchAttendDate = batchAttendDate;
        this.batchAttendTime = batchAttendTime;
        this.status = status;
    }

    public String getStudentName() {
        return StudentName;
    }

    public void setStudentName(String studentName) {
        StudentName = studentName;
    }

    public String getBatchCourse() {
        return batchCourse;
    }

    public void setBatchCourse(String batchCourse) {
        this.batchCourse = batchCourse;
    }

    public String getBatchAttendDate() {
        return batchAttendDate;
    }

    public void setBatchAttendDate(String batchAttendDate) {
        this.batchAttendDate = batchAttendDate;
    }

    public String getBatchAttendTime() {
        return batchAttendTime;
    }

    public void setBatchAttendTime(String batchAttendTime) {
        this.batchAttendTime = batchAttendTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
