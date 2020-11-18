package com.Project.OMRReader.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.Project.OMRReader.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

public class QuizDetailsOverallFragment extends Fragment {

    BarChart barChart;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.quizdetailsoverallfragment,container,false);
        init(view);
        setBarChart();

        return view;
    }
    private void init(View view){
        barChart = view.findViewById(R.id.barChart);
    }

    private void setBarChart(){

        BarDataSet barDataSet = new BarDataSet(data(),"Marks Distribution");

        BarData barData = new BarData();
        barData.addDataSet(barDataSet);

        barChart.setData(barData);
        barChart.invalidate();
    }

    private ArrayList<BarEntry> data(){
        ArrayList<BarEntry> list = new ArrayList<>();
        list.add(new BarEntry(10,0));
        list.add(new BarEntry(20,0));
        list.add(new BarEntry(30,1));
        list.add(new BarEntry(40,2));
        list.add(new BarEntry(50,5));
        list.add(new BarEntry(60,6));
        list.add(new BarEntry(70,8));
        list.add(new BarEntry(80,10));
        list.add(new BarEntry(90,5));
        list.add(new BarEntry(100,0));

        return list;

    }
}
