package com.example.c196ianmitchum.dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.c196ianmitchum.entities.Courses;

import java.util.List;

@Dao
public interface CourseDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Courses courses);

    @Update
    void update(Courses courses);

    @Delete
    void delete(Courses courses);

    @Query("SELECT * FROM COURSES ORDER BY courseID ASC")
    List<Courses> getAllCourses();

    @Query("SELECT * FROM COURSES WHERE termID=:term ORDER BY courseID ASC")
    List<Courses> getAssociatedCourses(int term);


}
