package com.Project.OMRReader.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.ArrayMap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.Project.OMRReader.Adapters.QuizDetailsTabLayoutAdapter;
import com.Project.OMRReader.MainActivity;
import com.Project.OMRReader.Models.QuizEvaluationModelView;
import com.Project.OMRReader.Models.RetrofitModels.QuizResponse;
import com.Project.OMRReader.R;
import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class QuizDetailsScreen extends AppCompatActivity {

    ViewPager viewPager;
    TabLayout tabLayout;
    QuizDetailsTabLayoutAdapter adapter;
    FloatingActionButton fab;
    RelativeLayout relativelay;
    ArrayList<QuizEvaluationModelView> results;
   LinearLayout mainList;
    QuizResponse response;
    ImageView image;
    private String answers= "1000010001000010000100101000001001011000110000011000001010001000000110001000010010000100000110000010";
    PieChart pieChart;
    int[] color = new int[]{Color.GREEN,Color.RED};


    private int fileNumber;


    private static final int CAMERA_REQUESTCODE = 1;
    private static final String TAG="QuizDetailsSrceen";

    private static final int REQUEST_IMAGE_CAPTURE = 1;


    private String currentPhotoPath="";
    private PyObject pyobj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_details_screen);
//        init();
//        setAdapter();
        fab = findViewById(R.id.fab);
        relativelay = findViewById(R.id.relativelay);
        mainList= findViewById(R.id.quiz_evaluation_list);

        Intent dataIntent = getIntent();
        response=dataIntent.getParcelableExtra("data");
        results=new ArrayList<QuizEvaluationModelView>();
        response=dataIntent.getExtras().getParcelable("data");

        Log.v( "Response ", response.toString()+"HELLO ");
        Log.d(TAG, "onResponse: "+ response.getAnswer());
        Log.d(TAG, "onCreate: "+ answers.length());

        fileNumber=0;

        if (!Python.isStarted()) {
            Python.start(new AndroidPlatform(this));
        }

        Python py = Python.getInstance();
        pyobj = py.getModule("main");


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                if (ActivityCompat.checkSelfPermission(QuizDetailsScreen.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(QuizDetailsScreen.this, Manifest.permission.CAMERA)) {
                        Snackbar.make(relativelay, "This app need camera permission", Snackbar.LENGTH_INDEFINITE)
                                .setAction("Grant permission", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                        intent.setData(Uri.parse("package: " + getPackageName()));
                                        startActivity(intent);
                                    }
                                }).show();
                    } else {
                        ActivityCompat.requestPermissions(QuizDetailsScreen.this, new String[]{Manifest.permission.CAMERA}, CAMERA_REQUESTCODE);
                    }
                } else {
                    dispatchTakePictureIntent();
                }

            }
        });

    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private void setPic() {
        // Get the dimensions of the View
        int targetW = image.getWidth();
        int targetH = image.getHeight();

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;

        BitmapFactory.decodeFile(currentPhotoPath, bmOptions);

        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.max(1, Math.min(photoW/targetW, photoH/targetH));

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(currentPhotoPath, bmOptions);
        image.setImageBitmap(bitmap);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {

            PyObject obj = pyobj.callAttr("startScanning", fileNumber);
            fileNumber++;

            answerComparison(obj);
            setPic();

            Log.d(TAG, "onActivityResult: "+ obj.toString());


            File fdelete = new File(currentPhotoPath);
            if (fdelete.exists()) {
                if (fdelete.delete()) {
                    System.out.println("file Deleted :" + currentPhotoPath);
                } else {
                    System.out.println("file not Deleted :" + currentPhotoPath);
                }
            }

        }

    }

    private void answerComparison(PyObject obj) {

//        ArrayMap<Integer,ArrayList<String>> answerKey;
//
//        for(int i=0;i<25;i++){
//            if(answers[i*4+1]=="1")
//        }

        displayEvalutionResult(122211,80);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case CAMERA_REQUESTCODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    dispatchTakePictureIntent();
                } else {
                    Snackbar.make(relativelay, "This app need camera permission", Snackbar.LENGTH_INDEFINITE)
                            .setAction("Grant permission", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                    intent.setData(Uri.parse("package: " + getPackageName()));
                                    startActivity(intent);
                                }
                            }).show();
                }
                break;
            default:
                break;
        }


    }
    protected void displayEvalutionResult(int enrollmentNumber,int totalMarks){
        LayoutInflater inflater = getLayoutInflater();
        View view= inflater.inflate(R.layout.quiz_evaluation_display,  mainList ,false);


        image= view.findViewById(R.id.answer_sheet);
        TextView textEnrollment = view.findViewById(R.id.student_enrollment_number);
        TextView totalMarksView= view.findViewById(R.id.Total_Marks);
        pieChart = view.findViewById(R.id.pieChart);
        setPieChart();


        textEnrollment.setText(Integer.toString(enrollmentNumber));
        totalMarksView.setText(Integer.toString(totalMarks));
        mainList.addView(view);


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
        list.add(new PieEntry(90,"Correct"));
        list.add(new PieEntry(10,"Incorrect"));
        return list;
    }

//    private void init() {
//        fab = findViewById(R.id.fab);
//        tabLayout = findViewById(R.id.tabLayout);
//        viewPager = findViewById(R.id.viewpager);
//        relativelay = findViewById(R.id.relativelay);
//        adapter = new QuizDetailsTabLayoutAdapter(getSupportFragmentManager());
//    }
//
//    private void setAdapter() {
//        viewPager.setAdapter(adapter);
//        tabLayout.setupWithViewPager(viewPager);
//    }
}