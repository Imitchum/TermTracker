package com.example.c196ianmitchum.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.c196ianmitchum.R;
import com.example.c196ianmitchum.database.Repository;
import com.example.c196ianmitchum.entities.Courses;
import com.example.c196ianmitchum.entities.Terms;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.SimpleTimeZone;

public class CourseDetails extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Repository repository;
    int courseID;
    String courseName;
    String instructorName;
    String phoneNumber;
    String emailAddress;
    String status;
    String notes;
    int termID;
    EditText startDatec;
    EditText endDatec;

    EditText editNote;
    EditText editName;
    EditText iName;
    EditText phone;
    EditText editEmail;

    DatePickerDialog.OnDateSetListener startDate;
    DatePickerDialog.OnDateSetListener endDate;
    final Calendar myCalendarStart = Calendar.getInstance();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details);
        repository = new Repository(getApplication());

        courseID = getIntent().getIntExtra("id",-1);
        termID = getIntent().getIntExtra("termID",-1);

        editName = findViewById(R.id.coursetitlecd);
        courseName = getIntent().getStringExtra("name");
        editName.setText(courseName);

        iName = findViewById(R.id.instructorname);
        instructorName = getIntent().getStringExtra("instructorName");
        iName.setText(instructorName);

        phone = findViewById(R.id.instructorphone);
        phoneNumber = getIntent().getStringExtra("phone");
        phone.setText(phoneNumber);

        editEmail = findViewById(R.id.emailaddress);
        emailAddress = getIntent().getStringExtra("email");
        editEmail.setText(emailAddress);

        startDatec = findViewById(R.id.startdatecd);
        String startstring = getIntent().getStringExtra("startDate");
        startDatec.setText(startstring);

        endDatec = findViewById(R.id.enddatecd);
        String endstring = getIntent().getStringExtra("endDate");
        endDatec.setText(endstring);

        editNote = findViewById(R.id.notecd);
        String editnotes = getIntent().getStringExtra("notes");
        editNote.setText(editnotes);



        Spinner statusspinner = (Spinner)findViewById(R.id.spinner3);
        ArrayAdapter<CharSequence> statusadapter= ArrayAdapter.createFromResource(this,R.array.status_array, android.R.layout.simple_spinner_item);
        statusadapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        statusspinner.setAdapter(statusadapter);
        statusspinner.setOnItemSelectedListener(this);

        status = getIntent().getStringExtra("status");
        statusspinner.setSelection(statusadapter.getPosition(status));
























        editNote = findViewById(R.id.notecd);


        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);


        startDatec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date date;
                String info = startDatec.getText().toString();
                if (info.equals("")) info = "11/08/23";
                try {
                    myCalendarStart.setTime(sdf.parse(info));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                new DatePickerDialog(CourseDetails.this, startDate, myCalendarStart
                        .get(Calendar.YEAR), myCalendarStart.get(Calendar.MONTH),
                        myCalendarStart.get(Calendar.DAY_OF_MONTH)).show();

            }
        });

        startDate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendarStart.set(Calendar.YEAR, year);
                myCalendarStart.set(Calendar.MONTH, month);
                myCalendarStart.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabelStart();

            }

        };

        endDatec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date date;
                String info = endDatec.getText().toString();
                if (info.equals("")) info = "11/09/23";
                try {
                    myCalendarStart.setTime(sdf.parse(info));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                new DatePickerDialog(CourseDetails.this, endDate, myCalendarStart
                        .get(Calendar.YEAR), myCalendarStart.get(Calendar.MONTH),
                        myCalendarStart.get(Calendar.DAY_OF_MONTH)).show();

            }
        });

        endDate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendarStart.set(Calendar.YEAR, year);
                myCalendarStart.set(Calendar.MONTH, month);
                myCalendarStart.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabelEnd();

            }

        };

    }




    private void updateLabelStart() {
            String myFormat = "MM/dd/yy";
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
            startDatec.setText(sdf.format(myCalendarStart.getTime()));
        }

    private void updateLabelEnd() {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        endDatec.setText(sdf.format(myCalendarStart.getTime()));
    }



    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_course_details, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }


        if (item.getItemId() == R.id.savecourse) {
            Courses courses;
            if (courseID == -1) {
//                if (repository.getmAllCourses().size() == 0)
//                    courseID = 1;
//                else
//                    courseID = repository.getmAllCourses().get(repository.getmAllCourses().size() - 1).getCourseID() + 1;
                courses = new Courses(0, editName.getText().toString(), iName.getText().toString(), phone.getText().toString(), editEmail.getText().toString(),startDatec.getText().toString(),endDatec.getText().toString(), termID, status, editNote.getText().toString());
                repository.insert(courses);

            } else {
                courses = new Courses(courseID, editName.getText().toString(), iName.getText().toString(), phone.getText().toString(), editEmail.getText().toString(),startDatec.getText().toString(),endDatec.getText().toString(), termID,status, editNote.getText().toString());
                repository.update(courses);

            }
            this.finish();
            return true;
        }

        for(Courses courses: repository.getmAllCourses()) {
            if(courses.getCourseID() == courseID) {
                repository.delete(courses);
                Toast.makeText(CourseDetails.this, courses.getCourseName() + "Course was deleted.",Toast.LENGTH_LONG).show();}
        }



        if( item.getItemId()== R.id.sharenote) {
            Intent sentIntent= new Intent();
            sentIntent.setAction(Intent.ACTION_SEND);
            sentIntent.putExtra(Intent.EXTRA_TEXT, editNote.getText().toString()+ "EXTRA_TEXT");
            sentIntent.putExtra(Intent.EXTRA_TITLE, editNote.getText().toString()+ "EXTRA_TITLE");
            sentIntent.setType("text/plain");
            Intent shareIntent=Intent.createChooser(sentIntent,null);
            startActivity(shareIntent);
            return true;
        }
        if(item.getItemId()==R.id.notify) {
            String dateFromScreen = startDatec.getText().toString();
            String myFormat = "MM/dd/yy";
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
            Date myDate = null;
            try {
                myDate = sdf.parse(dateFromScreen);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Long trigger = myDate.getTime();
            Intent intent = new Intent(CourseDetails.this, MyReceiver.class);
            intent.putExtra("key","Course Notification: ");
            PendingIntent sender = PendingIntent.getBroadcast(CourseDetails.this,++MainActivity.numAlert,intent,PendingIntent.FLAG_IMMUTABLE);
            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC_WAKEUP, trigger, sender);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long l) {
        status = (String) parent.getItemAtPosition(pos);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}