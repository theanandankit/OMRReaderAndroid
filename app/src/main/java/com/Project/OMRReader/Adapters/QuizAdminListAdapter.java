package com.Project.OMRReader.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Project.OMRReader.Models.RetrofitModels.QuizResponse;
import com.Project.OMRReader.R;

import java.util.ArrayList;

public class QuizAdminListAdapter extends RecyclerView.Adapter<QuizAdminListAdapter.quizholder> {

    private Context context;
    private ArrayList<QuizResponse> responses;

    public QuizAdminListAdapter(Context context,ArrayList<QuizResponse> responses){
        this.context=context;
        this.responses = responses;
    }

    @NonNull
    @Override
    public QuizAdminListAdapter.quizholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.quizadminitem, parent, false);
        return new quizholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuizAdminListAdapter.quizholder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class quizholder extends RecyclerView.ViewHolder {
        public quizholder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
