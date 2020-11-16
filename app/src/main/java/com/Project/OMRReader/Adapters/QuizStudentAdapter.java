package com.Project.OMRReader.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Project.OMRReader.R;

public class QuizStudentAdapter extends RecyclerView.Adapter<QuizStudentAdapter.quizStudentHolder> {

    Context context;

    public QuizStudentAdapter(Context context) {
        this.context = context;

    }

    @NonNull
    @Override
    public QuizStudentAdapter.quizStudentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.quizstudentitem, parent, false);
        return new quizStudentHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuizStudentAdapter.quizStudentHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class quizStudentHolder extends RecyclerView.ViewHolder {
        public quizStudentHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
