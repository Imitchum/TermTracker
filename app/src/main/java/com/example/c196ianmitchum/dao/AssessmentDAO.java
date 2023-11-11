package com.example.c196ianmitchum.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.c196ianmitchum.entities.Assessments;
import com.example.c196ianmitchum.entities.Courses;

import java.util.List;

@Dao
public interface AssessmentDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Assessments assessments);

    @Update
    void update(Assessments assessments);

    @Delete
    void delete(Assessments assessments);

    @Query("SELECT * FROM ASSESSMENTS ORDER BY assessmentID ASC")
    List<Assessments> getAllAssessments();
}
