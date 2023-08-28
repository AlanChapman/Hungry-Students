package za.ac.cput;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import za.ac.cput.domain.Student;
import za.ac.cput.repository.impl.StudentRepositoryImpl;
import za.ac.cput.utils.DBUtils;

public class StudentDetailsFragment extends Fragment {

    private EditText fullNameField, emailField, DOBField;
    private Button saveButton;

    private StudentRepositoryImpl studentRepository;
    private Student student;
    private int authenticatedStudentId;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        studentRepository = new StudentRepositoryImpl(getActivity());
//        authenticatedStudentEmail = getActivity().getIntent().getStringExtra(DBUtils.AUTHENTICATED_STUDENT_EMAIL);
//        authenticatedStudentName = getActivity().getIntent().getStringExtra(DBUtils.AUTHENTICATED_STUDENT_NAME);
        authenticatedStudentId = getActivity().getIntent().getIntExtra(DBUtils.AUTHENTICATED_STUDENT_ID, -999);

        // Retrieve the student's data using the studentId (replace 1 with the actual student ID)
        student = studentRepository.getStudent(authenticatedStudentId);

        System.out.println("student logged in");
        System.out.println(student);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_student_details, container, false);

       // studentIDField = rootView.findViewById(R.id.studentIDa);
        fullNameField = rootView.findViewById(R.id.fullNameField);
        emailField = rootView.findViewById(R.id.emailField);
        DOBField = rootView.findViewById(R.id.DOBField);
        saveButton = rootView.findViewById(R.id.saveButton);

        // Populate the EditText fields with student data
        if (student != null) {
           // studentIDField.setText(String.valueOf(student.getStudentId()));  // Display Student ID
            fullNameField.setText(student.getFullName());
            emailField.setText(student.getEmailAddress());
            DOBField.setText(student.getDateOfBirth().toString());

        }

        saveButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                saveChanges();
            }
        });

        return rootView;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void saveChanges() {
        // Retrieve and update student information from EditText fields
        //int studentId = Integer.parseInt(studentIDField.getText().toString());
        String fullName = fullNameField.getText().toString();
        String emailAddress = emailField.getText().toString();
        LocalDate dateOfBirth = LocalDate.parse(DOBField.getText().toString());

        // Update the student object
       // student.setStudentId(studentId);
        student.setFullName(fullName);
        student.setEmailAddress(emailAddress);
        student.setDateOfBirth(dateOfBirth);


        // Update student information in the database
        boolean result = studentRepository.updateStudentDetails(student);

        if (result) {
            Toast.makeText(requireContext(), "Changes saved successfully. You will be redirected to Login", Toast.LENGTH_LONG).show();
            startActivity(new Intent(getActivity(), LoginActivity.class));
        } else {
            Toast.makeText(requireContext(), "Failed to save changes", Toast.LENGTH_SHORT).show();
        }
    }
}
