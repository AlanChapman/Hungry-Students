package za.ac.cput;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import za.ac.cput.domain.Student;
import za.ac.cput.repository.impl.StudentRepositoryImpl;
import za.ac.cput.utils.DBUtils;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText fullNameEditText, emailEditText, DOBEditText, passwordEditText, confirmPasswordEditText;
    private Button signUpButton, existingUserButton;

    private DatePickerDialog.OnDateSetListener dateSetListener;
    private TimePickerDialog timePickerDialog;

    private String authenticatedStudentEmail;
    private int authenticatedStudentId;

    private Dialog dialog;
    private StudentRepositoryImpl studentRepository;
    private Student authenticatedStudent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sign_up);

        fullNameEditText = (EditText) findViewById(R.id.fullNameEditText);
        emailEditText = (EditText) findViewById(R.id.emailEditText);
        DOBEditText = (EditText) findViewById(R.id.DOBEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        confirmPasswordEditText = (EditText) findViewById(R.id.confirmPasswordEditText);

        signUpButton = (Button) findViewById(R.id.signUpBtn);

        existingUserButton = (Button) findViewById(R.id.existingUserBtn);

        studentRepository = new StudentRepositoryImpl(this);


        dialog = new Dialog(this);

        authenticatedStudentEmail =  getIntent().getStringExtra(DBUtils.AUTHENTICATED_STUDENT_EMAIL);
        authenticatedStudentId = getIntent().getIntExtra(DBUtils.AUTHENTICATED_STUDENT_ID, -999);

        DOBEditText.setOnClickListener(this);

        dateSetListener = (datePicker, year, month, day) -> {
            DOBEditText.setText(year + "-" + checkDigit(month + 1)  + "-" + checkDigit(day));
        };


        signUpButton.setOnClickListener(this);

        existingUserButton.setOnClickListener(this);
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View view) {
        if (view == existingUserButton) {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        } else if(view == signUpButton) {
            registerUser();
            clearInput();
        } else if(view == DOBEditText) {
            Calendar mcurrentDate=Calendar.getInstance();
            int year = mcurrentDate.get(Calendar.YEAR);
            int month = mcurrentDate.get(Calendar.MONTH);
            int day = mcurrentDate.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dialog = new DatePickerDialog(SignUpActivity.this, android.R.style.Theme_Holo_Light_Dialog, dateSetListener, year, month, day);

            dialog.setTitle("Select Date of Birth");
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void registerUser() {

        String name = fullNameEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        String birthDate = DOBEditText.getText().toString().trim();
        String pass = passwordEditText.getText().toString().trim();
        String confirmPass = confirmPasswordEditText.getText().toString().trim();

//        String name = "John Doe";
//        String email = "john1234@gmail.com";
//        String birthDate = "2001-05-18";
//        String pass = "password";
//        String confirmPass = "password";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        if(pass.equals(confirmPass)) {
            if (validateInput(name, email, pass)) {
                studentRepository.register(new Student(name, email, LocalDate.parse(birthDate, formatter), pass));
                clearInput();
            }
        } else {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_LONG).show();
        }
    }

    private void clearInput() {
        fullNameEditText.setText("");
        emailEditText.setText("");
        passwordEditText.setText("");
        confirmPasswordEditText.setText("");
        DOBEditText.setText("");
    }

    private boolean validateInput(String fullName, String emailAddress, String password) {

        boolean status = true;

        if (TextUtils.isEmpty(fullName) || TextUtils.isEmpty(emailAddress) || TextUtils.isEmpty(password)) {
            fullNameEditText.setError("Please enter a value");
            fullNameEditText.requestFocus();

            emailEditText.setError("Please enter a value");
            emailEditText.requestFocus();

            passwordEditText.setError("Please enter a value");
            passwordEditText.requestFocus();

            status = false;
        }

        if (password.length() < 6) {
            passwordEditText.setError("Must be longer than 6 characters");
            passwordEditText.requestFocus();
            status = false;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches()) {
            emailEditText.setError("Invalid email address");
            emailEditText.requestFocus();
            status = false;
        }

        return status;
    }

    private String checkDigit(int number) {
        return number <= 9 ? "0" + number : String.valueOf(number);
    }


}