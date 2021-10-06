package com.example.blueskycinema.Hasith;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.blueskycinema.DB_Handler;
import com.example.blueskycinema.R;

import java.util.Calendar;

public class add_movies_2 extends AppCompatActivity {
    EditText addMovieCast,addTrailerLink,addTicketPrice;
    Button ImageAdd;

    private static final int PICK_IMAGE = 100;

//    private static final int STORAGE_REQUEST_CODE = 101;
//
//    private static final int IMAGE_PICK_CAMERA_CODE = 102;
//    private static final int IMAGE_PICK_GALLERY_CODE = 102;
//
//    private String[] cameraPermissions;
//    private String[] storagePermissions;
//
//    private Uri imageUri;

    String takeMovieName;
    String takeDuration;
    String takeYear;
    String takeGenre;
    String takeDescription;

     DatePickerDialog datePickerDialog;
     DatePickerDialog datePickerDialog1;
     Button dateButton;
     Button dateButton2;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.add_movies_2);

        addMovieCast = findViewById(R.id.addMovieCast);
        addTrailerLink = findViewById(R.id.addTrailerLink);
        addTicketPrice = findViewById(R.id.addTicketPrice);

        ImageAdd = findViewById(R.id.buttonUploadImage);

        //Add Image
        ImageAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT,Uri.parse(
                        "content://media/internal/images/media"
                ));
                startActivityForResult(intent, PICK_IMAGE);
            }
        });

//        //Permissions
//        cameraPermissions = new String[] {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
//        storagePermissions = new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE};

//        //Image Upload Method
//        ImageAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                imagePickDialog();
//            }
//        });

        //Intent
        Intent intent = getIntent();

        takeMovieName = intent.getStringExtra("mName");
        takeDuration = intent.getStringExtra("mDuration");
        takeYear = intent.getStringExtra("mYear");
        takeGenre = intent.getStringExtra("mGenre");
        takeDescription = intent.getStringExtra("mDescription");

        initDatePicker();
        dateButton = findViewById(R.id.datePickerButton);
        dateButton.setText(getTodaysDate());

        initDatePicker2();
        dateButton2 = findViewById(R.id.datePickerButton2);
        dateButton2.setText(getTodaysDate());


    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==RESULT_OK && requestCode==PICK_IMAGE) {
            Uri uri = data.getData();
            String x = getPath(uri);

            Toast.makeText(getApplicationContext(),x,Toast.LENGTH_SHORT).show();

        }
    }

    public String getPath(Uri uri) {
        if(uri==null)return null;
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(uri,projection,null,null,null);
        if(cursor!=null){
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        }
        return uri.getPath();
    }


//    private void imagePickDialog() {
//
//        String[] options = {"Camera", "Gallery"};
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setItems(options, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                if (which == 0) {
//                    //If 0, then open the camera and also check the permissions of the camera
//                    if (!checkCameraPermission()){
//                        requestCameraPermission();
//                    }
//                    else {
//                        pickFromCamera();
//                    }
//                }
//                else if (which == 1) {
//                    if (!checkStoragePermission()) {
//                        requestStoragePermission();
//                    }
//                    else {
//                        pickFromStorage();
//                    }
//                }
//                builder.create().show();
//            }
//        });
//    }
//
//    private void pickFromStorage() {
//    }
//
//    private void pickFromCamera() {
//    }

    private String getTodaysDate()
    {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private void initDatePicker()
    {
        DatePickerDialog.OnDateSetListener dateSetListener = (datePicker, year, month, day) -> {
            month = month + 1;
            String date1 = makeDateString(day, month, year);
            dateButton.setText(date1);

        };
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog1 = new DatePickerDialog(this, style, dateSetListener, year, month, day);
//        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
    }

    //For Calender 2
    private void initDatePicker2()
    {
        DatePickerDialog.OnDateSetListener dateSetListener = (datePicker, year, month, day) -> {
            month = month + 1;
            String date = makeDateString(day, month, year);
            dateButton2.setText(date);

        };
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
//        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
    }

    private String makeDateString(int day, int month, int year) {
        return getMonthFormat(month) + " " + day + " " + year;
    }

    private String getMonthFormat(int month) {
        if(month == 1)
            return "JAN";
        if(month == 2)
            return "FEB";
        if(month == 3)
            return "MAR";
        if(month == 4)
            return "APR";
        if(month == 5)
            return "MAY";
        if(month == 6)
            return "JUN";
        if(month == 7)
            return "JUL";
        if(month == 8)
            return "AUG";
        if(month == 9)
            return "SEP";
        if(month == 10)
            return "OCT";
        if(month == 11)
            return "NOV";
        if(month == 12)
            return "DEC";

        return "JAN";
    }

    public void openDatePicker(View view)
    {
        datePickerDialog1.show();
    }
    public void openDatePicker2(View view)
    {
        datePickerDialog.show();
    }



//    //Verifying Permissions
//    private boolean checkStoragePermission() {
//        boolean result = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
//                == (PackageManager.PERMISSION_GRANTED);
//
//        return result;
//    }
//
//    private void requestStoragePermission() {
//        ActivityCompat.requestPermissions(this, storagePermissions, STORAGE_REQUEST_CODE);
//    }
//
//    private boolean checkCameraPermission() {
//        boolean result = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
//                == (PackageManager.PERMISSION_GRANTED);
//
//        boolean result1 = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
//                == (PackageManager.PERMISSION_GRANTED);
//
//        return result && result1;
//    }
//
//    private void requestCameraPermission() {
//        ActivityCompat.requestPermissions(this, cameraPermissions, CAMERA_REQUEST_CODE);
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull @org.jetbrains.annotations.NotNull String[] permissions, @NonNull @org.jetbrains.annotations.NotNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//    }
//
    //Submit All Data
    public void SubmitMovie(View view){

        DB_Handler DB = new DB_Handler(this);
        long info = DB.addMovie(takeMovieName, takeDuration, takeYear, takeGenre, takeDescription, addMovieCast.getText().toString(), addTrailerLink.getText().toString(), dateButton.getText().toString(), dateButton2.getText().toString(), addTicketPrice.getText().toString());

        if( info > 0 ){
            setContentView(R.layout.main_view);
            Intent intent = new Intent(this, displayMovies.class);
            startActivity(intent);
            Toast.makeText(this, "Successful", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "UnSuccessful", Toast.LENGTH_SHORT).show();
        }


    }
}
