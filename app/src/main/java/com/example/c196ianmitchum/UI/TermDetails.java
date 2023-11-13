package com.example.c196ianmitchum.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.c196ianmitchum.R;
import com.example.c196ianmitchum.database.Repository;
import com.example.c196ianmitchum.entities.Courses;
import com.example.c196ianmitchum.entities.Terms;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TermDetails extends AppCompatActivity {
    String title;
    int termID;
    EditText editStart;
    EditText editEnd;
    EditText editTitle;
    Repository repository;
    Terms currentTerm;
    int numCourses;
    DatePickerDialog.OnDateSetListener startDate;
    DatePickerDialog.OnDateSetListener endDate;
    final Calendar myCalendarStart = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_details);
        FloatingActionButton fab = findViewById(R.id.floatingActionButton2);

        editTitle=findViewById(R.id.titletext);
        title = getIntent().getStringExtra("title");
        editTitle.setText(title);

        editStart=findViewById(R.id.startdatet);
        editEnd = findViewById(R.id.enddatet);

        termID =getIntent().getIntExtra("id",-1);

        editStart = findViewById(R.id.startdatet);
        String startstring = getIntent().getStringExtra("startdate");
        editStart.setText(startstring);

        editEnd = findViewById(R.id.enddatet);
        String endstring = getIntent().getStringExtra("enddate");
        editEnd.setText(endstring);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TermDetails.this, CourseDetails.class);
                intent.putExtra("termID",termID);
                startActivity(intent);
            }
        });


        RecyclerView recyclerView= findViewById(R.id.termrecylerview);
        repository = new Repository(getApplication());
        final CourseAdapter courseAdapter= new CourseAdapter(this);
        recyclerView.setAdapter(courseAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<Courses> filteredCourses = new ArrayList<>();
        for(Courses c: repository.getmAllCourses()) {
            if(c.getTermID()==termID) filteredCourses.add(c);
        }
        courseAdapter.setCourses(filteredCourses);


        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);


        editStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date date;
                String info = editStart.getText().toString();
                if (info.equals("")) info = "11/01/23";
                try {
                    myCalendarStart.setTime(sdf.parse(info));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                new DatePickerDialog(TermDetails.this, startDate, myCalendarStart
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

        editEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date date;
                String info = editStart.getText().toString();
                if (info.equals("")) info = "12/30/23";
                try {
                    myCalendarStart.setTime(sdf.parse(info));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                new DatePickerDialog(TermDetails.this, endDate, myCalendarStart
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
        editStart.setText(sdf.format(myCalendarStart.getTime()));
    }

    private void updateLabelEnd() {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        editEnd.setText(sdf.format(myCalendarStart.getTime()));
    }


    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_term_details,menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.saveterm) {
            Terms terms;
            if(termID==-1) {
                if(repository.getmAllTerms().size()==0) termID=1;
                else termID = repository.getmAllTerms().get(repository.getmAllTerms().size()-1).getTermID() +1;
                terms = new Terms(termID,editTitle.getText().toString(),editStart.getText().toString(),editEnd.getText().toString());
                repository.insert(terms);
                this.finish();
            }
            else{
                terms = new Terms(termID,editTitle.getText().toString(),editStart.getText().toString(),editEnd.getText().toString());
                repository.update(terms);
                this.finish();
            }
        }
        if(item.getItemId()==R.id.deleteterm){
            for(Terms term:repository.getmAllTerms()) {
                if (term.getTermID() == termID) currentTerm = term;
            }
                numCourses = 0;
                for(Courses courses: repository.getmAllCourses()) {
                    if(courses.getTermID()==termID)++numCourses;
                }
                if(numCourses==0){
                    repository.delete(currentTerm);
                    Toast.makeText(TermDetails.this,currentTerm.getTermTitle() +" was deleted.",Toast.LENGTH_LONG).show();
                    TermDetails.this.finish();
                }
                else {
                    Toast.makeText(TermDetails.this, "Can't delete a Term with courses.",Toast.LENGTH_LONG).show();
                }

            }

        return true;
    }
}