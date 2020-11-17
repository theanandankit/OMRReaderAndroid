package com.Project.OMRReader.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.Project.OMRReader.Adapters.AddQuestionAdapter;
import com.Project.OMRReader.Models.AddQuestionModel;
import com.Project.OMRReader.R;
import com.Project.OMRReader.Utilities.AddQuestionDialog.AddQuestionDialogImpl;
import com.Project.OMRReader.Utilities.AddQuestionDialog.AddQuestionDialogInterface;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class AddQuestionScreen extends AppCompatActivity implements AddQuestionDialogInterface {

    MaterialButton button;
    AddQuestionDialogImpl addQuestionDialog;
    AddQuestionAdapter addQuestionAdapter;
    RecyclerView recyclerView;
    ArrayList<AddQuestionModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question_screen);
        init();
        setButton();

    }
    private void init(){
        button = findViewById(R.id.add);
        recyclerView = findViewById(R.id.recycleView);
        list = new ArrayList<>();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        addQuestionAdapter = new AddQuestionAdapter(getApplicationContext(),list);
        recyclerView.setAdapter(addQuestionAdapter);
        addQuestionDialog = new AddQuestionDialogImpl(AddQuestionScreen.this,this);
        addQuestionDialog.set();
    }
    private void setButton(){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addQuestionDialog.show(list.size()+1);
            }
        });
    }

    @Override
    public void Response(AddQuestionModel response) {
        list.add(response);
        addQuestionAdapter.notifyDataSetChanged();
    }
}