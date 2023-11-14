package com.example.c196ianmitchum.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "assessments")
public class Assessments {
    @PrimaryKey(autoGenerate = true)
    private int assessmentID;
    private String assessmentTitle;
    private int courseID;
    private String startDatea;
    private String endDatea;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }



    public String getStartDatea() {
        return startDatea;
    }

    public void setStartDatea(String startDatea) {
        this.startDatea = startDatea;
    }

    public String getEndDatea() {
        return endDatea;
    }

    public void setEndDatea(String endDatea) {
        this.endDatea = endDatea;
    }

    public Assessments(int assessmentID, String assessmentTitle, int courseID, String startDatea, String endDatea, String type) {
        this.assessmentID = assessmentID;
        this.assessmentTitle = assessmentTitle;
        this.courseID = courseID;
        this.startDatea = startDatea;
        this.endDatea = endDatea;
        this.type = type;
    }

    public int getAssessmentID() {
        return assessmentID;
    }

    public void setAssessmentID(int assessmentID) {
        this.assessmentID = assessmentID;
    }

    public String getAssessmentTitle() {
        return assessmentTitle;
    }

    public void setAssessmentTitle(String assessmentTitle) {
        this.assessmentTitle = assessmentTitle;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

}
