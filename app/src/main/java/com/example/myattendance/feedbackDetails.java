package com.example.myattendance;

public class feedbackDetails {

    String userEmail,courseName,feedbackDate,feedbackTime,Rating,facultyEmail;

    public feedbackDetails(){}

    public feedbackDetails(String userEmail, String courseName, String feedbackDate, String feedbackTime, String rating, String facultyEmail) {
        this.userEmail = userEmail;
        this.courseName = courseName;
        this.feedbackDate = feedbackDate;
        this.feedbackTime = feedbackTime;
        Rating = rating;
        this.facultyEmail = facultyEmail;
    }

    public String getFacultyEmail() {
        return facultyEmail;
    }

    public void setFacultyEmail(String facultyEmail) {
        this.facultyEmail = facultyEmail;
    }

    public String getFeedbackTime() {
        return feedbackTime;
    }

    public void setFeedbackTime(String feedbackTime) {
        this.feedbackTime = feedbackTime;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getFeedbackDate() {
        return feedbackDate;
    }

    public void setFeedbackDate(String feedbackDate) {
        this.feedbackDate = feedbackDate;
    }

    public String getRating() {
        return Rating;
    }

    public void setRating(String rating) {
        Rating = rating;
    }
}
