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
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.Project.OMRReader.Adapters.QuizDetailsTabLayoutAdapter;
import com.Project.OMRReader.MainActivity;
import com.Project.OMRReader.R;
import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class QuizDetailsScreen extends AppCompatActivity {

    ViewPager viewPager;
    TabLayout tabLayout;
    QuizDetailsTabLayoutAdapter adapter;
    FloatingActionButton fab;
    RelativeLayout relativelay;

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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {

            PyObject obj = pyobj.callAttr("startScanning", fileNumber);
            fileNumber++;
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