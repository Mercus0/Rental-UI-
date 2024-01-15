package com.example.rental_ui_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LoginPage extends AppCompatActivity {
    TextView txtRegister,txtEmail,txtPassword;
    Button btnSignIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        txtRegister=findViewById(R.id.idBackToSignUp);
        txtEmail=findViewById(R.id.idEmail);
        txtPassword=findViewById(R.id.idPassword);
        btnSignIn=findViewById(R.id.idSignIn);
        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toSignUp=new Intent(LoginPage.this,RegisterPage.class);
                startActivity(toSignUp);
            }
        });

        //login Button
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbHelper=new DBHelper(getApplicationContext());
                String email=txtEmail.getText().toString();
                String password=txtPassword.getText().toString();

                if(TextUtils.isEmpty(email) || (TextUtils.isEmpty(password))) {
                    Toast.makeText(getApplicationContext(), "Please fill in both email and password", Toast.LENGTH_SHORT).show();
                    return;
                }
                String userName = dbHelper.getUserName(email, password);

                if (userName != null) {
                    Intent t1=new Intent(LoginPage.this, PropertyView.class);
                    Toast.makeText(getApplicationContext(), "Welcome "+userName, Toast.LENGTH_SHORT).show();
                    t1.putExtra("USER_NAME",userName);
                    startActivity(t1);
                } else {
                    Toast.makeText(getApplicationContext(), "Wrong Email or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}