package za.ac.cput;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.time.LocalDate;

import za.ac.cput.domain.Student;
import za.ac.cput.repository.impl.StudentRepositoryImpl;
import za.ac.cput.services.ObjectiveAchievedService;
import za.ac.cput.utils.DBUtils;

public class LoginActivity extends AppCompatActivity {
    EditText emailAddress, password;
    private Button loginButton, nonExistingUserButton;
    private StudentRepositoryImpl studentRepository;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailAddress = (EditText) findViewById(R.id.loginEmailEditText);
        password = (EditText) findViewById(R.id.loginPasswordEditText);

        loginButton = findViewById(R.id.loginBtn);

        nonExistingUserButton = findViewById(R.id.nonExistingUserBtn);
        studentRepository = new StudentRepositoryImpl(this);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {

                String email = emailAddress.getText().toString();
                String pass = password.getText().toString();

                //String email = "john1234@gmail.com";
                //String pass = "password";

                //studentRepository.register(new Student("test", "demo@gmail.com", LocalDate.now(), "password"));

                if(email.equals("")){
                    Toast.makeText(LoginActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                    emailAddress.setError("Please enter a value");
                }
                else if(pass.equals("")) {
                    password.setError("Please enter a value");
                    Toast.makeText(LoginActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                } else{
                    boolean checkEmailPass = studentRepository.login(email, pass);
                    System.out.println(email + " " + pass);
                    if(checkEmailPass) {

                        Toast.makeText(LoginActivity.this, "Sign in successful", Toast.LENGTH_SHORT).show();
                        Intent intent  = new Intent(getApplicationContext(), NavActivity.class);
                        intent.putExtra(DBUtils.AUTHENTICATED_STUDENT_EMAIL, email);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        nonExistingUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intent);
            }
        });
    }


}
