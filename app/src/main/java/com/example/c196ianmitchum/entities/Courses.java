package com.example.c196ianmitchum.entities;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "courses")
public class Courses {
    @PrimaryKey(autoGenerate = true)
    private int courseID;
    private String courseName;
    private String instructorName;
    private String phoneNumber;
    private String emailAddress;
    private String strtDate;
    private String endDate;
    private String status;
    private String notes;
    private int termID;

    public Courses(int courseID, String courseName, String instructorName, String phoneNumber, String emailAddress, String strtDate, String endDate, int termID,String status,String notes) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.instructorName = instructorName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.strtDate = strtDate;
        this.endDate = endDate;
        this.termID = termID;
        this.status = status;
        this.notes = notes;
    }



    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStrtDate() {
        return strtDate;
    }

    public void setStrtDate(String strtDate) {
        this.strtDate = strtDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }


    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public int getTermID() {
        return termID;
    }

    public void setTermID(int termID) {
        this.termID = termID;
    }
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }







}
