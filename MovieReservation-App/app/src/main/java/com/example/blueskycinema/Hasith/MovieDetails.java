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

public class MovieDetails extends AppCompatActivity {

   TextView addMovieName,addDuration,addYear,addGenre,addDescription;

    Button buttonSave;



    DB_Handler db_handler;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_details);

        context = this;
        db_handler = new DB_Handler(context);

        buttonSave = findViewById(R.id.buttonSave);

        addMovieName = findViewById(R.id.addMovieName2);
        addDuration = findViewById(R.id.addDuration2);
        addYear = findViewById(R.id.addYear2);
        addGenre = findViewById(R.id.addGenre2);
        addDescription = findViewById(R.id.addDescription2);

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



    }
}
