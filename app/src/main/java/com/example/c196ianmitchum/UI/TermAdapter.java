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
import com.example.c196ianmitchum.entities.Terms;

import java.util.List;

public class TermAdapter extends RecyclerView.Adapter<TermAdapter.TermViewHolder>{
    private List<Terms> mTerms;
    private Context context;
    public final LayoutInflater mInflater;

    public TermAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context=context;
    }
    public class TermViewHolder extends RecyclerView.ViewHolder {
        private final TextView termItemView;
        private final TextView termItemView2;
        private final TextView termItemView3;



        public TermViewHolder(@NonNull View itemView) {
            super(itemView);
            termItemView=itemView.findViewById(R.id.textView2);
            termItemView2=itemView.findViewById(R.id.textView5);
            termItemView3=itemView.findViewById(R.id.textView6);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    final Terms current = mTerms.get(position);
                    Intent intent = new Intent(context, TermDetails.class);
                    intent.putExtra("id",current.getTermID());
                    intent.putExtra("title",current.getTermTitle());
                    intent.putExtra("startdate",current.getStartDate());
                    intent.putExtra("enddate",current.getEndDate());
                    context.startActivity(intent);
                }
            });
        }
    }
    @NonNull
    @Override
    public TermAdapter.TermViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView=mInflater.inflate(R.layout.term_list_item,parent,false);
        return new TermViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TermAdapter.TermViewHolder holder, int position) {
        if(mTerms!=null){
            Terms current=mTerms.get(position);
            String title=current.getTermTitle();
            holder.termItemView.setText(title);
            String startDate= current.getStartDate();
            holder.termItemView2.setText(startDate);
            String endDate = current.getEndDate();
            holder.termItemView3.setText(endDate);

        }
        else{
            holder.termItemView.setText("No Term title");
            holder.termItemView2.setText("No Start Date");
            holder.termItemView3.setText("No End Date");
        }

    }

    @Override
    public int getItemCount() {
        if(mTerms!=null){
            return mTerms.size();
        }
        else return 0;
    }

    public void setTerms(List<Terms> terms) {
        mTerms = terms;
        notifyDataSetChanged();
    }


}
