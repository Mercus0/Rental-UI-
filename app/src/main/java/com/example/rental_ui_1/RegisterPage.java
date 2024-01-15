package com.example.rental_ui_1;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterPage extends AppCompatActivity {
    TextView txtLogin,userName,userEmail,userPassword,userPhone,userConfirmPassword;
    RadioGroup rdoGroup;
    RadioButton rdoMale,rdoFemale;
    String gender;
    Button btnSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        //Back to Register Page
        txtLogin=findViewById(R.id.idBackToLogin);
        btnSignUp=findViewById(R.id.idBtnSignUp);
        userName=findViewById(R.id.idUserName);
        userEmail=findViewById(R.id.idUserEmail);
        userPassword=findViewById(R.id.idUserPassword);
        userConfirmPassword=findViewById(R.id.idUserConfirmPassword);
        userPhone=findViewById(R.id.idUserPhone);
        rdoGroup=findViewById(R.id.idRdoGroup);
        rdoMale=findViewById(R.id.idRdoMale);
        rdoFemale=findViewById(R.id.idRdoFemale);
        rdoMale.setChecked(true);

        int rdoId=rdoGroup.getCheckedRadioButtonId();
        if (rdoId != -1) {
            if(rdoId==R.id.idRdoMale){
                gender="Male";
            }
        }else gender="Female";

        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toLogin=new Intent(RegisterPage.this,LoginPage.class);
                startActivity(toLogin);
            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbHelper = new DBHelper(getApplicationContext());
                //dbHelper.addUser(userName.getText().toString(),userEmail.getText().toString(),userPassword.getText().toString(),gender,userPhone.toString().toString());

                // Get user input
                String name = userName.getText().toString();
                String email = userEmail.getText().toString();
                String password = userPassword.getText().toString();
                String confirmPassword=userConfirmPassword.getText().toString();
                String phone = userPhone.getText().toString();

                // Check for null or empty values
                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email) ||
                        TextUtils.isEmpty(password) || TextUtils.isEmpty(phone)) {
                    // Handle the case where any of the input fields is empty
                    Toast.makeText(getApplicationContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }
                //check password and confirm password are equal
                if(!password.equals(confirmPassword)){
                    Toast.makeText(getApplicationContext(),"Password and confirm Password are not equal",Toast.LENGTH_SHORT).show();
                    return;
                }

                // Add user to the database
                dbHelper.addUser(name, password, email, gender, phone);
                userName.setText("");
                userEmail.setText("");
                userPassword.setText("");
                userPhone.setText("");
                Toast.makeText(getApplicationContext(),"Sign Up successfully",Toast.LENGTH_LONG).show();
                Intent toLogin=new Intent(RegisterPage.this,LoginPage.class);
                startActivity(toLogin);
            }
        });

    }
}