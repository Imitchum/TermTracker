package com.example.c196ianmitchum.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.c196ianmitchum.R;
import com.example.c196ianmitchum.entities.Courses;
import com.example.c196ianmitchum.entities.Terms;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder>{
    private List<Courses> mCourses;
    private final Context context;
    private final LayoutInflater mInflater;

    class CourseViewHolder extends RecyclerView.ViewHolder {

        private final TextView courseItemView;
        private final TextView courseItemView2;

        private CourseViewHolder(View itemView) {
            super(itemView);
            courseItemView = itemView.findViewById(R.id.textView3);
            courseItemView2 = itemView.findViewById(R.id.textView4);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    final Courses current = mCourses.get(position);
                    Intent intent = new Intent(context, CourseDetails.class);
                    intent.putExtra("id", current.getCourseID());
                    intent.putExtra("name", current.getCourseName());
                    intent.putExtra("termID", current.getTermID());
                    intent.putExtra("instructorName",current.getInstructorName());
                    intent.putExtra("phone", current.getPhoneNumber());
                    intent.putExtra("email",current.getEmailAddress());
                    intent.putExtra("startDate",current.getStrtDate());
                    intent.putExtra("endDate",current.getEndDate());
                    context.startActivity(intent);
                }
            });
        }

    }

    public CourseAdapter(Context context) {
            mInflater = LayoutInflater.from(context);
            this.context = context;
        }

        @Override
        public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = mInflater.inflate(R.layout.course_list_item, parent, false);
            return new CourseViewHolder(itemView);
        }


        @Override
        public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        if(mCourses!=null) {
            Courses current = mCourses.get(position);
            String name=current.getCourseName();
            int termID= current.getTermID();
            holder.courseItemView.setText(name);
            holder.courseItemView2.setText(Integer.toString(termID));

        }
        else {
            holder.courseItemView.setText("No Course name");
            holder.courseItemView2.setText("No course ID");
        }
    }

    public void setCourses(List<Courses> courses) {
        mCourses=courses;
        notifyDataSetChanged();
    }
    public int getItemCount() {
        if(mCourses!= null) return mCourses.size();
        else return 0;
    }
}
