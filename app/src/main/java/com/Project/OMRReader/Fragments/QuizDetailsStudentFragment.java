package com.Project.OMRReader.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.Project.OMRReader.Adapters.QuizStudentAdapter;
import com.Project.OMRReader.R;

public class QuizDetailsStudentFragment  extends Fragment {

    RecyclerView recyclerView;
    QuizStudentAdapter quizStudentAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.quizdetailsstudentfragment,container,false);
        init(view);
        setRecyclerView();
        return view;
    }
    private void init(View view){
        recyclerView = view.findViewById(R.id.recycleView);
    }
    private  void setRecyclerView(){
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        quizStudentAdapter = new QuizStudentAdapter(getContext());
        recyclerView.setAdapter(quizStudentAdapter);
    }
}
