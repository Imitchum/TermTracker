package com.example.c196ianmitchum.database;

import android.app.Application;

import com.example.c196ianmitchum.dao.AssessmentDAO;
import com.example.c196ianmitchum.dao.CourseDAO;
import com.example.c196ianmitchum.dao.TermDAO;
import com.example.c196ianmitchum.entities.Assessments;
import com.example.c196ianmitchum.entities.Courses;
import com.example.c196ianmitchum.entities.Terms;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Repository {
    private TermDAO mTermDao;
    private CourseDAO mCourseDao;
    private AssessmentDAO mAssessmentDAO;

    private List<Terms> mAllTerms;
    private List<Courses> mAllCourses;
    private List<Assessments> mAllAssessments;

    private static int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public Repository(Application application){
        TermDatabaseBuilder db=TermDatabaseBuilder.getDatabase(application);
        mTermDao=db.termDAO();
        mCourseDao=db.courseDAO();
        mAssessmentDAO=db.assessmentDAO();
    }

    public List<Terms> getmAllTerms(){
        databaseExecutor.execute(()->{
            mAllTerms=mTermDao.getAllTerms();
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return mAllTerms;
    }

    public void insert(Terms terms) {
        databaseExecutor.execute(()->{
            mTermDao.insert(terms);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Terms terms) {
        databaseExecutor.execute(()->{
            mTermDao.update(terms);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Terms terms) {
        databaseExecutor.execute(()->{
            mTermDao.delete(terms);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Courses> getmAllCourses(){
        databaseExecutor.execute(()->{
            mAllCourses=mCourseDao.getAllCourses();
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return mAllCourses;
    }

    public List<Courses> getAssociatedCourses(int termID){
        databaseExecutor.execute(()->{
            mAllCourses=mCourseDao.getAssociatedCourses(termID);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return mAllCourses;
    }

    public void insert(Courses courses) {
        databaseExecutor.execute(()->{
            mCourseDao.insert(courses);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Courses courses) {
        databaseExecutor.execute(()->{
            mCourseDao.update(courses);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Courses courses) {
        databaseExecutor.execute(()->{
            mCourseDao.delete(courses);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Assessments> getmAllAssessments(){
        databaseExecutor.execute(()->{
            mAllAssessments=mAssessmentDAO.getAllAssessments();
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return mAllAssessments;
    }

    public void insert(Assessments assessments) {
        databaseExecutor.execute(()->{
            mAssessmentDAO.insert(assessments);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Assessments assessments) {
        databaseExecutor.execute(()->{
            mAssessmentDAO.update(assessments);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Assessments assessments) {
        databaseExecutor.execute(()->{
            mAssessmentDAO.delete(assessments);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    }

