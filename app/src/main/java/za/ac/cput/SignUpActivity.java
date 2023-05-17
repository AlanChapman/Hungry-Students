package za.ac.cput;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import za.ac.cput.repository.StudentRepositoryImpl;
import za.ac.cput.utils.DBUtils;

public class SignUpActivity extends AppCompatActivity {
    private EditText fullName, emailAddress, dateOfBirth, password, confirmPassword;
    private Button signUpButton, existingUserButton;
    private StudentRepositoryImpl DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        fullName = (EditText) findViewById(R.id.fullNameEditText);
        emailAddress = (EditText) findViewById(R.id.emailEditText);
        dateOfBirth = (EditText) findViewById(R.id.DOBEditText);
        password = (EditText) findViewById(R.id.passwordEditText);
        confirmPassword = (EditText) findViewById(R.id.confirmPasswordEditText);

        signUpButton = (Button) findViewById(R.id.signUpBtn);

        existingUserButton = (Button) findViewById(R.id.existingUserBtn);

        DB = new StudentRepositoryImpl(this);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = fullName.getText().toString();
                String email = emailAddress.getText().toString();
                String birthDate = dateOfBirth.getText().toString();
                String pass = password.getText().toString();
                String conPass = confirmPassword.getText().toString();

                if(name.equals("")||email.equals("")||birthDate.equals("")||pass.equals("")||conPass.equals(""))
                    Toast.makeText(SignUpActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    if(pass.equals(conPass)){
                        Boolean checkEmailAdd = DB.checkEmail(email);
                        if(checkEmailAdd==false){
                            Boolean insert = DB.insertData(name, email, birthDate, pass);
                            if(insert==true){
                                Toast.makeText(SignUpActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                                startActivity(intent);
                            } else{
                                Toast.makeText(SignUpActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        } else{
                            Toast.makeText(SignUpActivity.this, "User already exists! please sign in", Toast.LENGTH_SHORT).show();
                        }
                    } else{
                        Toast.makeText(SignUpActivity.this, "Passwords not matching", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        existingUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}