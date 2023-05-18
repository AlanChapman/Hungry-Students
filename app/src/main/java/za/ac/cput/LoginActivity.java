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

public class LoginActivity extends AppCompatActivity {
    EditText emailAddress, password;
    private Button loginButton, nonExistingUserButton;
    private StudentRepositoryImpl DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailAddress = (EditText) findViewById(R.id.loginEmailEditText);
        password = (EditText) findViewById(R.id.loginPasswordEditText);

        loginButton = findViewById(R.id.loginBtn);

        nonExistingUserButton = findViewById(R.id.nonExistingUserBtn);

        DB = new StudentRepositoryImpl(this);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = emailAddress.getText().toString();
                String pass = password.getText().toString();

                if(email.equals("")||pass.equals(""))
                    Toast.makeText(LoginActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    boolean checkEmailPass = DB.login(email, pass);
                    System.out.println(email + " " + pass);
                    if(checkEmailPass) {

                        Toast.makeText(LoginActivity.this, "Sign in successful", Toast.LENGTH_SHORT).show();
                        Intent intent  = new Intent(getApplicationContext(), MainActivity.class);
                        intent.putExtra(DBUtils.AUTHENTICATED_USER, email);
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
