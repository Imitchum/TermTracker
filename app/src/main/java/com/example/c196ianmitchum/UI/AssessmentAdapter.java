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
import com.example.c196ianmitchum.entities.Assessments;

import java.util.List;

public class AssessmentAdapter extends RecyclerView.Adapter<AssessmentAdapter.AssessmentViewHolder> {
    private List<Assessments> mAssessments;
    private final Context context;
    private final LayoutInflater mInflater;

    public AssessmentAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }
    public class AssessmentViewHolder extends RecyclerView.ViewHolder {
        private final TextView assessmentItemView;

        private final TextView assessmentItemView2;


        public AssessmentViewHolder(@NonNull View itemView) {
            super(itemView);
            assessmentItemView = itemView.findViewById(R.id.textView7);
            assessmentItemView2 = itemView.findViewById(R.id.textView8);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position= getAdapterPosition();
                    final Assessments current = mAssessments.get(position);
                    Intent intent = new Intent(context, AssessmentDetails.class);
                    intent.putExtra("id",current.getAssessmentID());
                    intent.putExtra("title",current.getAssessmentTitle());
                    intent.putExtra("courseID",current.getCourseID());
                    intent.putExtra("startdate",current.getStartDatea());
                    intent.putExtra("enddate",current.getEndDatea());
                    intent.putExtra("type",current.getType());
                    context.startActivity(intent);
                }
            });
        }
    }
    @NonNull
    @Override
    public AssessmentAdapter.AssessmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= mInflater.inflate(R.layout.assessment_list_item,parent,false);
        return new AssessmentViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull AssessmentAdapter.AssessmentViewHolder holder, int position) {
        if(mAssessments != null) {
            Assessments current = mAssessments.get(position);
            String name = current.getAssessmentTitle();
            holder.assessmentItemView.setText(name);
            String type = current.getType();
            holder.assessmentItemView2.setText(type);

        }
        else{
            holder.assessmentItemView.setText("No assessment title");
            holder.assessmentItemView2.setText("No assessment type");
        }

    }

    @Override
    public int getItemCount() {
        if(mAssessments != null) {
            return mAssessments.size();
        } else return 0;
    }

    public void setAssessments(List<Assessments> assessments) {
        mAssessments = assessments;
        notifyDataSetChanged();
    }


}
