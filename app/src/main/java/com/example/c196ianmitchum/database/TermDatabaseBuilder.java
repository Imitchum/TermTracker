package com.example.c196ianmitchum.database;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.c196ianmitchum.dao.AssessmentDAO;
import com.example.c196ianmitchum.dao.CourseDAO;
import com.example.c196ianmitchum.dao.TermDAO;
import com.example.c196ianmitchum.entities.Assessments;
import com.example.c196ianmitchum.entities.Courses;
import com.example.c196ianmitchum.entities.Terms;

@Database(entities = {Terms.class, Courses.class, Assessments.class},version = 6,exportSchema = false)
public abstract class TermDatabaseBuilder extends RoomDatabase {
    public abstract TermDAO termDAO();
    public abstract CourseDAO courseDAO();
    public abstract AssessmentDAO assessmentDAO();
    private static volatile TermDatabaseBuilder INSTANCE;


    static TermDatabaseBuilder getDatabase(final Context context) {
        if(INSTANCE==null) {
            synchronized (TermDatabaseBuilder.class){
                if(INSTANCE==null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),TermDatabaseBuilder.class,"MyTermDatabase.db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
