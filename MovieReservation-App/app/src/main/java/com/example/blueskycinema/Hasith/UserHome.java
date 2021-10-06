package com.example.blueskycinema.Hasith;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.blueskycinema.DB_Handler;
import com.example.blueskycinema.R;

public class UserHome extends AppCompatActivity {

    RecyclerView mRecyclerView;
    DB_Handler databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);

        mRecyclerView = findViewById(R.id.recyclerView);
        databaseHelper = new DB_Handler(this);

        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));


        showRecord();

    }

    private void showRecord() {
        UserAdapter adapter = new UserAdapter(UserHome.this, databaseHelper.getAllData(DB_Handler.MOVIE_COLUMN_START_DATE+ " DESC"));

        mRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        showRecord();
    }
    public void logout (View view){
        setContentView(R.layout.login);
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

}