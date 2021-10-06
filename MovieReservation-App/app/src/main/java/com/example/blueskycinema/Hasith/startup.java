package com.example.blueskycinema.Hasith;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.blueskycinema.R;

public class startup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.startup_page);

    }
    public void loginhere (View view){
        setContentView(R.layout.login);
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
}
