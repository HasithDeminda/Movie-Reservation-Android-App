package com.example.blueskycinema;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.blueskycinema.Hasith.Adapter;
import com.example.blueskycinema.Hasith.Login;
import com.example.blueskycinema.Hasith.UserHome;
import com.example.blueskycinema.Hasith.add_movies;
import com.example.blueskycinema.Hasith.add_movies_2;
import com.example.blueskycinema.Hasith.displayMovies;
import com.example.blueskycinema.Hasith.startup;
import com.example.blueskycinema.Zaid.ReserveNowActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {


//    private int KeyCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void startup (View view){
        setContentView(R.layout.startup_page);
        Intent intent = new Intent(this, startup.class);
        startActivity(intent);
    }




}