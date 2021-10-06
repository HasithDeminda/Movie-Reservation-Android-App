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

public class UserRegistration extends AppCompatActivity {

    EditText un, email, contact, pw1, pw2;
    Button btnReg;

    DB_Handler DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.signup);

        un = findViewById(R.id.signupUsername);
        email = findViewById(R.id.signupEmail);
        contact = findViewById(R.id.signupContact);
        pw1 = findViewById(R.id.signupPassword);
        pw2 = findViewById(R.id.signupPasswordReEnter);

        btnReg = findViewById(R.id.SignupBtn);

        DB = new DB_Handler(this);

        //register button
        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uName = un.getText().toString();
                String uEmail = email.getText().toString();
                String uContact = contact.getText().toString();
                String pw = pw1.getText().toString();
                String rePw = pw2.getText().toString();

                if (uName.equals("") || pw.equals("") || rePw.equals("")) {
                    Toast.makeText(UserRegistration.this, "Fill all fields", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(pw.equals(rePw)){
                        boolean checkUser = DB.checkUsername(uName);
                        if (checkUser == false) {
                            Boolean insert = DB.insertData(uName, uEmail, uContact, pw);
                            if (insert == true) {
                                Toast.makeText(UserRegistration.this, "Registered successful!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), Login.class);
                                startActivity(intent);
                            }
                            else {
                                Toast.makeText(UserRegistration.this, "Registration Failed!", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(UserRegistration.this, "User already exists!", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(UserRegistration.this, "Password mismatch!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}
