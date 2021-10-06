package com.example.blueskycinema.Hasith;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.blueskycinema.DB_Handler;
import com.example.blueskycinema.R;

public class Login extends AppCompatActivity {

    EditText un, pw;
    Button btnLogin, btnReg;

    DB_Handler dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        un = findViewById(R.id.usernameLogin);
        pw = findViewById(R.id.passwordLogin);

        btnLogin = findViewById(R.id.loginBtn);
        btnReg = findViewById(R.id.LoginToSignup);

        dbHelper = new DB_Handler(this);

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UserRegistration.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uName = un.getText().toString();
                String pass = pw.getText().toString();

                if (uName.equals("") || pass.equals("")){
                    Toast.makeText(Login.this, "Fill the fields!", Toast.LENGTH_SHORT).show();
                } else if (uName.equals("Admin99++") && pass.equals("Admin99++")){
                    Toast.makeText(Login.this, "Welcome Back Admin!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), displayMovies.class);
                    startActivity(intent);
                }
                else{
                    Boolean validation = dbHelper.validateLogin(uName, pass);

                    if (validation == true){
                        Toast.makeText(Login.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), UserHome.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(Login.this, "Login Failed!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });



    }
}
