package com.example.blueskycinema.Hasith;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.blueskycinema.DB_Handler;
import com.example.blueskycinema.R;

public class EditMovie extends AppCompatActivity {


    EditText addMovieName,addDuration,addYear,addGenre,addDescription;

    Button buttonSave;



    DB_Handler db_handler;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_movie);

        context = this;
        db_handler = new DB_Handler(context);

        buttonSave = findViewById(R.id.buttonSave);

        addMovieName = findViewById(R.id.addMovieName1);
        addDuration = findViewById(R.id.addDuration1);
        addYear = findViewById(R.id.addYear1);
        addGenre = findViewById(R.id.addGenre1);
        addDescription = findViewById(R.id.addDescription1);

        final String id = getIntent().getStringExtra("ID");

        Model MModel = db_handler.getSingleMovie(Integer.parseInt(id));

        //display previous added details

        addMovieName.setText(MModel.getMovieName());
        addDuration.setText(MModel.getDuration());
        addYear.setText(MModel.getYear());
        addGenre.setText(MModel.getGenre());
        addDescription.setText(MModel.getDescription());

        String trailer = MModel.getTrailer();
        String cast = MModel.getCast();
        String startDate = MModel.getStartDate();
        String endDate = MModel.getEndDate();
        String price = MModel.getTickets();

        //update button
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String MName = addMovieName.getText().toString();
                String MDuration = addDuration.getText().toString();
                String MYear = addYear.getText().toString();
                String MGenre = addGenre.getText().toString();
                String MDescription = addDescription.getText().toString();

                Model MModel = new Model(id, MName, MDuration, MYear, MGenre, MDescription, cast, trailer, startDate, endDate, price);
                int status = db_handler.updateInfo(MModel);
                if (status == 1){
                    startActivity(new Intent(context, displayMovies.class));
                    Toast.makeText(EditMovie.this, "Updated Successfully!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(EditMovie.this, "Not Updated!", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }



    }

