package com.Project.OMRReader.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.Project.OMRReader.Models.RetrofitModels.AdminHomeResponse;
import com.Project.OMRReader.R;
import com.Project.OMRReader.RetrofitCalls.AdminHomeInfoInterface;
import com.Project.OMRReader.RetrofitCalls.Impl.AdminHomeInfoImpl;

public class HomeAdminFragment extends Fragment implements AdminHomeInfoInterface {

    TextView totalStudent,totalQuiz,topName,topBatch,topAccuracy,lastTopic,lastDate,lastType,lastInitiated,quizId;
    AdminHomeInfoImpl adminHomeInfo;;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_admin_fragment,container,false);

        init(view);
        adminHomeInfo = new AdminHomeInfoImpl(this);
        adminHomeInfo.get();

        return view;
    }

    private void setLastDate(AdminHomeResponse response){
        totalQuiz.setText(String.valueOf(response.getTotalQuiz()));
        totalStudent.setText(String.valueOf(response.getTotalStudent()));
        topName.setText(response.getGainer().getUser_info().getFirst_name()+" "+response.getGainer().getUser_info().getLast_name());
        topBatch.setText(response.getGainer().getBatch());
        topAccuracy.setText(response.getGainer().getAbout());
        lastTopic.setText(response.getLastQuiz().getTopic());
        if (response.getLastQuiz().isNegative())
            lastType.setText("Negative Multiple Correct");
        else
            lastType.setText("Multiple Correct");
        lastInitiated.setText(response.getLastQuiz().getInitiatedBy());
        lastDate.setText(response.getLastQuiz().getDate());
        quizId.setText("Last Quiz: "+response.getLastQuiz().getId());
    }

    private void init(View view){
        totalQuiz = view.findViewById(R.id.quiz);
        totalStudent = view.findViewById(R.id.student);
        topAccuracy = view.findViewById(R.id.topAccuracy);
        topBatch = view.findViewById(R.id.topBatch);
        topName = view.findViewById(R.id.topName);
        lastDate = view.findViewById(R.id.lastDate);
        lastInitiated = view.findViewById(R.id.lastInitiated);
        lastTopic = view.findViewById(R.id.lastTopic);
        lastType = view.findViewById(R.id.lastType);
        quizId = view.findViewById(R.id.quizid);
    }

    @Override
    public void response(AdminHomeResponse response) {
            setLastDate(response);
    }
}
