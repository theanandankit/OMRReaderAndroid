package com.Project.OMRReader.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.Project.OMRReader.Adapters.QuizAdminListAdapter;
import com.Project.OMRReader.Models.RetrofitModels.QuizResponse;
import com.Project.OMRReader.R;
import com.Project.OMRReader.RetrofitCalls.Impl.QuizAllImpl;
import com.Project.OMRReader.RetrofitCalls.QuizAllInterface;

import java.util.ArrayList;

public class QuizsAdminFragment extends Fragment implements QuizAllInterface {

    RecyclerView recyclerView;
    QuizAdminListAdapter quizAdminListAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.quiz_admin_fragment,container,false);
        init(view);
        setRecyclerView(view);
        QuizAllImpl quizAll = new QuizAllImpl(this);
        quizAll.get();
        return view;
    }
    private void init(View view){
        recyclerView = view.findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
    private void setRecyclerView(View view){
        quizAdminListAdapter = new QuizAdminListAdapter(getContext());
        recyclerView.setAdapter(quizAdminListAdapter);
    }

    @Override
    public void response(ArrayList<QuizResponse> response) {

    }
}
