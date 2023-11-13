package com.example.c196ianmitchum.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.c196ianmitchum.R;
import com.example.c196ianmitchum.database.Repository;
import com.example.c196ianmitchum.entities.Assessments;
import com.example.c196ianmitchum.entities.Courses;
import com.example.c196ianmitchum.entities.Terms;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class TermList extends AppCompatActivity {
    private Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_list);
        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TermList.this, TermDetails.class);
                startActivity(intent);
            }
        });
        RecyclerView recyclerView=findViewById(R.id.recyclerview);
        repository=new Repository(getApplication());
        List<Terms> allTerms=repository.getmAllTerms();
        final TermAdapter termAdapter = new TermAdapter(this);
        recyclerView.setAdapter(termAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        termAdapter.setTerms(allTerms);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_term_list,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.sample) {
            repository=new Repository(getApplication());
         Terms terms = new Terms(1,"Term 1","11/9/23","12/31/23");
         repository.insert(terms);
         terms = new Terms(2,"Term 2","1/1/24","7/1/24");
         repository.insert(terms);
         Courses courses = new Courses(1,"Course 1","M.Jones","555-555-555","mjones@wgu.edu","11/10/23","12/31/23",1,"Completed", "test");
         repository.insert(courses);
         courses = new Courses(2,"Course 2","D.Terry","333-555-111","dterry@wgu.edu","11/10/23","12/31/23",1,"Completed", "test");
         repository.insert(courses);
         Assessments assessments = new Assessments(0,"Course 1 Assessment",1);
         repository.insert(assessments);
         assessments = new Assessments(0,"Course 2 Assessment",1);
         repository.insert(assessments);
            return true;
        }
        if(item.getItemId()==android.R.id.home) {
            this.finish();
            return true;
        }

      return true;
   }
}