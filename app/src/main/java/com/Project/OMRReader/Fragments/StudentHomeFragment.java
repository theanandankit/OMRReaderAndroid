package com.Project.OMRReader.Fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.Project.OMRReader.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;

public class StudentHomeFragment extends Fragment {
    PieChart pieChart;
    int[] color = new int[]{Color.GREEN,Color.RED};



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_student_home, container, false);
        pieChart = view.findViewById(R.id.pieChart);
        setPieChart();


        return view;
    }
    private void setPieChart(){
        PieDataSet pieDataSet = new PieDataSet(pieData(),"");
        pieDataSet.setColors(color);

        PieData pieData = new PieData(pieDataSet);

        pieChart.setDrawEntryLabels(true);
        pieChart.setUsePercentValues(false);
        pieChart.setDrawEntryLabels(false);
        pieChart.getDescription().setEnabled(false);

        pieChart.setData(pieData);
        pieChart.invalidate();
    }

    private ArrayList<PieEntry> pieData() {
        ArrayList<PieEntry> list = new ArrayList<>();
        list.add(new PieEntry(60,"Correct"));
        list.add(new PieEntry(40,"wrong"));
        return list;
    }
}