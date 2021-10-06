package com.example.blueskycinema.Hasith;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.blueskycinema.DB_Handler;
import com.example.blueskycinema.R;

public class add_movies extends AppCompatActivity {
    EditText addMovieName,addDuration,addYear,addGenre,addDescription;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.add_movies);

        addMovieName = findViewById(R.id.addMovieName);
        addDuration = findViewById(R.id.addDuration);
        addYear = findViewById(R.id.addYear);
        addGenre = findViewById(R.id.addGenre);
        addDescription = findViewById(R.id.addDescription);
    }

    //Intent
    public void NextButton(View view) {

        if (addMovieName.length() == 0) {
            addMovieName.setError("Enter Movie Name");
        }
        else if(addDuration.length() == 0) {
            addDuration.setError("Enter Duration");
        }
        else if(addYear.length() == 0) {
            addYear.setError("Enter Year");
        }
        else if(addYear.length() > 4) {
            addYear.setError("Enter Valid Year");
        }
        else if(addGenre.length() == 0) {
            addGenre.setError("Enter Genre");
        }
        else if(addDescription.length() == 0) {
            addDescription.setError("Enter Description");
        }
        else if (addDescription.length() >= 100){
            addDescription.setError(("Maximum 100 characters"));
        }
        else {
            Intent intent = new Intent(this, add_movies_2.class);

            String movieName = addMovieName.getText().toString();
            String duration = addDuration.getText().toString();
            String year = addYear.getText().toString();
            String genre = addGenre.getText().toString();
            String description = addDescription.getText().toString();

            intent.putExtra("mName", movieName);
            intent.putExtra("mDuration", duration);
            intent.putExtra("mYear", year);
            intent.putExtra("mGenre", genre);
            intent.putExtra("mDescription", description);

            startActivity(intent);
        }
    }
}
