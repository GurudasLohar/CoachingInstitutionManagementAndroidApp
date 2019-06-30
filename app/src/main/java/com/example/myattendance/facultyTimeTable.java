package com.example.myattendance;

public class facultyTimeTable {

    String facultyEmail;
    String facultyAllottedCourse;
    String courseTiming;

    public facultyTimeTable(){}

    public facultyTimeTable(String facultyEmail, String facultyAllottedCourse, String courseTiming) {
        this.facultyEmail = facultyEmail;
        this.facultyAllottedCourse = facultyAllottedCourse;
        this.courseTiming = courseTiming;
    }

    public String getFacultyName() {
        return facultyEmail;
    }

    public void setFacultyName(String facultyName) {
        this.facultyEmail = facultyName;
    }

    public String getFacultyAllottedCourse() {
        return facultyAllottedCourse;
    }

    public void setFacultyAllottedCourse(String facultyAllottedCourse) {
        this.facultyAllottedCourse = facultyAllottedCourse;
    }

    public String getCourseTiming() {
        return courseTiming;
    }

    public void setCourseTiming(String courseTiming) {
        this.courseTiming = courseTiming;
    }
}
