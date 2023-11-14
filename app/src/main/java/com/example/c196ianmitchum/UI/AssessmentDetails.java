package com.example.c196ianmitchum.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.c196ianmitchum.R;
import com.example.c196ianmitchum.database.Repository;
import com.example.c196ianmitchum.entities.Assessments;
import com.example.c196ianmitchum.entities.Courses;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AssessmentDetails extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Repository repository;

    int courseID;
    int assessmentID;
    String title;
    String type;
    EditText editTitle;
    EditText editStart;
    EditText editEnd;


    DatePickerDialog.OnDateSetListener startDate;
    DatePickerDialog.OnDateSetListener endDate;
    final Calendar myCalendarStart = Calendar.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessments);

        repository = new Repository(getApplication());

        editTitle = findViewById(R.id.titletextA);
        title = getIntent().getStringExtra("title");
        editTitle.setText(title);

        editStart = findViewById(R.id.startdateA);
        editEnd = findViewById(R.id.enddateA);

        courseID = getIntent().getIntExtra("courseID", -1);
        assessmentID = getIntent().getIntExtra("id", -1);

        editStart = findViewById(R.id.startdateA);
        String start = getIntent().getStringExtra("startdate");
        editStart.setText(start);

        editEnd = findViewById(R.id.enddateA);
        String end = getIntent().getStringExtra("enddate");
        editEnd.setText(end);




        Spinner typespinner = (Spinner)findViewById(R.id.assessmentspinner);
        ArrayAdapter<CharSequence> typeadapter= ArrayAdapter.createFromResource(this,R.array.assessment_array, android.R.layout.simple_spinner_item);
        typeadapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        typespinner.setAdapter(typeadapter);
        typespinner.setOnItemSelectedListener(this);

        type = getIntent().getStringExtra("type");
        typespinner.setSelection(typeadapter.getPosition(type));


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
                new DatePickerDialog(AssessmentDetails.this, startDate, myCalendarStart
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
                new DatePickerDialog(AssessmentDetails.this, endDate, myCalendarStart
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

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_assessment_details, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }

        if (item.getItemId() == R.id.saveassessment) {
            Assessments assessments;
            if (assessmentID == -1) {
                if(repository.getmAllAssessments().size()==0) assessmentID=1;
                else assessmentID = repository.getmAllAssessments().get(repository.getmAllAssessments().size()-1).getAssessmentID() +1;

                assessments = new Assessments(assessmentID, editTitle.getText().toString(), courseID, editStart.getText().toString(), editEnd.getText().toString(), type);
                repository.insert(assessments);
            } else {
                assessments = new Assessments(assessmentID, editTitle.getText().toString(), courseID, editStart.getText().toString(), editEnd.getText().toString(), type);
                repository.update(assessments);
            }
            this.finish();
            return true;
        }


        if (item.getItemId() == R.id.deleteassessment) {
            for (Assessments assessments : repository.getmAllAssessments()) {
                if (assessments.getAssessmentID() == assessmentID) {
                    repository.delete(assessments);
                    Toast.makeText(AssessmentDetails.this, assessments.getAssessmentTitle() + " Assessment was deleted.", Toast.LENGTH_LONG).show();
                }
            }

        }


        return true;
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long l) {
        type = (String) parent.getItemAtPosition(pos);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}







